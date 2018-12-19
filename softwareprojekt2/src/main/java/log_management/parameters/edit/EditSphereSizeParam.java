package log_management.parameters.edit;

import graph.graph.Sphere;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 */
@Data
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
    private Pair<Double,Double> oldSize;

    /**
     * The new size of the sphere.
     */
    @Getter
    private Pair<Double,Double> newSize;

    /**
     * Creates a parameter object of its own class.
     * @param pSphere The sphere containing its old size
     * @param pOldSize The old size of the sphere.
     * @param pNewSize The new size of the sphere.
     */
    public EditSphereSizeParam(Sphere pSphere,Pair<Double,Double> pOldSize, Pair<Double,Double> pNewSize) {
        this.sphere = pSphere;
        this.oldSize = pOldSize;
        this.newSize = pNewSize;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
