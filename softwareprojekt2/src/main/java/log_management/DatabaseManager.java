package log_management;

import actions.Action;
import actions.ObserverSyndrom;
import com.google.inject.Singleton;
import io.GXLio;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;
import log_management.tables.Graph;
import lombok.Data;

/**
 * the database manager, for managing the database access
 */
@Data
@Singleton
public class DatabaseManager implements ObserverSyndrom {

    /**
     * the log_management.dao for graphs for accessing the database
     */
    private GraphDao graphDao;

    /**
     * the log_management.dao for logs for accessing the database
     */
    private LogDao logDao;

    /**
     * the gxl io to get the gxl string
     */
    private GXLio gxlIo;

    /**
     *
     */
    private Graph graph;

    public DatabaseManager(){
        throw new UnsupportedOperationException();
    }

    /**
     * adds a log to the database (through dao)
     * @param action TODO
     */
    public static void addEntryDatabase(Action action) {
        throw new UnsupportedOperationException();
    }

    /**
     * setup for the database
     */
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
    public void updateEditMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNewGraph() {
        throw new UnsupportedOperationException();
    }
}