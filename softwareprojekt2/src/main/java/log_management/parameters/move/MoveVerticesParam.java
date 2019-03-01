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
    /**
     * The old vertices.
     */
    @Getter
    private List<Vertex> oldVertices;
    /**
     * The old positions of the vertices.
     */
    @Getter
    private List<Point2D> oldPositions;
    /**
     * The new vertices.
     */
    @Getter
    private List<Vertex> newVertices;
    /**
     * The new positions of the vertices.
     */
    @Getter
    private List<Point2D> newPositions;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The old vertices and their old positions.
     * @param pNewVertices The new vertices and their new position.
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
            information.append("Symptoms: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))).append(". ");
                information.append("Old coordinates: x = ").append((int) oldPositions.get(i).getX()).append(Y_IS).append((int) oldPositions.get(i).getY()).append(", ");
                information.append(", new Coordinates: x = ").append((int) newPositions.get(i).getX()).append(Y_IS).append((int) newPositions.get(i).getY()).append("; ");
            }
        } else {
            information.append("Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ");
                information.append("Alte Koordinaten: x = ").append((int) oldPositions.get(i).getX()).append(Y_IS).append((int) oldPositions.get(i).getY());
                information.append(", neue Koordinaten: x = ").append((int) newPositions.get(i).getX()).append(Y_IS).append((int) newPositions.get(i).getY()).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * Gets the old vertices and their old positions.
     *
     * @return The old vertices and their old positions.
     */
    public Map<Vertex, Point2D> getOldVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }

    /**
     * Gets the new vertices and their new positions.
     *
     * @return The new vertices and their new positions.
     */
    public Map<Vertex, Point2D> getNewVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newPositions.get(i));
        }
        return map;
    }
}
