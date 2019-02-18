package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import actions.remove.RemoveEdgesLogAction;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

import java.util.*;

/**
 * Adds a single/multiple edge/s to the graph.
 */
public class AddEdgesLogAction extends LogAction {

    private Pair<Vertex, Vertex> edge;
    /**
     * Constructor in case several edges shall be added.
     *
     * @param pPair A pair of vertices that gets a new edge between them
     */
    public AddEdgesLogAction(Pair<Vertex, Vertex> pPair) {
        super(LogEntryName.ADD_EDGES);
        edge = pPair;
    }

    /**
     * Adds Edges to the graph. This constructor will be used to realize the undo-method of
     * RemoveEdgesLogAction.
     *
     * @param pAddRemoveEdgesParam the parameter object, containing all edges to add.
     */
    public AddEdgesLogAction(AddRemoveEdgesParam pAddRemoveEdgesParam) {
        super(LogEntryName.ADD_EDGES);
        parameters = pAddRemoveEdgesParam;
    }

    /**
     * Adds the selected edges to the graph.
     */
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(!template.isLockedEdgesNumber() || graph.getEdges().size() < template.getMaxVertices()){
        if(parameters == null) {
            Map<Edge,Pair<Vertex,Vertex>> edges = new HashMap<>();
            graph.addEdge(edge.getKey(),edge.getValue());
            Edge edg = graph.findEdge(edge.getKey(),edge.getValue());
            Pair<Vertex,Vertex> vertexP = new Pair<>(edge.getKey(),edge.getValue());
            edges.put(edg,vertexP);
            createParameter(edges);
        }else{
            Map<Edge,Pair<Vertex,Vertex>> edges = ((AddRemoveEdgesParam)parameters).getEdges();
            for(Map.Entry<Edge,Pair<Vertex,Vertex>> entry : edges.entrySet()){
                graph.addEdgeExisting(entry.getKey(),entry.getValue().getKey(), entry.getValue().getValue());
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }else{
            helper.setActionText("Only " + template.getMaxEdges() + " edge(s) are allowed in the graph.", true);
            actionHistory.removeLastEntry();
        }

    }

    /**
     * Undoes the action.
     */
    @Override
    public void undo() {
        RemoveEdgesLogAction removeEdgesLogAction = new RemoveEdgesLogAction((AddRemoveEdgesParam) parameters);
        removeEdgesLogAction.action();
    }



    public void createParameter(Map<Edge,Pair<Vertex,Vertex>> edge) {
        parameters = new AddRemoveEdgesParam(edge);
    }
}
