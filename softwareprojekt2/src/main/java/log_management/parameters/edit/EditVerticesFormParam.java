package log_management.parameters.edit;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFormLogAction.
 */
public class EditVerticesFormParam extends Param{
    /**
     * The set of vertices containing their old shapes/ annotation.
     */
    @Getter
    List<Vertex> vertices;
    /**
     * The new shape of the vertices.
     */
    @Getter
    private Shape newShape;

    /**
     * Creates a parameter object of its own class.
     * @param pVertices The vertices containing their old shape/ annotation.
     * @param pNewShape The new shape.
     */
    public EditVerticesFormParam( List<Vertex> pVertices, Shape pNewShape) {
        this.vertices = pVertices;
        this.newShape = pNewShape;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
