package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.*;

/**
 * Parameter object of the action EditSphereColorLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditSphereColorParam implements Param {
    /**
     * The sphere containing its old color.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old color of the sphere.
     */
    @Getter
    private Color oldColor;
    /**
     * The new color the sphere should get.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a vertices object of its own class.
     *
     * @param sphere    The sphere.
     * @param pOldColor The old color of the sphere.
     * @param pNewColor The new color of the sphere.
     */
    public EditSphereColorParam(Sphere sphere, Color pOldColor, Color pNewColor) {
        this.sphere = sphere;
        this.oldColor = pOldColor;
        this.newColor = pNewColor;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". "
                    + "Old color: " + ColorNameCreator.getInstance().getColorName(oldColor, language)
                    + ", new color: " + ColorNameCreator.getInstance().getColorName(newColor, language) + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". "
                    + "Alte Farbe: " + ColorNameCreator.getInstance().getColorName(oldColor, language)
                    + ", neue Farbe: " + ColorNameCreator.getInstance().getColorName(newColor, language) + ". ";
        }
        return information;
    }
}
