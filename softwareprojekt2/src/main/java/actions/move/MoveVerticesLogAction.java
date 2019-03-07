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
     * The collection of vertices at their old state, which were moved.
     */
    private Set<Vertex> vertices;

    private Map<Vertex, Pair<Point2D, Sphere>> points;


    /**
     * Moves all vertices, which are defined in pParam from one to another position.
     *
     * @param pParam The MoveVerticesParam contains all vertices which should be removed.
     */
    private MoveVerticesLogAction(MoveVerticesParam pParam) {
        super(LogEntryName.MOVE_VERTICES);
        parameters = pParam;
    }

    /**
     * Moves all vertices according to the difference.
     * @param vertices vertices The collection of vertices to move.
     * @param points the sphere the vertices belonged to
     */
    public MoveVerticesLogAction(Set<Vertex> vertices, Map<Vertex, Pair<Point2D, Sphere>> points) {
        super(LogEntryName.MOVE_VERTICES);
        this.vertices = vertices;
        this.points = points;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        if (parameters == null) {
            Map<Vertex, Point2D> oldVertices = new HashMap<>();
            Map<Vertex, Point2D> newVertices = new HashMap<>();

            for (Vertex vertex : vertices) {
                oldVertices.put(vertex, points.get(vertex).getKey());
                newVertices.put(vertex, vertex.getCoordinates());

            }

            createParameter(oldVertices, newVertices);

            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();
        } else {
            Map<Vertex, Point2D> oldVertices = ((MoveVerticesParam) parameters).getOldVertices();
            Map<Vertex, Point2D> newVertices = ((MoveVerticesParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
                entry.getKey().setCoordinates(newVertices.get(entry.getKey()));
                layout.setLocation(entry.getKey(), newVertices.get(entry.getKey()));
            }
        }
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }


    @Override
    public void undo() {
        Map<Vertex, Point2D> oldVertices = ((MoveVerticesParam) parameters).getOldVertices();
        Map<Vertex, Point2D> newVertices = ((MoveVerticesParam) parameters).getNewVertices();
        MoveVerticesParam moveVerticesParam = new MoveVerticesParam(newVertices, oldVertices);
        MoveVerticesLogAction moveVerticesLogAction = new MoveVerticesLogAction(moveVerticesParam);
        moveVerticesLogAction.action();
    }

    /**
     * Creates a parameter-object for this action.
     * @param oldVertices   The map of the vertices and their old position.
     * @param newVertices   The map of the vertice and their new position.
     */
    public void createParameter(Map<Vertex, Point2D> oldVertices, Map<Vertex, Point2D> newVertices) {
        parameters = new MoveVerticesParam(oldVertices, newVertices);
    }
}