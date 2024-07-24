package com.lyrcsoft.modelio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class SqliteDBParser {
    private static String ddl = "DROP TABLE IF EXISTS  `t_datasource`; \n" +
            "CREATE TABLE `t_datasource` ( \n" +
            "    `id`  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "    `driver_class_name`    TEXT,\n" +
            "    `url`    TEXT,\n" +
            "    `username` TEXT, \n" +
            "    `password` TEXT \n" +
            ")";

    public static void createSqliteDb() {
        try {
            String currentDirectory = System.getProperty("user.dir");
            log.info("当前工作目录: " + currentDirectory);
            String databaseFileName = "modelio.db";
            if (!isDatabaseFileExist(databaseFileName)) {
                // 检测当前
                String url = "jdbc:sqlite:" + databaseFileName;
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                statement.executeUpdate(ddl);
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isDatabaseFileExist(String databaseFileName) {
        String currentDirectory = System.getProperty("user.dir");
        File databaseFile = new File(currentDirectory, databaseFileName);
        return databaseFile.exists();
    }

    public static void main(String[] args) {
        createSqliteDb();
    }
}
