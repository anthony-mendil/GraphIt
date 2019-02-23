package graph.visualization.transformer.vertex;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.visualization.SyndromVisualisationViewer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a vertex into its stroke. The input vertex is left unchanged.
 * Its extracting the stroke of a vertex.
 *
 * @param <V> The vertex type.
 */
public class VertexStrokeTransformer<V> implements Transformer<V, Stroke> {
    private SyndromVisualisationViewer syndromVisualisationViewer;

    public VertexStrokeTransformer(SyndromVisualisationViewer syndromVisualisationViewer){
        this.syndromVisualisationViewer = syndromVisualisationViewer;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Stroke transform(V v) {
        PickedState<V> vertexPickedState = syndromVisualisationViewer.getPickedVertexState();
        if (vertexPickedState.isPicked(v)){
            return new BasicStroke(4);
        } else {
            return new BasicStroke(2);
        }
    }
}
