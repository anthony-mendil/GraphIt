package actions.analyse;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.edge.EdgePaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexFillPaintTransformer;
import graph.visualization.transformer.vertex.VertexPaintAnalyseTransformer;
import javafx.util.Pair;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Cancels the highlight-option of the selected vertices.
 */
public class ShowAnalysisResultAction extends LogAction {
    /**
     * The list of vertices.
     */
    private ArrayList<Edge> edges;
    /**
     * The list of edges.
     */
    private ArrayList<Vertex> vertices;

    /**
     * Constructor in case the user annuls all/several highlighted vertices.
     */
    public ShowAnalysisResultAction(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
        super(LogEntryName.SHOW_HIGHLIGHTED);
        this.vertices = vertices;
        this.edges = edges;
    }


    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();

        vv.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(vertices));
        vv2.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(vertices));
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edges));
        vv2.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edges));
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edges));
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edges));
        vv.repaint();
        vv2.repaint();
    }

    /**
     * There is no undo operation for this action.
     */
    @Override
    public void undo() {
    }


    public void createParameter() {
    }
}