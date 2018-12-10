package actions.remove;

import actions.LogAction;
import graph.graph.Edge;
import log_management.LogEntryName;
import log_management.parameters.add.AddEdgesParam;
import log_management.parameters.remove.RemovesEdgesParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Removes edges from the syndrom-graph.
 */
public class RemoveEdgesLogAction extends LogAction {
    /**
     * all edge which should be removed
     */
    Collection<Edge> edges;

    /**
     * Removes all passed edges from the graph.
     *
     * @param edges collection of edges which should be removed
     */
    public RemoveEdgesLogAction(Collection<Edge> edges) {
        super(LogEntryName.REMOVE_EDGES);
        throw new NotImplementedException();
    }

    /**
     * Removes all edges with are defined in pParam.
     *
     * @param pParam the RemoveEdgesParam, containing all edges to remove
     */
    public RemoveEdgesLogAction(RemovesEdgesParam pParam) {
        super(LogEntryName.REMOVE_EDGES);
        throw new NotImplementedException();
    }

    /**
     * Adds all edges with are defined in pParam.
     *
     * @param pParam the AddEdgesParam, containing all edges to remove
     */
    public RemoveEdgesLogAction(AddEdgesParam pParam) {
        super(LogEntryName.REMOVE_EDGES);
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
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
}
