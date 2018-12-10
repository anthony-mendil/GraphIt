package actions.add;

import actions.LogAction;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.add.AddVertexParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;

/**
 * The action adds a vertex to the graph. A vertex is always bound to a sphere.
 */
public class AddVertexLogAction extends LogAction {
    /**
     * point of the mouse event where the vertex should be added
     */
    private Point2D point;
    /**
     * the internal state of the current graph
     */
    private SyndromGraph graph;
    /**
     * the sphere in which the vertex should be added
     */
    private Sphere sphere;
    /**
     * the new created vertex
     */
    private Vertex vertex;

    /**
     * @param point point of the mouse event where the vertex should be added
     */
    public AddVertexLogAction(Point2D point) {
        super(LogEntryName.ADD_VERTEX);
        throw new NotImplementedException();
    }

    @Override
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
        setParameters(new AddVertexParam(vertex, sphere.getId(), sphere.getName()));
    }
}
