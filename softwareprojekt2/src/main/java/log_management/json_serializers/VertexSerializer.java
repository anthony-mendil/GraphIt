package log_management.json_serializers;

import graph.graph.Vertex;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class VertexSerializer extends JsonSerializer<Vertex> {
    private ObjectMapper mapper  = new ObjectMapper();

    @Override
    public void serialize(Vertex value, JsonGenerator generator,
                          SerializerProvider serializerProvider)throws IOException, JsonProcessingException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        generator.writeFieldName(writer.toString());
    }
}
