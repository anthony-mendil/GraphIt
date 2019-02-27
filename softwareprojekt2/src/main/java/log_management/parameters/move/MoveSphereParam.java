package log_management.parameters.move;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;

/**
 * Parameter object of the action MoveSphereLogAction.
 */
@Data
public class MoveSphereParam implements Param {
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

    public static final String Y_IS = " y = ";

    /**
     * Creates a vertices object of its own class.
     *
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
                    + Y_IS + (int) oldPos.getY() + ", ";
            information += "new coordinates: x = "
                    + newPos.getX()
                    + Y_IS + (int) newPos.getY() + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". ";
            information += "Alte Koordinaten: x = "
                    + (int) oldPos.getX()
                    + Y_IS + (int) oldPos.getY() + ", ";
            information += "neue Koordinaten: x = "
                    + (int) newPos.getX()
                    + Y_IS + (int) newPos.getY() + ". ";
        }
        return information;
    }
}
