package log_management.dao;


import log_management.DatabaseManager;
import log_management.tables.Graph;
import log_management.tables.Log;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * The graph log_management.dao class. Manages the data access to the graphs.
 */
public class GraphDao implements Dao<Graph> {

    @Override
    public Optional<Graph> get(long id) {
        return get();
    }

    public Optional<Graph> get() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        Graph graph = selectAllGraphs.getResultList().get(0);

        entityManager.getTransaction().commit();
        entityManager.close();

        return Optional.of(graph);
    }

    @Override
    public List<Graph> getAll() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        List<Graph> list = selectAllGraphs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return list;
    }

    @Override
    public void save(Graph graph) {
        delete(-1);

        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(graph);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(Graph graph) {
        //not used
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//        entityManager.refresh(entityManager.merge(graph));
//        entityManager.flush();
//        entityManager.clear();
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
    }

    public void update() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        Graph graph = selectAllGraphs.getResultList().get(0);

        graph.setGxl(DatabaseManager.getInstance().getGxlIo().gxlFromInstanceWithTemplate());

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) {
        delete();
    }

    public void delete() {
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

            entityManager.remove(graph);
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public String gxlFromDatabase() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        List<Graph> graphList = selectAllGraphs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return graphList.get(0).getGxl();
    }

    public boolean isEmpty() {
        EntityManager entityManager = PersonalEntityManagerFactory.getInstance().createEntityManager();
        entityManager.getTransaction().begin();

        TypedQuery<Graph> selectAllGraphs = entityManager.createQuery("SELECT g from Graph g where g.id > 0", Graph.class);
        List<Graph> graphList = selectAllGraphs.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return (graphList.size() == 0);
    }
}
