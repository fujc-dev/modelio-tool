package com.lyrcsoft.modelio.controller;

import com.lyrcsoft.modelio.boot.UMLService;
import com.lyrcsoft.modelio.boot.model.DataSource;
import com.lyrcsoft.modelio.model.TDatasource;
import com.lyrcsoft.modelio.service.ITDatesourceService;
import com.lyrcsoft.modelio.utils.AlertUtils;
import com.lyrcsoft.modelio.utils.DbUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController
public class FMainController implements Initializable {
    @Autowired
    private ITDatesourceService datasourceService;
    @Autowired
    private UMLService mysql;
    @FXML
    private TextField filepath;

    @FXML
    private ComboBox<TDatasource> comboBox;

    @FXML
    void OnDatabaseSourceManagerClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FDatasource.fxml"));
        Stage primaryStage = new Stage();
        // 设置弹出框大小
        Scene scene = new Scene(parent, 465, 305);
        primaryStage.setScene(scene);
        // 设置弹出框标题
        primaryStage.setTitle("基础信息配置");
        // 设置弹出框大小是否可变
        primaryStage.initStyle(StageStyle.UTILITY);
        // 展示弹出框
        primaryStage.show();

    }

    @FXML
    void OnTextConnectionClick(ActionEvent event) {
        SingleSelectionModel<TDatasource> selectionModel = comboBox.getSelectionModel();
        if (selectionModel != null) {
            TDatasource selectedItem = selectionModel.getSelectedItem();
            try {
                DataSource dataSource = DataSource.builder()
                        .driver(selectedItem.getDriverClassName())
                        .url(selectedItem.getUrl())
                        .username(selectedItem.getUsername())
                        .password(selectedItem.getPassword())
                        .build();
                DbUtils.getConnection(dataSource);
                AlertUtils.INFORMATION("连接成功!");
            } catch (Exception e) {
                AlertUtils.WARNING(e.getMessage());
                return;
            }
        }

    }

    @FXML
    void OnRefreshDatasourceClick(ActionEvent event) {
        List<TDatasource> list = datasourceService.list();
        ObservableList<TDatasource> data = FXCollections.observableArrayList(list);
        comboBox.setItems(FXCollections.emptyObservableList());
        comboBox.setItems(data);
    }

    @FXML
    void OnGenerateUMLClick(ActionEvent event) {

        SingleSelectionModel<TDatasource> selectionModel = comboBox.getSelectionModel();
        if (selectionModel != null) {
            TDatasource selectedItem = selectionModel.getSelectedItem();
            try {
                DataSource dataSource = DataSource.builder()
                        .driver(selectedItem.getDriverClassName())
                        .url(selectedItem.getUrl())
                        .username(selectedItem.getUsername())
                        .password(selectedItem.getPassword())
                        .build();
                String filename = mysql.saveAsFileWriter(filepath.getText(), dataSource);
                Optional<ButtonType> result = AlertUtils.CONFIRMATION("生成成功，是否打开目录");
                if (result.isPresent() && result.get().getButtonData().isDefaultButton()) {
                    Desktop.getDesktop().open(new File(filename));
                }
            } catch (java.awt.HeadlessException e) {
                AlertUtils.WARNING("打开目录失败！");
            } catch (Exception e) {
                AlertUtils.WARNING(e.getMessage());
            }
        }
    }

    @FXML
    void OnChooserDirectoryClick(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("选择存储文件夹");
        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            String selectedFolderPath = selectedDirectory.getAbsolutePath();
            filepath.setText(selectedFolderPath);
        }

    }

    @FXML
    public void OnExitClick(ActionEvent actionEvent) {
    }

    @FXML
    public void OnAboutClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FrmAbout.fxml"));
        Stage primaryStage = new Stage();
        // 设置弹出框大小
        Scene scene = new Scene(parent, 300, 135);
        primaryStage.setScene(scene);
        // 设置弹出框标题
        primaryStage.setTitle("关于modelio-tool");
        // 设置弹出框大小是否可变
        primaryStage.initStyle(StageStyle.UTILITY);
        // 展示弹出框
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<TDatasource> list = datasourceService.list();
        ObservableList<TDatasource> data = FXCollections.observableArrayList(list);
        comboBox.setItems(data);
    }
}
