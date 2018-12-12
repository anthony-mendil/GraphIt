package actions.activate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateFadeoutParam;
import log_management.parameters.deactivate.DeactivateFadeoutParam;

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
     * Fadeout all vertices/ edges defined in ActivateFadeoutParam.
     *
     * @param pActivateFadeoutParam The parameters containing all vertices/ edges to fadeout
     */
    public ActivateFadeoutLogAction(ActivateFadeoutParam pActivateFadeoutParam) {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Fadeout all vertices/ edges defined in DeactivateFadeoutParam.
     *
     * @param pDeactivateFadeoutParam The parameters containing all vertices/ edges to fadeout
     */
    public ActivateFadeoutLogAction(DeactivateFadeoutParam pDeactivateFadeoutParam) {
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