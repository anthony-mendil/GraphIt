package actions;

import com.google.inject.Singleton;

/**
 * a bounded history of actions
 */
@Singleton
public class ActionHistory {
    /**
     * upper bound of actions
     */
    private static final int maxActions = 10;

    /**
     * saved actions
     */
    private Action[] actions = new Action[maxActions];

    /**
     * the current action in actions
     */
    private int current = -1;

    /**
     * adds a actions to the history and executes it
     *
     * @param action the action to execute
     */
    public void execute(Action action) {
        throw new UnsupportedOperationException();
    }

    /**
     * undo for a current action
     */
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * redo for a current action
     */
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
