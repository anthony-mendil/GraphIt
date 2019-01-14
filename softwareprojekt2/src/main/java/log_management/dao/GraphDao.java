package log_management.dao;


import log_management.tables.Graph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The graph log_management.dao class. Manages the data access to the graphs.
 */
public class GraphDao implements Dao<Graph> {
    /**
     * The id of the current logged action.
     */
    protected static int currentId;
    /**
     *  EntityManager instance is associated with the persistence context.
     */
    private static EntityManager entityManager = PersonalEntityManager.getInstance();
    @Override
    public Optional<Graph> get(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Graph> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Graph graph) {
        currentId = graph.getId();
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Graph graph) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }
}
