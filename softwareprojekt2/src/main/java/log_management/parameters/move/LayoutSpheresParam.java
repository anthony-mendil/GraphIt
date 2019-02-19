package log_management.parameters.move;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayoutSpheresParam extends Param implements Serializable {
    @Getter
    private List<Sphere> spheres;

    @Getter
    private List<Double> first;

    @Getter
    private List<Double> second;

    @Getter
    private List<Point2D> positions;

    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Point2D> oldPositions;


    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldPosition Map of vertices containing their old positions.
     */
    public LayoutSpheresParam(Map<Sphere,Pair<Pair<Double,Double>,Point2D>> pOldPosition, Map<Vertex,Point2D> pOldVertices) {
        spheres = new ArrayList<>();
        first = new ArrayList<>();
        second = new ArrayList<>();
        positions = new ArrayList<>();
        oldVertices = new ArrayList<>();
        oldPositions = new ArrayList<>();

        pOldPosition.forEach((s, p) -> {
            spheres.add(s);
            first.add(p.getKey().getKey());
            second.add(p.getKey().getValue());
            positions.add(p.getValue());
                });
        pOldVertices.forEach((v, p) -> {
            oldVertices.add(v);
            oldPositions.add(p);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The Spheres were automatically positioned or the automatic positioning was undone.";
        } else {
            return "Die Sphären wurden automatisch angeordnet oder die automatische Anordnung wurde rückgängig gemacht";
        }
    }

    public Map<Sphere,Pair<Pair<Double,Double>,Point2D>> getOldPosition() {
        Map<Sphere,Pair<Pair<Double,Double>,Point2D>> oldPosition = new HashMap<>();
        for (int i = 0; i < spheres.size(); i++) {
            oldPosition.put(spheres.get(i), new Pair<>(new Pair<>(first.get(i), second.get(i)), positions.get(i)));
        }
        return oldPosition;
    }

    public Map<Vertex, Point2D> getOldVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }
}
