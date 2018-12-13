package actions;

import java.util.LinkedList;

/**
 * superclass of all actions
 */
public abstract class Action {

    LinkedList<ObserverSyndrom> observers;

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

    public void attach (ObserverSyndrom o) {
        observers.add(o);
    }

    protected void notifyObserver(){
        for (ObserverSyndrom o: observers) {
            o.update();
        }
    }
}
