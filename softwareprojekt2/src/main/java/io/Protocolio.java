package io;

import log_management.LogToStringConverter;
import log_management.dao.LogDao;
import log_management.tables.Log;

import java.io.File;
import java.util.List;


/**
 * The OOF importer/exporter.
 */
public class Protocolio {
    private LogToStringConverter logToStringConverter = new LogToStringConverter();

    /**
     * Creates a new OOFio object.
     */
    public Protocolio() {
        // Can handle protocol-export now
    }

    /**
     * Export the current protocol as readable textfile.
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
