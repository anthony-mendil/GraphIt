package log_management.tables;

import actions.LogEntryName;

import javax.persistence.*;
import java.time.LocalDateTime;

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
     *
     * @return
     */
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @ManyToOne
    @Column(name = "GRAPH")
    public Graph getGraph() {
        return graph;
    }

    /**
     *
     * @return
     */
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    /**
     *
     * @return
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "LOGENTRYNAME")
    public LogEntryName getLogEntryName() {
        return logEntryName;
    }

    /**
     *
     * @return
     */
    public void setLogEntryName(LogEntryName logEntryName) {
        this.logEntryName = logEntryName;
    }

    /**
     *
     * @return
     */
    @Column(name = "PARAMETERS")
    public String getParameters() {
        return parameters;
    }

    /**
     *
     * @return
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @return
     */
    @Column(name = "TIME")
    public LocalDateTime getTime() {
        return time;
    }

    /**
     *
     * @return
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     *
     * @return
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
