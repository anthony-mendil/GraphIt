package log_management.dao;

import log_management.tables.Graph;
import log_management.tables.Log;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * The graph log_management.dao class. Manages the data access to the graphs.
 */
public class GraphDao implements Dao<Graph> {
    /**
     * The current graph.
     */
    protected static Graph currentGraph;
    /**
     *  EntityManager instance is associated with the persistence context.
     */
    private static EntityManager entityManager = PersonalEntityManager.getInstance();

    @Override
    public Optional<Graph> get(long id) {
        Query query = entityManager.createQuery("select g from Graph g where g.id = :gid");
        query.setParameter("gid", id);
        return Optional.of((Graph) query.getSingleResult());
    }

    @Override
    public List<Graph> getAll() {
        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        return selectAllGraphs.getResultList();
    }

    @Override
    public void save(Graph graph) {
        delete(-1);
        currentGraph = graph;

        entityManager.getTransaction().begin();
        entityManager.persist(graph);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Graph graph) {
        entityManager.refresh(graph);
        currentGraph = graph;
    }

    @Override
    public void delete(int id) {
        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        List<Graph> graphList = selectAllGraphs.getResultList();

        graphList.forEach(graph -> {
            TypedQuery<Log> selectAllLogs = entityManager.createQuery("SELECT l from Log l where l.graph.id = :gid", Log.class);
            selectAllLogs.setParameter("gid", graph.getId());
            List<Log> logList = selectAllLogs.getResultList();

            logList.forEach(log -> {
                entityManager.remove(log);
            });

            entityManager.remove(graph);
        });
    }

    public static Graph getCurrentGraph() {
        return currentGraph;
    }
}
