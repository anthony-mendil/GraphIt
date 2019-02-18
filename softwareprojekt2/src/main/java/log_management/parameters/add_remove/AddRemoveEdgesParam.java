package log_management.parameters.add_remove;

import graph.graph.Edge;
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
import java.util.Map;
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
    private Map<Edge,Pair<Vertex,Vertex>> edges;

    /**
     * Creates an parameter object of its own class.
     * @param pEdges List of edges and their start/end vertex id.
     */
    public AddRemoveEdgesParam(Map<Edge,Pair<Vertex,Vertex>> pEdges){
        this.edges = pEdges;
    }

    @Override
    public String prettyPrint() {
        /**
        List<Pair<Vertex,Vertex>> verticesList = new ArrayList<>();
        vertices.forEach(p -> verticesList.add(new Pair<>(p.getKey(), p.getValue())));

        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            String list = "Relations:\n";
            for (int i = 0; i < edges.size(); i++) {
                list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i), verticesList.get(i));
            }
            return list;
        } else {
            String list = "Relationen: ";
            for (int i = 0; i < edges.size(); i++) {
                    list += SyndromObjectPrinter.edgePrintGerman(edges.get(i), verticesList.get(i));
            }
            return list;
         */
        return null;
    }
}