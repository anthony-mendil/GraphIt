package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditSphereColorLogAction.
 */
@Data
public class EditSphereColorParam extends Param{
    /**
     * The sphere containing its the old color.
     */
    @Getter
    private Sphere sphere;
    /**
     * The new color it should get.
     */
    @Getter
    private Color oldColor;
    /**
     * The new color it should get.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a parameter object of its own class.
     * @param sphere  The sphere containing its the old color.
     * @param pOldColor The old color of the sphere.
     * @param pNewColor The new color of the sphere.
     */
    public EditSphereColorParam(Sphere sphere,Color pOldColor, Color pNewColor) {
        this.sphere = sphere;
        this.oldColor = pOldColor;
        this.newColor = pNewColor;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
