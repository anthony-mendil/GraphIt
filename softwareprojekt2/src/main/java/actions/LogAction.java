package actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import log_management.DatabaseManager;
import log_management.json_deserializers.Point2DDeserializer;
import log_management.json_serializers.Point2DSerializer;
import log_management.parameters.Param;
import log_management.tables.Log;
import lombok.Getter;
import lombok.Setter;

import java.awt.geom.Point2D;
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
        log.setGraph(DatabaseManager.getInstance().getGraphDao().get(-1).get());
        log.setLogEntryName(logEntryName);
        log.setTime(time);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DSerializer());
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DDeserializer());
        Gson gson = gsonBuilder.create();

        String paramString = null;
        try {
            paramString = gson.toJson(parameters);
        } catch (Exception e) {}
        log.setParameters(paramString);
        return log;
    }

}
