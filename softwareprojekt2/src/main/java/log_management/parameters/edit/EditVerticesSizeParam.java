package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 */
public class EditVerticesSizeParam extends Param{
    /**
     * The sphere containing its old size
     */
    @Getter
    private Map<Vertex,Integer> oldVertices;
    /**
     * The sphere containing its new size
     */
    @Getter
    private Map<Vertex,Integer> newVertices;


    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The vertices containing its old size.
     * @param pNewVertices The vertices containing its new size.
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
