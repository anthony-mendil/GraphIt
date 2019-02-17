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
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
@Data
public class EditEdgesStrokeParam extends Param implements Serializable {
    /**
     * The set of edges containing their old stoke-type.
     */
    @Getter
    Map<Edge, StrokeType> edgesOld;
    /**
     * The set of edges containing their old stoke-type.
     */
    @Getter
    Map<Edge, StrokeType> edgesNew;

    /**
     * Creates a new vertices object of its own class.
     * @param pEdgesOld The map of all edges and their old info.
     * @param pEdgesNew The map of all edges and their new info.
     */
    public EditEdgesStrokeParam(Map<Edge,StrokeType> pEdgesOld, Map<Edge,StrokeType> pEdgesNew
                                ) {
        this.edgesOld = pEdgesOld;
        this.edgesNew = pEdgesNew;
    }

    @Override
    public String prettyPrint() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed:\n";
            for (Map.Entry<Edge,StrokeType> entry : edgesOld.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey(),vertPair);

                information += "New stroke type: "
                        + EnumNameCreator.strokeTypeTranslaotr(entry.getKey().getStroke(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge,StrokeType> entry : edgesOld.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey(),vertPair);

                information += "Neue Linienart: "
                        + EnumNameCreator.strokeTypeTranslaotr(entry.getKey().getStroke(), Language.GERMAN) + "\n";
            }
        }
        return information;
    }
}
