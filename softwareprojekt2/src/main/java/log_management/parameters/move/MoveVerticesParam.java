package log_management.parameters.move;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for the action MoveVerticesParam.
 */
@Data
public class MoveVerticesParam extends Param{
    /**
     * The set of vertices containing its old position.
     */
    @Getter
    private List<Vertex>  vertices;
    /**
     * the difference between the x-coordinate where the user pressed the mouse and the point where the user released the mouse
     */
    @Getter
    private double dx;
    /**
     *  the difference between the y-coordinate where the user pressed the mouse and the point where the user
     *  released the mouse
     */
    @Getter
    private double dy;

    /**
     * Creates a parameter object of its own class.
     * @param pVertices The selected vertices.
     * @param pDx the difference between the x-coordinate where the user pressed the mouse and the point where the user released the mouse
     * @param pDy the difference between the y-coordinate where the user pressed the mouse and the point where the user
     *  released the mouse
     */
    public MoveVerticesParam(List<Vertex> pVertices, Double pDx, Double pDy) {
        this.vertices = pVertices;
        this.dx = pDx;
        this.dy = pDy;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
