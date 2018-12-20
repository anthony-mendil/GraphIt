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
 * Parameter object for the action MoveVerticesLogAction.
 */
@Data
public class MoveVerticesParam extends Param{
    /**
     * The set of old vertices and their position, which they were in.
     */
    @Getter
    private Map<Vertex,Point2D> oldVertices;
    /**
     * The set of new vertices and their position, which they will be in.
     */
    @Getter
    private Map<Vertex,Point2D> newVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The set of old vertices and their position.
     * @param pNewVertices The set of new vertices and their position.
     */
    public MoveVerticesParam(Map<Vertex,Point2D> pOldVertices, Map<Vertex,Point2D> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
