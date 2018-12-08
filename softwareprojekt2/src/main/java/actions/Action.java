package actions;

import LogManagement.Parameters.Param;
import log_management.LogEntryName;

import java.time.LocalDateTime;

public abstract class Action {

    private LogEntryName logEntryName;
    private Param parameters;
    private LocalDateTime time;

    public Action(LogEntryName nLogEntryName, Param nParameters) {
        logEntryName = nLogEntryName;
        parameters = nParameters;
        time = LocalDateTime.now();
    }

    public void action() {
    }

    public void undo() {
    }

    public void redo() {
    }

    public LogEntryName getLogEntryName() {
        return logEntryName;
    }

    public void setLogEntryName(LogEntryName logEntryName) {
        this.logEntryName = logEntryName;
    }

    public Param getParameters() {
        return parameters;
    }

    public void setParameters(Param parameters) {
        this.parameters = parameters;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
