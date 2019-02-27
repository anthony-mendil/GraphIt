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

import java.util.ArrayList;
import java.util.List;

/**
 * Parameter object for the action AddEdgesLogAction/RemoveEdgesLogAction.
 */
@Data
public class AddRemoveEdgesParam implements Param {
    /**
     * The edges added/removed.
     */
    @Getter
    private List<Edge> edges;

    /**
     * The start vertices of the edges.
     */
    @Getter
    private List<Vertex> startVertices;

    /**
     * The end vertices of th edges.
     */
    @Getter
    private List<Vertex> endVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pEdges The edges.
     * @param pStart The start vertices of the edges.
     * @param pEnd The end vertices of the edges.
     */
    public AddRemoveEdgesParam(List<Edge> pEdges, List<Vertex> pStart, List<Vertex> pEnd) {
        this.edges = pEdges;
        this.startVertices = pStart;
        this.endVertices = pEnd;
    }

    @Override
    public String prettyPrint() {
        List<Pair<Vertex, Vertex>> verticesList = new ArrayList<>();
        for (int i = 0; i < startVertices.size(); i++) {
            verticesList.add(new Pair<>(startVertices.get(i), endVertices.get(i)));
        }

        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            StringBuilder list = new StringBuilder("Relations: ");
            for (int i = 0; i < edges.size(); i++) {
                list.append(SyndromObjectPrinter.edgePrintEnglish( verticesList.get(i))).append("; ");
            }
            return list.toString();
        } else {
            StringBuilder list = new StringBuilder("Relationen: ");
            for (int i = 0; i < edges.size(); i++) {
                list.append(SyndromObjectPrinter.edgePrintGerman(verticesList.get(i))).append("; ");
            }
            return list.toString();
        }
    }

}