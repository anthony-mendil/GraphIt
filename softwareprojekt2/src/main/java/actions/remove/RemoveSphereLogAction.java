package actions.remove;

import actions.LogAction;
import actions.add.AddSphereLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
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
        parameters = pParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(parameters == null){
            PickedState<Sphere> pickedState = vv.getPickedSphereState();
            for (Sphere sp : pickedState.getPicked()) {
                graph.removeSphere(sp);
                createParameter(sp);
            }
        }else{
            graph.removeSphere(((AddRemoveSphereParam)parameters).getSphere());
        }
        vv.repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        AddSphereLogAction addSphereLogAction = new AddSphereLogAction((AddRemoveSphereParam)parameters);
        addSphereLogAction.action();
    }

    public void createParameter(Sphere sphere) {
        parameters = new AddRemoveSphereParam(sphere);
    }
}
