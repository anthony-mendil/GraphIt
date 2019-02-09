package actions.other;

import actions.GraphAction;
import graph.graph.FunctionMode;
import gui.Values;

/**
 * Changes the mode either to "Ersteller"/"Bearbeiter"/ "Auswerter".
 */
public class SwitchModiAction extends GraphAction {
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
    public SwitchModiAction(FunctionMode newMode) {
        mode = newMode;
        oldMode = Values.getInstance().getMode();

    }

    @Override
    public void action() {
        Values.getInstance().setMode(mode);
        System.out.println(Values.getInstance().getMode().name());
        if (mode == FunctionMode.EDIT) {
            notifyObserverEditMode();
        }
        else {
            notifyObserverFunctionMode(mode);
        }
    }

    @Override
    public void undo() {
    }
}
