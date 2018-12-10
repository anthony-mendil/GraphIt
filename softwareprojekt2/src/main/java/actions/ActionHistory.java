package actions;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * a bounded history of actions
 */
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
     */
    public void execute(Action action){
        throw new NotImplementedException();
    }

    /**
     * undo for a current action
     */
    public void undo(){
        throw new NotImplementedException();
    }

    /**
     * redo for a current action
     */
    public void redo(){
        throw new NotImplementedException();
    }
}
