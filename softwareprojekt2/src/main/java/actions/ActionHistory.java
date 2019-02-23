package actions;

import com.google.inject.Singleton;
import org.apache.log4j.Logger;

/**
 * A bounded history of actions.
 */
@Singleton
public class ActionHistory {
    /**
     * Upper bound of actions.
     */
    private static final int MAX_ACTIONS = 100;

    /**
     * Saved actions.
     */
    private Action[] actions = new Action[MAX_ACTIONS];

    /**
     * The current action in actions.
     */
    private int current = -1;
    private static Logger logger = Logger.getLogger(ActionHistory.class);
    private static ActionHistory history;

    /**
     * Adds an action to the history and executes it.
     *
     * @param action The action to execute.
     */
    public void execute(Action action) {
        current++;
        if(current == MAX_ACTIONS) {
            System.arraycopy(actions,1,actions,0,MAX_ACTIONS-1);
        }
        actions[current] = action;
        actions[current].action();
        for(int i = current+1; i< MAX_ACTIONS; i++){
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
            if (current >= 0) {
                actions[current].undo();
                current--;
            }else{
                logger.info("Can't undo further more actions.");
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
            logger.info("Can't redo the latest action.");
        }
    }

    /**
     * Removes the last action from the ActionHistory.
     */
    public void removeLastEntry(){
        if(current >= 0) {
            actions[current] = null;
            current--;
        }
    }

    /**
     * Wipes the existing ActionHistory.
     */
    public void wipe(){
        actions = new Action[MAX_ACTIONS];
        current = -1;
    }
}
