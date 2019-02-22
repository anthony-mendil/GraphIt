package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import actions.remove.RemoveVerticesLogAction;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adds a collection of vertices to the graph. AddVerticesLogAction reverts the RemoveVerticesLogAction. The different
 * constructors depict different application scenarios.
 */
public class AddVerticesLogAction extends LogAction {

    /**
     * The new position of the vertex.
     */
    Point2D position2D;

    /**
     * The sphere of the Vertex.
     */
    private Sphere sphere;
    /**
     * Map of vertices and the sphere.
     */
    private Map<Vertex,Sphere> vertices = new HashMap<>();

    /**
     * Adds all vertices that are defined in pParam. Also used to implement the undo-method of
     * RemoveVerticesLogAction.
     *
     * @param pParam The vertices object, containing all vertices to add.
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
        if(!template.isLockedVertexNumber() || graph.getVertices().size() < template.getMaxVertices()){
        if(parameters == null){
            if(sphere.isLockedVertices() && values.getMode() != FunctionMode.TEMPLATE){
                helper.setActionText("The vertices of this sphere is locked.",true);
                return;
            }
            position2D = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(position2D);
            Vertex newVertex = graph.addVertex(position2D, sphere);
            createParameter(newVertex, sphere);
            vv.getGraphLayout().setLocation(newVertex, position2D);
        }else{
            Map<Vertex, Sphere> vertices = ((AddRemoveVerticesParam)parameters).getVertices();
            List<Vertex> startVertices = ((AddRemoveVerticesParam)parameters).getStartVertexList();
            List<Edge> edgeList = ((AddRemoveVerticesParam)parameters).getEdgeList();
            List<Vertex> sinkVertices = ((AddRemoveVerticesParam)parameters).getSinkVertexList();

            for(Map.Entry<Vertex, Sphere> entry : vertices.entrySet()){
                Vertex vertex = entry.getKey();
                graph.addVertexExisting(vertex);
                vv.getGraphLayout().setLocation(vertex, vertex.getCoordinates());
            }
            for(int i = 0; i < edgeList.size(); i++){
                graph.addEdgeExisting(edgeList.get(i), startVertices.get(i),sinkVertices.get(i));
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }else{
            helper.setActionText("Only "+ template.getMaxVertices()+ " vertex/vertices are allowed.", true);
        }
    }

    @Override
    public void undo() {
        RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction((AddRemoveVerticesParam) parameters);
        removeVerticesLogAction.action();
    }


    public void createParameter(Vertex vertex, Sphere sphere) {
        vertices.put(vertex, sphere);
        parameters = new AddRemoveVerticesParam(vertices, new HashMap<>());
    }
}
