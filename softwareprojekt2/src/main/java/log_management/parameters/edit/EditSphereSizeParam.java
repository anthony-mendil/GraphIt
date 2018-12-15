package log_management.parameters.edit;

import graph.graph.Sphere;
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
    private Pair<Sphere,String> sphere;
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
     * @param pSphere The sphere-Id.
     * @param pOldSize The old size of the sphere.
     * @param pNewSize The new size of the sphere.
     */
    public EditSphereSizeParam(Pair<Sphere,String> pSphere, Pair<Double,Double> pOldSize, Pair<Double,Double> pNewSize) {
        this.sphere = pSphere;
        this.oldSize = pOldSize;
        this.newSize = pNewSize;

    }
}
