package com.lyrcsoft.modelio.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * t_datesource持久对象
 *
 * @author pangu
 * @since 2024-07-23 17:07:45
 */
@Data
@ToString(callSuper = true)
@TableName("t_datasource")
@Builder
public class TDatasource implements Serializable {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    @TableField("driver_class_name")
    private String driverClassName;

    @TableField("url")
    private String url;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @Override
    public String toString() {
        return
                "driverClassName='" + driverClassName + '\'' +
                        ", url='" + url + '\'' +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'';
    }
}
