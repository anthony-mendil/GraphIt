package log_management.json_serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.awt.geom.Point2D;
import java.lang.reflect.Type;

/**
 * Json deserializer for points2D.
 *
 * @author Anthony Mendil
 */
public class Point2DSerializer implements JsonSerializer<Point2D> {

    @Override
    public JsonElement serialize(Point2D position, Type type,
                                 JsonSerializationContext jsc) {
        JsonObject jo = new JsonObject();
        jo.addProperty("x", position.getX());
        jo.addProperty("y", position.getY());

        return jo;
    }
}
