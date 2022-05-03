package com.craig.keniprojectwefbluxproduct.util;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class I18AbleException extends RuntimeException{
    protected final transient  Object[] args;

    public I18AbleException( String key,Object[] args) {
        super(key);
        this.args = args;
    }

    public I18AbleException(String key) {
        super(key);
        this.args = new Object[0];
    }
}
