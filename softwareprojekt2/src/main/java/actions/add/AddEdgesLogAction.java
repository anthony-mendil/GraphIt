package actions.add;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.add_remove.AddRemoveEdgesParam;

/**
 * Adds a single/multiple edge/s to the graph.
 */
public class AddEdgesLogAction extends LogAction {
    /**
     * Constructor in case several edges shall be added.
     *
     * @param pPair A pair of vertices that gets a new edge between them
     */
    public AddEdgesLogAction(Pair<Vertex, Vertex> pPair) {
        super(LogEntryName.ADD_EDGES);
        throw new UnsupportedOperationException();
    }

    /**
     * Adds Edges to the graph. This constructor will be used to realize the undo-method of
     * RemoveEdgesLogAction.
     *
     * @param pAddRemoveEdgesParam the parameter object, containing all edges to add.
     */
    public AddEdgesLogAction(AddRemoveEdgesParam pAddRemoveEdgesParam) {
        super(LogEntryName.ADD_EDGES);
        throw new UnsupportedOperationException();
    }

    /**
     * Adds the selected edges to the graph.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Undoes the action.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
