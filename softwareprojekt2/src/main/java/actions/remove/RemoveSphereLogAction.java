package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddSphereLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveSphereParam;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Removes a sphere from the syndrom graph.
 * @author Clement Phung, Nina Unterberg
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
                if (!sp.isLockedStyle() && !sp.isLockedAnnotation() && !sp.isLockedPosition() && !sp.isLockedVertices()
                        || values.getMode() == FunctionMode.TEMPLATE) {
                    if(!removingSphere(sp,vertices,pickedState,pickedVertexState,graph)){
                        return;
                    }
                } else {
                    HelperFunctions helper = new HelperFunctions();
                    helper.setActionText("REMOVE_SPHERE_TEMPLATE_ALERT", true, true);
                    actionHistory.removeLastEntry();
                    return;

                }
            }
        } else {
            Sphere sp = ((AddRemoveSphereParam) parameters).getSphere();
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction(((AddRemoveSphereParam) parameters).
                    getAddRemoveVerticesParam());
            removeVerticesLogAction.action();
            graph.removeSphere(sp);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    /**
     * Removes the sphere, if it is allowed.
     * @param sp                The sphere.
     * @param vertices          The vertices in the sphere.
     * @param pickedState       The pickedState of the sphere.
     * @param pickedVertexState The pickedState od the vertices.
     * @param graph             The graph to work on.
     * @return                  Indicator whether the action was successful.
     */
    private boolean removingSphere(Sphere sp, List<Vertex> vertices, PickedState<Sphere> pickedState,
                                   PickedState<Vertex> pickedVertexState, SyndromGraph<Vertex, Edge> graph) {
        if(!allowedSphereRemove(sp)){
            return false;
        }
        AddRemoveVerticesParam addRemoveVerticesParam = new AddRemoveVerticesParam(new HashMap<>(), new HashMap<>());
        if(!sp.getVertices().isEmpty()) {
            pickedVertexState.clear();
            setVerticesPicked(sp, vertices, pickedVertexState);
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
            removeVerticesLogAction.action();
            addRemoveVerticesParam = (AddRemoveVerticesParam) removeVerticesLogAction.getParameters();
        }
        sp.getVertices().removeAll(vertices);
        pickedState.pick(sp, false);
        graph.removeSphere(sp);
        createParameter(sp, addRemoveVerticesParam);
        return true;
    }

    /**
     * Sets the vertices in a picked state.
     * @param sp                The sphere.
     * @param vertices          The vertices.
     * @param pickedVertexState The current picked state of the vertices.
     */
    private void setVerticesPicked(Sphere sp, List<Vertex> vertices, PickedState<Vertex> pickedVertexState){
        for (Vertex v : sp.getVertices()) {
            vertices.add(v);
            pickedVertexState.pick(v, true);
        }
    }

    @Override
    public void undo() {
        AddSphereLogAction addSphereLogAction = new AddSphereLogAction((AddRemoveSphereParam) parameters);
        addSphereLogAction.action();
    }

    /**
     * Creates a parameter object for this action.
     * @param sphere                    The sphere.
     * @param addRemoveVerticesParam    The vertices and their edges, that got removed too.
     */
    public void createParameter(Sphere sphere, AddRemoveVerticesParam addRemoveVerticesParam) {
        parameters = new AddRemoveSphereParam(sphere, addRemoveVerticesParam);
    }

    /**
     * Checks whether the sphere can be removed without offending the template rules regarding
     * the elements.
     * @param sp                        The sphere.
     * @return true: The sphere can be removed. | false: The sphere can't be removed.
     */
    private boolean allowedSphereRemove(Sphere sp){
        if ((helper.verticesLocked(sp) || helper.edgesLocked(sp)) && values.getMode() != FunctionMode.TEMPLATE) {
            HelperFunctions helper = new HelperFunctions();
            helper.setActionText("REMOVE_SPHERE_ALERT", true, true);
            actionHistory.removeLastEntry();
            return false;
        }
        return true;
    }

}
