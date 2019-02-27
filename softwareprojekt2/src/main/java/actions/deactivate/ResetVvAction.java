package actions.deactivate;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.vertex.VertexFillPaintTransformer;


/**
 * Cancels the highlight-option of the selected vertices. And refreshes
 * all vertices and relations.
 */
public class ResetVvAction extends GraphAction {

    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexFillPaintTransformer<>());
        vv2.getRenderContext().setVertexFillPaintTransformer(new VertexFillPaintTransformer<>());
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgeFillPaintTransformer<>());
        vv2.getRenderContext().setEdgeDrawPaintTransformer(new EdgeFillPaintTransformer<>());
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv.repaint();
        vv2.repaint();
    }

    @Override
    public void undo() {
        // no undo operation for this action.
    }
}
