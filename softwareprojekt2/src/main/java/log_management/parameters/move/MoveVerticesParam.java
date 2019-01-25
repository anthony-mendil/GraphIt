package log_management.parameters.move;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object for the action MoveVerticesLogAction.
 */
@Data
public class MoveVerticesParam extends Param implements Serializable {
    /**
     * The set of old vertices and their position, which they were in.
     */
    @Getter
    private Map<Vertex,Point2D> oldVertices;
    /**
     * The set of new vertices and their position, which they will be in.
     */
    @Getter
    private Map<Vertex,Point2D> newVertices;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The set of old vertices and their position.
     * @param pNewVertices The set of new vertices and their position.
     */
    public MoveVerticesParam(Map<Vertex,Point2D> pOldVertices, Map<Vertex,Point2D> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms moved:\n";
            for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintEnglish(entry.getKey());
                information += "New Coordinates: x = "
                        + newVertices.get(entry.getKey()).getX()
                        + " y = " + newVertices.get(entry.getKey()).getY() + "\n";
            }
        } else {
            information += "Bewegte Symptome:\n";
            for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintGerman(entry.getKey());
                information += "Neue Koordinaten: x = "
                        + newVertices.get(entry.getKey()).getX()
                        + " y = " + newVertices.get(entry.getKey()).getY() + "\n";
            }
        }
        return information;
    }
}
