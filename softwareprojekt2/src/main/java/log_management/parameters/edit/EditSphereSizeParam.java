package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
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
     * @param pSphere The sphere containing its old size.
     */
    public EditSphereSizeParam(Sphere pSphere, Pair<Double,Double> pOldSize, Pair<Double,Double> pNewSize) {
        this.sphere = pSphere;
        this.oldWidth = pOldSize.getKey();
        this.newWidth = pNewSize.getKey();
        this.oldHeight = pOldSize.getValue();
        this.newHeight = pNewSize.getValue();
    }

    @Override
    public String prettyPrint() {
        /*
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
            */
        return null;

    }

    public Pair<Double,Double> getOldSize(){
        return new Pair<>(oldWidth,oldHeight);
    }
    public Pair<Double,Double> getNewSize(){
        return new Pair<>(newWidth,newHeight);
    }
}
