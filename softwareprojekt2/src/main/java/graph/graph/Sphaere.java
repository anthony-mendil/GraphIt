package graph.graph;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.awt.*;

@Data
public class Sphaere {

    @NonNull
    private Color color;

    private double xCoor;
    private double yCoor;
    private double width;
    private double height;

    @NonNull
    private String name;

    @NonNull
    private Color fil;
    private final int id;
}
