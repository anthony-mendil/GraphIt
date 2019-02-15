package actions;

import com.google.inject.Singleton;
import graph.graph.FunctionMode;
import gui.Values;

/**
 * A bounded history of actions.
 */
@Singleton
public class ActionHistory {
    /**
     * Upper bound of actions.
     */
    private static final int maxActions = 100;

    /**
     * Saved actions.
     */
    private Action[] actions = new Action[maxActions];

    /**
     * The current action in actions.
     */
    private int current = -1;

    private static ActionHistory history;

    /**
     * Adds an action to the history and executes it.
     *
     * @param action The action to execute.
     */
    public void execute(Action action) {
        current++;
        if(current == maxActions) {
            for (int i = 0; i < maxActions - 1; i++) {
                actions[i] = actions[i + 1];
            }
            current = maxActions - 1;
        }
        actions[current] = action;
        actions[current].action();
        for(int i = current+1; i<maxActions; i++){
            actions[i] = null;
        }
    }

    public static ActionHistory getInstance(){
        if (history == null){
            history = new ActionHistory();
        }
        return history;
    }

    /**
     * Undo for a current action.
     */
    public void undo() {
        try {
            if (current > 0){
                actions[current].undo();
            current--;
        }
        }catch(UnsupportedOperationException e){
            System.err.println("Can't undo further more actions.");

        }
    }

    /**
     * Redo for a current action.
     */
    public void redo() {
        try{
            if(actions[current+1] != null) {
                current++;
                actions[current].action();
            }
        }catch(UnsupportedOperationException e){
            System.err.println("Can't redo the latest action.");
        }
    }

    /**
     * Removes the last action from the ActionHistory.
     */
    public void removeLastEntry(){
        actions[current] = null;
        current--;
    }
}
