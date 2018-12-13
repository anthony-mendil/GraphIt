package log_management.dao;

import edu.uci.ics.jung.graph.Graph;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The graph log_management.dao class. Manages the data access to the graphs.
 */
public class GraphDao implements Dao<Graph> {
    /**
     *  EntityManager instance is associated with the persistence context
     */
    private EntityManager entityManager;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Graph graph, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Graph graph) {
        throw new UnsupportedOperationException();
    }
}
