package log_management.parameters.move;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static log_management.parameters.move.MoveSphereParam.Y_IS;

/**
 * Parameter object for the action MoveVerticesLogAction.
 */
@Data
public class MoveVerticesParam implements Param {
    private List<Vertex> oldVertices;

    @Getter
    private List<Point2D> oldPositions;

    private List<Vertex> newVertices;

    @Getter
    private List<Point2D> newPositions;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The set of old vertices and their position.
     * @param pNewVertices The set of new vertices and their position.
     */
    public MoveVerticesParam(Map<Vertex, Point2D> pOldVertices, Map<Vertex, Point2D> pNewVertices) {
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
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append("Symptoms: ");
                information.append(SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))).append(". ");
                information.append("Old coordinates: x = ").append((int) oldPositions.get(i).getX()).append(Y_IS).append((int) oldPositions.get(i).getY()).append(", ");
                information.append(", new Coordinates: x = ").append((int) newPositions.get(i).getX()).append(Y_IS).append((int) newPositions.get(i).getY()).append(". ");
            }
        } else {
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append("Symptome: ");
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ");
                information.append("Alte Koordinaten: x = ").append((int) oldPositions.get(i).getX()).append(Y_IS).append((int) oldPositions.get(i).getY());
                information.append(", neue Koordinaten: x = ").append((int) newPositions.get(i).getX()).append(Y_IS).append((int) newPositions.get(i).getY()).append(". ");
            }
        }
        return information.toString();
    }

    public Map<Vertex, Point2D> getOldVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }

    public Map<Vertex, Point2D> getNewVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newPositions.get(i));
        }
        return map;
    }
}
