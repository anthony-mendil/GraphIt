package log_management.json_serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.awt.geom.AffineTransform;
import java.lang.reflect.Type;

public class AffineTransformSerializer implements JsonSerializer<AffineTransform> {
    @Override
    public JsonElement serialize(AffineTransform transform, Type type,
                                 JsonSerializationContext jsc) {
        JsonObject jo = new JsonObject();
        jo.addProperty("transform", false);
        return jo;
    }
}
