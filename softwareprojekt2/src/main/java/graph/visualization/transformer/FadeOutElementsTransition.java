package graph.visualization.transformer;

import graph.graph.Syndrom;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;
import lombok.Getter;

public class FadeOutElementsTransition extends Transition {
    @Getter
    private double frac;


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
