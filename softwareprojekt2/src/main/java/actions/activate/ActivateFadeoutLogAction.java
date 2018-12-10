package actions.activate;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateFadeoutParam;
import log_management.tables.Log;

import java.awt.geom.Point2D;
import java.util.Collection;

/**
 * The chosen vertices and all edges attached to them fadeout and will no longer be visible.
 */

public class ActivateFadeoutLogAction extends LogAction {
    /**
     * Constructor in the case only one vertex shall fadeout.
     * @param pPoint2D
     * The position of the Vertex.
     */
    public ActivateFadeoutLogAction(Point2D pPoint2D){
        super(LogEntryName.ACTIVATE_FADEOUT);
    }

    /**
     * Constructor in case several/all vertices shall fadeout.
     */
    public ActivateFadeoutLogAction(){
        super(LogEntryName.ACTIVATE_FADEOUT);
    }

    /**
     * Constructor which will be used to realize the undo-method of DeactivateFadeoutLogAction.
     * @param pActivateFadeoutParam
     */
    public ActivateFadeoutLogAction(ActivateFadeoutParam pActivateFadeoutParam){
        super(LogEntryName.ACTIVATE_FADEOUT);
    }

    /**
     * Chosen vertices and edges fadeout and adds the log to the database.
     */
    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    /**
     * Undoes the fadeout of the chosen vertices and edges.
     */
    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }
}