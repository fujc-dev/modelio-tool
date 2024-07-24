package com.lyrcsoft.modelio.controller;

import com.lyrcsoft.modelio.model.TDatasource;
import com.lyrcsoft.modelio.service.ITDatesourceService;
import com.lyrcsoft.modelio.utils.SpringUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
@FXMLController
public class FDatasourceController implements Initializable {

    @Autowired
    private ITDatesourceService datasourceService;
    @FXML
    private TableView<TDatasource> tableView;

    Stage primaryStage;

    @FXML
    void OnAddDatasourceClick(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/fxml/FrmDatasourceEdit.fxml"));
        primaryStage = new Stage();
        // 设置弹出框大小
        Scene scene = new Scene(parent, 465, 160);
        primaryStage.setScene(scene);
        // 设置弹出框标题
        primaryStage.setTitle("基础信息配置");
        // 设置弹出框大小是否可变
        primaryStage.initStyle(StageStyle.UTILITY);
        // 展示弹出框
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            initialize(null, null);
        });
    }

    @FXML
    void OnRefreshDatasourceClick(ActionEvent event) {
        initialize(null, null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 查询数据库中包含的数据源，并填充到TableView中
        if (datasourceService == null) {
            datasourceService = SpringUtils.getBean(ITDatesourceService.class);
        }
        List<TDatasource> list = datasourceService.list();
        tableView.setStyle(String.format("-fx-font-size: %dpx;", (int) (0.45 * 30)));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(list));
        TableColumn<TDatasource, String> driverClassName = new TableColumn<>("驱动名称");
        tableView.getColumns().add(driverClassName);
        TableColumn<TDatasource, String> url = new TableColumn<>("数据源连接字符串");
        tableView.getColumns().add(url);
        TableColumn<TDatasource, String> username = new TableColumn<>("账号");
        tableView.getColumns().add(username);
        TableColumn<TDatasource, String> password = new TableColumn<>("密码");
        tableView.getColumns().add(password);
        driverClassName.setCellValueFactory(new PropertyValueFactory<TDatasource, String>("driverClassName"));
        url.setCellValueFactory(new PropertyValueFactory<TDatasource, String>("url"));
        username.setCellValueFactory(new PropertyValueFactory<TDatasource, String>("username"));
        password.setCellValueFactory(new PropertyValueFactory<TDatasource, String>("password"));
    }
}
