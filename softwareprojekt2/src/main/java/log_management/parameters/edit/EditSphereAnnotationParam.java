package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditSphereLogAction.
 */
public class EditSphereAnnotationParam extends Param{
    /**
     * The selected sphere-Id.
     */
    @Getter
    private int sphereId;
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
     * Creates a parameterobject of its own class.
     * @param pSphereId The sphere-Id.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditSphereAnnotationParam(int pSphereId, String pOldAnnotation, String pNewAnnotation) {
        this.sphereId = pSphereId;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }

}
