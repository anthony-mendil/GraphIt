package actions.remove;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.add.AddVerticesParam;
import log_management.parameters.remove.RemoveVerticesParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Removes vertices from the syndrom-graph.
 */
public class RemoveVerticesLogAction extends LogAction {

    /**
     * all vertices which should be removed
     */
    Collection<Vertex> vertices;

    /**
     * Removes all passed vertices from the graph.
     *
     * @param vertices collection of vertices which should be removed
     */
    public RemoveVerticesLogAction(Collection<Vertex> vertices) {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices which are defined in pParam.
     *
     * @param pParam the RemoveVerticesParam, containing all vertices to remove
     */
    public RemoveVerticesLogAction(RemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices with are defined in pParam.
     *
     * @param pParam the AddVerticesParam, containing all vertices to remove
     */
    public RemoveVerticesLogAction(AddVerticesParam pParam) {
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
