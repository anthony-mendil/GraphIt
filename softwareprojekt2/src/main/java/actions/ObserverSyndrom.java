package actions;

import graph.graph.FunctionMode;

/**
 * Observer interface, for observing components.
 * @author Clement Phung, Nina Unterberg
 */
public interface ObserverSyndrom {
    /**
     * Calls update if the graph needs to be updated.
     */
    void updateGraph();

    /**
     * Calls if the function mode changed.
     *
     * @param mode The function mode td change to.
     */
    void updateFunctionMode(FunctionMode mode);

    /**
     * Calls if the function mode changed into edit mode.
     */
    void updateEditMode();

    /**
     * Calls if a new graph was created or imported.
     */
    void updateNewGraph();
}
