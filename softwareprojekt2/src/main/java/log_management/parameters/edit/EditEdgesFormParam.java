package log_management.parameters.edit;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditEdgesFormParam extends Param implements Serializable {

    private List<Edge> edge;
    private List<Vertex> sourceVertex;
    private List<Vertex> targetVertex;
    private List<Stroke> oldStroke;
    private List<Stroke> newStroke;

    public EditEdgesFormParam(List<Edge> edge, List<Vertex> sourceVertex, List<Vertex> targetVertex, List<Stroke> oldStroke, List<Stroke> newStroke) {
        this.edge = edge;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
        this.oldStroke = oldStroke;
        this.newStroke = newStroke;
    }
}
