package graph.visualization.transformer.sphere;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform a sphere into its stroke type. The input sphere is left unchanged.
 * Its extracting the stroke type of a sphere.
 *
 * @param <S> The sphere
 */
public class SphereStrokeTransformer<S> implements Transformer<S, Stroke> {
    /**
     * THe visualization viewer to work on.
     */
    private SyndromVisualisationViewer<Vertex, Edge> syndromVisualisationViewer;

    /**
     * Transforms the stroke of the sphere, if its picked, the stroke is bigger
     * The current visualisation viewer.
     *
     * @param syndromVisualisationViewer the visualisation viewer
     */
    public SphereStrokeTransformer(SyndromVisualisationViewer<Vertex, Edge> syndromVisualisationViewer) {
        this.syndromVisualisationViewer = syndromVisualisationViewer;
    }

    @Override
    public Stroke transform(S s) {
        Sphere sphere = (Sphere) s;
        PickedState<Sphere> spherePickedState = syndromVisualisationViewer.getPickedSphereState();
        if (spherePickedState.isPicked(sphere)) {
            return new BasicStroke(4);
        } else {
            return new BasicStroke(1);
        }
    }
}
