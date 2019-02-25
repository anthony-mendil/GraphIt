package io;

import log_management.LogToStringConverter;
import log_management.dao.LogDao;
import log_management.tables.Log;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * The OOF importer/exporter.
 */
public class Protocolio {
    /**
     * Creates a new OOFio object.
     */
    public Protocolio(){
        // Can handle protocol-export now
    }

    private static Logger logger= Logger.getLogger(Protocolio.class);
    private LogToStringConverter logToStringConverter = new LogToStringConverter();

    /**
     * Export the current protocol as readable textfile.
     *
     * @param pFile The destination file
     */
    public void exportAsReadableProtocol(File pFile){
        LogDao logDao = new LogDao();
        List<Log> logs = logDao.getAll();
        StringBuilder protocol = new StringBuilder();
        for (Log log : logs) {
            protocol.append(logToStringConverter.convertForTextFile(log));
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))){
            bufferedWriter.write(protocol.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Export the current protocol as usable textfile.
     *
     * @param pFile The destination file
     */
    public void exportAsUsableProtocol(File pFile){
        LogDao logDao = new LogDao();
        String protocol=logDao.getAllString();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))){
            bufferedWriter.write(protocol);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

}
