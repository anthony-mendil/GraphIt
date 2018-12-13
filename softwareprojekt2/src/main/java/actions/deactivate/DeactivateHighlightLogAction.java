package actions.deactivate;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;


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
     * Cancels the highlight-option of the vertices. Also used to implement the undo-method of
     * ActivateHighLightLogAction.
     *
     * @param pParam The used parameters.
     */
    public DeactivateHighlightLogAction(ActivateDeactivateHighlightParam pParam) {
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
