package log_management.parameters.add_remove;

import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action of AddVerticesLogAction/RemoveVerticesLogAction.
 */
@Data
public class AddRemoveVerticesParam extends Param implements Serializable {

    @Getter
    private List<Vertex> vertexList;

    @Getter
    private List<Sphere> sphereList;

    @Getter
    private List<Vertex> startVertexList;

    @Getter
    private List<Vertex> sinkVertexList;

    @Getter
    private List<Edge> edgeList;

    /**
     * Creates an parameter object of its own class.
     * @param pParameter The set of vertices and their sphere.
     */
    public AddRemoveVerticesParam(Map<Vertex, Sphere> pParameter, Map<Edge,Pair<Vertex,Vertex>> edges) {
        vertexList = new ArrayList<>();
        sphereList = new ArrayList<>();
        pParameter.forEach((v, s) -> {
            vertexList.add(v);
            sphereList.add(s);
        });
        startVertexList = new ArrayList<>();
        sinkVertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
        for(Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edges.entrySet()){
            edgeList.add(entry.getKey());
            startVertexList.add(entry.getValue().getKey());
            sinkVertexList.add(entry.getValue().getValue());
        }
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations: ";
            for (int i = 0; i < vertexList.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintEnglish(vertexList.get(i)) + ". ";
                information += "In sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphereList.get(i)) + ". ";
            }
        } else {
            information += "Relationen: ";
            for (int i = 0; i < vertexList.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintGerman(vertexList.get(i)) + ". ";
                information += "In Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphereList.get(i)) + ". ";
            }
        }
        return information;
    }

    public Map<Vertex, Sphere> getVertices() {
        Map<Vertex, Sphere> map = new HashMap<>();
        for (int i = 0; i < vertexList.size(); i++) {
            map.put(vertexList.get(i), sphereList.get(i));
        }
        return map;
    }

}
