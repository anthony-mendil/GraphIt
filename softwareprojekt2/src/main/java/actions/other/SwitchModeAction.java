package actions.other;

import actions.ActionHistory;
import actions.GraphAction;
import graph.graph.FunctionMode;
import gui.Values;

/**
 * Changes the mode either to "Ersteller"/"Bearbeiter"/ "Auswerter".
 */
public class SwitchModeAction extends GraphAction {
    /**
     * The function mode to change to.
     */
    private FunctionMode mode;
    /**
     * The old function mode.
     */
    private FunctionMode oldMode;

    /**
     * Constructor in case the user changes the mode.
     * @param newMode The new mode.
     */
    public SwitchModeAction(FunctionMode newMode) {
        mode = newMode;
        oldMode = Values.getInstance().getMode();
    }

    @Override
    public void action() {
        Values.getInstance().setMode(mode);
        if (mode == FunctionMode.EDIT) {
            notifyObserverEditMode();
            syndrom.setPluggableModeEdit();
        }
        else {
            notifyObserverFunctionMode(mode);
            if (mode == FunctionMode.TEMPLATE){
                syndrom.setPluggableModeEdit();
            } else {
                // mode ==  FunctionMode.ANALYSE
                syndrom.setPluggableModeAnalyse();
            }
        }
        ActionHistory.getInstance().wipe();
    }

    @Override
    public void undo() {

    }
}
