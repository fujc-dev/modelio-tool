package com.lyrcsoft.modelio.boot.model;

import com.lyrcsoft.modelio.utils.GenerateBase64Strings;
import lombok.Data;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
@Data
public class Contents extends OwnedElement {

    public Contents() {
        defaultValue = new DefaultValue();
        this.setId(GenerateBase64Strings.getRandomBase64String());
    }

    private DefaultValue defaultValue;

    @Override
    public String toString() {
        return "<contents xmi:type=\"uml:Property\" xmi:id=\"" + getId() + "\" name=\"exporterVersion\">"
                + "<defaultValue xmi:type=\"uml:LiteralString\" xmi:id=\"" + defaultValue.getId() + "\" value=\"3.0.0\"/>"
                + "</contents>"

                ;
    }
}
