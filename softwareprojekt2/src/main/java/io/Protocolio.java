package io;

import com.google.inject.Inject;
import log_management.dao.LogDao;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The OOF importer/exporter.
 */
public class Protocolio {
    /**
     * The log dao object, for accessing the log data.
     */
    @Inject
    private LogDao logDao;

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
        String protocol=""; //hier kommt die funktion die einen String mit dem lesbaren Protokoll zurückgibt hin
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
        String protocol=logDao.getAllString();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))){
            bufferedWriter.write(protocol);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

}
