package log_management.dao;

import actions.LogEntryName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import log_management.DatabaseManager;
import log_management.json_deserializers.Point2DDeserializer;
import log_management.json_serializers.Point2DSerializer;
import log_management.parameters.add_remove.AddRemoveSphereParam;
import log_management.tables.AddRemoveSphereLog;
import log_management.tables.Graph;
import log_management.tables.Log;

import javax.persistence.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Type;
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
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select l from Log l where l.id = :lid");
        query.setParameter("lid", id);
        Log log = (Log) query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return Optional.of(log);
    }

    /**
     * Returns a list of log objects for the current graph.
     * @return A list of log objects.
     */
    @Override
    public List<Log> getAll() throws NoSuchElementException {

        Graph graph = DatabaseManager.getInstance().getGraphDao().get(-1).get();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
        selectLogs.setParameter("gid", graphId);
        List<Log> list = selectLogs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        return list;
    }

    /**
     * Returns a string (json) containing all logs from the current graph.
     * @return A a string (json) containing all logs.
     */
    public String getAllString() throws NoSuchElementException {
        Graph graph = DatabaseManager.getInstance().getGraphDao().get(-1).get();
        if (graph == null) {
            throw new IllegalStateException();
        }
        int graphId = graph.getId();

        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
        selectLogs.setParameter("gid", graphId);
        List<Log> logList = selectLogs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DSerializer());
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DDeserializer());
        Gson gson = gsonBuilder.create();

        String logString = null;
        try {
            Type myType = new TypeToken<List<Log>>() {}.getType();
            logString = gson.toJson(logList, myType);
        } catch (Exception e) {}

        return  logString;
    }

    /**
     * Saves the log object.
     * @param log The log object to save.
     */
    @Override
    public void save(Log log) {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

//        Log newLog = new Log();
//        newLog.setTime(log.getTime());
//        newLog.setParameters(log.getParameters());
//        newLog.setLogEntryName(log.getLogEntryName());
        AddRemoveSphereLog newLog = new AddRemoveSphereLog();
        newLog.setTime(log.getTime());
        newLog.setParameters(((AddRemoveSphereLog)log).getParameters());
        newLog.setLogEntryName(log.getLogEntryName());

        newLog.setGraph(DatabaseManager.getInstance().getGraphDao().get(-1).get());
        entityManager.persist(newLog);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private void deleteAllLogs() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        List<Graph> graphList = selectAllGraphs.getResultList();

        graphList.forEach(graph -> {
            TypedQuery<Log> selectAllLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
            selectAllLogs.setParameter("gid", graph.getId());
            List<Log> logList = selectAllLogs.getResultList();

            logList.forEach(log -> {
                entityManager.remove(log);
            });
        });
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveLogs(String oofLogs) {
        ArrayList<Log> logs;

        deleteAllLogs();

        Type myType = new TypeToken<ArrayList<Log>>() {}.getType();
        logs = new Gson().fromJson(oofLogs, myType);

        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            save(log);
        }
    }

    @Override
    public void delete(int id) {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.remove(get(id));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /**
     * Gets all logs of a specific log type.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type.
     */
    public List<Log> getLogType(LogEntryName logEntryName) throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        Graph graph = DatabaseManager.getInstance().getGraphDao().get(-1).get();
        if (graph == null) {
            throw new NoSuchElementException();
        }
        int graphId = graph.getId();

        TypedQuery<Log> selectLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid and l.logEntryName = :logType", Log.class);
        selectLogs.setParameter("gid", graphId);
        selectLogs.setParameter("logType", logEntryName);
        List<Log> list = selectLogs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }

    /**
     * Gets all logs of a specific log type as a list of strings.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type as strings.
     */
    public List<String> getLogTypeString(LogEntryName logEntryName) throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        Graph graph = DatabaseManager.getInstance().getGraphDao().get(-1).get();
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

        entityManager.getTransaction().commit();
        entityManager.close();

        return logs;
    }

    /**
     * Gets all log entries as strings.
     * @return List of strings containing all log entries.
     */
    public List<String> getAllStrings() throws NoSuchElementException {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        Graph graph = DatabaseManager.getInstance().getGraphDao().get(-1).get();
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

        entityManager.getTransaction().commit();
        entityManager.close();

        return logs;
    }
}
