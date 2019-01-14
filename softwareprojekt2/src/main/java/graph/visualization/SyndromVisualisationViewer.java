package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Sphere;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@EqualsAndHashCode(callSuper = true)
@Data
public class SyndromVisualisationViewer<V,E> extends VisualizationViewer<V,E> {
    @Setter(AccessLevel.NONE)
    private PickedState<Sphere> pickedSphereState;

    public SyndromVisualisationViewer(VisualizationModel<V, E> model, Dimension preferredSize) {
        super(model, preferredSize);
        pickedSphereState = new MultiPickedState<>();
    }

    public void setPickedSphereState(PickedState<Sphere> pickedSphereState){
        this.pickedSphereState = pickedSphereState;
        if(pickEventListener == null) {
            pickEventListener = new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    repaint();
                }
            };
        }
        pickedSphereState.addItemListener(pickEventListener);
    }
}
