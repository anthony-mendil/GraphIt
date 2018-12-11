package actions.deactivate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateAnchorPointsFadeoutParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Makes the selected anchor-points visible again.
 */
public class DeactivateAnchorPointsFadeoutLogAction extends LogAction {

    /**
     * Constructor which realizes the undo-method of ActivateAnchorPointsLogAction.
     * @param pDeactivateAnchorPointsFadeoutParam
     * The used parameters.
     */
    public DeactivateAnchorPointsFadeoutLogAction(DeactivateAnchorPointsFadeoutParam pDeactivateAnchorPointsFadeoutParam){
        super(LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

    @Override
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
        throw new NotImplementedException();
    }
}
