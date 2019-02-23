package log_management.json_serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import javafx.util.Pair;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.lang.reflect.Type;

public class PairSerializer implements JsonSerializer<Pair> {
    @Override
    public JsonElement serialize(Pair pair, Type type,
                                 JsonSerializationContext jsc) {




        JsonObject jo = new JsonObject();
        jo.addProperty("xFirst", ((Point2D)pair.getKey()).getX());
        jo.addProperty("yFirst", ((Point2D)pair.getValue()).getY());

        if (pair.getValue() instanceof Point2D) {
            jo.addProperty("xSecond", ((Point2D) pair.getKey()).getX());
            jo.addProperty("ySecond", ((Point2D) pair.getValue()).getY());
        } else if (pair.getValue() instanceof AffineTransform) {
            jo.addProperty("transform", false);
        }
        return jo;
    }
}
