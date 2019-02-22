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

/**
 * Parameter object of the action MoveSphereLogAction.
 */
@Data
public class MoveSphereParam extends Param {
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
        this.oldPos = pOldPos;
        this.newPos = pNewPos;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". ";
            information += "Old coordinates: x = "
                    + oldPos.getX()
                    + " y = " + (int) oldPos.getY() + ", ";
            information += "new coordinates: x = "
                    + newPos.getX()
                    + " y = " + (int) newPos.getY() + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". ";
            information += "Alte Koordinaten: x = "
                    + (int) oldPos.getX()
                    + " y = " + (int) oldPos.getY() + ", ";
            information += "neue Koordinaten: x = "
                    + (int) newPos.getX()
                    + " y = " +  (int) newPos.getY() + ". ";
        }
        return information;
    }
}
