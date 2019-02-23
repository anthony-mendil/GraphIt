package io;

import com.google.inject.Inject;
import log_management.DatabaseManager;
import log_management.dao.LogDao;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Scanner;


/**
 * The OOF importer/exporter.
 */
public class OOFio {
    private static Logger logger = Logger.getLogger(OOFio.class);
    /**
     * The log dao object, for accessing the log data.
     */
    @Inject
    private LogDao logDao = new LogDao();

    /**
     * Creates a new OOFio object.
     */
    public OOFio() {
        // Can handle oof-import/-export now
    }

    /**
     * Creates an OOF out of the GXL and the JSON.
     *
     * @param pGXL  The GXL string representation of the graph that gets written in the OOF file.
     * @param pJSON The protocol as JSON string of the user that gets written in the OOF file.
     * @return The OOF file as string.
     */
    private String createOOF(String pGXL, String pJSON) {
        return pGXL + "\0" + pJSON;
    }

    /**
     * Gets the GXL String from the OOF string.
     *
     * @param pOOF The OOF containing a GXL string.
     * @return The GXL string.
     */
    private String gxlFromOOF(String pOOF) {
        String[] splits = pOOF.split("\0");
        return splits[0];
    }

    /**
     * Gets the JSON string from the OOF string.
     *
     * @param pOOF The OOF containing a JSON string.
     * @return The JSON string.
     */
    private String jsonFromOOF(String pOOF) {
        String[] splits = pOOF.split("\0");
        return splits[1];
    }

    /**
     * Export the current graph and protocol as OOF file.
     *
     * @param pFile The destination file
     */
    public void exportAsOOF(File pFile) {
        GXLio gxlio = new GXLio();
        String oof = createOOF(gxlio.gxlFromInstance(true), logDao.getAllString());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))) {
            bufferedWriter.write(oof);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Import the graph and protocol from an OOF file.
     *
     * @param pFile The file to import
     */
    public void importOOF(File pFile) {
        String oof = "";
        try (Scanner scanner = new Scanner(pFile)) {
            oof = scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            logger.error(e.toString());
        }
        GXLio gxlio = new GXLio();

        gxlio.gxlToInstance(gxlFromOOF(oof), true);

        DatabaseManager.getInstance().saveOofGraph(gxlFromOOF(oof));

        DatabaseManager.getInstance().saveOofLogs(jsonFromOOF(oof));
    }

}
