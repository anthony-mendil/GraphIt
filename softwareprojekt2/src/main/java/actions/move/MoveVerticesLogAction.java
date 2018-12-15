package actions.move;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.move.MoveVerticesParam;

import java.util.Collection;


/**
 * Moves vertices from one to another position. A vertex can not be positioned where another vertex is already located
 * and it must me positioned within a sphere.
 */
public class MoveVerticesLogAction extends LogAction {
    /**
     * the vertices with should be moved.
     */
    Collection<Vertex> vertices;

    /**
     * Moves all vertices, which are defined in pParam from one to another position
     *
     * @param pParam the MoveVerticesParam contains all vertices which should be removed.
     */
    public MoveVerticesLogAction(MoveVerticesParam pParam) {
        super(LogEntryName.MOVE_VERTICES);
        throw new UnsupportedOperationException();
    }


    /**
     * Moves all vertices according to the difference.
     *
     * @param dx       the difference between the x-coordinate from the point where the user pressed the mouse and the
     *                 point where the user released the mouse
     * @param dy       the difference between the y-coordinate from the point where the user pressed the mouse and the
     *                 point where the user released the mouse
     * @param vertices the collection of vertices to move
     */
    public MoveVerticesLogAction(double dx, double dy, Collection<Vertex> vertices) {
        super(LogEntryName.MOVE_VERTICES);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
