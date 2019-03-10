package io;

import log_management.LogToStringConverter;
import log_management.dao.LogDao;
import log_management.tables.Log;

import java.io.File;
import java.util.List;


/**
 * The action log protocol export handler.
 * @author Jonah Jaeger
 */
public class Protocolio {

    /**
     * The LogToStringConverter to convert the logs into a String.
     */
    private LogToStringConverter logToStringConverter;

    /**
     * Creates a new Protocolio object and initializes the LogToStringConverter.
     */
    public Protocolio() {
        logToStringConverter = new LogToStringConverter();
    }

    /**
     * Export the current protocol as readable text file.
     *
     * @param pFile The destination file
     */
    public void exportAsReadableProtocol(File pFile) {
        LogDao logDao = new LogDao();
        List<Log> logs = logDao.getAll();
        StringBuilder protocol = new StringBuilder();
        for (Log log : logs) {
            protocol.append(logToStringConverter.convertForTextFile(log));
        }
        FileHandler.stringToFile(protocol.toString(), pFile);
    }


}
