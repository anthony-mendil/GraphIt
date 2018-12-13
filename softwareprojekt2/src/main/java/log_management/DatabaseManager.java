package log_management;

import actions.ObserverSyndrom;
import log_management.dao.GraphDao;
import lombok.Data;

import java.sql.SQLException;

@Data
public class DatabaseManager implements ObserverSyndrom {

    /**
     * is true if the user is in edit mode and the protocol gets recorded and stored in the database
     */
    private boolean shouldRecord;

    /**
     * the log_management.dao for graphs
     */
    private GraphDao graphDap;

    public void setup() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateGraph() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFunctionMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNewGraph() {
        throw new UnsupportedOperationException();
    }
}