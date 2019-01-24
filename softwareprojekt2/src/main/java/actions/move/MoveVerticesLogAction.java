package actions.move;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.move.MoveVerticesParam;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Moves vertices from one to another position. A vertex can not be positioned where another vertex is already located
 * and it must be positioned within a sphere.
 */
public class MoveVerticesLogAction extends LogAction {
    /**
     * The difference in x.
     */
    private Double dX;
    /**
     * The difference in y.
     */
    private Double dY;
    /**
     * The collection of vertices, which were moved.
     */
    private Set<Vertex> vertices;


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
     * @param dx       The difference between the x-coordinate from the point where the user pressed the mouse and the
     *                 point where the user released the mouse.
     * @param dy       The difference between the y-coordinate from the point where the user pressed the mouse and the
     *                 point where the user released the mouse.
     * @param vertices The collection of vertices to move.
     */
    public MoveVerticesLogAction(double dx, double dy, Set<Vertex> vertices) {
        super(LogEntryName.MOVE_VERTICES);
        dX = dx;
        dY = dy;
        this.vertices = vertices;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        Layout<Vertex, Edge> layout = vv.getGraphLayout();
        if (parameters == null) {
            Map<Vertex, Point2D> oldVer = new HashMap<>();
            Map<Vertex, Point2D> newVer = new HashMap<>();
            for (Vertex vertex : vertices) {
                Point2D oldPoint = new Point2D.Double(vertex.getCoordinates().getX() - dX, vertex.getCoordinates().getY() - dY);
                oldVer.put(vertex, oldPoint);
                newVer.put(vertex, vertex.getCoordinates());
            }
            createParameter(oldVer, newVer);

        } else {
            Map<Vertex, Point2D> oldVertices = ((MoveVerticesParam) parameters).getOldVertices();
            Map<Vertex, Point2D> newVertices = ((MoveVerticesParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
                entry.getKey().setCoordinates(newVertices.get(entry.getKey()));

            }
        }
        vv.repaint();
        Syndrom.getInstance().getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();


    }


    @Override
    public void undo() {
        Map<Vertex,Point2D> oldVertices = ((MoveVerticesParam)parameters).getOldVertices();
        Map<Vertex,Point2D> newVertices = ((MoveVerticesParam)parameters).getNewVertices();
        MoveVerticesParam  moveVerticesParam = new MoveVerticesParam(newVertices,oldVertices);
        MoveVerticesLogAction moveVerticesLogAction= new MoveVerticesLogAction(moveVerticesParam);
        moveVerticesLogAction.action();
    }

    public void createParameter(Map<Vertex,Point2D> oldVertices, Map<Vertex,Point2D> newVertices) {
        parameters = new MoveVerticesParam(oldVertices,newVertices);
    }
}