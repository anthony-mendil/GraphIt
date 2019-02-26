package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddSphereLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveSphereParam;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Removes a sphere from the syndrom graph.
 */
public class RemoveSphereLogAction extends LogAction {

    /**
     * Removes the passed sphere from the graph.
     * Gets the picked sphere though pick support.
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
        if (parameters == null) {
            List<Vertex> vertices = new LinkedList<>();
            PickedState<Sphere> pickedState = vv.getPickedSphereState();
            PickedState<Vertex> pickedVertexState = vv.getPickedVertexState();
            for (Sphere sp : pickedState.getPicked()) {
                if (!sp.isLockedStyle() && !sp.isLockedAnnotation() && !sp.isLockedPosition() && !sp.isLockedVertices() || values.getMode() == FunctionMode.TEMPLATE) {

                    if (sp.verticesLocked()) {
                        HelperFunctions helper = new HelperFunctions();
                        helper.setActionText("REMOVE_SPHERE_ALERT", true, true);
                        actionHistory.removeLastEntry();
                        return;
                    }
                    pickedVertexState.clear();
                    for (Vertex v : sp.getVertices()) {
                        vertices.add(v);
                        pickedVertexState.pick(v, true);
                    }
                    RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
                    removeVerticesLogAction.action();
                    AddRemoveVerticesParam addRemoveVerticesParam = (AddRemoveVerticesParam) removeVerticesLogAction.getParameters();
                    sp.getVertices().removeAll(vertices);
                    pickedState.pick(sp, false);
                    graph.removeSphere(sp);
                    createParameter(sp, vertices, addRemoveVerticesParam);
                } else {
                    HelperFunctions helper = new HelperFunctions();
                    helper.setActionText("REMOVE_SPHERE_TEMPLATE_ALERT", true, true);

                }
            }
        } else {
            Sphere sp = ((AddRemoveSphereParam) parameters).getSphere();
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction(((AddRemoveSphereParam) parameters).getAddRemoveVerticesParam());
            removeVerticesLogAction.action();
            graph.removeSphere(sp);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        AddSphereLogAction addSphereLogAction = new AddSphereLogAction((AddRemoveSphereParam) parameters);
        addSphereLogAction.action();
    }

    public void createParameter(Sphere sphere, List<Vertex> vertices, AddRemoveVerticesParam addRemoveVerticesParam) {
        parameters = new AddRemoveSphereParam(sphere, vertices, addRemoveVerticesParam);
    }
}
