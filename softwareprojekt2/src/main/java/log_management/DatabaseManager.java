package log_management;

import actions.LogAction;
import actions.ObserverSyndrom;
import com.google.inject.Singleton;
import graph.graph.FunctionMode;
import io.GXLio;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;
import log_management.tables.Graph;
import log_management.tables.Log;
import lombok.Data;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

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
    FunctionMode mode;

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
     * @param logAction The called action, which will be logged.
     */
    public void addEntryDatabase(LogAction logAction) {
        graph.setGxl(gxlIo.gxlFromInstance());
        updateGraph();

        Log log = new Log();
        log.setGraph(graph);
        log.setLogEntryName(logAction.getLogEntryName());
        log.setTime(logAction.getTime());

        ObjectMapper mapper = new ObjectMapper();
        String paramString = null;

        try {
            paramString = mapper.writeValueAsString(logAction.getParameters());
        } catch (Exception e) {}
        log.setParameters(paramString);

        logDao.save(log);
    }

    /**
     * Setup for the database.
     */
    public void setup() {
        Graph graph = new Graph();
        graph.setGxl(gxlIo.gxlFromInstance());
        graphDao.save(graph);
        setGraph(graph);
    }

    @Override
    public void updateGraph() {
        graph.setGxl(gxlIo.gxlFromInstance());
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
        setup();
    }
}