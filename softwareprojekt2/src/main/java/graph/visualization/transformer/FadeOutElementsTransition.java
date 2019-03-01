package graph.visualization.transformer;

import graph.graph.Syndrom;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;
import lombok.Getter;

/**
 * the fadeout transition for the fade out action
 */
public class FadeOutElementsTransition extends Transition {
    /**
     * the value to get the transparency
     */
    @Getter
    private double value;
    public FadeOutElementsTransition() {
        setCycleDuration(Duration.millis(2000));
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double value) {
        this.value = value;
        Syndrom.getInstance().getVv().repaint();
    }
}
