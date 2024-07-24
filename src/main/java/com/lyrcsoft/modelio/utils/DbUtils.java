package com.lyrcsoft.modelio.utils;

import com.lyrcsoft.modelio.boot.model.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB工具类
 *
 * 
 */
public class DbUtils {
    private static final int CONNECTION_TIMEOUTS_SECONDS = 6;

    /**
     * 获得数据库连接
     */
    public static Connection getConnection(DataSource dataSource) throws ClassNotFoundException, SQLException {
        DriverManager.setLoginTimeout(CONNECTION_TIMEOUTS_SECONDS);
        Class.forName(dataSource.getDriver());
        Connection connection = DriverManager.getConnection(dataSource.getUrl(), dataSource.getUsername(), dataSource.getPassword());
        return connection;
    }


}