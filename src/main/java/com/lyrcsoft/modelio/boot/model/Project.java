package com.lyrcsoft.modelio.boot.model;

import lombok.Data;

import java.util.List;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-20 17:33
 */
@Data
public class Project extends OwnedElement {
    private String _type = "uml:Model";
    private EAnnotations eAnnotations;
    private List<PackagedElement> packagedElement;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<uml:Model xmi:version=\"2.1\" xmlns:xmi=\"http://schema.omg.org/spec/XMI/2.1\" xmlns:uml=\"http://www.eclipse.org/uml2/3.0.0/UML\" xmi:id=\"_rcdKwEi-Ee-fWesxfPKa5g\" name=\"Model\">");
        sb.append(eAnnotations.toString());
        if (packagedElement != null) {
            for (PackagedElement packagedElement : packagedElement) {
                sb.append(packagedElement.toString());
            }
        }
        sb.append("</uml:Model>");
        return sb.toString();
    }
}
