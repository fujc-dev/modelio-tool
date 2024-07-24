package com.lyrcsoft.modelio.boot.model;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author fujc2dev@126.com
 * @date 2024-05-21 9:04
 */
@Data
@Builder
public class DataSource {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://10.20.2.249:3306/crungoo-zhgl?useUnicode=true&characterEncoding=UTF-8";
    String username = "root";
    String password = "superAdmin@112233";

}
