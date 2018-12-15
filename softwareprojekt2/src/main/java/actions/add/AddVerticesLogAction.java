package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveVerticesParam;


import java.awt.geom.Point2D;
import java.util.Map;

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
     * Adds all vertices that are defined in pParam. Also used to implement the undo-method of
     * RemoveVerticesLogAction.
     *
     * @param pParam the AddVerticesParameters, containing all vertices to add.
     */
    public AddVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.ADD_VERTICES);
    }


    /**
     * Adds a vertex at point
     *
     * @param point point of the mouse event where the vertex should be added
     */
    public AddVerticesLogAction(Point2D point) {
        super(LogEntryName.ADD_VERTEX);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
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
