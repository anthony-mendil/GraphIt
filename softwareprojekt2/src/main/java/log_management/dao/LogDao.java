package log_management.dao;

import actions.LogEntryName;
import log_management.parameters.Param;
import log_management.tables.Log;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The log log_management.dao class. Manages the data access to the logs.
 */
public class LogDao implements Dao<Log> {
    /**
     *  EntityManager instance is associated with the persistence context.
     */
    private static EntityManager entityManager = PersonalEntityManager.getInstance();

    /**
     * Returns a logs object, attribute parameter gets converted from string to object with jackson.
     * @param id The id of the object to get.
     * @return The log object.
     */
    @Override
    public Optional<Log> get(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of log objects, attribute parameters gets converted from string to objects with jackson.
     * @return A list of log objects.
     */
    @Override
    public List<Log> getAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of log objects assigned to the graphId, attribute parameters gets converted from string to objects
     * with jackson.
     * @param graphId The graph id.
     * @return A list of log objects.
     */
    public List<Log> getAll(int graphId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a string (json) containing all logs assigned to the graphId.
     * @param graphId The graph id.
     * @return A list of log objects.
     */
    public List<Log> getAllString(int graphId) {
        throw new UnsupportedOperationException();
    }

    /**
     * Converts the attribute parameters into a json string and calls the save method
     * with the log object containing the parameter string (json).
     * @param log The log object to save.
     * @param parameters The parameter to convert
     */
    public void save(Log log, Param parameters) {

        int i = GraphDao.currentId;
        throw new UnsupportedOperationException();
    }

    /**
     * Saves the log object.
     * @param log The log object to save.
     */
    @Override
    public void save(Log log) {

        int i = GraphDao.currentId;
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Log log) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets all logs of a specific log type.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type.
     */
    public List<Log> getLogType(LogEntryName logEntryName){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets all logs of a specific log type as a list of strings.
     * @param logEntryName The log type.
     * @return A list with all logs of this log type as strings.
     */
    public List<String> getLogTypeString(LogEntryName logEntryName){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets all log entries as strings.
     * @return List of strings containing all log entries.
     */
    public List<String> getAllStrings(){
        throw new UnsupportedOperationException();
    }
}
