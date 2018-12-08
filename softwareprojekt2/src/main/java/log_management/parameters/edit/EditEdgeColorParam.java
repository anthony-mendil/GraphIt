package log_management.parameters.edit;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.awt.*;
import java.io.Serializable;

public class EditEdgeColorParam extends Param implements Serializable {

    private Edge edge;
    private Vertex sourceVertex;
    private Vertex targetVertex;
    private Color oldColor;
    private Color newColor;

    public EditEdgeColorParam(Edge edge, Vertex sourceVertex, Vertex targetVertex, Color oldColor, Color newColor) {
        this.edge = edge;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }
}
