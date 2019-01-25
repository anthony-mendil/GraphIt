package actions.move;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import log_management.DatabaseManager;
import log_management.parameters.move.MoveSphereParam;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * Moves a sphere from one to another position. A sphere can not be positioned where another sphere is already located.
 * If that's the case the sphere stays at its old position. All vertices assigned to this sphere move with the sphere.
 */
public class MoveSphereLogAction extends LogAction {
    /**
     * The old position.
     */
    private Point2D oldPosition;
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
        oldPosition = oldPos;
        sphere = pSphere;
        position = newPos;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        if(parameters == null) {
            Map<Sphere,Point2D> oldSphere = new HashMap<>();
            Map<Sphere,Point2D> newSphere = new HashMap<>();
            oldSphere.put(sphere,oldPosition);
            sphere.setCoordinates(position);
            newSphere.put(sphere,position);

            createParameter(oldSphere, newSphere);
        }else{
            Map<Sphere,Point2D> oldSphere = ((MoveSphereParam)parameters).getOldSphere();
            Map<Sphere,Point2D> newSphere = ((MoveSphereParam)parameters).getNewSphere();
            for(Map.Entry<Sphere,Point2D> entry : oldSphere.entrySet()){
                entry.getKey().setCoordinates(newSphere.get(entry.getKey()));
                Double dX = newSphere.get(entry.getKey()).getX() - oldSphere.get(entry.getKey()).getX();
                Double dY = newSphere.get(entry.getKey()).getY() - oldSphere.get(entry.getKey()).getY();
                for(Vertex vertex : entry.getKey().getVertices()){
                    Point2D point = new Point2D.Double(vertex.getCoordinates().getX() + dX, vertex.getCoordinates()
                            .getY() + dY);
                    vertex.setCoordinates(point);
                    Layout layout = vv.getGraphLayout();
                    layout.setLocation(vertex, point);
                }
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
        Map<Sphere,Point2D> oldSphere = ((MoveSphereParam)parameters).getOldSphere();
        Map<Sphere,Point2D> newSphere = ((MoveSphereParam)parameters).getNewSphere();
        MoveSphereParam moveSphereParam = new MoveSphereParam(newSphere,oldSphere);
        MoveSphereLogAction moveSphereLogAction = new MoveSphereLogAction(moveSphereParam);
        moveSphereLogAction.action();
    }


    public void createParameter(Map<Sphere,Point2D> oldSphere, Map<Sphere,Point2D> newSphere) {
        parameters = new MoveSphereParam(oldSphere, newSphere);
    }
}
