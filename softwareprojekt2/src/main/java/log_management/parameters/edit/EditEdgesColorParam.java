package log_management.parameters.edit;

import LogManagement.Parameters.Param;
import graph.graph.Edge;
import graph.graph.Vertex;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditEdgesColorParam extends Param implements Serializable {

    private List<Edge> edgeList;
    private List<Vertex> sourceVertexList;
    private List<Vertex> targetVertexList;
    private List<Color> oldColorList;
    private List<Color> newColorList;

    public EditEdgesColorParam(List<Edge> edgeList, List<Vertex> sourceVertexList, List<Vertex> targetVertexList, List<Color> oldColorList, List<Color> newColorList) {
        this.edgeList = edgeList;
        this.sourceVertexList = sourceVertexList;
        this.targetVertexList = targetVertexList;
        this.oldColorList = oldColorList;
        this.newColorList = newColorList;
    }
}
