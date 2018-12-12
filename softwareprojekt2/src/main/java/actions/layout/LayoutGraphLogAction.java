package actions.layout;

import actions.LogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import log_management.LogEntryName;
import log_management.parameters.move.LayoutParam;

/**
 * Layouts the graph according to a previously defined layout.
 */
public class LayoutGraphLogAction extends LogAction {

    /**
     * the old layout
     */
    Layout oldLayout;

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutGraphLogAction() {
        super(LogEntryName.EDIT_LAYOUT);
        throw new UnsupportedOperationException();
    }

    /**
     * Layouts the graph according to the passed layout.
     *
     * @param pParam the LayoutParam, containing the layout to reset to
     */
    public LayoutGraphLogAction(LayoutParam pParam) {
        super(LogEntryName.EDIT_LAYOUT);
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
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
