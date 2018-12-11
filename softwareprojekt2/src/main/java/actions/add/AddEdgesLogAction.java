package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.LogEntryName;
import log_management.parameters.add.AddEdgesParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

/**
 * Adds a single/multiple edge/s to the graph.
 */
public class AddEdgesLogAction extends LogAction {
    /**
     * Constructor in case several edges shall be added.
     *
     * @param pCollectionPair
     */
    public AddEdgesLogAction(Collection<Pair<Vertex, Vertex>> pCollectionPair) {
        super(LogEntryName.ADD_EDGES);
        throw new NotImplementedException();
    }

    /**
     * This constructor will be used to realize the undo-method of the method RemoveEdgesLogAction.
     *
     * @param pAddEdgesParam
     */
    public AddEdgesLogAction(AddEdgesParam pAddEdgesParam) {
        super(LogEntryName.ADD_EDGES);
        throw new NotImplementedException();
    }

    /**
     * Adds the selected edges to the graph.
     */
    @Override
    public void action() {
        throw new NotImplementedException();
    }

    /**
     * Undoes the action.
     */
    @Override
    public void undo() {
        throw new NotImplementedException();
    }


    @Override
    public void createParameter() {
        throw new NotImplementedException();
    }
}
