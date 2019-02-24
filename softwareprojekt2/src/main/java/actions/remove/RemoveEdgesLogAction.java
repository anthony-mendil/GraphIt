package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddEdgesLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

import java.util.*;
import java.util.function.Function;

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



    public void createParameter(List<Edge> pEdges, List<Vertex> pStart, List<Vertex> pSink) {
        parameters = new AddRemoveEdgesParam(pEdges, pStart, pSink);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(parameters == null) {
            List<Edge> parameterEdges = new LinkedList<>();
            List<Vertex> parameterStartEdges = new LinkedList<>();
            List<Vertex> parameterSinkVertex = new LinkedList<>();
            List<Edge> lockedEdges = new LinkedList<>();
            for (Edge e: pickedState.getPicked()) {
                if(!e.isLockedPosition() && !e.isLockedEdgeType() && !e.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE){
                    edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);
                    parameterEdges.add(e);
                    parameterStartEdges.add(vertices.getFirst());
                    parameterSinkVertex.add(vertices.getSecond());
                    graph.removeEdge(e);
                }else{
                    lockedEdges.add(e);
                }
            }
            if(!lockedEdges.isEmpty()){
                helper.setActionText("REMOVE_EDGES_ALERT", true, true);
            }
            if(lockedEdges.size() == pickedState.getPicked().size()){
                actionHistory.removeLastEntry();
                return;
            }
            for(Edge e : parameterEdges){
                pickedState.pick(e, false);
            }
            createParameter(parameterEdges, parameterStartEdges, parameterSinkVertex);
        }else{
            //Map<Edge,Pair<Vertex,Vertex>> edg = ((AddRemoveEdgesParam)parameters).getEdges();
            //for(Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edg.entrySet()){
            //    graph.removeEdge(entry.getKey());
            List<Edge> edges = ((AddRemoveEdgesParam)parameters).getEdges();
            edges.forEach(e -> graph.removeEdge(e));
            //}
        }

        vv.repaint();
        syndrom.getVv2().repaint();


        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        AddEdgesLogAction addEdgesLogAction = new AddEdgesLogAction((AddRemoveEdgesParam)parameters);
        addEdgesLogAction.action();
    }
}
