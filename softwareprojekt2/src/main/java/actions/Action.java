package actions;

import java.util.LinkedList;

/**
 * Superclass of all actions.
 */
public abstract class Action {
    /**
     * List of all observers that needs to be updated, if an action is called (based on Model-View-Controller pattern).
     */
    private static LinkedList<ObserverSyndrom> observers;

    /**
     * Executes the defined behavior of the action.
     */
    public abstract void action();

    /**
     * Reverts the action. The internal state of the graph is the same as before the action was executed.
     */
    public abstract void undo();

    /**
     * Executes the defined behavior of the action again.
     */
    public abstract void redo();

    /**
     * Attaches the observer so that the observer can observe it.
     * @param o The observer to attach.
     */
    public static void attach(ObserverSyndrom o) {
        observers.add(o);
    }

    /**
     * Notify the observer if the graph changes.
     */
    protected void notifyObserverGraph(){
        for (ObserverSyndrom o: observers) {
            o.updateGraph();
        }
    }

    /**
     * Notify the observer if the application changes the mode.
     */
    protected void notifyObserverFunctionMode(){
        for (ObserverSyndrom o: observers) {
            o.updateFunctionMode();
        }
    }

    /**
     * Notify the observer if a new graph gets loaded.
     */
    protected void notifyObserverNewGraph(){
        for (ObserverSyndrom o: observers) {
            o.updateNewGraph();
        }
    }

    /**
     * Notify the observer if the application is in edit mode.
     */
    protected void notifyObserverEditMode(){
        for (ObserverSyndrom o: observers) {
            o.updateEditMode();
        }
    }
}
