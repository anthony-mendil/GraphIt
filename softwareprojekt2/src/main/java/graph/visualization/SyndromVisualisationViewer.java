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

@EqualsAndHashCode(callSuper = true)
@Data
public class SyndromVisualisationViewer<V,E> extends VisualizationViewer<V,E> {
    @Setter(AccessLevel.NONE)
    private PickedState<Sphere> pickedSphereState;
    private Transformer<V,Paint> vertexFontColorTransformer;

    public SyndromVisualisationViewer(VisualizationModel<V, E> model, Dimension preferredSize) {
        super(model, preferredSize);
        pickedSphereState = new MultiPickedState<>();
        vertexFontColorTransformer = new VertexFontColorTransformer();
    }

    public void setPickedSphereState(PickedState<Sphere> pickedSphereState){
        this.pickedSphereState = pickedSphereState;
        if(pickEventListener == null) {
            pickEventListener = e -> repaint();
        }
        pickedSphereState.addItemListener(pickEventListener);
    }
}
