package actions.remove;

import actions.LogAction;
import graph.graph.Sphere;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveSphereParam;

/**
 * Removes a sphere from the syndrom-graph.
 */
public class RemoveSphereLogAction extends LogAction {
    /**
     * the sphere which should be removed
     */
    private Sphere sphere;

    /**
     * Removes the passed sphere from the graph.
     * Gets the picked sphere though picksupport
     *
     */
    public RemoveSphereLogAction() {
        super(LogEntryName.REMOVE_SPHERE);
        throw new UnsupportedOperationException();
    }
    /**
     * Removes the sphere which is defined in pParam. Also used to implement the undo-method of
     * AddSphereLogAction.
     *
     * @param pParam the RemoveSphereParam, containing the sphere to remove
     */
    public RemoveSphereLogAction(AddRemoveSphereParam pParam) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new UnsupportedOperationException();
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
