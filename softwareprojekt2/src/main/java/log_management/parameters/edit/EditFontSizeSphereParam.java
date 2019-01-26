package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Syndrom;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.Map;


/**
 * Parameter object of the action EditFontSizeSphereLogAction.
 */
@Data
public class EditFontSizeSphereParam extends Param{
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
     * @param pSphere The sphere containing the old annotation.
     * @param pOldFontSize The old font size.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeSphereParam(Sphere pSphere, int pOldFontSize, int pNewFontSize) {
        this.sphere = pSphere;
        this.oldFontSize = pOldFontSize;
        this.newFontSize = pNewFontSize;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere:\n" + SyndromObjectPrinter.spherePrintEnglish(sphere)
            + " New font size: " + newFontSize;
        } else {
            information += "Sphäre:\n" + SyndromObjectPrinter.spherePrintGerman(sphere)
                    + " Neue Schriftgröße: " + newFontSize;
        }
        return information;
    }
}
