package log_management.dao;

import actions.LogEntryName;
import log_management.tables.Log;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * The log log_management.dao class. Manages the data access to the logs.
 */
public class LogDao implements Dao<Log> {
    /**
     *  EntityManager instance is associated with the persistence context
     */
    private static EntityManager entityManager = PersonalEntityManager.getInstance();

    /**
     * return a logs object, attribute parameter gets converted from string to object with jackson
     * @param id the id of the object to get
     * @return the log object
     */
    @Override
    public Optional<Log> get(long id) {
        throw new UnsupportedOperationException();
    }

    /**
     * return a list of log objects, attribute parameters gets converted from string to objects with jackson
     * @return a list of log objects
     */
    @Override
    public List<Log> getAll() {
        throw new UnsupportedOperationException();
    }

    /**
     * return a list of log objects assigned to the graphId, attribute parameters gets converted from string to objects
     * with jackson
     * @param graphId the graph id
     * @return a list of log objects
     */
    public List<Log> getAll(int graphId) {
        throw new UnsupportedOperationException();
    }

    /**
     * return a string (json) containing all logs assigned to the graphId
     * @param graphId the graph id
     * @return a list of log objects
     */
    public List<Log> getAllString(int graphId) {
        throw new UnsupportedOperationException();
    }

    /**
     * converts the attribute parameters into a json string and saves the log
     * @param log the log object to save
     */
    @Override
    public void save(Log log) {

        int i = GraphDao.currentId;
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Log log, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    /**
     * gets all logs of a specific log type
     * @param logEntryName the log type
     * @return a list with all logs of this log type
     */
    public List<Log> getLogType(LogEntryName logEntryName){
        throw new UnsupportedOperationException();
    }

    /**
     * gets all logs of a specific log type as a List of strings
     * @param logEntryName the log type
     * @return a list with all logs of this log type as strings
     */
    public List<String> getLogTypeString(LogEntryName logEntryName){
        throw new UnsupportedOperationException();
    }

    /**
     * get all log entries as strings
     * @return list of strings containing all log entries
     */
    public List<String> getAllStrings(){
        throw new UnsupportedOperationException();
    }
}
