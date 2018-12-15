package log_management.tables;

import log_management.GraphAndLogId;
import actions.LogEntryName;
import log_management.parameters.Param;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("all")
@Entity
@IdClass(GraphAndLogId.class)
@Table(name = "LOGS", schema = "PUBLIC", catalog = "TEST")
public class Log {
    @Id
    private int id;
    @Id
    private int graphId;
    private LogEntryName logEntryName;
    private Param parameters;
    private LocalDateTime time;

    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "GRAPHID")
    public int getGraphId() {
        return graphId;
    }

    public void setGraphId(int graphId) {
        this.graphId = graphId;
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
    public Param getParameters() {
        return parameters;
    }

    public void setParameters(Param parameters) {
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
                " GraphId: " + this.getGraphId() +
                " LogEntryName: " + this.getLogEntryName() +
                " parameters: " + this.getParameters() +
                " Time: " + this.getTime();
    }
}
