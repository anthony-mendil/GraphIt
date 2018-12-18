package log_management.parameters.edit;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesColorLogAction.
 */
public class EditVerticesColorParam extends Param{
    /**
     * The set of vertices containing their old colors.
     */
    @Getter
    private List<Vertex> pVertices;
    /**
     * The new color.
     */
    @Getter
    private Color newColor;

    /**
     * Creates a parameter object of its own class.
     * @param pVertices The selected vertices containing their old color.
     * @param pNewColor The new color.
     */
    public EditVerticesColorParam(List<Vertex> pVertices, Color pNewColor) {
        this.pVertices = pVertices;
        this.newColor = pNewColor;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
