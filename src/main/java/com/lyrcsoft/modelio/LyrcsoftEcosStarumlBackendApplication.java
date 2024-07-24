package com.lyrcsoft.modelio;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LyrcsoftEcosStarumlBackendApplication extends AbstractJavaFxApplicationSupport {

    private ConfigurableApplicationContext context;
    private Parent root;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LyrcsoftEcosStarumlBackendApplication.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/FMain.fxml"));
        loader.setControllerFactory(context::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Modelio Open Source 5.4 生成Modelio工具");
        //如果只想禁用最大化按钮
        //stage.resizableProperty().setValue(Boolean.FALSE);
        //如果您想禁用最大化和最小化，除了关闭使用
        stage.initStyle(StageStyle.UTILITY);
        //如果您想要删除所有这三个，则使用
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        context.stop();
    }

    public static void main(String[] args) {
        //启动检测是否存在本地报警日志库
        SqliteDBParser.createSqliteDb();
        launch(args);
    }
}
