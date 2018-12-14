package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;

/**
 * Parameterobject of the action EditSphereColorLogAction.
 */
public class EditSphereColorParam extends Param{
    /**
     * The sphere-Id to change its color.
     */
    @Getter
    private int sphereId;
    /**
     * The annotation of the sphere.
     */
    @Getter
    private String sphereAnnotation;
    /**
     * The old color of the sphere.
     */
    @Getter
    private Color oldColor;
    /**
     * The new color it should get.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a parameterobject of its own class.
     * @param pSphereId The sphere-Id to change the color of.
     * @param pSphereAnnotation The annotation of the sphere.
     * @param pOldColor The old color of the sphere.
     * @param pNewColor The new color of the sphere.
     */
    public EditSphereColorParam(int pSphereId, String pSphereAnnotation, Color pOldColor, Color pNewColor) {
        this.sphereId = pSphereId;
        this.sphereAnnotation = pSphereAnnotation;
        this.oldColor = pOldColor;
        this.newColor = pNewColor;
    }

}
