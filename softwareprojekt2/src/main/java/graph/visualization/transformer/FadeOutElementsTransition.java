package graph.visualization.transformer;

import graph.graph.Syndrom;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class FadeOutElementsTransition extends Transition {
    public double frac;

    public FadeOutElementsTransition(){
        setCycleDuration(Duration.millis(2000));
        setInterpolator(Interpolator.LINEAR);

    }
    @Override
    protected void interpolate(double frac) {
        this.frac = frac;
        Syndrom.getInstance().getVv().repaint();
    }
}
