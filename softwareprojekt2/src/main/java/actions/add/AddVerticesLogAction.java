package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import actions.remove.RemoveVerticesLogAction;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import javafx.util.Pair;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Adds a collection of vertices to the graph. AddVerticesLogAction reverts the RemoveVerticesLogAction. The different
 * constructors depict different application scenarios.
 */
public class AddVerticesLogAction extends LogAction {
    /**
     * Map with vertices and corresponding pairs, containing the sphere id and sphere annotation.
     */
    Map<Vertex, Pair<Integer, String>> vertexPairMap;

    private Point2D pos;

    /**
     * The new position of the vertex.
     */
    Point2D position2D;

    /**
     * The sphere of the Vertex.
     */
    private Sphere sphere;
    /**
     * Adds all vertices that are defined in pParam. Also used to implement the undo-method of
     * RemoveVerticesLogAction.
     *
     * @param pParam The parameter object, containing all vertices to add.
     */
    public AddVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.ADD_VERTICES);
        parameters = pParam;
    }


    /**
     * Adds a vertex at given point.
     *
     * @param point Point of the mouse event where the vertex should be added.
     */
    public AddVerticesLogAction(Point2D point, Sphere sphere) {
        super(LogEntryName.ADD_VERTICES);
        position2D = point;
        this.sphere = sphere;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex,Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if(parameters == null){
            Vertex newVertex = graph.addVertex(position2D, sphere);
            createParameter(newVertex, sphere);
            vv.getGraphLayout().setLocation(newVertex, position2D);
        }else{
            Map<Vertex, Sphere> vertices = ((AddRemoveVerticesParam)parameters).getParameter();
            for(Map.Entry<Vertex, Sphere> entry : vertices.entrySet()){
                Vertex vertex = entry.getKey();
                Vertex newVertex = graph.addVertex(vertex.getCoordinates(), entry.getValue());
                vv.getGraphLayout().setLocation(newVertex, newVertex.getCoordinates());
            }
        }
        vv.repaint();
    }

    @Override
    public void undo() {
        RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction((AddRemoveVerticesParam) parameters);
        removeVerticesLogAction.action();
    }


    public void createParameter(Vertex vertex, Sphere sphere) {
        Map<Vertex, Sphere> vertices = new HashMap<>();
        vertices.put(vertex, sphere);
        parameters = new AddRemoveVerticesParam(vertices);
    }
}
