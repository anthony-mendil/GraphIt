package actions.other;

import actions.GraphAction;
import graph.graph.FunctionMode;

/**
 * Changes the mode either to "Ersteller"/"Betrachter"/ "Auswerter".
 */
public class SwitchModiEditorAction extends GraphAction {
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
     * @param mode The new mode.
     */
    public SwitchModiEditorAction(FunctionMode mode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
