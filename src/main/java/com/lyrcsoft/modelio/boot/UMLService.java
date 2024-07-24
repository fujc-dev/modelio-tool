package com.lyrcsoft.modelio.boot;

import com.lyrcsoft.modelio.boot.model.DataSource;

/**
 * UML服务
 *
 * @author fujc2dev@126.com
 * @date 2024-05-21 8:57
 */
public interface UMLService {

    /**
     * 本地保存文件
     *
     * @param filePath
     */
    String saveAsFileWriter(String filePath, DataSource dataSource);
}
