package actions.move;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.move.MoveSphereParam;

import java.awt.geom.Point2D;

/**
 * Moves a sphere from one to another position. A sphere can not be positioned where another sphere is already located.
 * If that's the case the sphere stays at its old position. All vertices assigned to this sphere move with the sphere.
 */
public class MoveSphereLogAction extends LogAction {
    /**
     * The old position.
     */
    private Point2D.Double oldPosition;
    /**
     * The selected sphere.
     */
    private Sphere sphere;
    /**
     * The new position of the sphere.
     */
    private Point2D position;

    /**
     * Moves the sphere which is defined in pParam.
     *
     * @param pParam The MoveSphereParam object, containing the sphere and the position data.
     */
    public MoveSphereLogAction(MoveSphereParam pParam) {
        super(LogEntryName.MOVE_SPHERE);
        parameters = pParam;
    }

    /**
     * Moves the sphere from its old to the new position.
     *
     * @param newPos  The new position to put the sphere.
     * @param pSphere The sphere to move.
     */
    public MoveSphereLogAction(Sphere pSphere,Point2D oldPos, Point2D newPos) {
        super(LogEntryName.MOVE_SPHERE);
        oldPosition = new Point2D.Double(oldPos.getX(),oldPos.getY());
        sphere = pSphere;
        position = newPos;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        if(parameters == null) {
            sphere.setVertices(sphere.getVertices());
            sphere.setCoordinates(position);
            createParameter(sphere, oldPosition, position);
        }else{
            Sphere sphere = ((MoveSphereParam)parameters).getSphere();
            Point2D oldPos = ((MoveSphereParam)parameters).getOldPos();
            Point2D newPos = ((MoveSphereParam)parameters).getNewPos();
                sphere.setCoordinates(newPos);
                Double dX = newPos.getX() - oldPos.getX();
                Double dY = newPos.getY() - oldPos.getY();
                for(Vertex vertex : sphere.getVertices()) {
                    Point2D point = new Point2D.Double(vertex.getCoordinates().getX() + dX, vertex.getCoordinates()
                            .getY() + dY);
                    vertex.setCoordinates(point);
                    Layout layout = vv.getGraphLayout();
                    layout.setLocation(vertex, point);
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
        Sphere sphere = ((MoveSphereParam)parameters).getSphere();
        Point2D oldPos = ((MoveSphereParam)parameters).getOldPos();
        Point2D newPos = ((MoveSphereParam)parameters).getNewPos();
        MoveSphereParam moveSphereParam = new MoveSphereParam(sphere, newPos, oldPos);
        MoveSphereLogAction moveSphereLogAction = new MoveSphereLogAction(moveSphereParam);
        moveSphereLogAction.action();
    }


    public void createParameter(Sphere sphere, Point2D oldPosition, Point2D newPosition) {
        parameters = new MoveSphereParam(sphere, oldPosition, newPosition);
    }
}
