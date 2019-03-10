package actions;


import lombok.Getter;
import org.apache.log4j.Logger;

/**
 * A bounded history of actions.
 * @author Clement Phung, Nina Unterberg
 */
public class ActionHistory {
    /**
     * Upper bound of actions.
     */
    private static final int MAX_ACTIONS = 100;
    /**
     * The logger of the action history.
     */
    private static Logger logger = Logger.getLogger(ActionHistory.class);
    /**
     * The action history itself.
     */
    private static ActionHistory history;
    /**
     * Saved actions.
     */
    private Action[] actions = new Action[MAX_ACTIONS];
    /**
     * The current action in actions.
     */
    @Getter
    private int current = -1;

    /**
     * Returning the unique instance of the action history.
     *
     * @return the instance of the action history
     */
    public static ActionHistory getInstance() {
        if (history == null) {
            history = new ActionHistory();
        }
        return history;
    }

    /**
     * Adds an action to the history and executes it.
     *
     * @param action The action to execute.
     */
    public void execute(Action action) {
        current++;
        if (current == MAX_ACTIONS) {
            System.arraycopy(actions, 1, actions, 0, MAX_ACTIONS - 1);
            current--;
        }
        actions[current] = action;
        actions[current].action();
        for (int i = current + 1; i < MAX_ACTIONS; i++) {
            actions[i] = null;
        }
    }

    /**
     * Undo for a current action.
     */
    public void undo() {
        if (current >= 0) {
            actions[current].undo();
            current--;
        } else {
            logger.info("Can't undo further more actions.");
        }
    }

    /**
     * Redo for a current action.
     */
    public void redo() {
        try {
            if (current < MAX_ACTIONS - 1 && actions[current + 1] != null) {
                current++;
                actions[current].action();
            }
        } catch (UnsupportedOperationException e) {
            logger.info("Can't redo the latest action.");
        }
    }

    /**
     * Removes the last action from the ActionHistory.
     */
    public void removeLastEntry() {
        if (current >= 0) {
            actions[current] = null;
            current--;
        }
    }

    /**
     * Wipes the existing ActionHistory.
     */
    public void wipe() {
        actions = new Action[MAX_ACTIONS];
        current = -1;
    }

    /**
     * Checks, whether the action has reached the upper bound.
     *
     * @return If the action was the last one.
     */
    public boolean isLast() {
        if (current < MAX_ACTIONS - 1) {
            return actions[current + 1] == null;
        }
        return current == MAX_ACTIONS - 1;
    }
}
