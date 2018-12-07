package graph.graph;

import java.awt.geom.Point2D;

/**
 *
 */
public class ScopeVertexEdge {
    private Point2D pos_1;
    private Point2D pos_2;
    private Point2D pos_3;
    private Point2D pos_4;

    /**
     *
     * @param scopePoint
     * @param point
     */
    ScopeVertexEdge(ScopePoint scopePoint, Point2D point){
        setPoint(scopePoint, point);
    }

    /**
     * TODO
     * @param scopePoint
     * @param point
     */
    public void setPoint(ScopePoint scopePoint, Point2D point){
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     * @param scopePoint
     * @return
     */
    public Point2D getPoint(ScopePoint scopePoint){
        throw new UnsupportedOperationException();
    }
}
