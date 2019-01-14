package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveSphereParam;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

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
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        graph.addSphere(point2D);
        if(parameters == null) {
            parameters = createParameter();
        }
        vv.repaint();
        DatabaseManager.addEntryDatabase(this);
    }


    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public AddRemoveSphereParam createParameter() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        List<Sphere> spheres = new LinkedList<>(pickedState.getPicked());
        return new AddRemoveSphereParam(spheres);
    }
}
