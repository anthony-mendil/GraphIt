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
public class EditSphereAnnotationParam extends Param{
    /**
     * The sphere containing its old annotation.
     */
    @Getter
    private Sphere sphere;
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
     * @param pSphere The sphere containing its old annotation.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditSphereAnnotationParam(Sphere pSphere,String pOldAnnotation, String pNewAnnotation) {
        this.sphere = pSphere;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Sphere:\n" + SyndromObjectPrinter.spherePrintEnglish(sphere)
                    + " New annotation: " + newAnnotation;
        } else {
            information += "Sphäre:\n" + SyndromObjectPrinter.spherePrintGerman(sphere)
                    + " Neue Beschriftung: " + newAnnotation;
        }
        return information;
    }
}
