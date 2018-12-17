package log_management.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface for basic log_management.dao layer - isolate the application/ business layer from the persistence layer
 *
 * @param <T> the log_management.dao type
 */
public interface Dao<T> {

    /**
     * Gets a specific object from T
     * @param id the id of the object to get
     * @return the optional object for T
     */
    Optional<T> get(long id);

    /**
     * Gets all objects from T
     * @return a List of all found objects
     */
    List<T> getAll();

    /**
     * Save an object T
     * @param t the object to save
     */
    void save(T t);

    /**
     * Updates an object
     * @param t the updated object
     */
    void update(T t);

    /**
     * deletes the object
     * @param id the id of the object to delete
     */
    void delete(int id);
}