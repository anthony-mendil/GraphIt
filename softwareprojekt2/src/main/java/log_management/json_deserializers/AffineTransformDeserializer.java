package log_management.json_deserializers;

import com.google.gson.*;

import java.awt.geom.AffineTransform;
import java.lang.reflect.Type;

public class AffineTransformDeserializer implements JsonDeserializer<AffineTransform> {
    @Override
    public AffineTransform deserialize(JsonElement json, Type type,
                                       JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new AffineTransform();
    }
}