package com.addressprocessor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class AddressLineDetailsDeserializer extends StdDeserializer<AddressLineDetail> {

    protected AddressLineDetailsDeserializer() {
        super(AddressLineDetail.class);
    }

    @Override
    public AddressLineDetail deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String line1 = node.get("line1").asText();
        String line2 = node.get("line2").asText();
        return new AddressLineDetail(line1, line2);
    }
}
