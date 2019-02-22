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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
@Data
public class AddRemoveEdgesParam extends Param {
    /**
     * The set of edges(pair describes the respective start-vertex and sink-vertex) bound to their edge-type.
     */
    @Getter
    private List<Edge> edges;

    //@Getter
    //private Set<Pair<Vertex,Vertex>> vertices;

    @Getter
    private List<Vertex> startVertices;

    private List<Vertex> endVertices;

    /**
     * Creates an parameter object of its own class.
     * @param pEdges List of edges and their start/end vertex id.
     */
    public AddRemoveEdgesParam(List<Edge> pEdges, Set<Pair<Vertex,Vertex>> pVertices){
        this.edges = pEdges;

        List<Vertex> list1 = new ArrayList();
        List<Vertex> list2 = new ArrayList();
        pVertices.forEach(s -> list1.add(s.getKey()));
        pVertices.forEach(s -> list2.add(s.getValue()));

        this.startVertices = list1;
        this.endVertices = list2;
    }

    @Override
    public String prettyPrint() {
        List<Pair<Vertex,Vertex>> verticesList = new ArrayList<>();
        for (int i = 0; i < startVertices.size(); i++) {
            verticesList.add(new Pair<>(startVertices.get(i), endVertices.get(i)));
        }

        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            String list = "Relations: ";
            for (int i = 0; i < edges.size(); i++) {
                list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i), verticesList.get(i)) + ". ";
            }
            return list;
        } else {
            String list = "Relationen: ";
            for (int i = 0; i < edges.size(); i++) {
                list += SyndromObjectPrinter.edgePrintGerman(edges.get(i), verticesList.get(i)) + ". ";
            }
            return list;
        }
    }

    public Set<Pair<Vertex,Vertex>> getVertices() {
        Set<Pair<Vertex,Vertex>> set = new HashSet<>();
        for (int i = 0; i < startVertices.size(); i++) {
            set.add(new Pair<>(startVertices.get(i), endVertices.get(i)));
        }
        return set;
    }
}