package log_management.json_deserializers;

import com.google.gson.*;

import java.awt.geom.Point2D;
import java.lang.reflect.Type;

/**
 * Json deserializer for points2D.
 */
public class Point2DDeserializer implements JsonDeserializer<Point2D>{

    @Override
    public Point2D deserialize(JsonElement json, Type type,
                               JsonDeserializationContext jsonDeserializationContext) {

        JsonObject jsonObject = json.getAsJsonObject();

        JsonElement x = jsonObject.get("x");
        JsonElement y = jsonObject.get("y");

        return new Point2D.Double(x.getAsDouble(),y.getAsDouble());
    }
}