package graph.visualization.control;

import actions.ActionHistory;
import com.google.inject.Inject;
import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;

import java.awt.event.MouseEvent;

/**
 * A graph mouse plugin that supports the visualisation of picking if the user selected the edit function mode.
 */
public class AnimatedPickingGraphMouseEditSyndromPlugin extends AnimatedPickingGraphMousePlugin {
    /**
     * the action history
     */
    @Inject
    ActionHistory history;
    /**
     * Creates a new animated picking graph mouse edit syndrom plugin.
     */
    public AnimatedPickingGraphMouseEditSyndromPlugin() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new animated picking graph mouse edit syndrom plugin with a defined button mask.
     *
     * @param selectionModifiers the selection modifiers define the button mask
     */
    public AnimatedPickingGraphMouseEditSyndromPlugin(int selectionModifiers) {
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
