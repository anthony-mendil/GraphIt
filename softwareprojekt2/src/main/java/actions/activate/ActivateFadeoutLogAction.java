package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.EdgeIsVisiblePredicate;
import graph.algorithmen.predicates.VertexIsVisiblePredicate;
import graph.graph.Edge;
import graph.graph.FadeType;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.FadeOutElementsTransition;
import graph.visualization.transformer.edge.EdgeFadeoutPaintTransformer;
import graph.visualization.transformer.vertex.VertexFadeoutPaintTransformer;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * The chosen vertices and all edges attached to them fadeout and will no longer be visible.
 */

public class ActivateFadeoutLogAction extends LogAction {
    /**
     * Constructor
     *
     */
    public ActivateFadeoutLogAction() {
        super(LogEntryName.ACTIVATE_FADEOUT);
    }

    /**
     * Fadeout all vertices/edges defined in ActivateFadeoutParam. Also used to implement the undo-method of
     * DeactivateFadeoutLogAction.
     *
     * @param pActivateDeactivateFadeoutParam The parameter object containing all vertices/edges to fadeout
     */
    public ActivateFadeoutLogAction(ActivateDeactivateFadeoutParam pActivateDeactivateFadeoutParam) {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }



    /**
     * Chosen vertices and edges fadeout and adds the log to the database.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        RenderContext<Vertex, Edge> rc = vv.getRenderContext();

        final FadeOutElementsTransition transition = new FadeOutElementsTransition();

        Transformer<Vertex, Paint> oldTransformerFill = rc.getVertexFillPaintTransformer();
        Transformer<Vertex, Paint> oldTransformerDraw = rc.getVertexDrawPaintTransformer();
        Transformer<Vertex, Paint> oldTransformerFont = vv.getVertexFontColorTransformer();
        Transformer<Edge, Paint> oldEdgeTransformer = rc.getEdgeDrawPaintTransformer();
        Transformer<Edge, Paint> oldEdgeArrowTransformer = rc.getArrowFillPaintTransformer();

        FadeType fadeType = FadeType.ACTIVATE;

        VertexFadeoutPaintTransformer<Vertex> vertexFadeoutPaintTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerFill, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexVertexDrawFadeoutPaintTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerDraw, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexFontColorFadeOutTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerFont, fadeType);
        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutPaintTransformer = new EdgeFadeoutPaintTransformer<>(transition, oldEdgeTransformer, fadeType);
        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutArrowTransformer = new EdgeFadeoutPaintTransformer<>(transition, oldEdgeArrowTransformer, fadeType);

        rc.setVertexFillPaintTransformer(vertexFadeoutPaintTransformer);
        rc.setVertexDrawPaintTransformer(vertexVertexDrawFadeoutPaintTransformer);
        vv.setVertexFontColorTransformer(vertexFontColorFadeOutTransformer);
        rc.setEdgeDrawPaintTransformer(edgeFadeoutPaintTransformer);
        rc.setArrowFillPaintTransformer(edgeFadeoutArrowTransformer);

        transition.setOnFinished(
                event -> {
                   rc.setVertexIncludePredicate(new VertexIsVisiblePredicate<>());
                    vv2.getRenderContext().setVertexIncludePredicate(new VertexIsVisiblePredicate<>());
                    rc.setEdgeIncludePredicate(new EdgeIsVisiblePredicate<>());
                    vv2.getRenderContext().setEdgeIncludePredicate(new EdgeIsVisiblePredicate<>());
                    rc.setVertexFillPaintTransformer(oldTransformerFill);
                    rc.setVertexDrawPaintTransformer(oldTransformerDraw);
                    vv.setVertexFontColorTransformer(oldTransformerFont);
                    rc.setEdgeDrawPaintTransformer(oldEdgeTransformer);
                    rc.setArrowFillPaintTransformer(oldEdgeArrowTransformer);
                    vv.repaint();
                    vv2.repaint();
                }
        );
        transition.play();
    }

    /**
     * Undoes the fadeout of the chosen vertices and edges.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}