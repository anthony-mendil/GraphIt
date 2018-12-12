package actions.deactivate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateHighlightParam;

/**
 * Cancels the highlight-option of the selected vertices.
 */
public class DeactivateHighlightLogAction extends LogAction {

    /**
     * Constructor in case the user annuls all/several highlighted vertices.
     */
    public DeactivateHighlightLogAction() {
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }

    /**
     * Constructor which will be used to realize the undo-method of ActivateHighlightLogAction.
     *
     * @param parameters The used parameters.
     */
    public DeactivateHighlightLogAction(DeactivateHighlightParam parameters) {
        super(LogEntryName.DEACTIVATE_HIGHLIGHT);
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
