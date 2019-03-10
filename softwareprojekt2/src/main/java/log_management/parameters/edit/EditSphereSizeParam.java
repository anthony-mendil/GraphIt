package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditSphereSizeParam implements Param {
    /**
     * The sphere which size should be changed.
     */
    @Getter
    private Sphere sphere;

    /**
     * The old width.
     */
    @Getter
    private Double oldWidth;
    /**
     * The new width.
     */
    @Getter
    private Double newWidth;
    /**
     * The old height.
     */
    @Getter
    private Double oldHeight;
    /**
     * The new height.
     */
    @Getter
    private Double newHeight;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pSphere  The sphere.
     * @param pOldSize The old size.
     * @param pNewSize The new size.
     */
    public EditSphereSizeParam(Sphere pSphere, Pair<Double, Double> pOldSize, Pair<Double, Double> pNewSize) {
        this.sphere = pSphere;
        this.oldWidth = pOldSize.getKey();
        this.newWidth = pNewSize.getKey();
        this.oldHeight = pOldSize.getValue();
        this.newHeight = pNewSize.getValue();
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere)
                    + ". Old size " + oldWidth
                    + ", new size: " + newWidth;
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere)
                    + ". Alte Größe: " + oldWidth
                    + ", neue Größe: " + newWidth;
        }
        return information;
    }

    /**
     * Gets the old size.
     *
     * @return The old size.
     */
    public Pair<Double, Double> getOldSize() {
        return new Pair<>(oldWidth, oldHeight);
    }

    /**
     * Gets the new size.
     *
     * @return The new size.
     */
    public Pair<Double, Double> getNewSize() {
        return new Pair<>(newWidth, newHeight);
    }
}
