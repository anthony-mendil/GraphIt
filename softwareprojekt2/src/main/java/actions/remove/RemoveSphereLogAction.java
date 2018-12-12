package actions.remove;

import actions.LogAction;
import graph.graph.Sphere;
import log_management.LogEntryName;
import log_management.parameters.add.AddSphereParam;
import log_management.parameters.remove.RemoveSphereParam;

/*
    linksclick Sphäre auswählen (markieren auch über Übersicht) -> GUI Button Delete(Sphäre)
    GUI Button Delete(Sphäre) -> Sphäre auswählen (markieren auch über Übersicht)
    Rechtsclick auf Sphäre -> Drop-Down-Menü -> Löschen
 */

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
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the sphere which is defined in pParam.
     *
     * @param pParam the AddSphereParam, containing the sphere to remove
     */
    public RemoveSphereLogAction(AddSphereParam pParam) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the passed sphere from the graph.
     *
     * @param pSphere the sphere to remove
     */
    public RemoveSphereLogAction(Sphere pSphere) {
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
