package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFormLogAction.
 */
@Data
public class EditVerticesFormParam extends Param implements Serializable {
    /**
     * The set of vertices containing their old shapes/annotation.
     */
    @Getter
    Map<Vertex,Shape> oldVertices;
    /**
     * The set of vertices containing their old shapes/annotation.
     */
    @Getter
    Map<Vertex,Shape> newVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The vertices containing their old shape/annotation.
     * @param pNewVertices The vertices containing their new shape/annotation.
     */
    public EditVerticesFormParam(Map<Vertex,Shape> pOldVertices, Map<Vertex,Shape> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
