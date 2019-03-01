package gui;

import actions.LogEntryName;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AnalysisTypeHandler implements EventHandler<ActionEvent> {
    private final LogEntryName type;
    private final Controller c;

    public AnalysisTypeHandler(Controller pC, LogEntryName type) {
        c = pC;
        this.type = type;
    }

    @Override
    public void handle(ActionEvent evt) {
        c.filterLogs(type);
    }
}
