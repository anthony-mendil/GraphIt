package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddVerticesLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Removes vertices from the syndrom graph.
 */
public class RemoveVerticesLogAction extends LogAction {

    /**
     * All vertices which should be removed.
     */
    /* Must be set on private or public */
    public Collection<Vertex> vertices;

    /**
     * Removes all passed vertices from the graph.
     * Gets the picked vertices through pick support.
     */
    public RemoveVerticesLogAction() {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices which are defined in pParam. Also used to implement the undo-method of
     * AddVerticesLogAction.
     *
     * @param pParam The RemoveVerticesParam object, containing all vertices to remove.
     */
    public RemoveVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
        parameters = pParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        if(parameters == null) {
            PickedState<Vertex> pickedState = vv.getPickedVertexState();
            Map<Vertex,Sphere> params = new HashMap<>();
            for (Vertex vertex : pickedState.getPicked()) {
                graph.removeVertex(vertex);
                Point2D posVertex = vertex.getCoordinates();
                Sphere sp = pickSupport.getSphere(posVertex.getX(),posVertex.getY());
                params.put(vertex, sp);
            }
            createParameter(params);
        }else{
            Map<Vertex, Sphere> vertices = ((AddRemoveVerticesParam)parameters).getParameter();
            for(Map.Entry<Vertex, Sphere> entry : vertices.entrySet()){
                graph.removeVertex(entry.getKey());
            }
        }
        vv.repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(this);
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction((AddRemoveVerticesParam)parameters);
        addVerticesLogAction.action();
    }


    public void createParameter(Map<Vertex,Sphere> vertices) {
        parameters = new AddRemoveVerticesParam(vertices);
    }
}
