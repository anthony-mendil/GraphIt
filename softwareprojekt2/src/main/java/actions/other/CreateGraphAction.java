package actions.other;

import actions.GraphAction;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;

import java.awt.*;

/**
 * Creates a new graph.
 *
 */
public class CreateGraphAction extends GraphAction {
    private String graphName;

    /**
     * Constructor in case the user creates a new graph.
     *
     * @param pGraphName The name of the graph.
     */
    public CreateGraphAction(String pGraphName) {
       super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        /*SyndromGraph g = new SyndromGraph<>();
        syndrom.setGraph(g);
        StaticLayout<Vertex, Edge> layout = new StaticLayout<>(g);
        Dimension size = syndrom.getValues().getDefaultLayoutSize();
        layout.setSize(size);
        syndrom.setLayout(layout);
        syndrom.setVisualisationViewer(syndrom.getVisualisationViewer().setLayout(layout));
        */throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
