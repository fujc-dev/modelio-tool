package com.lyrcsoft.modelio.boot.model;

import com.lyrcsoft.modelio.utils.GenerateBase64Strings;
import lombok.Data;

/**
 * @author fujc2dev@126.com
 * @date 2024-07-23
 */
@Data
public class DefaultValue extends  OwnedElement {
    public  DefaultValue(){
        this.setId(GenerateBase64Strings.getRandomBase64String());
    }
}
