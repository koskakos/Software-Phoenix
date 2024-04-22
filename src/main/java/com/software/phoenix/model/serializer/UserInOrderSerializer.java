package com.software.phoenix.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.software.phoenix.model.User;
import java.io.IOException;

public class UserInOrderSerializer extends StdSerializer<User> {

    public UserInOrderSerializer() {
        this(null);
    }

    public UserInOrderSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(user.getUsername());
    }
}