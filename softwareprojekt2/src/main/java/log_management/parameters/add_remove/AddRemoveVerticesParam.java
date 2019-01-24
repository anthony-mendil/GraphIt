package log_management.parameters.add_remove;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.json_serializers.VertexSerializer;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action of AddVerticesLogAction/RemoveVerticesLogAction.
 */
@Data
public class AddRemoveVerticesParam extends Param{
    /**
     * Set of vertices to their sphere.
     */
    @Getter
    @JsonSerialize(keyUsing = VertexSerializer.class)
    private Map<Vertex, Sphere> vertices = new HashMap<>();

    /**
     * Creates an parameter object of its own class.
     * @param pParameter The set of vertices and their sphere.
     */
    public AddRemoveVerticesParam(Map<Vertex, Sphere> pParameter) {
        vertices = pParameter;
       // for (Map.Entry<Vertex,Sphere> entry : pParameter.entrySet()){
        //    vertex = entry.getKey();
         //   sphere = entry.getValue();
         //   vertices.put(vertex,sphere);
      //  }

    }

    @Override
    public String toString() {
        int counter = 1;
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            for (Map.Entry<Vertex, Sphere> entry : vertices.entrySet()) {
                information += "Symptom " + counter++ + ": " + SyndromObjectPrinter.vertexPrintEnglish(entry.getKey());
                information += "In Sphere: " + SyndromObjectPrinter.spherePrintEnglish(entry.getValue());
            }
        } else {
            for (Map.Entry<Vertex, Sphere> entry : vertices.entrySet()) {
                information += "Symptom " + counter++ + ": " + SyndromObjectPrinter.vertexPrintGerman(entry.getKey());
                information += "In Sphäre: " + SyndromObjectPrinter.spherePrintGerman(entry.getValue());
            }
        }
        return information;
    }
}
