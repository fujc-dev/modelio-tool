package com.lyrcsoft.modelio.controller;

import com.lyrcsoft.modelio.boot.UMLService;
import com.lyrcsoft.modelio.boot.model.DataSource;
import com.lyrcsoft.modelio.model.TDatasource;
import com.lyrcsoft.modelio.service.ITDatesourceService;
import com.lyrcsoft.modelio.utils.AlertUtils;
import com.lyrcsoft.modelio.utils.DbUtils;
import com.lyrcsoft.modelio.utils.SpringUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * 数据源添加或修改控制器
 *
 * @author fujc2dev@126.com
 * @date 2024-05-21 9:46
 */
@FXMLController
public class FrmDatasourceEditController {

    @Autowired
    private UMLService mysql;
    @Resource
    private ITDatesourceService datasourceService;
    @FXML
    private TextField password;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField driver_class_name;

    @FXML
    private TextField url;

    @FXML
    private TextField username;

    @FXML
    void OnTestConnectionClick(ActionEvent event) {
        try {
            // 判断
            if (driver_class_name.getText().isEmpty() || url.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()) {
                AlertUtils.WARNING("驱动，连接字符串，用户名或密码为空!");
                return;
            }
            DataSource dataSource = DataSource.builder()
                    .driver(driver_class_name.getText())
                    .url(url.getText())
                    .username(username.getText())
                    .password(password.getText())
                    .build();
            DbUtils.getConnection(dataSource);
            AlertUtils.INFORMATION("连接成功!");
        } catch (Exception e) {
            AlertUtils.WARNING(e.getMessage());
            return;
        }
    }

    @FXML
    void OnSaveConnectionClick(ActionEvent event) {
        try {
            // 判断
            if (driver_class_name.getText().isEmpty() || url.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()) {
                AlertUtils.WARNING("驱动，连接字符串，用户名或密码为空!");
                return;
            }
            TDatasource dataSource = TDatasource.builder()
                    .driverClassName(driver_class_name.getText())
                    .url(url.getText())
                    .username(username.getText())
                    .password(password.getText())
                    .build();
            // 将数据源保存到sqlite数据库中
            if (datasourceService == null) {
                datasourceService = SpringUtils.getBean(ITDatesourceService.class);
            }
            datasourceService.save(dataSource);
            AlertUtils.INFORMATION("保存成功");
            Stage window = (Stage) mainPane.getScene().getWindow();
            window.close();
        } catch (Exception e) {
            AlertUtils.WARNING(e.getMessage());
        }
    }

}
