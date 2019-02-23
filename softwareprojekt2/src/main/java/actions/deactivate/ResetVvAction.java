package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.vertex.VertexFillPaintTransformer;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;


/**
 * Cancels the highlight-option of the selected vertices.
 */
public class ResetVvAction extends LogAction {

    /**
     * Constructor in case the user annuls all/several highlighted vertices.
     */
    public ResetVvAction() {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }

    /**
     * Cancels the highlight-option of the vertices. Also used to implement the undo-method of
     * ActivateHighLightLogAction.
     *
     * @param pParam The vertices object that contains every vertices that is needed.
     */
    public ResetVvAction(ActivateDeactivateHighlightParam pParam) {
        super(LogEntryName.DEACTIVATE_HIGHLIGHT);
    }

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
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
