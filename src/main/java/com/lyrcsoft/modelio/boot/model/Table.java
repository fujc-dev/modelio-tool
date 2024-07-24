package com.lyrcsoft.modelio.boot.model;

import lombok.Data;

import java.util.List;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-20 17:34
 */
@Data
public class Table extends OwnedElement {
    private String _type = "ERDEntity";
    private String documentation;
    private List<Column> columns;
}
