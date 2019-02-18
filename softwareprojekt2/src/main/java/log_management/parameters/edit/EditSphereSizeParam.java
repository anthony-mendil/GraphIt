package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 */
@Data
public class EditSphereSizeParam extends Param implements Serializable {
    /**
     * The sphere which size should be changed.
     */
    @Getter
    private Sphere sphere;

    @Getter
    private boolean enlarge;

    /**
     * Creates a vertices object of its own class.
     * @param pSphere The sphere containing its old size.
     * @param pEnlarge For knowing which type of size change it is.
     */
    public EditSphereSizeParam(Sphere pSphere, boolean pEnlarge) {
        this.sphere = pSphere;
        this.enlarge = pEnlarge;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (enlarge) {
            if (language == Language.ENGLISH) {
                information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere)
                        + " New width: " + (sphere.getWidth() + 10) +", New height: " + (sphere.getHeight() + 10);
            } else {
                information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere)
                        + " Neue Breite: " + (sphere.getWidth() + 10) + ", Neue Höhe: " + (sphere.getHeight() + 10);
            }
            return information;
        }
        else {
            if (language == Language.ENGLISH) {
                information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere)
                        + " New width: " + (sphere.getWidth() - 10) +", New height: " + (sphere.getHeight() - 10);
            } else {
                information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere)
                        + " Neue Breite: " + (sphere.getWidth() - 10) + ", Neue Höhe: " + (sphere.getHeight() - 10);
            }
            return information;
        }
    }
}
