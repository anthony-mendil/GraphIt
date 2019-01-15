package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;

import java.awt.geom.Point2D;

/**
 * Highlights the chosen vertices and the attached edges.
 */
public class ActivateHighlightLogAction extends LogAction {
    /**
     * Constructor in case only one vertex shall be highlighted.
     *
     * @param pPoint2D The position of the vertex.
     */
    public ActivateHighlightLogAction(Point2D pPoint2D) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case several/all vertices shall be highlighted.
     */
    public ActivateHighlightLogAction() {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
        throw new UnsupportedOperationException();
    }

    /**
     * Highlight all vertices passed by ActivateHighlightParam. Also used to implement the undo-method of
     * DeactivateHighlightLogAction.
     *
     * @param pActivateDeactivateHighlightParam The parameter object containing all vertices to highlight
     */
    public ActivateHighlightLogAction(ActivateDeactivateHighlightParam pActivateDeactivateHighlightParam) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
        throw new UnsupportedOperationException();
    }

    /**
     * Highlights chosen vertices and edges and adds the log to the database.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Undoes the performed highlight-action.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
