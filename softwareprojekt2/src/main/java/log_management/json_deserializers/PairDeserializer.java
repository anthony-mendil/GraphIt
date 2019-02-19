package log_management.json_deserializers;

import com.google.gson.*;
import javafx.util.Pair;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.lang.reflect.Type;

public class PairDeserializer implements JsonDeserializer<Pair> {
    @Override
    public Pair deserialize(JsonElement json, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement xFirst = jsonObject.get("xFirst");
        JsonElement yFirst = jsonObject.get("yFirst");

        if (jsonObject.has("transform")) {
            return new Pair<>(new Point2D.Double(0,0), new AffineTransform());
        } else {
            JsonElement xSecond = jsonObject.get("xSecond");
            JsonElement ySecond = jsonObject.get("ySecond");

            if (xFirst == null || yFirst == null || xSecond == null || ySecond == null) {
                return new Pair<>(new Point2D.Double(0, 0), new Point2D.Double(0, 0));
            } else {
                return new Pair<>(new Point2D.Double(xFirst.getAsDouble(), yFirst.getAsDouble()), new Point2D.Double(xSecond.getAsDouble(), ySecond.getAsDouble()));
            }
        }
    }

}