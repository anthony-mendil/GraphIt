package actions.edit;

import actions.LogAction;
import graph.graph.Edge;
import graph.graph.StrokeType;
import log_management.LogEntryName;
import log_management.parameters.edit.EditEdgesStrokeParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Collection;

/**
 * Changes the StrokeType from a collection of edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     * all edges which should get the new stoke type
     */
    Collection<Edge> edges;

    /**
     * the new stroke type
     */
    StrokeType newStroke;

    /**
     * Changes the stroke type from all passed edges
     * @param strokeType the new stroke type
     * @param edges the collection of edges where to change the stoke type
     */
    public EditEdgesStrokeLogAction(StrokeType strokeType, Collection<Edge> edges) {
        super(LogEntryName.EDIT_EDGES_SIZE);
        throw new NotImplementedException();
    }

    /**
     * Change the stroke type from all defined edges in pParam.
     * @param pParam the EditEdgesStrokeParam, containing all edges where to change the stroke type
     */
    public EditEdgesStrokeLogAction(EditEdgesStrokeParam pParam) {
        super(LogEntryName.EDIT_EDGES_SIZE);
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
