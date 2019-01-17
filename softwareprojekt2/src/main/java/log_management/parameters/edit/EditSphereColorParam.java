package log_management.parameters.edit;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.*;

/**
 * Parameter object of the action EditSphereColorLogAction.
 */
@Data
public class EditSphereColorParam extends Param{
    /**
     * The sphere containing its old color.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old color of the sphere.
     */
    @Getter
    private Paint oldColor;
    /**
     * The new color the sphere should get.
     */
    @Getter
    private Paint newColor;

    /**
     * Creates a parameter object of its own class.
     * @param sphere  The sphere containing its old color.
     * @param pOldColor The old color of the sphere.
     * @param pNewColor The new color of the sphere.
     */
    public EditSphereColorParam(Sphere sphere,Paint pOldColor, Paint pNewColor) {
        this.sphere = sphere;
        this.oldColor = pOldColor;
        this.newColor = pNewColor;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
