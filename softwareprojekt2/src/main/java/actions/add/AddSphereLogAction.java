package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import actions.remove.RemoveSphereLogAction;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveSphereParam;

import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 * Adds a sphere to the graph.
 */
public class AddSphereLogAction extends LogAction {

    private Point2D point2D;

    /**
     * Constructor in case the user clicks somewhere on blank space to create a sphere.
     *
     * @param pPoint2D The point where to add the sphere and it refers to the upper left corner of the sphere.
     */
    public AddSphereLogAction(Point2D pPoint2D) {
        super(LogEntryName.ADD_SPHERE);
        point2D = pPoint2D;
    }

    /**
     * Adds a sphere to the graph.
     * Constructor which will be used to realize the undo-method of RemoveSphereLogAction.
     *
     * @param pAddRemoveSphereParam The parameter object that contains every parameter that is needed.
     */
    public AddSphereLogAction(AddRemoveSphereParam pAddRemoveSphereParam) {
        super(LogEntryName.ADD_SPHERE);
        parameters = pAddRemoveSphereParam;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        if (parameters == null) {
                graph.addSphere(point2D);
                Sphere sp = pickSupport.getSphere(point2D.getX(),point2D.getY());
                createParameter(sp);
            } else {
                graph.getSpheres().add(((AddRemoveSphereParam) parameters).getSphere());
            }
            vv.repaint();
            syndrom.getVv2().repaint();

            DatabaseManager databaseManager = DatabaseManager.getInstance();
            databaseManager.addEntryDatabase(createLog());

            notifyObserverGraph();
    }

    @Override
    public void undo() {
        RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction((AddRemoveSphereParam)parameters);
        removeSphereLogAction.action();
    }


    public void createParameter(Sphere sphere) {
        parameters = new AddRemoveSphereParam(sphere,new LinkedList<>());
    }
}
