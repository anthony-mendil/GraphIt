package log_management.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface for basic log_management.dao layer - isolate the application/business layer from the persistence layer.
 *
 * @param <T> The log_management.dao type.
 */
public interface Dao<T> {

    /**
     * Gets a specific object from T.
     *
     * @param id The id of the object to get.
     * @return The optional object for T.
     */
    Optional<T> get(long id);

    /**
     * Gets all objects from T.
     *
     * @return A List of all found objects.
     */
    List<T> getAll();

    /**
     * Save an object T.
     *
     * @param t The object to save.
     */
    void save(T t);

    /**
     * Deletes the object.
     *
     * @param id The id of the object to delete.
     */
    void delete(int id);
}