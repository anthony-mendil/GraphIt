package log_management.tables;

import actions.LogEntryName;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Represents the Log objects that are persisted into
 * the logs table of the database.
 */
@SuppressWarnings("all")
@Entity
@Table(name = "LOGS", schema = "PUBLIC", catalog = "TEST")
public class Log {
    @Id
    private int id;
    private Graph graph;
    private LogEntryName logEntryName;
    private String parameters = "";
    private LocalDateTime time;

    /**
     * Gets the id of the log entry.
     *
     * @return The id of the log entry.
     */
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the log entry.
     *
     * @param id The id of the log entry.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the graph which the action was performed on.
     *
     * @return The graph.
     */
    @ManyToOne
    @Column(name = "GRAPH")
    public Graph getGraph() {
        return graph;
    }

    /**
     * Sets the graph which the action was performed on.
     *
     * @param graph The graph.
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     * Gets the name of the log entry.
     *
     * @return The name of the log entry.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "LOGENTRYNAME")
    public LogEntryName getLogEntryName() {
        return logEntryName;
    }

    /**
     * Sets the name of the log entry.
     *
     * @param logEntryName The name of the log entry.
     */
    public void setLogEntryName(LogEntryName logEntryName) {
        this.logEntryName = logEntryName;
    }

    /**
     * Gets the parameters.
     *
     * @return The parameters in the JSON format.
     */
    @Column(name = "PARAMETERS")
    public String getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters.
     *
     * @param parameters The parameters in the JSON format.
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     * Gets the time when the corresponding action was created.
     *
     * @return The time when the corresponding action was created.
     */
    @Column(name = "TIME")
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets the time when the corresponding action was created.
     *
     * @param time The time when the corresponding action was created.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Combines all informations about this log entry into one
     * readable text.
     *
     * @return The formated informations about this log entry.
     */
    @Override
    public String toString() {
        return "Id: " + this.getId() +
                " GraphId: " + this.getGraph() +
                " LogEntryName: " + this.getLogEntryName() +
                " parameters: " + this.getParameters() +
                " Time: " + this.getTime();
    }
}
