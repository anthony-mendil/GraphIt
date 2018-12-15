package actions;

import com.google.inject.Inject;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Syndrom;
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
     * the internal state of the layout
     */
    @NonNull
    private Layout layout;

    /**
     * the internal state of the graph
     */
    @NonNull
    private SyndromGraph<Vertex, Edge> graph;

    /**
     * the syndrom picksupport, containing all picked spheres/ edges/ vertices
     */
    @NonNull
    private SyndromPickSupport<Integer, String> pickSupport;

    /**
     * the visualisation viewer
     */
    @NonNull
    private VisualizationViewer<Vertex, Edge> visualizationViewer;

    /**
     * the syndrom to work with
     */
    @Inject
    private Syndrom syndrom;

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
