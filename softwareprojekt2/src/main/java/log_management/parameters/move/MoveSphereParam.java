package log_management.parameters.move;

import graph.graph.Sphere;
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
 * Parameter object of the action MoveSphereLogAction.
 */
@Data
public class MoveSphereParam extends Param implements Serializable {
    /**
     * The sphere containing its old position.
     */
    @Getter
    private Map<Sphere,Point2D> oldSphere;
    /**
     * The sphere containing its new position.
     */
    @Getter
    private Map<Sphere,Point2D> newSphere;

    /**
     * Creates a vertices object of its own class.
     * @param pOldSphere The sphere containing its old position.
     * @param pNewSphere The sphere containing its new position.
     */
    public MoveSphereParam(Map<Sphere,Point2D> pOldSphere, Map<Sphere,Point2D> pNewSphere) {
        this.oldSphere = pNewSphere;
        this.newSphere = pOldSphere;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Spheres moved:\n";
            for (Map.Entry<Sphere, Point2D> entry : oldSphere.entrySet()) {
                information += "Sphere : " + SyndromObjectPrinter.spherePrintEnglish(entry.getKey());
                information += "New Coordinates: x = "
                        + newSphere.get(entry.getKey()).getX()
                        + " y = " + newSphere.get(entry.getKey()).getY() + "\n";
            }
        } else {
            information += "Bewegte Sphären:\n";
            for (Map.Entry<Sphere, Point2D> entry : oldSphere.entrySet()) {
                information += "Sphäre : " + SyndromObjectPrinter.spherePrintGerman(entry.getKey());
                information += "Neue Koordinaten: x = "
                        + newSphere.get(entry.getKey()).getX()
                        + " y = " + newSphere.get(entry.getKey()).getY() + "\n";
            }
        }
        return information;
    }
}
