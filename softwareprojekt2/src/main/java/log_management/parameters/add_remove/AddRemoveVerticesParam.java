package log_management.parameters.add_remove;

import graph.graph.Sphere;
import graph.graph.Vertex;
import log_management.json_serializers.VertexSerializer;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
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
    private Map<Vertex, Sphere> parameter = new HashMap<>();

    /**
     * Creates an parameter object of its own class.
     * @param pParameter The set of vertices and their sphere.
     */
    public AddRemoveVerticesParam(Map<Vertex, Sphere> pParameter) {
        parameter = pParameter;
       // for (Map.Entry<Vertex,Sphere> entry : pParameter.entrySet()){
        //    vertex = entry.getKey();
         //   sphere = entry.getValue();
         //   parameter.put(vertex,sphere);
      //  }

    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
