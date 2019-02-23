package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeHighlightTransformer;
import graph.visualization.transformer.vertex.VertexPaintHighlightTransformer;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;

/**
 * Highlights the chosen vertices and the attached edges.
 */
public class ActivateHighlightAction extends LogAction {

    /**
     * Constructor in case several/all vertices shall be highlighted.
     */
    public ActivateHighlightAction() {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }

    /**
     * Highlight all vertices passed by ActivateHighlightParam. Also used to implement the undo-method of
     * ResetVvAction.
     *
     * @param pActivateDeactivateHighlightParam The parameter object containing all vertices to highlight
     */
    public ActivateHighlightAction(ActivateDeactivateHighlightParam pActivateDeactivateHighlightParam) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
        throw new UnsupportedOperationException();
    }

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
     * Undoes the performed highlight-action.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
