package actions.other;

import actions.GraphAction;
import graph.graph.FunctionMode;

import java.util.function.Function;

/*
 * Changes the mode either to "Ersteller"/"Betrachter"
 */
public class SwitchModiEditorAction extends GraphAction {
    /**
     * the function mode to change to
     */
    private FunctionMode mode;
    /**
     * the old function mode
     */
    private FunctionMode oldMode;
    /**
     * Constructor in case the user changes the mode.
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
