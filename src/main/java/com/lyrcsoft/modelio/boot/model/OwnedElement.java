package com.lyrcsoft.modelio.boot.model;

import lombok.Data;

/**
 * 基础
 *
 * @author fujc2dev@126.com
 * @date 2024-05-20 17:32
 */
@Data
public class OwnedElement {
    protected String type;
    private String id;
    private String name;
}
