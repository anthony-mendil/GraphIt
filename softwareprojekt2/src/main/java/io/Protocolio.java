package io;

import com.google.inject.Inject;
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

    /**
     * Export the current protocol as readable textfile.
     *
     * @param pFile The destination file
     */
    public void exportAsReadableProtocol(File pFile){
        LogDao logDao = new LogDao();
        List<Log> logs = logDao.getAll();
        String protocol = "";
        for (int i = 0; i < logs.size(); i++) {
            protocol += logs.get(i).toStringForTextFile();
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))){
            bufferedWriter.write(protocol);
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
