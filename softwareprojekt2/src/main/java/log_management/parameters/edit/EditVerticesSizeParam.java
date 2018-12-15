package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object of the action EditSphereSizeLogAction.
 */
public class EditVerticesSizeParam extends Param{
    /**
     * The sphere containing its old size
     */
    @Getter
    private List<Vertex>  pVertices;
    /**
     * The new size of the sphere.
     */
    @Getter
    private int newSize;

    /**
     * Creates a parameter object of its own class.
     * @param pVertices  The sphere containing its old size
     * @param pNewSize The new size of the sphere.
     */
    public EditVerticesSizeParam(List<Vertex> pVertices, int pNewSize) {
        this.pVertices = pVertices;
        this.newSize = pNewSize;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
