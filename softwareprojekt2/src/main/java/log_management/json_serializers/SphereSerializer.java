package log_management.json_serializers;

import graph.graph.Sphere;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.io.StringWriter;

public class SphereSerializer extends JsonSerializer<Sphere> {
    private ObjectMapper mapper  = new ObjectMapper();

    @Override
    public void serialize(Sphere value, JsonGenerator generator,
                          SerializerProvider serializerProvider)throws IOException, JsonProcessingException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, value);
        generator.writeFieldName(writer.toString());
    }
}
