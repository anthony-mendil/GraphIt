package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import log_management.parameters.add.AddEdgeParam;
import log_management.parameters.add.AddEdgesParam;

import java.util.Collection;

/**
 * Adds a single/multiple edge/s to the graph.
 */
public class AddEdgesLogAction extends LogAction {
    /**
     * Constructor in case several edges shall be added.
     * @param pCollectionPair
     */
    public AddEdgesLogAction(Collection<Pair<Vertex,Vertex>> pCollectionPair) {
        super(LogEntryName.ADD_EDGES);
    }
    /**
     * This constructor will be used to realize the undo-method of the method RemoveEdgesLogAction.
     * @param pAddEdgesParam
     *
     */
    public AddEdgesLogAction(AddEdgesParam pAddEdgesParam) {
        super(LogEntryName.ADD_EDGES);
    }

    /**
     * Adds the selected edges to the graph.
     */
    @Override
    public void action() {
        // other stuff that is done when actions is performed

        LogDatabaseManager.addLogEntryToDatabase(this);
    }

    /**
     * Undoes the action.
     */
    @Override
    public void undo() {
        // stuff that is done when undoing
        // and adding the according actions to the database
        // (opposite actions)
    }


}
