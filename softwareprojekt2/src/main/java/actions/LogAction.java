package actions;

import log_management.LogEntryName;
import log_management.parameters.Param;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public abstract class LogAction extends GraphAction {

    @Getter
    @lombok.Setter
    private LogEntryName logEntryName;
    @Getter
    @Setter
    private Param parameters;
    @Getter
    private LocalDateTime time;

    public LogAction(LogEntryName nLogEntryName) {
        logEntryName = nLogEntryName;
        time = LocalDateTime.now();
    }

    /**
     * creates the parameters object
     */
    public abstract void createParameter();
}
