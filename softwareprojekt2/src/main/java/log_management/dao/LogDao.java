package log_management.dao;

import actions.LogEntryName;
import log_management.DatabaseManager;
import log_management.tables.Graph;
import log_management.tables.Log;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The log log_management.dao class. Manages the data access to the logs.
 */
public class LogDao implements Dao<Log> {

    /**
     * Returns a logs object, attribute vertices gets converted from string to object with jackson.
     * @param id The id of the object to get.
     * @return The log object.
     */
    @Override
    public Optional<Log> get(long id) {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Query query = entityManager.createQuery("select l from Log l where l.id = :lid");
        query.setParameter("lid", id);
        return Optional.of((Log) query.getSingleResult());
    }

    /**
     * Returns a list of log objects for the current graph.
     * @return A list of log objects.
     */
    @Override
    public List<Log> getAll() throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Graph graph = DatabaseManager.getInstance().getGraph();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
        selectLogs.setParameter("gid", graphId);
        return selectLogs.getResultList();
    }

    /**
     * Returns a string (json) containing all logs from the current graph.
     * @return A a string (json) containing all logs.
     */
    public String getAllString() throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Graph graph = DatabaseManager.getInstance().getGraph();
        if (graph == null) {
            throw new IllegalStateException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
        selectLogs.setParameter("gid", graphId);
        List<Log> logList = selectLogs.getResultList();

        ObjectMapper mapper = new ObjectMapper();
        String logString = null;
        try {
            logString = mapper.writeValueAsString(logList);
        } catch (Exception e) {}

        return  logString;
    }

    /**
     * Saves the log object.
     * @param log The log object to save.
     */
    @Override
    public void save(Log log) {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        entityManager.getTransaction().begin();
        entityManager.persist(log);
        entityManager.getTransaction().commit();
    }

    public  void saveLogs(String oofLogs) {
        ObjectMapper mapper = new ObjectMapper();
        List<Log> logs;
        try {
            logs = mapper.readValue(oofLogs, List.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        logs.forEach(log -> save(log));
    }

    @Override
    public void update(Log log) {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        entityManager.refresh(entityManager.merge(log));
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        entityManager.remove(get(id));
    }

    /**
     * Gets all logs of a specific log type.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type.
     */
    public List<Log> getLogType(LogEntryName logEntryName) throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Graph graph = DatabaseManager.getInstance().getGraph();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid and l.logEntryName = :logType", Log.class);
        selectLogs.setParameter("gid", graphId);
        selectLogs.setParameter("logType", logEntryName);
        return selectLogs.getResultList();
    }

    /**
     * Gets all logs of a specific log type as a list of strings.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type as strings.
     */
    public List<String> getLogTypeString(LogEntryName logEntryName) throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Graph graph = DatabaseManager.getInstance().getGraph();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid and l.logEntryName = :logType", Log.class);
        selectLogs.setParameter("gid", graphId);
        selectLogs.setParameter("logType", logEntryName);
        List<Log> logList = selectLogs.getResultList();

        List<String> logs = new ArrayList<>();
        logList.forEach(log -> {
            logs.add(log.toString());
        });
        return logs;
    }

    /**
     * Gets all log entries as strings.
     * @return List of strings containing all log entries.
     */
    public List<String> getAllStrings() throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManager.getInstance();

        Graph graph = DatabaseManager.getInstance().getGraph();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectAllLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
        selectAllLogs.setParameter("gid", graphId);
        List<Log> logList = selectAllLogs.getResultList();

        List<String> logs = new ArrayList<>();
        logList.forEach(log -> {
            logs.add(log.toString());
        });
        return logs;
    }
}
