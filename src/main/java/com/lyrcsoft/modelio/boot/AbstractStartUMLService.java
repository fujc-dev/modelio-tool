package com.lyrcsoft.modelio.boot;

import cn.hutool.core.io.IoUtil;
import com.lyrcsoft.modelio.boot.model.*;
import com.lyrcsoft.modelio.utils.GenerateBase64Strings;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-21 9:07
 */
public abstract class AbstractStartUMLService implements UMLService {

    public abstract List<Table> getTableList(DataSource ds);

    @Override
    public String saveAsFileWriter(String filePath, DataSource dataSource) {
        List<Table> tableList = getTableList(dataSource);
        String database = getDBByUrl(dataSource.getUrl());
        String content = getProject(database, tableList);
        FileWriter fwriter = null;
        String filename = filePath + "\\" + database + ".xmi";
        try {
            fwriter = new FileWriter(filename);
            fwriter.write(content);
            return filePath;
        } catch (IOException ex) {
            throw new RuntimeException("写入失败：" + database, ex);
        } finally {
            try {
                if (null != fwriter) {
                    fwriter.flush();
                    fwriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private static int left = 100;
    private static int top = 100;

    /**
     * 构建StatUML根信息
     *
     * @param dbName    数据库名称
     * @param tableList 表列表
     * @return
     */
    public String getProject(String dbName, List<Table> tableList) {
        EAnnotations eAnnotations = new EAnnotations();
        List<PackagedElement> packagedElements = new ArrayList<>();
        for (Table table : tableList) {
            PackagedElement packagedElement = new PackagedElement();
            packagedElement.setId(GenerateBase64Strings.getRandomBase64String());
            packagedElement.setName(convertColumnName(table.getName()));
            for (Column column : table.getColumns()) {
                OwnedAttribute ownedAttribute = new OwnedAttribute();
                ownedAttribute.setId(GenerateBase64Strings.getRandomBase64String());
                ownedAttribute.convertType(column.getType());
                ownedAttribute.setName(convertColumnName(column.getName(), false));
                packagedElement.add(ownedAttribute);
            }
            packagedElements.add(packagedElement);
        }


        Project project = new Project();
        project.setEAnnotations(eAnnotations);
        project.setPackagedElement(packagedElements);
        return project.toString();
    }


    public String convertColumnName(String columnName) {
        return convertColumnName(columnName, true);
    }

    // 获取数据库列名
    public String convertColumnName(String columnName, boolean firstCharacter) {
        // 转换为驼峰命名
        String[] words = columnName.split("_");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                if (firstCharacter) {
                    result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
                } else {
                    result.append(words[i]);
                }

            } else {
                result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
            }
        }
        return result.toString();
    }


    /**
     * 从连接字符串中截取数据库名称
     *
     * @param url 连接字符串
     * @return
     */
    public String getDBByUrl(String url) {
        if (null == url || url.isEmpty()) {
            throw new RuntimeException("地址为空");
        }
        if (url.indexOf(":") == 0 && url.length() <= 1) {
            throw new RuntimeException("地址有误");
        }
        while (url.indexOf(":") > 0) {
            url = url.substring(url.indexOf(":") + 1);
        }
        if (url.indexOf("?") > 0) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.indexOf("/") > 0) {
            url = url.substring(url.indexOf("/") + 1);
        }
        return url;
    }


    public void close(PreparedStatement pstmt, Connection conn, ResultSet rs) {
        try {
            if (null != rs) {
                rs.close();
                rs = null;
            }
            if (null != pstmt) {
                pstmt.close();
                pstmt = null;
            }
            if (null != conn) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("关闭数据库连接异常", e);
        }
    }


    public List<Map<String, String>> runSql(Connection conn, String sql) {
        if (null == sql || sql.isEmpty()) {
            throw new RuntimeException("执行的sql不可为空");
        }
        List<Map<String, String>> list = new ArrayList<>();
        if (null == conn) {
            throw new RuntimeException("获取数据库连接失败");
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnName(i), rs.getString(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            throw new RuntimeException("执行sql异常", e);
        } finally {
            close(pstmt, null, rs);
        }
        return list;
    }
}
