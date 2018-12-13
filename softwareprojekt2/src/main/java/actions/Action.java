package actions;

import java.util.LinkedList;

/**
 * superclass of all actions
 */
public abstract class Action {

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
     * Attaches the Observer so that the observer can observe it.
     * @param o the observer to attach
     */
    public static void attach(ObserverSyndrom o) {
        observers.add(o);
    }

    /**
     *
     */
    protected void notifyObserverGraph(){
        for (ObserverSyndrom o: observers) {
            o.updateGraph();
        }
    }

    protected void notifyObserverFunctionMode(){
        for (ObserverSyndrom o: observers) {
            o.updateFunctionMode();
        }
    }

    protected void notifyObserverNewGraph(){
        for (ObserverSyndrom o: observers) {
            o.updateNewGraph();
        }
    }
}
