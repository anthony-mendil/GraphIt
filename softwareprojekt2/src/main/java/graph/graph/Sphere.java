package graph.graph;

import config.Constants;
import lombok.Data;
import lombok.NonNull;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@Data
public class Sphere {
    @NonNull
    private final int id;
    @NonNull
    private Color color;
    @NonNull
    private double xCoor;
    @NonNull
    private double yCoor;
    @NonNull
    private double width;
    @NonNull
    private double height;
    @NonNull
    private String name;
    @NonNull
    private Color fill;
    private LinkedList<Vertex> vertices;

    public Sphere(double xCoor, double yCoor, int id) {
        this(xCoor, yCoor, id, Constants.getDefaultColorSphere(), Constants.getWidthSphere(), Constants.getHeightSphere(),
                Constants.getDefaultFillColorSphere(), Constants.getDefaultNameSphere());
    }

    public Sphere(double xCoor, double yCoor, int id, Color color, double width, double height, Color fill, String name) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.id = id;
        this.color = color;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.name = name;
    }
}
