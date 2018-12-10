package actions.activate;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateHighlightParam;

import java.awt.geom.Point2D;
import java.util.Collection;

/**
 * Highlights the chosen vertices and the attached edges.
 */
public class ActivateHighlightLogAction extends LogAction {
    /**
     * Constructor in case only one vertex shall be highlighted.
     * @param pPoint2D
     * The position of the vertex.
     */
    public ActivateHighlightLogAction(Point2D pPoint2D){
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }
    /**
     * Constructor in case several/all vertices shall be highlighted.
     */
    public ActivateHighlightLogAction(){
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }
    /**
     * Constructor which will be used to realize the undo-method of DeactivateHighlightLogAction.
     * @param pActivateHighlightParam
     */
    public ActivateHighlightLogAction(ActivateHighlightParam pActivateHighlightParam) {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }

    /**
     * Highlights chosen vertices and edges and adds the log to the database.
     */
    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    /**
     * Undoes the performed highlight-action.
     */
    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}
