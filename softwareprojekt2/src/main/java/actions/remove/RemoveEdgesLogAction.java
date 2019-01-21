package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
<<<<<<< HEAD
=======
import actions.add.AddEdgesLogAction;
>>>>>>> b95ce74c041cdd3ab813648b241742a774f25d66
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
<<<<<<< HEAD
=======
import javafx.util.Pair;
import log_management.DatabaseManager;
>>>>>>> b95ce74c041cdd3ab813648b241742a774f25d66
import log_management.parameters.add_remove.AddRemoveEdgesParam;

/**
 * Removes edges from the syndrom graph.
 */
public class RemoveEdgesLogAction extends LogAction {
    /**
     * The pair of vertices connecting the edge.
     */
    private Pair<Vertex,Vertex> edge;

    /**
     * Removes all passed edges from the graph.
     * Gets the picked edges through pick support.
     *
     */
    public RemoveEdgesLogAction(Pair<Vertex,Vertex> pEdge) {
        super(LogEntryName.REMOVE_EDGES);
        edge = pEdge;
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

        for (Edge e: pickedState.getPicked()) {
            graph.removeEdge(e);
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(parameters == null) {
           // graph.removeEdge(edge)
        }else{

        }
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
