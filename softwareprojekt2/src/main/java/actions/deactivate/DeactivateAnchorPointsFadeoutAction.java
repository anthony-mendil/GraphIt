package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import actions.activate.ActivateAnchorPointsFadeoutAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintAnchorTransformer;
import gui.Values;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;

/**
 * Makes the selected anchor-points visible again.
 */
public class DeactivateAnchorPointsFadeoutAction extends LogAction {

    /**
     * Makes all selected anchor-points visible again. Also used to implement the
     * undo-method of ActivateAnchorPointsFadeoutAction.
     *
     * @param pParam The vertices object that contains every vertices that is needed.
     */
    public DeactivateAnchorPointsFadeoutAction(ActivateDeactivateAnchorPointsFadeoutParam pParam) {
        super(LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

    public DeactivateAnchorPointsFadeoutAction() {
        super(LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

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
        ActivateAnchorPointsFadeoutAction activateAnchorPointsFadeoutAction = new ActivateAnchorPointsFadeoutAction();
        activateAnchorPointsFadeoutAction.action();
    }


    public void createParameter() {
        return;
    }
}
