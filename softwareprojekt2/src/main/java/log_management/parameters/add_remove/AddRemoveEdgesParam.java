package log_management.parameters.add_remove;

import graph.graph.Edge;
import graph.graph.Syndrom;
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
import java.util.List;
import java.util.Set;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
@Data
public class AddRemoveEdgesParam extends Param implements Serializable {
    /**
     * The set of edges(pair describes the respective start-vertex and sink-vertex) bound to their edge-type.
     */
    @Getter
    private Set<Pair<Vertex,Vertex>> edges;
    /**
     * Creates an parameter object of its own class.
     * @param pEdges List of edges and their start/end vertex id.
     */
    public AddRemoveEdgesParam(Set<Pair<Vertex,Vertex>> pEdges){
        this.edges = pEdges;
    }

    @Override
    public String toString() {
        List<Edge> edgeList = new ArrayList<>();

        for(Pair<Vertex, Vertex> pair : edges){
            Edge edge = Syndrom.getInstance().getGraph().findEdge(pair.getKey(), pair.getValue());
            edgeList.add(edge);
        }

        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            String list = "Relations:\n";
            for (int i = 0; i < edgeList.size(); i++) {
                list += SyndromObjectPrinter.edgePrintEnglish(edgeList.get(i));
            }
            return list;
        } else {
            String list = "Relationen:\n";
            for (int i = 0; i < edgeList.size(); i++) {
                list += SyndromObjectPrinter.edgePrintGerman(edgeList.get(i));
            }
            return list;
        }
    }
}