package graph.visualization.transformer;

import graph.graph.Syndrom;
import gui.Values;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;
import lombok.Getter;

/**
 * The fadeout transition for the fade out action.
 */
public class FadeOutElementsTransition extends Transition {
    /**
     * The values instance.
     */
    private Values values = Values.getInstance();
    /**
     * Disables the button if false.
     */
    private boolean setDisabled = false;
    /**
     * The value to get the transparency.
     */
    @Getter
    private double value;

    /**
     * TODO
     */
    public FadeOutElementsTransition() {
        setCycleDuration(Duration.millis(2000));
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double value) {
        this.value = value;
        if (!setDisabled) {
            values.getAnimationFadeout().setDisable(true);
            setDisabled = true;
        } else if (value == 1.0) {
            values.getAnimationFadeout().setDisable(false);
        }
        Syndrom.getInstance().getVv().repaint();
    }
}
