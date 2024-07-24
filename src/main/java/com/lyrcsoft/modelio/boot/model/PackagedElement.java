package com.lyrcsoft.modelio.boot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
public class PackagedElement extends OwnedElement {
    private List<OwnedAttribute> ownedAttribute = new ArrayList<>();

    public  void add(OwnedAttribute ownedAttribute){
        this.ownedAttribute.add(ownedAttribute);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<packagedElement xmi:type=\"uml:Class\" xmi:id=\"" + getId() + "\" name=\"" + getName() + "\">");
        if (ownedAttribute != null) {
            for (OwnedAttribute ownedAttribute : ownedAttribute) {
                sb.append(ownedAttribute.toString());
            }
        }
        sb.append("</packagedElement>");
        return sb.toString();
    }
}
