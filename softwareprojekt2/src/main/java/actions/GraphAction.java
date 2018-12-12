package actions;

import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.picking.SyndromPickSupport;
import lombok.NonNull;

/**
 * superclass of all actions that need access to the internal state of the graph, the visualisation viewer and the
 * layout
 */
public abstract class GraphAction extends Action {
    /**
     * the internal state of the layout, at the time the action was initialized
     */
    @NonNull
    private Layout layout;

    /**
     * the internal state of the graph, at the time the action was initialized
     */
    @NonNull
    private SyndromGraph<Vertex, Edge> graph;

    /**
     *
     */
    @NonNull
    private SyndromPickSupport<Integer, String> pickSupport;


    /**
     * sets layout, graph, pickSupport from the current syndrom presentation
     */
    public GraphAction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
