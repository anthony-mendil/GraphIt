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
import java.util.Map;

public class LayoutSpheresParam extends Param implements Serializable {
    /**
     * The spheres containing their old position and size.
     */
    @Getter
    private Map<Sphere,Pair<Pair<Double,Double>,Point2D>> oldPosition;
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
        this.oldPosition = pOldPosition;
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
}
