package com.addressprocessor;

public enum AddressLineDetail {
    LINE1("line1"),
    LINE2("line2");

    private final String fieldName;

    AddressLineDetails(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
