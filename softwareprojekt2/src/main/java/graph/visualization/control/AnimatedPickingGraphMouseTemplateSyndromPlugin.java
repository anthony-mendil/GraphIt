package graph.visualization.control;

import actions.ActionHistory;
import com.google.inject.Inject;
import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;

import java.awt.event.MouseEvent;

/**
 * A graph mouse plugin that supports the visualisation of picking if the user selected the template function mode.
 */
public class AnimatedPickingGraphMouseTemplateSyndromPlugin extends AnimatedPickingGraphMousePlugin {
    /**
     * the action history
     */
    @Inject
    ActionHistory history;
    /**
     * Creates a new animated picking graph mouse template syndrom plugin.
     */
    public AnimatedPickingGraphMouseTemplateSyndromPlugin() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new animated picking graph mouse template syndrom plugin with a defined button mask.
     *
     * @param selectionModifiers the selection modifiers define the button mask
     */
    public AnimatedPickingGraphMouseTemplateSyndromPlugin(int selectionModifiers) {
        super(selectionModifiers);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException();
    }
}
