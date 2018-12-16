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
    private String parameters;
    private LocalDateTime time;

    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @Column(name = "GRAPH")
    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "LOGENTRYNAME")
    public LogEntryName getLogEntryName() {
        return logEntryName;
    }

    public void setLogEntryName(LogEntryName logEntryName) {
        this.logEntryName = logEntryName;
    }

    @Column(name = "PARAMETERS")
    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    @Column(name = "TIME")
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() +
                " GraphId: " + this.getGraph() +
                " LogEntryName: " + this.getLogEntryName() +
                " parameters: " + this.getParameters() +
                " Time: " + this.getTime();
    }
}
