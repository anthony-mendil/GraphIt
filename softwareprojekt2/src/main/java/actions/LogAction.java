package actions;

import LogManagement.Parameters.Param;
import jdk.nashorn.internal.objects.annotations.Setter;
import log_management.LogEntryName;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;


public abstract class LogAction extends Action{

    @Getter
    @lombok.Setter
    private LogEntryName logEntryName;
    @Getter
    @lombok.Setter
    private Param parameters;
    @Getter
    private LocalDateTime time;

    public LogAction(LogEntryName nLogEntryName, Param nParameters) {
        logEntryName = nLogEntryName;
        parameters = nParameters;
        time = LocalDateTime.now();
    }
}
