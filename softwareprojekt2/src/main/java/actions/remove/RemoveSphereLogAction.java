package actions.remove;

import actions.LogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import actions.LogEntryName;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.add_remove.AddRemoveSphereParam;

/**
 * Removes a sphere from the syndrom graph.
 */
public class RemoveSphereLogAction extends LogAction {

    /**
     * Removes the passed sphere from the graph.
     * Gets the picked sphere though pick support.
     *
     */
    public RemoveSphereLogAction() {
        super(LogEntryName.REMOVE_SPHERE);
    }
    /**
     * Removes the sphere which is defined in pParam. Also used to implement the undo-method of
     * AddSphereLogAction.
     *
     * @param pParam The RemoveSphereParam object, containing the sphere to remove.
     */
    public RemoveSphereLogAction(AddRemoveSphereParam pParam) {
        super(LogEntryName.REMOVE_SPHERE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Sphere sp: pickedState.getPicked()) {
            graph.removeSphere(sp);
        }
        vv.repaint();
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
