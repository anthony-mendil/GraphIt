package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.picking.ShapePickSupport;
import graph.graph.Sphaere;

/**
 * TODO
 */
public class SyndromPickSupport<V, E> extends ShapePickSupport {

    /**
     * TODO
     */
    @SuppressWarnings("unchecked")
    SyndromPickSupport(VisualizationServer<V, E> vv) {
        super(vv);
    }

    /**
     * TODO
     */
    public Sphaere getSphaere(VisualizationServer vv, double x, double y) {
        throw new UnsupportedOperationException();
    }
}
