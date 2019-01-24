package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.List;
/**
 * Parameter object of the action ActivateFadeoutLogAction/DeactivateFadeoutLogAction.
 */
@Data
public class ActivateDeactivateFadeoutParam extends Param{
    /**
     * List of vertices to activate/cancel the highlight-option.
     */
    @Getter
    private List<Vertex> vertices;
    /**
     * List of edges to activate/cancel the highlight-option.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates an parameter object of its own class.
     * @param pVertices The list of vertices to work on.
     * @param pEdges The list of edges to work on.
     */
    public ActivateDeactivateFadeoutParam(List<Vertex> pVertices, List<Edge> pEdges) {
        this.vertices = pVertices;
        this.edges = pEdges;
    }

    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            String list = "Relations:\n";
            if (edges != null) {
                for (int i = 0; i < edges.size(); i++) {
                    list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i));
                }
            }
            list += "Symptoms:\n";
            if (vertices != null) {
                for (int i = 0; i < vertices.size(); i++) {
                    list += SyndromObjectPrinter.vertexPrintEnglish(vertices.get(i));
                }
            }
            return list;
        } else {
            String list = "";
            if (edges != null) {
                list += "Relationen:\n";
                for (int i = 0; i < edges.size(); i++) {
                    list += SyndromObjectPrinter.edgePrintGerman(edges.get(i));
                }
            }
            if (vertices != null) {
                list += "Symptome:\n";
                for (int i = 0; i < vertices.size(); i++) {
                    list += SyndromObjectPrinter.vertexPrintGerman(vertices.get(i));
                }
            }
            return list;
        }
    }
}
