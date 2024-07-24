package com.lyrcsoft.modelio;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-24
 */
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFolderExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 设置要打开的文件夹路径
            String folderPath = "E:\\";
            // 创建对应文件夹的File对象
            File folder = new File(folderPath);
            // 检查Desktop是否支持打开URL
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                // 打开文件夹
                Desktop.getDesktop().browse(folder.toURI());
            } else {
                System.out.println("Desktop不支持打开URL或文件夹打开失败。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}