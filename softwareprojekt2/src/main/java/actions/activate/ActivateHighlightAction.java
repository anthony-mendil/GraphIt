package actions.activate;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeHighlightTransformer;
import graph.visualization.transformer.vertex.VertexPaintHighlightTransformer;

/**
 * Highlights the chosen vertices and the attached edges.
 */
public class ActivateHighlightAction extends GraphAction {

    /**
     * Constructor in case several/all vertices shall be highlighted.
     */
    public ActivateHighlightAction() {}

    /**
     * Highlights chosen vertices and edges and adds the log to the database.
     */
    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexPaintHighlightTransformer<>());
        vv2.getRenderContext().setVertexFillPaintTransformer(new VertexPaintHighlightTransformer<>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgeHighlightTransformer<>());
        vv2.getRenderContext().setEdgeDrawPaintTransformer(new EdgeHighlightTransformer<>());
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgeHighlightTransformer<>());
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgeHighlightTransformer<>());
        vv.repaint();
        vv2.repaint();
    }

    /**
     * No undo operation for this action.
     */
    @Override
    public void undo() {
        // no undo operation for this action.
    }
}
