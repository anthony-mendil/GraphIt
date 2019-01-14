package actions.remove;

import actions.LogAction;
import actions.add.AddSphereLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import actions.LogEntryName;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveSphereParam;

import java.util.LinkedList;
import java.util.List;

/**
 * Removes a sphere from the syndrom graph.
 */
public class RemoveSphereLogAction extends LogAction {

    /**
     * Removes the passed sphere from the graph.
     * Gets the picked sphere through pick support.
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
        parameters = pParam;

    }

    @Override
    public void action() {
        if(parameters == null) {
            parameters = createParameter();
        }
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Sphere sp : pickedState.getPicked()) {
            graph.removeSphere(sp);
        }
        vv.repaint();
        DatabaseManager.addEntryDatabase(this);
    }

    @Override
    public void undo() {
        AddSphereLogAction addSphereLogAction = new AddSphereLogAction(createParameter());
        addSphereLogAction.action();
    }

    @Override
    public AddRemoveSphereParam createParameter() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        List<Sphere> spheres = new LinkedList<>(pickedState.getPicked());
        return new AddRemoveSphereParam(spheres);
    }
}
