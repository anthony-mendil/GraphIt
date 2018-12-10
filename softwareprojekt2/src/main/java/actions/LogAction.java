package actions;

import LogManagement.Parameters.Param;
import edu.uci.ics.jung.algorithms.layout.Layout;
import jdk.nashorn.internal.objects.annotations.Setter;
import log_management.LogEntryName;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;


public abstract class LogAction extends GraphAction{

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
