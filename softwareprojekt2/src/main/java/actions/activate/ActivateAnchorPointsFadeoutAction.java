package actions.activate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 */
public class ActivateAnchorPointsFadeoutAction extends LogAction {

    public ActivateAnchorPointsFadeoutAction() {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
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
    public void undo() {
        throw new NotImplementedException();
    }
}
