package com.addressprocessor;

public enum AddressType {
    PHYSICAL("1", "Physical Address"),
    POSTAL("2", "Postal Address"),
    BUSINESS("5", "Business Address");

    private final String code;
    private final String name;

    AddressType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
