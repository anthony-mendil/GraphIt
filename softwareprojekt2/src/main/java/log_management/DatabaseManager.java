package log_management;

import actions.ObserverSyndrom;
import graph.graph.FunctionMode;
import gui.Values;
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
public class DatabaseManager implements ObserverSyndrom {
    /**
     * The instance of this class.
     */
    private static DatabaseManager databaseManager;
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
     * The current mode.
     */
    private FunctionMode mode;

    /**
     * Creates a database manager organizing the database.
     */
    private DatabaseManager() {
        graphDao = new GraphDao();
        logDao = new LogDao();
        gxlIo = new GXLio();
        mode = Values.getInstance().getMode();
    }

    /**
     * Gets the instance of this class.
     *
     * @return The instance of thi class.
     */
    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    /**
     * Adds a log to the database (through dao).
     *
     * @param log The log for the called action.
     */
    public void addEntryDatabase(Log log) {
        if (mode == FunctionMode.EDIT) {
            updateGraph();
            logDao.save(log);
        }
    }

    /**
     * Saves the graph in teh database (through dao).
     *
     * @param gxl The gxl of the graph.
     */
    public void saveOofGraph(String gxl) {
        Graph graph = new Graph();
        graph.setGxl(gxl);
        graphDao.save(graph);
    }

    /**
     * Saves the logs in the database (through dao).
     *
     * @param oofLogs The logs of the graph.
     */
    public void saveOofLogs(String oofLogs) {
        logDao.saveLogs(oofLogs);
    }

    @Override
    public void updateGraph() {
        graphDao.update();
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
        graph.setGxl(gxlIo.gxlFromInstance(true));
        graphDao.save(graph);
    }

    /**
     * Gets the gxl graph from the database (through dao).
     *
     * @return The gxl graph from the database as string.
     */
    public String getGxlFromDatabase() {
        return graphDao.gxlFromDatabase();
    }

    /**
     * Determines if the graph table in the database is empty (through dao).
     *
     * @return True if the graph table in the database is empty.
     */
    public boolean databaseEmpty() {
        return graphDao.isEmpty();
    }
}