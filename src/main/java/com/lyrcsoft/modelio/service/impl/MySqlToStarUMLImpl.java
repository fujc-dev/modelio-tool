package com.lyrcsoft.modelio.service.impl;

import com.lyrcsoft.modelio.boot.AbstractStartUMLService;
import com.lyrcsoft.modelio.boot.model.Column;
import com.lyrcsoft.modelio.boot.model.DataSource;
import com.lyrcsoft.modelio.boot.model.Table;
import com.lyrcsoft.modelio.utils.GenerateBase64Strings;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基于Mysql数据库生成StarUML
 *
 * @author fujc2dev@126.com
 * @date 2024-05-21 9:05
 */
@Service("mysql")
public class MySqlToStarUMLImpl extends AbstractStartUMLService {

    @Override
    public List<Table> getTableList(DataSource ds) {
        Connection connection;
        try {
            Class.forName(ds.getDriver());
            connection = DriverManager.getConnection(ds.getUrl(), ds.getUsername(), ds.getPassword());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("加载驱动失败，找不到：" + ds.getDriver());
        } catch (SQLException e) {
            throw new RuntimeException("获取数据库连接失败，请检查配置和日志", e);
        }

        String database = this.getDBByUrl(ds.getUrl());
        String sqlTable = String.format("select * from information_schema.tables where TABLE_SCHEMA='%s'", database);

        List<Map<String, String>> mapList = this.runSql(connection, sqlTable);

        List<Table> tableList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, String> map = mapList.get(i);
            Table table = new Table();
            String tableId = GenerateBase64Strings.getRandomBase64String();
            table.setId(tableId);
            table.setName(map.get("TABLE_NAME"));
            table.setDocumentation(map.get("TABLE_COMMENT"));
            String sqlColumn = String.format("select * from information_schema.columns where TABLE_SCHEMA='%s' and TABLE_NAME = '%s'", database, table.getName());
            List<Map<String, String>> mapList2 = runSql(connection, sqlColumn);
            List<Column> columnList = new ArrayList<>();
            for (Map<String, String> stringMap : mapList2) {
                Column column = new Column();
                column.setId(GenerateBase64Strings.getRandomBase64String());
                column.setTableId(tableId);
                column.setName(stringMap.get("COLUMN_NAME"));
                column.setType(stringMap.get("DATA_TYPE"));
                column.setDocumentation(stringMap.get("COLUMN_COMMENT"));
                String columnType = stringMap.get("COLUMN_TYPE");
                if (columnType.indexOf("(") > 0) {
                    column.setLength(columnType.substring(columnType.indexOf("(") + 1, columnType.indexOf(")")));
                } else {
                    column.setLength(stringMap.get("CHARACTER_MAXIMUM_LENGTH"));
                }
                column.setOrdinalPosition(Integer.parseInt(stringMap.get("ORDINAL_POSITION")));
                column.setNullable("YES".equals(stringMap.get("IS_NULLABLE")));
                column.setPrimaryKey("PRI".equals(stringMap.get("COLUMN_KEY")));
                column.setUnique("UNI".equals(stringMap.get("COLUMN_KEY")));
                columnList.add(column);
            }
            columnList.sort((c1, c2) -> c1.getOrdinalPosition() - c2.getOrdinalPosition());
            table.setColumns(columnList);
            tableList.add(table);
        }

        this.close(null, connection, null);
        return tableList;
    }
}
