package log_management.parameters.edit;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.awt.*;
import java.io.Serializable;

public class EditEdgeFormParam extends Param implements Serializable {

    private Edge edge;
    private Vertex sourceVertex;
    private Vertex targetVertex;
    private Stroke oldStroke;
    private Stroke newStroke;

    public EditEdgeFormParam(Edge edge, Vertex sourceVertex, Vertex targetVertex, Stroke oldStroke, Stroke newStroke) {
        this.edge = edge;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
        this.oldStroke = oldStroke;
        this.newStroke = newStroke;
    }
}
