package actions;

/**
 * Observer interface, for observing components
 */
public interface ObserverSyndrom {
    /**
     * calls update if the graph need to be updated
     */
    void updateGraph();

    /**
     * calls if the function mode changed
     */
    void updateFunctionMode();

    /**
     * calls if the function mode changed into edit mode
     */
    void updateEditMode();

    /**
     * calls if a new graph was created/ imported
     */
    void updateNewGraph();
}
