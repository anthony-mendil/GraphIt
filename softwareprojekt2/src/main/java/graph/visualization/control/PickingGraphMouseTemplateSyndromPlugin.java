package graph.visualization.control;

import actions.ActionHistory;
import com.google.inject.Inject;
import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import graph.graph.Vertex;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Map;

/**
 * A graph mouse plugin that supports the visualisation of picking if the user selected the template function mode
 * and executes actions.
 */
public class PickingGraphMouseTemplateSyndromPlugin<V, E> extends PickingGraphMousePlugin implements MouseListener, MouseMotionListener {
    /**
     * the coordinate where the user pressed the mouse
     */
    private Point2D source;

    /**
     * the coordinates of the picked vertices in relation to their vertices
     */
    private Map<Vertex, Point2D> points;

    /**
     * the action history
     */
    @Inject
    ActionHistory history;

    /**
     * Creates a new picking graph mouse template syndrom plugin.
     */
    PickingGraphMouseTemplateSyndromPlugin() {
        this(InputEvent.BUTTON3_MASK, InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK);
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new picking graph mouse template syndrom plugin with a defined button mask.
     *
     * @param selectionModifiers      the selection modifiers defines the button mask
     * @param addToSelectionModifiers the add to selection modifiers defines the button mask for adding items to a
     *                                selection
     */
    private PickingGraphMouseTemplateSyndromPlugin(int selectionModifiers, int addToSelectionModifiers) {
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
}
