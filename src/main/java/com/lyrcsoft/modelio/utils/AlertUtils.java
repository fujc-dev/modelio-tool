package com.lyrcsoft.modelio.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-21 10:53
 */
public class AlertUtils {
    public static Optional<ButtonType> INFORMATION(String msg) {
        return INFORMATION("系统提示", msg);
    }

    public static Optional<ButtonType> INFORMATION(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert.showAndWait();
    }


    public static Optional<ButtonType> WARNING(String msg) {
        return WARNING("系统提示", msg);
    }

    public static Optional<ButtonType> WARNING(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert.showAndWait();
    }

    public static Optional<ButtonType> ERROR(String msg) {
        return ERROR("系统提示", msg);
    }

    public static Optional<ButtonType> ERROR(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert.showAndWait();
    }

    public static Optional<ButtonType> CONFIRMATION(String msg) {
        return CONFIRMATION("系统提示", msg);
    }

    public static Optional<ButtonType> CONFIRMATION(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        return alert.showAndWait();
    }
}
