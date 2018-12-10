package actions.remove;

import actions.LogAction;
import graph.graph.Sphere;
import log_management.LogEntryName;
import log_management.parameters.add.AddSphereParam;
import log_management.parameters.remove.RemoveSphereParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Removes a sphere from the syndrom-graph.
 */
public class RemoveSphereLogAction extends LogAction {
    /**
     * the sphere which should be removed
     */
    private Sphere sphere;


    /**
     * Removes the sphere which is defined in pParam.
     *
     * @param pParam the RemoveSphereParam, containing the sphere to remove
     */
    public RemoveSphereLogAction(RemoveSphereParam pParam) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new NotImplementedException();
    }

    /**
     * Removes the sphere which is defined in pParam.
     *
     * @param pParam the AddSphereParam, containing the sphere to remove
     */
    public RemoveSphereLogAction(AddSphereParam pParam) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new NotImplementedException();
    }

    /**
     * Removes the passed sphere from the graph.
     *
     * @param pSphere the sphere to remove
     */
    public RemoveSphereLogAction(Sphere pSphere) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new NotImplementedException();
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
