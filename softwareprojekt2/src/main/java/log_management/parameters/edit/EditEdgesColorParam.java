package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesColorLogAction.
 */
@Data
public class EditEdgesColorParam extends Param implements Serializable {
    /**
     * The list of edges containing their old color.
     */
    @Getter
    private Map<Edge,Color> edgesOld;
    /**
     * The list of edges containing their new color.
     */
    @Getter
    private Map<Edge,Color> edgesNew;

    /**
     * Creates an vertices object of its own class.
     * @param pEdgesOld The list of edges and their old color.
     * @param pEdgesNew The list of edges and their new color.
     */
    public EditEdgesColorParam(Map<Edge,Color> pEdgesOld, Map<Edge,Color> pEdgesNew) {
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
            for (Map.Entry<Edge, Color> entry : edgesOld.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey(),vertPair);


                information += "New Color: "
                        + ColorNameCreator.getInstance().getColorName(entry.getKey().getColor(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge, Color> entry : edgesOld.entrySet()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(entry.getKey());
                Pair<Vertex,Vertex> vertPair = new Pair<>(vertices.getFirst(),vertices.getSecond());
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey(),vertPair);


                information += "Neue Farbe: "
                        + ColorNameCreator.getInstance().getColorName(entry.getKey().getColor(), Language.GERMAN) +  "\n";
            }
        }
        return information;

    }
}
