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
    private Layout layout;

    /**
     * the internal state of the graph
     */
    private SyndromGraph<Vertex, Edge> graph;

    /**
     * the visualisation viewer
     */
    private VisualizationViewer<Vertex, Edge> visualizationViewer;

    /**
     * the syndrom to work with
     */
    @Inject
    protected Syndrom syndrom;

    /**
     * sets layout, graph, pickSupport from the current syndrom presentation
     */
    public GraphAction(){

    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
