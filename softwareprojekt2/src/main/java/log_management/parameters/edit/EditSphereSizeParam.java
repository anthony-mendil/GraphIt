package log_management.parameters.edit;

import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditSphereSizeLogAction.
 */
public class EditSphereSizeParam extends Param{
    /**
     * The sphere-Id to change its size.
     */
    @Getter
    private int sphereId;
    /**
     * The annotation of the sphere.
     */
    @Getter
    private String sphereAnnotation;
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
     * Creates a paramterobject of its own class.
     * @param pSphereId The sphere-Id.
     * @param pSphereAnnotation The annotation of the sphere.
     * @param pOldSize The old size of the sphere.
     * @param pNewSize The new size of the sphere.
     */
    public EditSphereSizeParam(int pSphereId, String pSphereAnnotation, Pair<Double,Double> pOldSize, Pair<Double,Double> pNewSize) {
        this.sphereId = pSphereId;
        this.sphereAnnotation = pSphereAnnotation;
        this.oldSize = pOldSize;
        this.newSize = pNewSize;

    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
