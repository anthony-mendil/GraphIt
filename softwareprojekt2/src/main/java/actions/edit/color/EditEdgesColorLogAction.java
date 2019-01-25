package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesColorParam;

import java.awt.*;
import java.util.HashMap;
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
            Map<Edge,Pair<Vertex,Vertex>> oldEdge = new HashMap<>();
            Map<Edge,Pair<Vertex,Vertex>> newEdge = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> sourceTargetJung = Syndrom.getInstance().getGraph().getEndpoints(e);
                Pair<Vertex,Vertex> sourceTarget = new Pair<>(sourceTargetJung.getFirst(),sourceTargetJung.getSecond());
                oldEdge.put(e,sourceTarget);
                e.setColor(color);
                newEdge.put(e,sourceTarget);
            }
            createParameter(oldEdge,newEdge);
        }else{
            Map<Edge,Pair<Vertex,Vertex>> oldEdge = ((EditEdgesColorParam)parameters).getEdgesOld();
            Map<Edge,Pair<Vertex,Vertex>> newEdge = ((EditEdgesColorParam)parameters).getEdgesNew();
            for(Map.Entry<Edge,Pair<Vertex,Vertex>> entry : oldEdge.entrySet()){
         //       entry.getKey().setColor(newEdge.get(newEdge.keySet().));
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
        throw new UnsupportedOperationException();
    }

    public void createParameter(Map<Edge,Pair<Vertex,Vertex>> oldEdge, Map<Edge,Pair<Vertex,Vertex>> newEdge) {
        parameters = new EditEdgesColorParam(oldEdge,newEdge);
    }
}
