package graph.graph;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.*;

public class Sphaere {
    private Color color;
    private double xCoor;
    private double yCoor;
    private double width;
    private double height;
    private String name;
    private Color fil;
    private final int id;


    /**
     * TODO
     */
    Sphaere(double xCoor, double yCoor, double width, double height, String name, int id){
        throw new UnsupportedOperationException();
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getxCoor() {
        return xCoor;
    }

    public void setxCoor(double xCoor) {
        this.xCoor = xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }

    public void setyCoor(double yCoor) {
        this.yCoor = yCoor;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getFil() {
        return fil;
    }

    public void setFil(Color fil) {
        this.fil = fil;
    }

    public int getId() {
        return id;
    }
}
