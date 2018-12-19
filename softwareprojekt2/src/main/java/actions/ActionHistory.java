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

    /**
     * Adds an action to the history and executes it.
     *
     * @param action The action to execute.
     */
    public void execute(Action action) {
        throw new UnsupportedOperationException();
    }

    /**
     * Undo for a current action.
     */
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Redo for a current action.
     */
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
