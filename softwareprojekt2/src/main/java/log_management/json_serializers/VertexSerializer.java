//package log_management.json_serializers;
//
//
//import graph.graph.Vertex;
//
//
//import java.io.IOException;
//import java.io.StringWriter;
//
//public class VertexSerializer extends JsonSerializer<Vertex> {
//    private ObjectMapper mapper  = new ObjectMapper();
//
//    @Override
//    public void serialize(Vertex value, JsonGenerator generator,
//                          SerializerProvider serializerProvider)throws IOException, JsonProcessingException {
//        StringWriter writer = new StringWriter();
//        mapper.writeValue(writer, value);
//        generator.writeFieldName(writer.toString());
//    }
//}
