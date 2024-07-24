package com.lyrcsoft.modelio.utils;

import com.lyrcsoft.modelio.boot.UMLService;
import com.lyrcsoft.modelio.boot.model.DataSource;
import com.lyrcsoft.modelio.service.impl.MySqlToStarUMLImpl;

/**
 * Mysql 数据库转 StarUML
 *
 * @author fujc2dev@126.com
 * @date 2024-05-20 14:22
 */
public class Programs {


    public static void main(String[] args) {

        UMLService startUMLService = new MySqlToStarUMLImpl();
        String filePath = "D:\\crungoo-zhgl.mdj";
        startUMLService.saveAsFileWriter(filePath, DataSource.builder().build());
        System.out.println("===============生成成功================");

    }


}