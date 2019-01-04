package actions;

import com.google.inject.Inject;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.picking.SyndromPickSupport;
import gui.Values;
import lombok.NonNull;

/**
 * Superclass of all actions that need access to the internal state of the graph, the visualization viewer and the
 * layout.
 */
public abstract class GraphAction extends Action {
    /**
     * The internal state of the layout.
     */
    private Layout layout;

    /**
     * The internal state of the graph.
     */
    private SyndromGraph<Vertex, Edge> graph;

    /**
     * The visualisation viewer.
     */
    private VisualizationViewer<Vertex, Edge> visualizationViewer;

    /**
     * The syndrom to work with.
     */
    protected Syndrom syndrom = Syndrom.getInstance();

    protected Values values = Values.getInstance();

    /**
     * Sets layout, graph, pick support from the current syndrom presentation.
     */
    public GraphAction(){

    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
