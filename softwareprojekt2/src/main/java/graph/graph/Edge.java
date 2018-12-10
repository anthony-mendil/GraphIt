package graph.graph;

import config.Constants;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Map;

@Data
public class Edge {
    @NonNull
    private Color color;
    @NonNull
    private Stroke stroke;
    @NonNull
    private final int id;
    private EdgeArrowType arrowType;
    private boolean hasAnchor;
    private double anchorAngle;
    private int weight;

    public Edge(int id, EdgeArrowType arrowType) {
        this(Constants.getDefaultColorEdge(), Constants.getDefaultStrokeEdge(), id, arrowType, Constants.getDefaultEdgeWeight());
    }

    public Edge(Color color, Stroke stroke, int id, EdgeArrowType arrowType, int weight) {
        this.id = id;
        this.color = color;
        this.stroke = stroke;
        this.arrowType = arrowType;
        this.weight = weight;
    }
}
