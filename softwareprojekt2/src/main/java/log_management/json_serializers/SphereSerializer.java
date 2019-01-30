package log_management.json_serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import graph.graph.Sphere;

import java.lang.reflect.Type;

public class SphereSerializer implements JsonSerializer<Sphere> {
    public JsonElement serialize(Sphere position, Type type,
                                 JsonSerializationContext jsc){
        JsonObject jo = new JsonObject();
        return null;

    }
}
