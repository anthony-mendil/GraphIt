package actions.deactivate;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.deactivate.DeactivateHighlightParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.util.Collection;

/**
 * Cancels the highlight-option of the selected vertices.
 */
public class DeactivateHighlightLogAction extends LogAction {

    /**
     * Constructor in case the user annuls all/several highlighted vertices.
     */
    public DeactivateHighlightLogAction(){
        super(LogEntryName.ACTIVATE_HIGHLIGHT);
    }

    /**
     * Constructor which will be used to realize the undo-method of ActivateHighlightLogAction.
     * @param parameters
     * The used parameters.
     */
    public DeactivateHighlightLogAction(DeactivateHighlightParam parameters) {
        super(LogEntryName.DEACTIVATE_HIGHLIGHT);
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
