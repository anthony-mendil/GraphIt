package actions.other;

import actions.GraphAction;
import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

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
        SyndromGraph graph = new SyndromGraph<>();
        syndrom.setGraph(graph);
        Layout layout = new AggregateLayout(new StaticLayout(graph));
        syndrom.setLayout(layout);
        final VisualizationModel<Vertex, Edge> visualizationModel =
                new DefaultVisualizationModel(layout, values.getDefaultLayoutVVSize()); // TODO im A4 Format
        SyndromVisualisationViewer vv = new SyndromVisualisationViewer<>(visualizationModel, values
                .getDefaultLayoutSize());
        syndrom.setVisualisationViewer(vv);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
