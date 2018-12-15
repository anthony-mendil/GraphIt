package log_management.parameters.edit;

import graph.graph.Sphere;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 */
public class EditSphereSizeParam extends Param{
    /**
     * The sphere-Id to change its size.
     */
    @Getter
    private Sphere sphere;
    /**
     * The new size of the sphere.
     */
    @Getter
    private Pair<Double,Double> newSize;

    /**
     * Creates a parameter object of its own class.
     * @param pSphere The sphere containing its old size
     * @param pNewSize The new size of the sphere.
     */
    public EditSphereSizeParam(Sphere pSphere, Pair<Double,Double> pNewSize) {
        this.sphere = pSphere;
        this.newSize = pNewSize;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
