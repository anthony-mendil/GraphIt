package actions.edit;

import actions.LogAction;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesTypeParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Changes the EdgeType from a collection of edges.
 */
public class EditEdgesTypeLogAction extends LogAction {
    /**
     * all edge  which should get the new EdgeType
     */
    Collection<Edge> edges;

    /**
     * the new edge type
     */
    EdgeArrowType edgeType;

    /**
     * Changes the edge type from all defined edges in pParam.
     * @param pParam the EditEdgesTypeParam, containing all edges where to change the edge type
     */
    public EditEdgesTypeLogAction(EditEdgesTypeParam pParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        throw new NotImplementedException();
    }

    /**
     * Changes the edge type from all passed edges
     * @param edges the collection of edges where to change the edge type
     * @param edgeType the new edge type
     */
    public EditEdgesTypeLogAction(Collection<Edge> edges, EdgeArrowType edgeType){
        super(LogEntryName.EDIT_EDGES_TYPE);
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
