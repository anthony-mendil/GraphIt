package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Sphere;
import graph.visualization.transformer.vertex.VertexFontColorTransformer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * SyndromVisualisationViewer containing in addition to VisualizationViewer a pickedSphereState
 * <p>
 * SONARQUBE: The class inherits from structures of the JUNG framework, so unfortunately we can not do anything about
 * the sonarqube issue : This class has 7 parents which is greater than 5 authorized.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SyndromVisualisationViewer<V, E> extends VisualizationViewer<V, E> {
    /**
     * the pickedSphereState, containing all picked Spheres
     */
    @Setter(AccessLevel.NONE)
    private transient PickedState<Sphere> pickedSphereState;
    /**
     * the vertex font transformer
     */
    private transient Transformer<V, Paint> vertexFontColorTransformer;

    /**
     * the syndrom visualisation viewer
     *
     * @param model         the visualisation model
     * @param preferredSize the size
     */
    public SyndromVisualisationViewer(VisualizationModel<V, E> model, Dimension preferredSize) {
        super(model, preferredSize);
        pickedSphereState = new MultiPickedState<>();
        vertexFontColorTransformer = new VertexFontColorTransformer<>();
    }
}
