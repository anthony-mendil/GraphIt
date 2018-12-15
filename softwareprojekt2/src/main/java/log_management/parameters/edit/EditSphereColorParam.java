package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameterobject of the action EditSphereColorLogAction.
 */
public class EditSphereColorParam extends Param{
    /**
     * The sphere and the old color.
     */
    @Getter
    private Pair<Sphere,Pair<String,Color>> sphereOldColor;
    /**
     * The new color it should get.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a parameterobject of its own class.
     * @param pSphereOldColor The sphere and the old color
     * @param pNewColor The new color of the sphere.
     */
    public EditSphereColorParam(Pair<Sphere,Pair<String,Color>> pSphereOldColor, Color pNewColor) {
        this.sphereOldColor = pSphereOldColor;
        this.newColor = pNewColor;
    }

}
