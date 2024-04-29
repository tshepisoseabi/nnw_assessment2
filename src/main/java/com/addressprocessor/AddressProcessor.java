package com.addressprocessor;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;

public class AddressProcessor {
    private final List<Address> addresses;

    // Constructor to initialize addresses from JSON file
    public AddressProcessor(String jsonFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        addresses = mapper.readValue(AddressProcessor.class.getResourceAsStream(jsonFilePath), new TypeReference<List<Address>>(){});
    }

    // Task a: Pretty print an address
    public String prettyPrintAddress(Address address) {
        return String.format("%s: %s - %s - %s - %s - %s - %s",
                address.getType().getName(),
                address.getAddressLineDetail() != null ? address.getAddressLineDetail().getLine1() : "",
                address.getAddressLineDetail() != null ? address.getAddressLineDetail().getLine2() : "",
                address.getCityOrTown(),
                address.getProvinceOrState() != null ? address.getProvinceOrState().getName() : "",
                address.getPostalCode(),
                address.getCountry().getName());
    }

    // Task b: Pretty print all addresses
    public void prettyPrintAllAddresses() {
        for (Address address : addresses) {
            System.out.println(prettyPrintAddress(address));
        }
    }

    // Task c: Pretty print addresses of a certain type
    public void prettyPrintAddressesOfType(String type) {
        for (Address address : addresses) {
            if (address.getType().getCode().equals(type)) {
                System.out.println(prettyPrintAddress(address));
            }
        }
    }

    // Task d: Validate an address
    public boolean validateAddress(Address address) {
        // Check if postal code is numeric
        if (!address.getPostalCode().matches("\\d+")) {
            return false;
        }

        // Check if country is provided
        if (address.getCountry() == null || address.getCountry().getName().isEmpty()) {
            return false;
        }

        // Check if at least one address line is provided
        if (address.getAddressLineDetail() == null ||
                (address.getAddressLineDetail().getLine1().isEmpty() && address.getAddressLineDetail().getLine2().isEmpty())) {
            return false;
        }

        // If country is ZA, province is required
        return !address.getCountry().getCode().equals("ZA") ||
                (address.getProvinceOrState() != null && !address.getProvinceOrState().getName().isEmpty());
    }

    // Task e: Validate all addresses and print validation status with reasons for invalid addresses
    public void validateAllAddresses() {
        for (Address address : addresses) {
            System.out.println("Address:");
            System.out.println(prettyPrintAddress(address));

            boolean isValid = validateAddress(address);
            System.out.println("Validation status: " + (isValid ? "Valid" : "Invalid"));

            if (!isValid) {
                System.out.println("Reasons for invalidity:");

                if (!address.getPostalCode().matches("\\d+")) {
                    System.out.println("- Postal code is not numeric");
                }

                if (address.getCountry() == null || address.getCountry().getName().isEmpty()) {
                    System.out.println("- Country is missing");
                }

                if (address.getAddressLineDetail() == null ||
                        (address.getAddressLineDetail().getLine1().isEmpty() && address.getAddressLineDetail().getLine2().isEmpty())) {
                    System.out.println("- Address lines are missing");
                }

                if (address.getCountry().getCode().equals("ZA") &&
                        (address.getProvinceOrState() == null || address.getProvinceOrState().getName().isEmpty())) {
                    System.out.println("- Province is missing for South Africa");
                }
            }

            System.out.println();
        }
    }


    public static void main(String[] args) {
        try {
            AddressProcessor processor = new AddressProcessor("/addresses.json");
            processor.prettyPrintAllAddresses();
            System.out.println("Addresses of type '1':");
            processor.prettyPrintAddressesOfType("1");
            processor.validateAllAddresses();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
