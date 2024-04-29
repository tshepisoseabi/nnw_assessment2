import com.addressprocessor.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressProcessorTest {

    private AddressProcessor addressProcessor;

    @BeforeEach
    public void setUp() {
        try {
            addressProcessor = new AddressProcessor("/addresses.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Test cases for address validation

    @Test
    public void testValidAddress() {
        Address validAddress = new Address(
                new AddressLineDetail("2078", "Gecko"),
                "City 1",
                new ProvinceOrState("5", "Eastern Cape"),
                new Country("ZA", "South Africa"),
                "2145"
        );

        assertEquals(true, addressProcessor.validateAddress(validAddress));
    }

    @Test
    public void testInvalidPostalCode() {
        Address invalidPostalCodeAddress = new Address(
                new AddressLineDetail("Address 1", "Line 2"),
                "City 1",
                new ProvinceOrState("5", "Eastern Cape"),
                new Country("ZA", "South Africa"),
                "ABCD" // Invalid postal code (not numeric)
        );

        assertEquals(false, addressProcessor.validateAddress(invalidPostalCodeAddress));
    }

    @Test
    public void testInvalidCountry() {
        Address invalidCountryAddress = new Address(
                new AddressLineDetail("Address 1", "Line 2"),
                "City 1",
                new ProvinceOrState("5", "Eastern Cape"),
                new Country("", ""), // Invalid country (empty)
                "2145"
        );

        assertEquals(false, addressProcessor.validateAddress(invalidCountryAddress));
    }

    @Test
    public void testInvalidAddressLines() {
        Address invalidAddressLinesAddress = new Address(
                new AddressLineDetail("", ""), // Invalid address lines (both blank)
                "City 1",
                new ProvinceOrState("5", "Eastern Cape"),
                new Country("ZA", "South Africa"),
                "2145"
        );

        assertEquals(false, addressProcessor.validateAddress(invalidAddressLinesAddress));
    }

    @Test
    public void testInvalidProvinceForZA() {
        Address invalidProvinceForZAAddress = new Address(
                new AddressLineDetail("Address 1", "Line 2"),
                "City 1",
                new ProvinceOrState("", ""), // Invalid province for South Africa (empty)
                new Country("ZA", "South Africa"),
                "2145"
        );

        assertEquals(false, addressProcessor.validateAddress(invalidProvinceForZAAddress));
    }
}
