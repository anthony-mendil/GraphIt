package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesDrawColorLogAction.
 */
@Data
public class EditVerticesDrawColorParam extends Param{
    /**
     * The set of vertices containing their old colors.
     */
    @Getter
    private Map<Vertex,Paint> oldVertices;
    /**
     * The set of vertices containing their new colors.
     */
    @Getter
    private Map<Vertex,Paint> newVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The selected vertices containing their old color.
     * @param pNewVertices The selected vertices containing their new color.
     */
    public EditVerticesDrawColorParam(Map<Vertex,Paint> pOldVertices, Map<Vertex,Paint> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
