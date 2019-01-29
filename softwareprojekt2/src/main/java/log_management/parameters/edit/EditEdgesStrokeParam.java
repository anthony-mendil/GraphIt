package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.StrokeType;
import log_management.parameters.Param;
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
    public String toString() {
        /**
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOld.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintEnglish(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNew.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "New stroke type: "
                        + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.ENGLISH) + "\n";
            }
        } else {
            information += "Geänderte Relationen:\n";
            for (Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edgesOld.entrySet()) {
                information += "Relation : " + SyndromObjectPrinter.edgePrintGerman(entry.getKey());

                Pair<Vertex, Vertex> endPoints = edgesNew.get(entry.getKey());
                Edge edge = Syndrom.getInstance().getGraph().findEdge(endPoints.getKey(), endPoints.getValue());

                information += "Neue Linienart: "
                        + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.GERMAN) + "\n";
            }
        }
        return information;
         */
        return null;
    }
}
