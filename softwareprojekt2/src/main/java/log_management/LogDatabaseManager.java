package log_management;

import actions.LogAction;
import actions.remove.RemoveSphereLogAction;
import log_management.parameters.remove.RemoveSphereParam;
import log_management.tables.Graph;
import log_management.tables.Log;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
//import java.util.List;

public class LogDatabaseManager {

    private static int graphId;
    private static int logCounter;

    public static void main(String[] args) throws SQLException {
        // this should only be done once, when starting the program
        setup();

        RemoveSphereLogAction a = new RemoveSphereLogAction(new RemoveSphereParam());
        a.action();

        RemoveSphereLogAction b = new RemoveSphereLogAction(new RemoveSphereParam());
        b.action();

        RemoveSphereLogAction c = new RemoveSphereLogAction(new RemoveSphereParam());
        c.action();

        printLogs();

        System.exit(0);
    }

    public static void addLogEntryToDatabase(LogAction logAction) {
        Log log = new Log();
        log.setId(personalLogIncrementor());
        log.setGraphId(getGraphId());
        log.setLogEntryName(logAction.getLogEntryName());
        log.setParameters(logAction.getParameters());
        log.setTime(logAction.getTime());

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(log);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static void addGraphEntryToDatabase() {
        Graph graph = new Graph();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(graph);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public static int personalLogIncrementor() {
        setLogCounter(getLogCounter() + 1);
        return getLogCounter();
    }

    public static void setup() {
        addGraphEntryToDatabase();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query maxQuery = entityManager.createQuery("select l.id from Log l where l.graphId = :gid and l.id = max(id)");
        maxQuery.setParameter("gid", getGraphId());
        int max;
        if (getLogCounter() != 0) {
            max = (int) maxQuery.getSingleResult();
        } else {
            max = 0;
        }

        setLogCounter(max);
        entityManager.close();
        entityManagerFactory.close();
    }

    public static void printLogs() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Log> selectAllLogs = entityManager.createQuery("SELECT l from Log l where l.graphId = :gid", Log.class);
        selectAllLogs.setParameter("gid", getGraphId());
        List<Log> list = selectAllLogs.getResultList();

        list.forEach(l -> {
            System.out.println("Id: " + l.getId() +
                    " GraphId: " + l.getGraphId() +
                    " LogEntryName: " + l.getLogEntryName() +
                    " parameters: " + l.getParameters() +
                    " Time: " + l.getTime());
        });
        entityManager.close();
        entityManagerFactory.close();
    }

    public static int getGraphId() {
        return graphId;
    }

    public static void setGraphId(int graphId) {
        LogDatabaseManager.graphId = graphId;
    }

    public static int getLogCounter() {
        return logCounter;
    }

    public static void setLogCounter(int logCounter) {
        LogDatabaseManager.logCounter = logCounter;
    }
}