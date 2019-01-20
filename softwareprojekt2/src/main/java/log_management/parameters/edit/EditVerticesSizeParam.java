package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesSizeLogAction.
 */
@Data
public class EditVerticesSizeParam extends Param implements Serializable {
    /**
     * The set of vertices containing their old size.
     */
    @Getter
    private Map<Vertex,Integer> oldVertices;
    /**
     * The set of vertices containing their new size.
     */
    @Getter
    private Map<Vertex,Integer> newVertices;


    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The vertices containing their old size.
     * @param pNewVertices The vertices containing their new size.
     */
    public EditVerticesSizeParam(Map<Vertex,Integer> pOldVertices, Map<Vertex,Integer> pNewVertices ) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
