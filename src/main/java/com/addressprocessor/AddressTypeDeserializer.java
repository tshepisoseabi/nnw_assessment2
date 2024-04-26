package com.addressprocessor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AddressTypeDeserializer extends StdDeserializer<AddressType> {

    protected AddressTypeDeserializer() {
        super(AddressType.class);
    }

    @Override
    public AddressType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String code = node.get("code").asText();
        // Assuming you have a method to get AddressType instance by code
        return getAddressTypeByCode(code);
    }

    private AddressType getAddressTypeByCode(String code) {
        // Implement logic to return AddressType instance based on code
        // For example:
        if ("1".equals(code)) {
            return AddressType.PHYSICAL;
        } else if ("2".equals(code)) {
            return AddressType.POSTAL;
        } else if ("5".equals(code)) {
            return AddressType.BUSINESS;
        } else {
            throw new IllegalArgumentException("Invalid AddressType code: " + code);
        }
    }
}
