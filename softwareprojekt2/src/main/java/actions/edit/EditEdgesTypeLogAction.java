package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesTypeParam;

import java.util.*;

/**
 * Changes the EdgeType from a collection of edges.
 */
public class EditEdgesTypeLogAction extends LogAction {
    /**
     * The new arrow-type.
     */
    private EdgeArrowType type;

    /**
     * Changes the edge type from all defined edges in pParam.
     *
     * @param pParam The EditEdgesTypeParam object, containing all edges where to change the edge type.
     */
    private EditEdgesTypeLogAction(EditEdgesTypeParam pParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        parameters = pParam;
    }

    /**
     * Changes the edge type from all passed edges
     *
     * @param pEdgeArrowType The new edge type.
     */
    public EditEdgesTypeLogAction(EdgeArrowType pEdgeArrowType) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        type = pEdgeArrowType;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        if (parameters == null) {
            if (values.getMode() != FunctionMode.TEMPLATE && !isAllowedEdit(type)) {
                    return;
            }
            List<Edge> lockedEdges = new LinkedList<>();
            PickedState<Edge> pickedState = vv.getPickedEdgeState();
            Map<Edge, EdgeArrowType> oldEdges = new HashMap<>();
            Map<Edge, EdgeArrowType> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                if (!e.isLockedEdgeType() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldEdges.put(e, e.getArrowType());
                    e.setArrowType(type);
                    newEdges.put(e, type);
                } else {
                    lockedEdges.add(e);
                }
            }
            if (!lockedEdges.isEmpty()) {
                helper.setActionText("EDIT_EDGES_TYPE_ACTION", true, true);
            }
            if (lockedEdges.size() == pickedState.getPicked().size()) {
                actionHistory.removeLastEntry();
                return;
            }
            createParameter(oldEdges, newEdges);
        } else {
            Map<Edge, EdgeArrowType> oldEdges = ((EditEdgesTypeParam) parameters).getEdgesOldEdgeType();
            Map<Edge, EdgeArrowType> newEdges = ((EditEdgesTypeParam) parameters).getEdgesNewEdgeType();
            for (Map.Entry<Edge, EdgeArrowType> entry : oldEdges.entrySet()) {
                entry.getKey().setArrowType(newEdges.get(entry.getKey()));
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Map<Edge, EdgeArrowType> oldEdges = ((EditEdgesTypeParam) parameters).getEdgesOldEdgeType();
        Map<Edge, EdgeArrowType> newEdges = ((EditEdgesTypeParam) parameters).getEdgesNewEdgeType();
        List<Vertex> starts = ((EditEdgesTypeParam) parameters).getStartVertices();
        List<Vertex> ends = ((EditEdgesTypeParam) parameters).getEndVertices();
        EditEdgesTypeParam editEdgesTypeParam = new EditEdgesTypeParam(oldEdges, newEdges, starts, ends);
        EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(editEdgesTypeParam);
        editEdgesTypeLogAction.action();
    }

    /**
     * Creates a new parameter-object of this action.
     *
     * @param oldEdges The old edges and their old arrow-type.
     * @param newEdges The new edges and their new arrow-type.
     */
    public void createParameter(Map<Edge, EdgeArrowType> oldEdges, Map<Edge, EdgeArrowType> newEdges) {
        List<Vertex> starts = new ArrayList<>();
        List<Vertex> ends = new ArrayList<>();

        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        newEdges.forEach((e, ed) -> {
            edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);

            starts.add(vertices.getFirst());
            ends.add(vertices.getSecond());
        });

        parameters = new EditEdgesTypeParam(oldEdges, newEdges, starts, ends);
    }

    /**
     * Checks whether the edit of the edge arrow-type is allowed or not.
     * @param type The new type of the relations.
     * @return  The indicator, if it is allowed.
     */
    private boolean isAllowedEdit(EdgeArrowType type){
        switch (type) {
            case REINFORCED:
                if (!template.isReinforcedEdgesAllowed()) {
                    helper.setActionText("EDGES_TYPE_REINFORCED_ALERT", true, true);
                    return false;
                }
                break;
            case EXTENUATING:
                if (!template.isExtenuatingEdgesAllowed()) {
                    helper.setActionText("EDGES_TYPE_EXTENUATING_ALERT", true, true);
                    return false;
                }
                break;
            case NEUTRAL:
                if (!template.isNeutralEdgesAllowed()) {
                    helper.setActionText("EDGES_TYPE_NEURAL_ALERT", true, true);
                    return false;
                }
                break;
        }
        return true;
    }
}
