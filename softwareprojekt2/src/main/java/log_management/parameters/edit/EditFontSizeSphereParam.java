package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;


/**
 * Parameter object of the action EditFontSizeSphereLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditFontSizeSphereParam implements Param {
    /**
     * The sphere containing the annotation.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old size of the font.
     */
    @Getter
    private int oldFontSize;
    /**
     * The new size of the font.
     */
    @Getter
    private int newFontSize;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pSphere      The sphere.
     * @param pOldFontSize The old font size.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeSphereParam(Sphere pSphere, int pOldFontSize, int pNewFontSize) {
        this.sphere = pSphere;
        this.oldFontSize = pOldFontSize;
        this.newFontSize = pNewFontSize;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". "
                    + "Old font size: " + oldFontSize
                    + ", new font size: " + newFontSize + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". "
                    + "Alte Schriftgröße: " + oldFontSize
                    + ", neue Schriftgröße: " + newFontSize + ". ";
        }
        return information;
    }
}
