package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.EdgeArrowType;
import log_management.parameters.edit.EditEdgesTypeParam;
import lombok.Data;

/**
 * Changes the EdgeType from a collection of edges.
 */
@Data
public class EditEdgesTypeLogAction extends LogAction {

    /**
     * Changes the edge type from all defined edges in pParam.
     *
     * @param pParam The EditEdgesTypeParam object, containing all edges where to change the edge type.
     */
    public EditEdgesTypeLogAction(EditEdgesTypeParam pParam) {
        super(LogEntryName.EDIT_EDGES_TYPE);
        throw new UnsupportedOperationException();
    }

    /**
     * Changes the edge type from all passed edges
     *
     * @param pEdgeArrowType The new edge type.
     */
    public EditEdgesTypeLogAction(EdgeArrowType pEdgeArrowType) {
        super(LogEntryName.EDIT_EDGES_TYPE);
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


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
