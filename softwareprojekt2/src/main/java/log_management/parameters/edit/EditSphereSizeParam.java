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
    /**
     * The old size of the sphere.
     */
    @Getter
    private Pair<Double,Double> oldSize;

    /**
     * The new size of the sphere.
     */
    @Getter
    private Pair<Double,Double> newSize;

    /**
     * Creates a vertices object of its own class.
     * @param pSphere The sphere containing its old size.
     * @param pOldSize The old size of the sphere.
     * @param pNewSize The new size of the sphere.
     */
    public EditSphereSizeParam(Sphere pSphere,Pair<Double,Double> pOldSize, Pair<Double,Double> pNewSize) {
        this.sphere = pSphere;
        this.oldSize = pOldSize;
        this.newSize = pNewSize;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere)
                    + " New width: " + newSize.getKey() + ", New height: " + newSize.getValue();
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere)
                    + " Neue Breite: " + newSize.getKey() + ", Neue Höhe: " + newSize.getValue();
        }
        return information;
    }
}
