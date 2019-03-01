package actions.activate;

import actions.GraphAction;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
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
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * The chosen vertices and all edges attached to them fadeout and will no longer be visible.
 */

public class ActivateFadeoutAction extends GraphAction {

    /**
     * Chosen vertices and edges fadeout and adds the log to the database.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        RenderContext<Vertex, Edge> rc = vv.getRenderContext();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();

        final FadeOutElementsTransition transition = new FadeOutElementsTransition();

        Transformer<Vertex, Paint> oldTransformerDraw = rc.getVertexDrawPaintTransformer();
        Transformer<Vertex, Paint> oldTransformerFill = rc.getVertexFillPaintTransformer();
        Transformer<Edge, Paint> oldEdgeTransformer = rc.getEdgeDrawPaintTransformer();
        Transformer<Edge, Paint> oldEdgeArrowTransformer = rc.getArrowFillPaintTransformer();
        Transformer<Vertex, Paint> oldTransformerFont = vv.getVertexFontColorTransformer();

        FadeType fadeType = FadeType.ACTIVATE;

        Predicate<Context<Graph<Vertex, Edge>, Edge>> predicateEdge = new EdgeIsVisiblePredicate<>();
        Predicate<Context<Graph<Vertex, Edge>, Vertex>> predicateVertex = new VertexIsVisiblePredicate<>();


        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutPaintTransformer = new EdgeFadeoutPaintTransformer<>(transition, oldEdgeTransformer, fadeType);
        EdgeFadeoutPaintTransformer<Edge> edgeFadeoutArrowTransformer = new EdgeFadeoutPaintTransformer<>(transition, oldEdgeArrowTransformer, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexFadeoutPaintTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerFill, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexVertexDrawFadeoutPaintTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerDraw, fadeType);
        VertexFadeoutPaintTransformer<Vertex> vertexFontColorFadeOutTransformer = new VertexFadeoutPaintTransformer<>(transition, oldTransformerFont, fadeType);

        vv.setVertexFontColorTransformer(vertexFontColorFadeOutTransformer);
        rc.setVertexFillPaintTransformer(vertexFadeoutPaintTransformer);
        rc.setVertexDrawPaintTransformer(vertexVertexDrawFadeoutPaintTransformer);
        rc.setArrowFillPaintTransformer(edgeFadeoutArrowTransformer);
        rc.setEdgeDrawPaintTransformer(edgeFadeoutPaintTransformer);

        transition.setOnFinished(
                event -> {
                    rc.setVertexIncludePredicate(predicateVertex);
                    vv2.getRenderContext().setVertexIncludePredicate(predicateVertex);
                    rc.setEdgeIncludePredicate(predicateEdge);
                    vv2.getRenderContext().setEdgeIncludePredicate(predicateEdge);
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
        // no undo operation for this action.
    }
}