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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for the action MoveVerticesLogAction.
 */
@Data
public class MoveVerticesParam extends Param {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Point2D> oldPositions;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<Point2D> newPositions;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The set of old vertices and their position.
     * @param pNewVertices The set of new vertices and their position.
     */
    public MoveVerticesParam(Map<Vertex,Point2D> pOldVertices, Map<Vertex,Point2D> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldPositions = new ArrayList<>();
        newVertices = new ArrayList<>();
        newPositions = new ArrayList<>();

        pOldVertices.forEach((v, p) -> {
            oldVertices.add(v);
            oldPositions.add(p);
        });
        pNewVertices.forEach((v, p) -> {
            newVertices.add(v);
            newPositions.add(p);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptoms: ";
                information += SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i)) + ". ";
                information += "Old coordinates: x = "
                        + (int) oldPositions.get(i).getX()
                        + " y = " + (int) oldPositions.get(i).getY() + ", ";
                information += ", new Coordinates: x = "
                        + (int) newPositions.get(i).getX()
                        + " y = " + (int) newPositions.get(i).getY() + ". ";
            }
        } else {
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptome: ";
                information += SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i)) + ". ";
                information += "Alte Koordinaten: x = "
                        + (int) oldPositions.get(i).getX()
                        + " y = " + (int) oldPositions.get(i).getY();
                information += ", neue Koordinaten: x = "
                        + (int) newPositions.get(i).getX()
                        + " y = " + (int) newPositions.get(i).getY() + ". ";
            }
        }
        return information;
    }

    public Map<Vertex,Point2D> getOldVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }

    public Map<Vertex,Point2D> getNewVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newPositions.get(i));
        }
        return map;
    }
}
