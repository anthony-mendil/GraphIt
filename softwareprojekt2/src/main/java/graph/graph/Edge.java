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
    private Map<ScopePoint, Point2D>  vertixArrowReinforced = null;
    private Map<ScopePoint, Point2D> vertixArrowNeutral = null;
    private Map<ScopePoint, Point2D> vertixArrowExtenuating = null;

    public Edge(int id) {
        this(Constants.getDefaultColorEdge(), Constants.getDefaultStrokeEdge(), id);
    }

    public Edge(Color color, Stroke stroke, int id) {
        this.id = id;
        this.color = color;
        this.stroke = stroke;
    }
}
