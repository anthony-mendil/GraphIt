package graph.visualization.control;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ErrorMessagesTransition extends Transition {
    private final Pane hBox;

    public ErrorMessagesTransition(Pane hBox){
        setCycleDuration(Duration.millis(3000));
        setInterpolator(Interpolator.LINEAR);
        this.hBox = hBox;
    }
    @Override
    protected void interpolate(double frac) {
        hBox.setOpacity(1 - frac);
    }
}
