package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesColorParam;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        if(parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            Map<Edge,Color> oldEdges = new HashMap<>();
            Map<Edge,Color> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                if (!e.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldEdges.put(e, e.getColor());
                    e.setColor(color);
                    newEdges.put(e, color);
                }else{
                    helper.setActionText("Die Farbe der Kante(n) darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedEdges.add(e);
                }
            }
                createParameter(oldEdges, newEdges);
        }else {
            Map<Edge,Color> oldEdges = ((EditEdgesColorParam) parameters).getEdgesOld();
            Map<Edge,Color> newEdges = ((EditEdgesColorParam) parameters).getEdgesNew();
            for (Map.Entry<Edge,Color> entry : oldEdges.entrySet()) {
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
        Map<Edge,Color> edgesOld = ((EditEdgesColorParam)parameters).getEdgesOld();
        Map<Edge,Color> edgesNew = ((EditEdgesColorParam)parameters).getEdgesNew();
        EditEdgesColorParam editEdgesColorParam = new EditEdgesColorParam(edgesNew,edgesOld);
        EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(editEdgesColorParam);
        editEdgesColorLogAction.action();
    }

    public void createParameter(Map<Edge,Color> oldEdge, Map<Edge,Color> newEdge) {
        parameters = new EditEdgesColorParam(oldEdge,newEdge);
    }
}
