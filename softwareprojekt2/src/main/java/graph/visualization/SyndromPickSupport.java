package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import graph.graph.Sphere;

/**
 * TODO
 */
public class SyndromPickSupport<V, E> extends ShapePickSupport {

    /**
     * TODO
     *
     * @param vv TODO
     */
    @SuppressWarnings("unchecked")
    SyndromPickSupport(VisualizationServer<V, E> vv) {
        super(vv);
    }

    /**
     * TODO
     *
     * @param vv TODO
     * @param x TODO
     * @param y TODO
     * @return TODO
     */
    public Sphere getSphaere(VisualizationServer vv, double x, double y) {
        throw new UnsupportedOperationException();
    }
}
