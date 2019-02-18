package log_management.parameters.edit;

import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
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
    public String prettyPrint() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed:\n";
            for (Map.Entry<Edge,EdgeArrowType> entry : edgesOldEdgeType.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey(),vertPair);

                information += "New arrow type: "
                        + EnumNameCreator.edgeArrowTypeTranslator(entry.getKey().getArrowType(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge,EdgeArrowType> entry : edgesOldEdgeType.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey(),vertPair);

                information += "Neue Pfeilspitze: "
                        + EnumNameCreator.edgeArrowTypeTranslator(entry.getKey().getArrowType(), Language.GERMAN) + "\n";
            }
        }
        return information;
    }

}
