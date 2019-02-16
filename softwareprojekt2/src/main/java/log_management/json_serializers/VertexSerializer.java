package log_management.json_serializers;

import com.google.gson.*;
import graph.graph.Vertex;

import java.lang.reflect.Type;

public class VertexSerializer implements JsonSerializer<Vertex> {
    public JsonElement serialize(Vertex vertex, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject jo = new JsonObject();
        jo.addProperty("id",vertex.getId());
        jo.add("fillColor", new Gson().toJsonTree(vertex.getFillColor()));
        jo.add("coordinates", new Gson().toJsonTree(vertex.getCoordinates()));
        jo.add("shape", new Gson().toJsonTree(vertex.getShape()));
        jo.add("annotation", new Gson().toJsonTree(vertex.getAnnotation()));
        jo.add("drawColor", new Gson().toJsonTree(vertex.getDrawColor()));
        jo.add("vertexArrowReinforced", new Gson().toJsonTree(vertex.getVertexArrowReinforced()));
        jo.add("vertexArrowNeutral", new Gson().toJsonTree(vertex.getVertexArrowNeutral()));
        jo.add("vertexArrowExtenuating", new Gson().toJsonTree(vertex.getVertexArrowExtenuating()));
        jo.addProperty("size",vertex.getSize());
        jo.addProperty("isVisible", vertex.isVisible());
        jo.addProperty("font", vertex.getFont());
        jo.addProperty("fontSize", vertex.getFontSize());
        jo.addProperty("isHighlighted", vertex.isHighlighted());
        jo.addProperty("isLockedStyle", vertex.isLockedStyle());
        jo.addProperty("isLockedPosition", vertex.isLockedPosition());
        jo.addProperty("isLockedAnnotation", vertex.isLockedAnnotation());

        return jo;
    }
}
