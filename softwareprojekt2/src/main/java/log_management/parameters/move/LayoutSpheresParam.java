package log_management.parameters.move;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

public class LayoutSpheresParam extends Param implements Serializable {
    /**
     * The vertices containing their old position.
     */
    @Getter
    private Map<Sphere, Point2D> oldSpheres;

    /**
     * The map from vertex ids to the new position.
     */
    @Getter
    private Map<Sphere, Point2D> newSpheres;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldPosition Map of vertices containing their old positions.
     * @param pNewPosition Map of vertices containing their new positions.
     */
    public LayoutSpheresParam(Map<Sphere, Point2D> pOldPosition, Map<Sphere, Point2D> pNewPosition) {
        this.oldSpheres = pOldPosition;
        this.newSpheres= pNewPosition;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The Spheres were automatically positioned";
        } else {
            return "Die Sphären wurden automatisch angeordnet";
        }
    }
}
