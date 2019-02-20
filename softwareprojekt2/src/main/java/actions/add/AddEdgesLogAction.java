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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
                graph.addEdge(edge.getKey(),edge.getValue());
                createParameter(edge);
            }else{
                List<Edge> edges = ((AddRemoveEdgesParam)parameters).getEdges();
                List<Vertex> startVertices = ((AddRemoveEdgesParam)parameters).getStartVertices();
                List<Vertex> endVertices = ((AddRemoveEdgesParam)parameters).getEndVertices();
                for(Edge edge : edges){
                    graph.addEdgeExisting(edge, startVertices.get(edges.indexOf(edge)), endVertices.get(edges.indexOf(edge)));
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



    public void createParameter(Pair<Vertex,Vertex> edge) {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        List<Edge> edges = new ArrayList<>();
        Edge toAdd = graph.findEdge(edge.getKey(), edge.getValue());
        edges.add(toAdd);

        Set<Pair<Vertex, Vertex>> vertices = new HashSet<>();
        vertices.add(new Pair<>(edge.getKey(),edge.getValue()));
        parameters = new AddRemoveEdgesParam(edges, vertices);
    }
}