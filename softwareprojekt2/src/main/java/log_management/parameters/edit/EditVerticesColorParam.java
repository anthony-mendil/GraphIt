package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject of the action EditVerticesColorLogAction.
 */
public class EditVerticesColorParam extends Param{
    /**
     * The set of vertices and their old colors.
     */
    @Getter
    private Map<Vertex, Color> oldVerticesColor;
    /**
     * The new color.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a parameterobject of its own class.
     * @param pVerticesColor The selected vertices and their old color.
     * @param pNewColor The new color.
     */
    public EditVerticesColorParam(Map<Vertex, Color> pVerticesColor, Color pNewColor) {
        this.oldVerticesColor = pVerticesColor;
        this.newColor = pNewColor;
    }

}
