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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action of AddVerticesLogAction/RemoveVerticesLogAction.
 */
@Data
public class AddRemoveVerticesParam implements Param {

    /**
     * The list of vertices.
     */
    @Getter
    private List<Vertex> vertexList;

    /**
     * The list of spheres.
     */
    @Getter
    private List<Sphere> sphereList;

    /**
     * The start vertices of the edges.
     */
    @Getter
    private List<Vertex> startVertexList;

    /**
     * The end vertices of the edges.
     */
    @Getter
    private List<Vertex> sinkVertexList;

    /**
     * The edges.
     */
    @Getter
    private List<Edge> edgeList;

    /**
     * Creates a parameter object of its own class.
     * @param pParameter The vertices and the spheres they are positioned in.
     * @param edges The edges as well as their start and end vertices.
     */
    public AddRemoveVerticesParam(Map<Vertex, Sphere> pParameter, Map<Edge, Pair<Vertex, Vertex>> edges) {
        vertexList = new ArrayList<>();
        sphereList = new ArrayList<>();
        pParameter.forEach((v, s) -> {
            vertexList.add(v);
            sphereList.add(s);
        });
        startVertexList = new ArrayList<>();
        sinkVertexList = new ArrayList<>();
        edgeList = new ArrayList<>();
        for (Map.Entry<Edge, Pair<Vertex, Vertex>> entry : edges.entrySet()) {
            edgeList.add(entry.getKey());
            startVertexList.add(entry.getValue().getKey());
            sinkVertexList.add(entry.getValue().getValue());
        }
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            information.append("Relations: ");
            for (int i = 0; i < vertexList.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintEnglish(vertexList.get(i))).append(". ");
                information.append("In sphere: ").append(SyndromObjectPrinter.spherePrintEnglish(sphereList.get(i))).append("; ");
            }
        } else {
            information.append("Relationen: ");
            for (int i = 0; i < vertexList.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(vertexList.get(i))).append(". ");
                information.append("In Sphäre: ").append(SyndromObjectPrinter.spherePrintGerman(sphereList.get(i))).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * Gets the vertices and their spheres.
     * @return The vertices and their spheres.
     */
    public Map<Vertex, Sphere> getVertices() {
        Map<Vertex, Sphere> map = new HashMap<>();
        for (int i = 0; i < vertexList.size(); i++) {
            map.put(vertexList.get(i), sphereList.get(i));
        }
        return map;
    }
}
