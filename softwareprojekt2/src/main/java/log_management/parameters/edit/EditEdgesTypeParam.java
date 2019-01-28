package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesTypeLogAction.
 */
@Data
public class EditEdgesTypeParam extends Param implements Serializable {
    /**
     * The set of all edges containing their old edge-type.
     */
    @Getter
    private Map<Edge,EdgeArrowType> edgesOldEdgeType;
    /**
     * The set of all edges containing their new edge-type.
     */
    @Getter
    private Map<Edge,EdgeArrowType> edgesNewEdgeType;

    /**
     * Creates a new vertices object of its own class.
     * @param pOldEdges The set of edges containing their old edge-types.
     * @param pNewEdges The set of edges containing their new edge-types.
     */
    public EditEdgesTypeParam(Map<Edge,EdgeArrowType> pOldEdges, Map<Edge,EdgeArrowType> pNewEdges) {
        this.edgesOldEdgeType = pOldEdges;
        this.edgesNewEdgeType = pNewEdges;
    }
    @Override
    public String toString() {
        /**
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOldEdgeType.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNewEdgeType.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "New arrow type: "
                        + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOldEdgeType.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNewEdgeType.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "Neue Pfeilspitze: "
                        + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.GERMAN) + "\n";
            }
        }
        return information;
         */
        return null;
    }

}
