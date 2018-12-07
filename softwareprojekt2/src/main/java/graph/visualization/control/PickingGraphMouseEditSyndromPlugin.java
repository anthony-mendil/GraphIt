package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;

/**
 * TODO
 */
public class PickingGraphMouseEditSyndromPlugin<V, E> extends PickingGraphMousePlugin implements MouseListener, MouseMotionListener {

    private Point2D source;
    private Point2D[] points;


    /**
     * TODO
     */
    PickingGraphMouseEditSyndromPlugin() {
        this(InputEvent.BUTTON3_MASK, InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK | InputEvent.SHIFT_MASK | InputEvent.CTRL_MASK);
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param selectionModifiers TODO
     * @param addToSelectionModifiers TODO
     */
    private PickingGraphMouseEditSyndromPlugin(int selectionModifiers, int addToSelectionModifiers) {
        super(selectionModifiers, addToSelectionModifiers);
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param e TODO
     */
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param e TODO
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param e TODO
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException();
    }
}
