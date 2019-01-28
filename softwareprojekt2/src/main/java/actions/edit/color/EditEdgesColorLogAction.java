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
            Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> oldEdge = new HashMap<>();
            Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> newEdge = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                edu.uci.ics.jung.graph.util.Pair<Vertex> sourceTargetJung = vv.getGraphLayout().getGraph().getEndpoints(e);
                Pair<Vertex,Vertex> sourceTarget = new Pair<>(sourceTargetJung.getFirst(),sourceTargetJung.getSecond());
                oldEdge.put(new Pair<>(e,e.getColor()),sourceTarget);
                e.setColor(color);
                newEdge.put(new Pair<>(e,color),sourceTarget);
            }
            createParameter(oldEdge,newEdge);
        }else {
            Map<Pair<Edge,Color>, Pair<Vertex, Vertex>> oldEdges = ((EditEdgesColorParam) parameters).getEdgesOld();
            Map<Pair<Edge,Color>, Pair<Vertex, Vertex>> newEdges = ((EditEdgesColorParam) parameters).getEdgesNew();
            Set<Pair<Edge,Color>> setNewVertices = newEdges.keySet();
            System.out.println("afa");
            for (Map.Entry<Pair<Edge,Color>, Pair<Vertex, Vertex>> entry : oldEdges.entrySet()) {
                Color newEdgeColor = null;
                for(Pair<Edge,Color> entries : setNewVertices){
                    if(entry.getKey().getKey().getId() == (entries.getKey().getId())){
                        newEdgeColor = entries.getKey().getColor();
                    }
                }
                entry.getKey().getKey().setColor(newEdgeColor);
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
        Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> edgesOld = ((EditEdgesColorParam)parameters).getEdgesOld();
        Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> edgesNew = ((EditEdgesColorParam)parameters).getEdgesNew();
        EditEdgesColorParam editEdgesColorParam = new EditEdgesColorParam(edgesNew,edgesOld);
        EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(editEdgesColorParam);
        editEdgesColorLogAction.action();
    }

    public void createParameter(Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> oldEdge, Map<Pair<Edge,Color>,Pair<Vertex,Vertex>> newEdge) {
        parameters = new EditEdgesColorParam(oldEdge,newEdge);
    }
}
