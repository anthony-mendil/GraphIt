package log_management.parameters.edit;

import graph.graph.Edge;
import log_management.parameters.Param;
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
        /**
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOld.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNew.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "New Color: "
                        + ColorNameCreator.getInstance().getColorName(edge.getColor(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOld.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNew.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "Neue Farbe: "
                        + ColorNameCreator.getInstance().getColorName(edge.getColor(), Language.GERMAN) +  "\n";
            }
        }
        return information;
         */
        return null;
    }
}
