package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.LogEntryName;
import log_management.parameters.add.AddVerticesParam;
import log_management.parameters.remove.RemoveVerticesParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.util.Map;

/*
    Nutzung bei Undo von deleteVertices
 */

/**
 * Adds a collection of vertices to the graph. AddVerticesLogAction reverts the RemoveVerticesLogAction. The different
 * constructors depict different application scenarios.
 */
public class AddVerticesLogAction extends LogAction {
    /**
     * map with vertices and corresponding pair, containing the sphere id and sphere annotation.
     */
    Map<Vertex, Pair<Integer, String>> vertexPairMap;

    /**
     * Adds all vertices that are defined in pParam.
     *
     * @param pParam the AddVerticesParameters, containing all vertices to add.
     */
    public AddVerticesLogAction(AddVerticesParam pParam) {
        super(LogEntryName.ADD_VERTICES);
    }

    /**
     * Adds all vertices that are defined in RemoveVerticesParameters.
     *
     * @param pParam the RemoveVerticesParameters, containing all vertices to add.
     */
    public AddVerticesLogAction(RemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Adds a vertex at point
     *
     * @param point point of the mouse event where the vertex should be added
     */
    public AddVerticesLogAction(Point2D point) {
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
        throw new NotImplementedException();
    }
}
