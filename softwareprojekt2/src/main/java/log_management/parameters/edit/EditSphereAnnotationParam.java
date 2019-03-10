package log_management.parameters.edit;

import graph.graph.Sphere;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

/**
 * Parameter object of the action EditSphereAnnotationLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditSphereAnnotationParam implements Param {
    /**
     * The sphere containing its old annotation.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old annotation of the sphere.
     */
    @Getter
    private String oldAnnotationEnglish;

    /**
     * The new annotation of the sphere.
     */
    @Getter
    private String newAnnotationEnglish;
    /**
     * The old annotation of the sphere.
     */
    @Getter
    private String oldAnnotationGerman;

    /**
     * The new annotation of the sphere.
     */
    @Getter
    private String newAnnotationGerman;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pSphere               The sphere.
     * @param pOldAnnotationEnglish The old annotation.
     * @param pNewAnnotationEnglish The new annotation.
     * @param pOldAnnotationGerman  The old annotation.
     * @param pNewAnnotationGerman  The new annotation.
     */
    public EditSphereAnnotationParam(Sphere pSphere, String pOldAnnotationEnglish, String pNewAnnotationEnglish, String pOldAnnotationGerman, String pNewAnnotationGerman) {
        this.sphere = pSphere;
        this.oldAnnotationEnglish = pOldAnnotationEnglish;
        this.newAnnotationEnglish = pNewAnnotationEnglish;
        this.oldAnnotationGerman = pOldAnnotationGerman;
        this.newAnnotationGerman = pNewAnnotationGerman;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". "
                    + "Old annotation(English): " + oldAnnotationEnglish
                    + ", new annotation(English): " + newAnnotationEnglish
                    + ". Old annotation(German): " + oldAnnotationGerman
                    + ", new annotation(German): " + newAnnotationGerman + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". "
                    + "Alte Beschriftung(Englisch): " + oldAnnotationEnglish
                    + ", neue Beschriftung(Englisch): " + newAnnotationEnglish
                    + ". Alte Beschriftung(Deutsch): " + oldAnnotationGerman
                    + ", neue Beschriftung(Deutsch): " + newAnnotationGerman + ". ";
        }
        return information;
    }
}
