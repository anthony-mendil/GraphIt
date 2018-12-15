package log_management.dao;


import javafx.util.Pair;
import log_management.tables.Graph;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     * gets all graph elements with id, string, local datetime
     * @return a map that points from the id of the graph to a pair of name and timestamp of the graph
     */
    public Map<Integer, Pair<String, LocalDateTime>> getAllGraphs(){
        throw new UnsupportedOperationException();
    }
}
