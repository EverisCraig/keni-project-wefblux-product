package com.craig.keniprojectwefbluxproduct.exception;

import com.craig.keniprojectwefbluxproduct.util.I18AbleException;

public class ProductNotFoundException extends I18AbleException {
    public ProductNotFoundException(String key, Object[] args) {
        super(key, args);
    }

    public ProductNotFoundException(String key) {
        super(key);
    }
}
