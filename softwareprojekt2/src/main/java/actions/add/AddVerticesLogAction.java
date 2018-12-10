package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.LogEntryName;
import log_management.parameters.add.AddVerticesParam;
import log_management.parameters.remove.RemoveVerticesParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

/**
 * Adds a collection of vertices to the graph. AddVerticesLogAction reverts the RemoveVerticesLogAction.
 */
public class AddVerticesLogAction extends LogAction {
    /**
     * map with vertices and corresponding pair, containing the sphere id and sphere annotation.
     */
    Map<Vertex, Pair<Integer, String>> vertexPairMap;

    /**
     *
     * @param pParam
     */
    public AddVerticesLogAction(AddVerticesParam pParam) {
        super(LogEntryName.ADD_VERTICES);
    }

    public AddVerticesLogAction(RemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
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
