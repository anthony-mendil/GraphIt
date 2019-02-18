package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * Parameter object for action ActivateHighlightAction/DeactivateHighlightAction.
 */
@Data
public class ActivateDeactivateHighlightParam extends Param implements Serializable {
    /**
     * List of vertices, which will highlight/stop highlight.
     */
    @Getter
    private List<Vertex> vertices;
    /**
     * List of edges, which will highlight/stop highlight.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates an parameter object of its own class.
     *
     * @param vertices List of selected vertices.
     * @param edges   List of edges attached to the vertices.
     */
    public ActivateDeactivateHighlightParam(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    @Override
    public String prettyPrint() {
//        Language language = Values.getInstance().getGuiLanguage();
//        if (language == Language.ENGLISH) {
//            String list = "Relations:\n";
//            if (edges != null) {
//                for (int i = 0; i < edges.size(); i++) {
//                    list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i));
//                }
//            }
//            list += "Symptoms:\n";
//            if (vertices != null) {
//                for (int i = 0; i < vertices.size(); i++) {
//                    list += SyndromObjectPrinter.vertexPrintEnglish(vertices.get(i));
//                }
//            }
//            return list;
//        } else {
//            String list = "";
//            if (edges != null) {
//                list += "Relationen:\n";
//                for (int i = 0; i < edges.size(); i++) {
//                    list += SyndromObjectPrinter.edgePrintGerman(edges.get(i));
//                }
//            }
//            if (vertices != null) {
//                list += "Symptome:\n";
//                for (int i = 0; i < vertices.size(); i++) {
//                    list += SyndromObjectPrinter.vertexPrintGerman(vertices.get(i));
//                }
//            }
//            return list;
//        }
        return "";
    }
}
