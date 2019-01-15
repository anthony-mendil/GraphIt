package graph.visualization.transformer.sphere;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.visualization.SyndromVisualisationViewer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

public class SphereStrokeTransformer<S> implements Transformer<S, Stroke> {
    private SyndromVisualisationViewer syndromVisualisationViewer;
    public SphereStrokeTransformer(SyndromVisualisationViewer syndromVisualisationViewer){
        this.syndromVisualisationViewer = syndromVisualisationViewer;
    }
    @Override
    @SuppressWarnings("unchecked")
    public Stroke transform(S s) {
        PickedState<S> spherePickedState = syndromVisualisationViewer.getPickedSphereState();

        if (spherePickedState.isPicked(s)){
            return new BasicStroke(4);
        } else {
            return new BasicStroke(1);
        }
    }
}
