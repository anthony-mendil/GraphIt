package log_management;

import java.io.Serializable;

public class GraphAndLogId implements Serializable {
    protected int id;
    protected int graphId;

    public GraphAndLogId() {
        throw new UnsupportedOperationException();
    }

    public GraphAndLogId(int id, int graphId) {
        this.id = id;
        this.graphId = graphId;
    }

}
