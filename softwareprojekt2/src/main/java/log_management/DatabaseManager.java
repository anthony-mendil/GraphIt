package log_management;

import actions.ObserverSyndrom;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gui.Values;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;
import lombok.Data;

/**
 * the database manager, for managing the database access
 */
@Data
@Singleton
public class DatabaseManager implements ObserverSyndrom {
    /**
     * the current Values
      */
   @Inject
   private Values values;

    /**
     * the log_management.dao for graphs for accessing the database
     */
    private GraphDao graphDap;

    /**
     * the log_management.dao for logs for accessing the database
     */
    private LogDao logDao;

    /**
     *
     */
    public void addEntryDatabase(){

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