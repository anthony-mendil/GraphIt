package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;

import java.awt.geom.Point2D;

/**
 * The chosen vertices and all edges attached to them fadeout and will no longer be visible.
 */

public class ActivateFadeoutLogAction extends LogAction {
    /**
     * Constructor in the case only one vertex shall fadeout.
     *
     * @param pPoint2D The position of the Vertex.
     */
    public ActivateFadeoutLogAction(Point2D pPoint2D) {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Constructor in case several/all vertices shall fadeout.
     * The action is applied to all picked edges/ anchor points.
     */
    public ActivateFadeoutLogAction() {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Fadeout all vertices/edges defined in ActivateFadeoutParam. Also used to implement the undo-method of
     * DeactivateFadeoutLogAction.
     *
     * @param pActivateDeactivateFadeoutParam The parameter object containing all vertices/edges to fadeout
     */
    public ActivateFadeoutLogAction(ActivateDeactivateFadeoutParam pActivateDeactivateFadeoutParam) {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }



    /**
     * Chosen vertices and edges fadeout and adds the log to the database.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Undoes the fadeout of the chosen vertices and edges.
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