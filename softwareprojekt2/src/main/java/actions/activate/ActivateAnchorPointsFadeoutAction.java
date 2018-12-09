package actions.activate;

import LogManagement.Parameters.Param;
import actions.LogAction;
import log_management.LogEntryName;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 */
public class ActivateAnchorPointsFadeoutAction extends LogAction {

   public ActivateAnchorPointsFadeoutAction(Param nParameters) {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT, nParameters);
    }

    /**
     * All existing anchor- points fadeout
     */
    @Override
    public void action() {
        throw new NotImplementedException();
    }

    /**
     *
     */
    @Override
    public void redo() {
        throw new NotImplementedException();
    }

    /**
     *
     */
    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
