package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesColorParam;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Changes the color of the selected edges.
 */
public class EditEdgesColorLogAction extends LogAction {
    /**
     * The color of the new edges.
     */
    private Color color;

    /**
     * Constructor in case the user changes the color of all/several edges.
     * Gets the picked edges through pick support.
     *
     * @param pColor The color to paint the edges.
     */
    public EditEdgesColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_EDGES_COLOR);
        color = pColor;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditEdgesColorParam The vertices object that contains every vertices that is needed.
     */
    public EditEdgesColorLogAction(EditEdgesColorParam pEditEdgesColorParam) {
        super(LogEntryName.EDIT_EDGES_COLOR);
        parameters = pEditEdgesColorParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        if (parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            Map<Edge, Color> oldEdges = new HashMap<>();
            Map<Edge, Color> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                if (!e.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {

                    oldEdges.put(e, e.getColor());
                    e.setColor(color);
                    newEdges.put(e, color);
                } else {
                    lockedEdges.add(e);
                }
            }
            if (!lockedEdges.isEmpty()) {
                helper.setActionText("EDIT_EDGES_COLOR_ALERT", true, true);
            }
            if (pickedState.getPicked().size() == lockedEdges.size()) {
                actionHistory.removeLastEntry();
                return;
            }
            createParameter(oldEdges, newEdges);
        } else {
            Map<Edge, Color> oldEdges = ((EditEdgesColorParam) parameters).getEdgesOld();
            Map<Edge, Color> newEdges = ((EditEdgesColorParam) parameters).getEdgesNew();
            for (Map.Entry<Edge, Color> entry : oldEdges.entrySet()) {
                entry.getKey().setColor(newEdges.get(entry.getKey()));
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
        Map<Edge, Color> edgesOld = ((EditEdgesColorParam) parameters).getEdgesOld();
        Map<Edge, Color> edgesNew = ((EditEdgesColorParam) parameters).getEdgesNew();
        List<Vertex> starts = ((EditEdgesColorParam) parameters).getStartVertices();
        List<Vertex> ends = ((EditEdgesColorParam) parameters).getEndVertices();
        EditEdgesColorParam editEdgesColorParam = new EditEdgesColorParam(edgesNew, edgesOld, starts, ends);
        EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(editEdgesColorParam);
        editEdgesColorLogAction.action();
    }

    public void createParameter(Map<Edge, Color> oldEdge, Map<Edge, Color> newEdge) {
        List<Vertex> starts = new ArrayList<>();
        List<Vertex> ends = new ArrayList<>();

        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        newEdge.forEach((e, c) -> {
            edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);

            starts.add(vertices.getFirst());
            ends.add(vertices.getSecond());
        });

        parameters = new EditEdgesColorParam(oldEdge, newEdge, starts, ends);
    }
}
