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
     * The sphere.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old position of the sphere.
     */
    @Getter
    private Point2D oldPos;
    /**
     * The new position of the sphere.
     */
    @Getter
    private Point2D newPos;

    /**
     * Creates a vertices object of its own class.
     * @param pSphere The sphere.
     * @param pOldPos The sphere containing its old position.
     * @param pNewPos The sphere containing its new position.
     */
    public MoveSphereParam(Sphere pSphere, Point2D pOldPos, Point2D pNewPos) {
        this.sphere = pSphere;
        this.oldPos = pNewPos;
        this.newPos = pOldPos;
    }
    @Override
    public String prettyPrint() {
        /*Language language = Values.getInstance().getGuiLanguage();
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
        return information;*/
        return null;
    }
}
