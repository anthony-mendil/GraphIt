package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import actions.deactivate.DeactivateAnchorPointsFadeoutAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import gui.Values;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;

/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 */
public class ActivateAnchorPointsFadeoutAction extends LogAction {

    /**
     * Constructor in case all/several anchor-points shall fadeout. The action is applied to all picked edges/anchor
     * points.
     */
    public ActivateAnchorPointsFadeoutAction() {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

    /**
     * Fadeout all anchor points defined in ActivateAnchorPointsFadeoutParam. Also used to implement the undo-method of
     * DeactivateAnchorPointsFadeoutAction.
     *
     * @param pActivateAnchorPointsFadeoutParam The used parameter object containing the anchor points to fade out.
     */
    public ActivateAnchorPointsFadeoutAction(ActivateDeactivateAnchorPointsFadeoutParam pActivateAnchorPointsFadeoutParam) {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

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
        DeactivateAnchorPointsFadeoutAction deactivateAnchorPointsFadeoutAction = new DeactivateAnchorPointsFadeoutAction();
        deactivateAnchorPointsFadeoutAction.action();
    }


    public void createParameter() {
        return;
    }
}