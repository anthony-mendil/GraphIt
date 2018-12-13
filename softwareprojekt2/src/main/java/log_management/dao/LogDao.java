package log_management.dao;

import log_management.LogEntryName;
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
    private EntityManager entityManager;
    @Override
    public Optional<Log> get(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Log> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Log log) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Log log, String[] params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Log log) {
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
