package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesStrokeParam;

import java.util.*;

/**
 * Changes the stroke of the selected edges.
 * @author Clement Phung, Nina Unterberg
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * The new stroke-type of the edge.
     */
    private StrokeType stroke;

    /**
     * Constructor for the EditEdgesStrokeLogAction.
     *
     * @param pStrokeType The new stroke type.
     */
    public EditEdgesStrokeLogAction(StrokeType pStrokeType) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        stroke = pStrokeType;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditEdgesStrokeParam The vertices object that contains every vertices that is needed.
     */
    private EditEdgesStrokeLogAction(EditEdgesStrokeParam pEditEdgesStrokeParam) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        parameters = pEditEdgesStrokeParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        if (parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            PickedState<Edge> pickedState = vv.getPickedEdgeState();
            Map<Edge, StrokeType> oldEdges = new HashMap<>();
            Map<Edge, StrokeType> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                if (!e.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldEdges.put(e, e.getStroke());
                    e.setStroke(stroke);
                    newEdges.put(e, stroke);
                } else {
                    lockedEdges.add(e);
                }
            }
            if (checkTemplateRules(lockedEdges, pickedState)) {
                return;
            }
            createParameter(oldEdges, newEdges);
        } else {
            Map<Edge, StrokeType> oldEdges = ((EditEdgesStrokeParam) parameters).getEdgesOld();
            Map<Edge, StrokeType> newEdges = ((EditEdgesStrokeParam) parameters).getEdgesNew();
            for (Map.Entry<Edge, StrokeType> entry : oldEdges.entrySet()) {
                entry.getKey().setStroke(newEdges.get(entry.getKey()));
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
        Map<Edge, StrokeType> oldEdges = ((EditEdgesStrokeParam) parameters).getEdgesOld();
        Map<Edge, StrokeType> newEdges = ((EditEdgesStrokeParam) parameters).getEdgesNew();
        List<Vertex> starts = ((EditEdgesStrokeParam) parameters).getStartVertices();
        List<Vertex> ends = ((EditEdgesStrokeParam) parameters).getEndVertices();
        EditEdgesStrokeParam editEdgesStrokeParam = new EditEdgesStrokeParam(newEdges, oldEdges, starts, ends);
        EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(editEdgesStrokeParam);
        editEdgesStrokeLogAction.action();
    }

    /**
     * Creates a new parameter-object of this action.
     *
     * @param oldEdges The old edges and their old stroke-type.
     * @param newEdges The new edges and theit new stroke-type.
     */
    public void createParameter(Map<Edge, StrokeType> oldEdges, Map<Edge, StrokeType> newEdges) {
        List<Vertex> starts = new ArrayList<>();
        List<Vertex> ends = new ArrayList<>();

        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        newEdges.forEach((e, s) -> {
            edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);

            starts.add(vertices.getFirst());
            ends.add(vertices.getSecond());
        });
        parameters = new EditEdgesStrokeParam(oldEdges, newEdges, starts, ends);
    }

    /**
     * Checks whether the template rules allow this action.
     *
     * @param lockedEdges The list of locked edges.
     * @param pickedState The set of the picked elements
     * @return The indicator, if the action is allowed.
     */
    private boolean checkTemplateRules(List<Edge> lockedEdges, PickedState<Edge> pickedState) {
        if (!lockedEdges.isEmpty()) {
            helper.setActionText("EDIT_EDGES_STROKE_ALERT", true, true);
        }
        if (lockedEdges.size() == pickedState.getPicked().size()) {
            actionHistory.removeLastEntry();
            return true;
        }
        return false;
    }
}
