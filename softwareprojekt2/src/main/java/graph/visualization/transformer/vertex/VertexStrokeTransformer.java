package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its stroke. The input vertex is left unchanged.
 * Its extracting the stroke of a vertex.
 *
 * @author Nina Unterberg
 *
 * @param <V> The vertex type.
 */
public class VertexStrokeTransformer<V> implements Transformer<V, Stroke> {
    /**
     * The SyndromVisualisationViewer needed to get the picked vertices.
     */
    private SyndromVisualisationViewer<Vertex, Edge> syndromVisualisationViewer;

    /**
     * Creates a new VertexStrokeTransformer and sets this SyndromVisualisationViewer.
     * @param syndromVisualisationViewer The SyndromVisualisationViewer that gets set as this SyndromVisualisationViewer.
     */
    public VertexStrokeTransformer(SyndromVisualisationViewer<Vertex, Edge> syndromVisualisationViewer) {
        this.syndromVisualisationViewer = syndromVisualisationViewer;
    }

    @Override
    public Stroke transform(V v) {
        PickedState<Vertex> vertexPickedState = syndromVisualisationViewer.getPickedVertexState();
        Vertex vertex = (Vertex) v;
        if (vertexPickedState.isPicked(vertex)) {
            return new BasicStroke(4);
        } else {
            return new BasicStroke(2);
        }
    }
}
