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

    /**
     * The vertices and their old position.
     */
    @Getter
    private Map<Vertex,Point2D> oldVertices;


    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldPosition Map of vertices containing their old positions.
     */
    public LayoutSpheresParam(Map<Sphere,Pair<Pair<Double,Double>,Point2D>> pOldPosition, Map<Vertex,Point2D> pOldVertices) {
        List<Sphere> pSpheres = new ArrayList<>();
        List<Double> pFirst = new ArrayList<>();
        List<Double> pSecond = new ArrayList<>();
        List<Point2D> pPositions = new ArrayList<>();

        pOldPosition.forEach((s, p) -> {
            pSpheres.add(s);
            pFirst.add(p.getKey().getKey());
            pSecond.add(p.getKey().getValue());
            pPositions.add(p.getValue());
                });

        this.spheres = pSpheres;
        this.first = pFirst;
        this.second = pSecond;
        this.positions = pPositions;
        this.oldVertices = pOldVertices;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The Spheres were automatically positioned";
        } else {
            return "Die Sphären wurden automatisch angeordnet";
        }
    }

    public Map<Sphere,Pair<Pair<Double,Double>,Point2D>> getOldPosition() {
        Map<Sphere,Pair<Pair<Double,Double>,Point2D>> oldPosition = new HashMap<>();
        for (int i = 0; i < spheres.size(); i++) {
            oldPosition.put(spheres.get(i), new Pair<>(new Pair<>(first.get(i), second.get(i)), positions.get(i)));
        }
        return oldPosition;
    }
}
