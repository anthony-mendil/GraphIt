package log_management.parameters.move;

import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject for the action MoveVerticesParam.
 */
public class MoveVerticesParam extends Param{
    /**
     * The set of vertices and their annotation.
     */
    @Getter
    private Map<Integer,String> vertices;
    /**
     * The old sphere, in which the vertices belonged to.
     */
    @Getter
    private Pair<Integer,String> oldSphere;
    /**
     * The new sphere-Id.
     */
    @Getter
    private int newSphere;
    /**
     * The old position.
     */
    @Getter
    private Point2D oldPoint;
    /**
     * The new position.
     */
    @Getter
    private Point2D newPoint;

    /**
     * Creates a paramterobject of its own class.
     * @param pVertices The selected vertices.
     * @param pOldSphere The old sphere.
     * @param pNewSphereId The new sphere.
     * @param pOldPoint The old point.
     * @param pNewPoint The new point.
     */
    public MoveVerticesParam(Map<Integer,String> pVertices, Pair<Integer,String> pOldSphere, int pNewSphereId, Point2D pOldPoint, Point2D pNewPoint) {
        this.vertices = pVertices;
        this.oldSphere = pOldSphere;
        this.newSphere = pNewSphereId;
        this.oldPoint = pOldPoint;
        this.newPoint = pNewPoint;

    }

    @Override
    public String convertToJson() {
        return null;
    }
}
