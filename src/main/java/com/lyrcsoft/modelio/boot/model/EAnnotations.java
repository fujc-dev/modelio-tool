package com.lyrcsoft.modelio.boot.model;

import lombok.Data;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
@Data
public class EAnnotations extends OwnedElement {
    private Contents contents = new Contents();

    @Override
    public String toString() {
        return "<eAnnotations xmi:id=\"" + getId() + "\" source=\"Objing\">"
                + contents.toString()
                + "</eAnnotations>";
    }
}
