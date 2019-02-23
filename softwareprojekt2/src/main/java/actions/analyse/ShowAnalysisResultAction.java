package actions.analyse;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.edge.EdgePaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexFillPaintTransformer;
import graph.visualization.transformer.vertex.VertexPaintAnalyseTransformer;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;


/**
 * Cancels the highlight-option of the selected vertices.
 */
public class ShowHighlighted extends LogAction {

    /**
     * Constructor in case the user annuls all/several highlighted vertices.
     */
    public ShowHighlighted(List<Edge>) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }


    @Override
    public void action() {
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(verticesAnalyse));
        vv2.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(verticesAnalyse));
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv2.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
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
