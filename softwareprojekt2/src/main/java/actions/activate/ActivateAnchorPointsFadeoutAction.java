package actions.activate;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import gui.Values;

/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 * @author Clement Phung, Nina Unterberg
 */
public class ActivateAnchorPointsFadeoutAction extends GraphAction {


    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv.getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv2.getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        Values.getInstance().setShowAnchor(false);
        vv.repaint();
        vv2.repaint();
    }

    @Override
    public void undo() {
        // no undo operation for this action.
    }
}
