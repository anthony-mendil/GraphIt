package gui;

import actions.LogEntryName;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Handles the event when a menuitem is selected from the log filter options and filters the logs accordingly
 * to the selection.
 */
public class AnalysisTypeHandler implements EventHandler<ActionEvent> {
    /**
     * The logtype to filter for.
     */
    private final LogEntryName type;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;

    /**
     * TODO
     * @param pC
     * @param type
     */
    AnalysisTypeHandler(Controller pC, LogEntryName type) {
        c = pC;
        this.type = type;
    }

    /**
     * When the event is fired, it calls the filter function for the different log filter types.
     */
    @Override
    public void handle(ActionEvent evt) {
        c.filterLogs(type);
    }
}
