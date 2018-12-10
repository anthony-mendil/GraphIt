package actions;

import log_management.LogEntryName;
import log_management.parameters.Param;
import lombok.Getter;

import java.time.LocalDateTime;


public abstract class LogAction extends GraphAction {

    @Getter
    @lombok.Setter
    private LogEntryName logEntryName;
    @Getter
    @lombok.Setter
    private Param parameters;
    @Getter
    private LocalDateTime time;

    public LogAction(LogEntryName nLogEntryName) {
        logEntryName = nLogEntryName;
        time = LocalDateTime.now();
    }
}
