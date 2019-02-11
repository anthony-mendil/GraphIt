package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddEdgesLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Removes edges from the syndrom graph.
 */
public class RemoveEdgesLogAction extends LogAction {

    /**
     * Removes all passed edges from the graph.
     * Gets the picked edges through pick support.
     *
     */
    public RemoveEdgesLogAction() {
        super(LogEntryName.REMOVE_EDGES);
    }

    /**
     * Removes all edges which are defined in pParam. Also used to implement the undo-method of
     * AddEdgesLogAction.
     *
     * @param pParam The RemoveEdgesParam object, containing all edges to remove.
     */
    public RemoveEdgesLogAction(AddRemoveEdgesParam pParam) {
        super(LogEntryName.REMOVE_EDGES);
        parameters = pParam;
    }



    public void createParameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(parameters == null) {
            List<Edge> lockedEdges = new LinkedList<>();
            for (Edge e: pickedState.getPicked()) {
                if(!e.isLockedEdgeType() && !e.isLockedStyle()){
                    graph.removeEdge(e);
                }else{
                    helper.setActionText("Die Kanten können nicht aufgrund der Vorlageregeln verändert werden." , true);
                    lockedEdges.add(e);
                }
            }
        }else{
            Set<Pair<Vertex,Vertex>> edges = ((AddRemoveEdgesParam)parameters).getEdges();
            for(Pair<Vertex,Vertex> edge : edges){
                Edge removeEdge = graph.findEdge(edge.getKey(),edge.getValue());
                graph.removeEdge(removeEdge);
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
        AddRemoveEdgesParam addRemoveEdgesParam = new AddRemoveEdgesParam(((AddRemoveEdgesParam)parameters).getEdges());
        AddEdgesLogAction addEdgesLogAction = new AddEdgesLogAction(addRemoveEdgesParam);
        addEdgesLogAction.action();
    }
}
