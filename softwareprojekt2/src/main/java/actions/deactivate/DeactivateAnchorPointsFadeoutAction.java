package actions.deactivate;

import actions.GraphAction;
import actions.LogAction;
import actions.LogEntryName;
import actions.activate.ActivateAnchorPointsFadeoutAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintAnchorTransformer;
import gui.Values;
import log_management.tables.Graph;

/**
 * Makes the selected anchor-points visible again.
 */
public class DeactivateAnchorPointsFadeoutAction extends GraphAction {

    public DeactivateAnchorPointsFadeoutAction(){}

    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        syndrom.getVv().getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintAnchorTransformer<>());
        syndrom.getVv().getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintAnchorTransformer<>());
        syndrom.getVv2().getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintAnchorTransformer<>());
        syndrom.getVv2().getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintAnchorTransformer<>());
        Values.getInstance().setShowAnchor(true);
        vv.repaint();
        vv2.repaint();
    }

    @Override
    public void undo() {
        // no undo operation for this action.
    }

}
