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
 */
@Data
public class EditSphereAnnotationParam implements Param {
    /**
     * The sphere containing its old annotation.
     */
    @Getter
    private Sphere sphere;
    /**
     * The new sphere containing its new annotation.
     */
    @Getter
    private Sphere newSphere;
    /**
     * The old annotation of the sphere.
     */
    @Getter
    private String oldAnnotation;

    /**
     * The new annotation of the sphere.
     */
    @Getter
    private String newAnnotation;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pSphere        The sphere containing its old annotation.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditSphereAnnotationParam(Sphere pSphere, Sphere pNewSphere, String pOldAnnotation, String pNewAnnotation) {
        this.sphere = pSphere;
        this.newSphere = pNewSphere;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ". "
                    + "Old annotation: " + oldAnnotation
                    + ", new annotation: " + newAnnotation + ". ";
        } else {
            information += "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ". "
                    + "Alte Beschriftung: "
                    + ", neue Beschriftung: " + newAnnotation + ". ";
        }
        return information;
    }
}
