package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.FadeType;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.FadeOutElementsTransition;
import graph.visualization.transformer.edge.EdgeFadeoutPaintTransformer;
import graph.visualization.transformer.vertex.VertexFadeoutPaintTransformer;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.TruePredicate;

import java.awt.*;


/**
 * Makes the vertices and attached edges, which used to be invisible, visible again.
 */
public class DeactivateFadeoutAction extends LogAction {
    /**
     * Constructor in case the user wants to make every vertex and edge visible again.
     */
    public DeactivateFadeoutAction() {
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

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

        FadeType fadeType = FadeType.DEACTIVATE;

        VertexFadeoutPaintTransformer<Vertex> vertexFadeoutPaintTransformer =
                new VertexFadeoutPaintTransformer<>(transition, oldTransformerFill, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexVertexDrawFadeoutPaintTransformer =
                new VertexFadeoutPaintTransformer<>(transition, oldTransformerDraw, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexFontColorFadeOutTransformer =
                new VertexFadeoutPaintTransformer<>(transition, oldTransformerFont, fadeType);
        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutPaintTransformer =
                new EdgeFadeoutPaintTransformer<>(transition, oldEdgeTransformer, fadeType);
        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutArrowTransformer =
                new EdgeFadeoutPaintTransformer<>(transition, oldEdgeArrowTransformer, fadeType);

        rc.setVertexFillPaintTransformer(vertexFadeoutPaintTransformer);
        rc.setVertexDrawPaintTransformer(vertexVertexDrawFadeoutPaintTransformer);
        vv.setVertexFontColorTransformer(vertexFontColorFadeOutTransformer);
        rc.setEdgeDrawPaintTransformer(edgeFadeoutPaintTransformer);
        rc.setArrowFillPaintTransformer(edgeFadeoutArrowTransformer);

        vv.getRenderContext().setVertexIncludePredicate(TruePredicate.getInstance());
        vv2.getRenderContext().setVertexIncludePredicate(TruePredicate.getInstance());
        vv.getRenderContext().setEdgeIncludePredicate(TruePredicate.getInstance());
        vv2.getRenderContext().setEdgeIncludePredicate(TruePredicate.getInstance());
        vv.repaint();
        vv2.repaint();

        transition.play();
    }

    @Override
    public void undo() {
        // no undo operation for this action.
    }

}
