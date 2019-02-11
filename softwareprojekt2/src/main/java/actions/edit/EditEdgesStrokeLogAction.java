package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditEdgesStrokeParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Changes the stroke of the selected edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * Temporary variable for the new stroke-type of the edge.
     */
    private StrokeType stroke;
    /**
     * Constructor for the EditEdgesStrokeLogAction.
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
    public EditEdgesStrokeLogAction(EditEdgesStrokeParam pEditEdgesStrokeParam) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        parameters = pEditEdgesStrokeParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        if(parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            PickedState<Edge> pickedState = vv.getPickedEdgeState();
            Map<Edge,StrokeType> oldEdges = new HashMap<>();
            Map<Edge,StrokeType> newEdges = new HashMap<>();
            for (Edge e : pickedState.getPicked()) {
                if(!e.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldEdges.put(e, e.getStroke());
                    e.setStroke(stroke);
                    newEdges.put(e, stroke);
                }else{
                    helper.setActionText("Die Linienart der Kante(n) darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedEdges.add(e);
                }
            }
            createParameter(oldEdges, newEdges);
        }else{
            Map<Edge,StrokeType> oldEdges = ((EditEdgesStrokeParam)parameters).getEdgesOld();
            Map<Edge,StrokeType> newEdges = ((EditEdgesStrokeParam)parameters).getEdgesNew();
            for(Map.Entry<Edge,StrokeType> entry : oldEdges.entrySet()){
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
        Map<Edge,StrokeType> oldEdges = ((EditEdgesStrokeParam)parameters).getEdgesOld();
        Map<Edge,StrokeType> newEdges = ((EditEdgesStrokeParam)parameters).getEdgesNew();
        EditEdgesStrokeParam editEdgesStrokeParam = new EditEdgesStrokeParam(newEdges,oldEdges);
        EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(editEdgesStrokeParam);
        editEdgesStrokeLogAction.action();
    }

    public void createParameter(Map<Edge,StrokeType> oldEdges, Map<Edge,StrokeType> newEdges) {
        parameters = new EditEdgesStrokeParam(oldEdges,newEdges);
    }
}
