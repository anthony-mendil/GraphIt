package log_management.parameters.move;


import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for LayoutSphereGraphLogAction, changes the position of the vertices.
 */
@Data
public class LayoutVerticesParam implements Param {

    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Point2D> oldPositions;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices Map of vertices containing their old positions.
     */
    public LayoutVerticesParam(Map<Vertex, Point2D> pOldVertices) {
        oldVertices = new ArrayList<>();
        oldPositions = new ArrayList<>();

        pOldVertices.forEach((v, p) -> {
            oldVertices.add(v);
            oldPositions.add(p);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The symptoms were automatically positioned or the automatic positioning was undone.";
        } else {
            return "Die Symptome wurden automatisch angeordnet oder die automatische Anordnung wurde rückgängig gemacht.";
        }
    }

    public Map<Vertex, Point2D> getOldVerticesMap() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }
}
