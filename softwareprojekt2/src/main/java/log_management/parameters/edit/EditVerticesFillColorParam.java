package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFillColorLogAction.
 */
public class EditVerticesFillColorParam extends Param{
    /**
     * The set of vertices containing their old colors.
     */
    @Getter
    private Map<Vertex,Color> oldVertices;
    /**
     * The set of vertices containing their new colors.
     */
    @Getter
    private Map<Vertex,Color> newVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The selected vertices containing their old color.
     * @param pNewVertices The selected vertices containing their new color.
     */
    public EditVerticesFillColorParam(Map<Vertex,Color> pOldVertices, Map<Vertex,Color> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
