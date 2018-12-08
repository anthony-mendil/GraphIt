package graph.graph;

import config.Constants;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.collections15.map.HashedMap;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Map;

@Data
public class Vertex {
    @NonNull
    private final int id;
    @NonNull
    private Color color;
    @NonNull
    private double xCoor;
    @NonNull
    private double yCoor;
    @NonNull
    private Shape shape;
    @NonNull
    private String name;
    @NonNull
    private Color fill;
    private Map<ScopePoint, Point2D>  vertixArrowReinforced = null;
    private Map<ScopePoint, Point2D> vertixArrowNeutral = null;
    private Map<ScopePoint, Point2D> vertixArrowExtenuating = null;

    public Vertex(double xCoor, double yCoor, int id) {
        this(xCoor, yCoor, id, Constants.getDefaultColorSphere(), Constants.getDefaultShapeVertex(),
                Constants.getDefaultFillColorVertex(), Constants.getDefaultNameVertex());
    }

    public Vertex(double xCoor, double yCoor, int id, Color color, Shape shape, Color fill, String name) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.id = id;
        this.color = color;
        this.shape = shape;
        this.fill = fill;
        this.name = name;
    }
}
