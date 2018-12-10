package log_management.parameters.add;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.Map;

public class AddVerticesParam extends Param implements Serializable {

    @Getter
    private Map<Vertex, Pair<Integer, String>> parameter;

    public AddVerticesParam(Map<Vertex, Pair<Integer, String>> pParameter) {
        parameter = pParameter;
    }

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }
}
