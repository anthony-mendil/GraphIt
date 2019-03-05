package graph.visualization.transformer;

import graph.graph.FadeType;
import graph.graph.Syndrom;
import gui.Values;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;
import lombok.Getter;

/**
 * the fadeout transition for the fade out action
 */
public class FadeOutElementsTransition extends Transition {
    /**
     * a values instance
     */
    private Values values = Values.getInstance();
    /**
     * disables the button if false
     */
    private boolean setDisabled = false;
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
        if (!setDisabled){
            values.getAnimationFadeout().setDisable(true);
            setDisabled = true;
        } else if (value == 1.0){
            values.getAnimationFadeout().setDisable(false);
        }
        Syndrom.getInstance().getVv().repaint();
    }
}
