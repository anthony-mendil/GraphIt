package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import actions.remove.RemoveEdgesLogAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

import java.util.HashSet;
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
        if(parameters == null) {
            graph.addEdge(edge.getKey(),edge.getValue());
            createParameter(edge);
        }else{
            for(Pair<Vertex, Vertex> pair : ((AddRemoveEdgesParam)parameters).getEdges()){
                graph.addEdge(pair.getKey(),pair.getValue());
            }
        }
        vv.repaint();
        syndrom.getInstance().getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
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
        Set<Pair<Vertex, Vertex>> edges = new HashSet<>();
        edges.add(new Pair<>(edge.getKey(),edge.getValue()));
        parameters = new AddRemoveEdgesParam(edges);
    }
}
