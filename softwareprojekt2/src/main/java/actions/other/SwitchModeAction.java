package actions.other;

import actions.ActionHistory;
import actions.GraphAction;
import graph.graph.FunctionMode;

/**
 * Changes the mode either to "Ersteller"/"Bearbeiter"/ "Auswerter".
 */
public class SwitchModeAction extends GraphAction {
    /**
     * The function mode to change to.
     */
    private FunctionMode mode;

    /**
     * Constructor in case the user changes the mode.
     *
     * @param newMode The new mode.
     */
    public SwitchModeAction(FunctionMode newMode) {
        mode = newMode;
    }

    @Override
    public void action() {
        values.setMode(mode);
        if (mode == FunctionMode.EDIT) {
            notifyObserverEditMode();
            syndrom.setGraphMouseModeEdit();
        } else {
            notifyObserverFunctionMode(mode);
            if (mode == FunctionMode.TEMPLATE) {
                syndrom.setGraphMouseModeEdit();
            } else {
                // mode ==  FunctionMode.ANALYSE
                syndrom.setGraphMouseModeAnalyse();
            }
        }
        ActionHistory.getInstance().wipe();
    }

    @Override
    public void undo() {
        //no undo-operation needed
    }
}
