package com.lyrcsoft.modelio.boot.model;

import lombok.Data;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-20 17:33
 */
@Data
public class Column {
    private String _type = "ownedAttribute";
    private String id;
    private String name;
    private String tableId;
    private String type;
    private String length;
    // UNI、PRI
    private String columnKey;
    private int ordinalPosition;
    private Boolean primaryKey;
    private Boolean unique;
    private Boolean nullable;
    private String documentation;
}
