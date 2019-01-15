package actions;

import com.google.inject.Singleton;

/**
 * A bounded history of actions.
 */
@Singleton
public class ActionHistory {
    /**
     * Upper bound of actions.
     */
    private static final int maxActions = 10;

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
        try{
            actions[current].undo();
            current--;
        }catch(UnsupportedOperationException e){
            System.err.println("Can't undo further more actions.");
            throw new UnsupportedOperationException();

        }
    }

    /**
     * Redo for a current action.
     */
    public void redo() {
        try{
            current++;
            actions[current].action();
        }catch(UnsupportedOperationException e){
            System.err.println("Can't redo the latest action.");
            throw new UnsupportedOperationException();
        }
    }
}
