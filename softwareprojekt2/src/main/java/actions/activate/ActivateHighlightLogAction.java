package actions.activate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateHighlightParam;
import log_management.parameters.deactivate.DeactivateHighlightParam;

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
     * Highlight all vertices passed by ActivateHighlightParam
     *
     * @param pActivateHighlightParam The parameters containing all vertices to highlight
     */
    public ActivateHighlightLogAction(ActivateHighlightParam pActivateHighlightParam) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor which will be used to realize the undo-method of DeactivateHighlightLogAction.
     *
     * @param pDeactivateHighlightParam The parameters containing all vertices to highlight
     */
    public ActivateHighlightLogAction(DeactivateHighlightParam pDeactivateHighlightParam) {
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

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
