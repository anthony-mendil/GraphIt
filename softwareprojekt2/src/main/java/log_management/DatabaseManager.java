package log_management;

import actions.ObserverSyndrom;
import com.google.inject.Singleton;
import graph.graph.FunctionMode;
import io.GXLio;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;
import log_management.tables.Graph;
import log_management.tables.Log;
import lombok.Data;

/**
 * The database manager, for managing the database access.
 */
@Data
@Singleton
public class DatabaseManager implements ObserverSyndrom {

    /**
     * The log_management.dao GraphDao object for graphs, for accessing the database.
     */
    private GraphDao graphDao;

    /**
     * The log_management.dao LogDao object for logs, for accessing the database.
     */
    private LogDao logDao;

    /**
     * The GXLio object to get the GXL string.
     */
    private GXLio gxlIo;

    /**
     * The current graph object.
     */
    private Graph graph;

    /**
     * The current mode.
     */
    private FunctionMode mode;

    private static DatabaseManager databaseManager;

    /**
     * Creates a database manager organizing the database.
     */
    private DatabaseManager(){
        graphDao = new GraphDao();
        logDao = new LogDao();
        gxlIo = new GXLio();
    }

    public static DatabaseManager getInstance(){
        if (databaseManager == null){
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    /**
     * Adds a log to the database (through dao).
     * @param log The log for the called action.
     */
    public void addEntryDatabase(Log log) {
        graph.setGxl(gxlIo.gxlFromInstance());
        updateGraph();

        logDao.save(log);
    }

    public void saveOofLogs(String oofLogs) {
        logDao.saveLogs(oofLogs);
    }

    @Override
    public void updateGraph() {
        graph.setGxl(gxlIo.gxlFromInstanceWithTemplate());
        graphDao.update(graph);
    }

    @Override
    public void updateFunctionMode(FunctionMode functionMode) {
        mode = functionMode;
    }

    @Override
    public void updateEditMode() {
        mode = FunctionMode.EDIT;
    }

    @Override
    public void updateNewGraph() {
        Graph graph = new Graph();
        graph.setGxl(gxlIo.gxlFromInstanceWithTemplate());
        graphDao.save(graph);
        setGraph(graph);
    }

    public String getGxlFromDatabase() {
        return graphDao.gxlFromDatabase();
    }

    public boolean databaseEmpty() {
        return graphDao.isEmpty();
    }

}