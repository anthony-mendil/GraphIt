package graph.visualization.control;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Transition for fading out the alert bix
 */
public class ErrorMessagesTransition extends Transition {
    private final Pane hBox;

    /**
     * the transition action for fading out the alert messages/ box
     * @param hBox the box to fade out
     */
    public ErrorMessagesTransition(Pane hBox){
        setCycleDuration(Duration.millis(5000));
        setInterpolator(Interpolator.LINEAR);
        this.hBox = hBox;
    }
    @Override
    protected void interpolate(double value) {
        hBox.setOpacity(1 - value);
    }
}
