package actions.move;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.move.MoveVerticesParam;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Moves vertices from one to another position. A vertex can not be positioned where another vertex is already located
 * and it must be positioned within a sphere.
 */
public class MoveVerticesLogAction extends LogAction {

    /**
     * The collection of vertices, which were moved.
     */
    private Set<Vertex> vertices;

    private Map<Vertex, Pair<Point2D, Sphere>> points;


    /**
     * Moves all vertices, which are defined in pParam from one to another position.
     *
     * @param pParam The MoveVerticesParam contains all vertices which should be removed.
     */
    public MoveVerticesLogAction(MoveVerticesParam pParam) {
        super(LogEntryName.MOVE_VERTICES);
        parameters = pParam;
    }


    /**
     * Moves all vertices according to the difference.
     *
     * @param vertices The collection of vertices to move.
     */
    public MoveVerticesLogAction(Set<Vertex> vertices,  Map<Vertex, Pair<Point2D, Sphere>> points) {
        super(LogEntryName.MOVE_VERTICES);
        this.vertices = vertices;
        this.points = points;
    }

    @Override
    public void action() {
       if (parameters != null){
           SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
           Layout<Vertex, Edge> layout = vv.getGraphLayout();

           Map<Vertex,Point2D> oldVertices = ((MoveVerticesParam)parameters).getOldVertices();
           Map<Vertex,Point2D> newVertices = ((MoveVerticesParam)parameters).getNewVertices();
           oldVertices.clear();

           for (Vertex v : vertices) {
               Point2D vp = newVertices.get(v);
               newVertices.put(v, v.getCoordinates());
               oldVertices.put(v, vp);
               v.setCoordinates(vp);
               layout.setLocation(v, vp);
           }

           vv.repaint();
           Syndrom.getInstance().getVv2().repaint();
       } else {
           Map<Vertex,Point2D> oldVertices = ((MoveVerticesParam)parameters).getOldVertices();
           Map<Vertex,Point2D> newVertices = new HashMap<>();
           for (Vertex v : points.keySet()){
               oldVertices.put(v, points.get(v).getKey());
               newVertices.put(v, v.getCoordinates());
           }
           createParameter(oldVertices, newVertices);
       }
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }


    @Override
    public void undo() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        MoveVerticesParam param = (MoveVerticesParam)parameters;
        Map<Vertex,Point2D> oldVertices = param.getOldVertices();
        Map<Vertex,Point2D> newVertices = param.getNewVertices();
        newVertices.clear();

        for (Vertex v : vertices) {
            Point2D vp = oldVertices.get(v);
            newVertices.put(v, vp);
            oldVertices.put(v, v.getCoordinates());
            v.setCoordinates(vp);
            layout.setLocation(v, vp);
        }

        param.setNewVertices(newVertices);
        param.setOldVertices(oldVertices);
    }

    public void createParameter(Map<Vertex,Point2D> oldVertices, Map<Vertex,Point2D> newVertices) {
        parameters = new MoveVerticesParam(oldVertices,newVertices);
    }
}