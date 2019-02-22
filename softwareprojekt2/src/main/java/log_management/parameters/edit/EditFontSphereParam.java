package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;


/**
 * Parameter object for the action EditFontSphereLogAction.
 */
@Data
public class EditFontSphereParam extends Param {
    /**
     * The sphere containing its old annotation-font.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old font.
     */
    @Getter
    private String oldFont;
    /**
     * The new font.
     */
    @Getter
    private String newFont;

    /**
     * Creates a new vertices object of its own class.
     * @param sphere The sphere containing its old font.
     * @param pOldFont The old font.
     * @param pNewFont The new font.
     */
    public EditFontSphereParam(Sphere sphere, String pOldFont, String pNewFont) {
        this.sphere = sphere;
        this.oldFont = pOldFont;
        this.newFont = pNewFont;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". "
                    + "Old font: " + oldFont
                    + ", new font: " + newFont + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". "
                    + "Alte Schriftart: " + oldFont
                    + ", neue Schriftart: " + newFont + ". ";
        }
        return information;
    }
}