package log_management.parameters.edit;

import graph.graph.Sphere;
import javafx.util.Pair;
import log_management.parameters.Param;
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
    private Sphere pSphere;
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
     * Creates a parameter object of its own class.
     * @param pSphere The sphere containing its old annotation.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditSphereAnnotationParam(Sphere pSphere,String pOldAnnotation, String pNewAnnotation) {
        this.pSphere = pSphere;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
