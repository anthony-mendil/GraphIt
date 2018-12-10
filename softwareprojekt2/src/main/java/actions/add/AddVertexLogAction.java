package actions.add;

import actions.GraphAction;
import actions.LogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import log_management.LogEntryName;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;

/**
 * The action adds a vertex to the graph. A vertex is always bound to a sphere.
 */
public class AddVertexLogAction extends LogAction {
    private Point2D e;
    private SyndromGraph graph;
    private Sphere sphere;

    /**
     * @param point the coordinates where the vertex should be added
     */
    public AddVertexLogAction(Point2D point) {
        super(LogEntryName.ADD_VERTEX);
        throw new NotImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
