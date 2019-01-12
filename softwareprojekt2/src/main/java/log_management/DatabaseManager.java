package log_management;

import actions.Action;
import actions.ObserverSyndrom;
import com.google.inject.Singleton;
import io.GXLio;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;
import log_management.dao.PersonalEntityManager;
import log_management.tables.Graph;
import lombok.Data;

import javax.persistence.EntityManager;

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
    private static Graph graph;

    /**
     * Creates a database manager organizing the database.
     */
    public DatabaseManager(){
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a log to the database (through dao).
     * @param action The called action, which will be logged.
     */
    public static void addEntryDatabase(Action action) {
        throw new UnsupportedOperationException();
    }

    /**
     * Setup for the database.
     */
    public void setup() {
        Graph graph = new Graph();
        EntityManager entityManager = PersonalEntityManager.getInstance();

        entityManager.getTransaction().begin();
        entityManager.persist(graph);
        entityManager.getTransaction().commit();
    }

    public static void setGraph(Graph pGraph) {
        graph = pGraph;
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