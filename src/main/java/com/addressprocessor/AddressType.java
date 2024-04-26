package com.addressprocessor;

public class AddressType {
    public static final AddressType PHYSICAL = new AddressType("1", "Physical Address");
    public static final AddressType POSTAL = new AddressType("2", "Postal Address");
    public static final AddressType BUSINESS = new AddressType("5", "Business Address");

    private final String code;
    private final String name;

    private AddressType(String code, String name) {
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
