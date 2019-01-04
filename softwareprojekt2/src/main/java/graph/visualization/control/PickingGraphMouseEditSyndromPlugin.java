package graph.visualization.control;

import actions.ActionHistory;
import com.google.inject.Inject;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

/**
 * A graph mouse plugin that supports the visualization of picking if the user selected the edit function mode
 * and executes actions.
 */
public class PickingGraphMouseEditSyndromPlugin<V, E> extends PickingGraphMousePlugin implements MouseListener, MouseMotionListener {

    /**
     * The coordinate where the user pressed the mouse.
     */
    private Point2D source;

    /**
     * The coordinates of the picked vertices in relation to their vertices.
     */
    private Point2D[] points;

    /**
     * The action history.
     */
    /* Has to be set private or public */
    @Inject
    private ActionHistory history;

    /**
     * Creates a new picking graph mouse edit syndrom plugin.
     */
    public PickingGraphMouseEditSyndromPlugin() {
        this(InputEvent.BUTTON3_MASK, InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK);
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new animated picking graph mouse edit syndrom plugin with a defined button mask.
     *
     * @param selectionModifiers      The selection modifiers defines the button mask.
     * @param addToSelectionModifiers The add to selection modifiers defines the button mask for adding items to a
     *                                selection.
     */
    private PickingGraphMouseEditSyndromPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers, addToSelectionModifiers);
        throw new UnsupportedOperationException();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException();
    }
}
