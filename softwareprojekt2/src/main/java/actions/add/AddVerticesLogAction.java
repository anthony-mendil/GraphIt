package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import javafx.util.Pair;
import log_management.parameters.add_remove.AddRemoveVerticesParam;

import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Adds a collection of vertices to the graph. AddVerticesLogAction reverts the RemoveVerticesLogAction. The different
 * constructors depict different application scenarios.
 */
public class AddVerticesLogAction extends LogAction {
    /**
     * Map with vertices and corresponding pairs, containing the sphere id and sphere annotation.
     */
    Map<Vertex, Pair<Integer, String>> vertexPairMap;

    private Point2D pos;

    /**
     * Adds all vertices that are defined in pParam. Also used to implement the undo-method of
     * RemoveVerticesLogAction.
     *
     * @param pParam The parameter object, containing all vertices to add.
     */
    public AddVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.ADD_VERTICES);
    }


    /**
     * Adds a vertex at given point.
     *
     * @param point Point of the mouse event where the vertex should be added.
     */
    public AddVerticesLogAction(Point2D point) {
        super(LogEntryName.ADD_VERTICES);
        pos = point;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        Sphere sp = pickSupport.getSphere(pos.getX(), pos.getY());
        Vertex newVertex = graph.addVertex(pos, sp);
        vv.getGraphLayout().setLocation(newVertex, pos);
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
