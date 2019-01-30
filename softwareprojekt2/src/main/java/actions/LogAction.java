package actions;

import com.google.gson.Gson;
import graph.graph.Sphere;
import log_management.DatabaseManager;
import log_management.parameters.Param;
import log_management.tables.Log;
import lombok.Getter;
import lombok.Setter;



import java.time.LocalDateTime;

/**
 * Represents an action, which will be logged into the database.
 */
public abstract class LogAction extends GraphAction {

    @Getter
    @lombok.Setter
    /**
     * The name of the action.
     */
    private LogEntryName logEntryName;
    @Getter
    @Setter
    /**
     * The parameters, which were used in the action.
     */
    protected Param parameters;
    @Getter
    /**
     * The time, the action gets called.
     */
    private LocalDateTime time;

    /**
     * Creates an action in the program, which will also be persisted into in the database.
     * @param nLogEntryName The name of the log entry.
     */
    public LogAction(LogEntryName nLogEntryName) {
        logEntryName = nLogEntryName;
        time = LocalDateTime.now();
    }

    public Log createLog() {
        Log log = new Log();
        log.setGraph(DatabaseManager.getInstance().getGraph());
        log.setLogEntryName(logEntryName);
        log.setTime(time);

        String paramString = null;
        try {
            paramString = new Gson().toJson(parameters);
        } catch (Exception e) {}
        log.setParameters(paramString);

        return log;
    }

}
