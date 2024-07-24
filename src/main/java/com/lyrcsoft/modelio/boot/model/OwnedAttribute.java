package com.lyrcsoft.modelio.boot.model;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
public class OwnedAttribute extends OwnedElement {


    public void convertType(String type) {
        switch (type) {
            case "varchar":
                super.setType("String");
                break;
            case "int":
            case "tinyint":
                this.setType("Integer");
                break;
            case "char":
                this.setType("Char");
                break;
            case "bigint":
                this.setType("Long");
                break;
            case "float":
                this.setType("Float");
                break;
            case "double":
                this.setType("Double");
                break;
            case "bit":
                this.setType("Boolean");
                break;
            case "date":
            case "datetime":
            case "timestamp":
                this.setType("Date");
                break;
            default:
                this.setType("String");
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ownedAttribute xmi:id=\"" + getId() + "\" name=\"" + getName() + "\" visibility=\"public\" isUnique=\"false\">");
        sb.append("<type xmi:type=\"uml:PrimitiveType\" href=\"pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml#" + getType() + "\"/>");
        sb.append("</ownedAttribute>");
        return sb.toString();
    }
}
