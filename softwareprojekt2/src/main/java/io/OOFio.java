package io;

import log_management.DatabaseManager;
import log_management.dao.LogDao;

import java.io.File;


/**
 * The OOF importer/exporter.
 */
public class OOFio {
    /**
     * The log dao object, for accessing the log data.
     */
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
    String createOOF(String pGXL, String pJSON) {
        return pGXL + "\0" + pJSON;
    }

    /**
     * Gets the GXL String from the OOF string.
     *
     * @param pOOF The OOF containing a GXL string.
     * @return The GXL string.
     */
    String gxlFromOOF(String pOOF) {
        String[] splits = pOOF.split("\0");
        return splits[0];
    }

    /**
     * Gets the JSON string from the OOF string.
     *
     * @param pOOF The OOF containing a JSON string.
     * @return The JSON string.
     */
    String jsonFromOOF(String pOOF) {
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
        FileHandler.stringToFile(oof, pFile);
    }

    /**
     * Import the graph and protocol from an OOF file.
     *
     * @param pFile The file to import
     */
    public void importOOF(File pFile) {
        String oof=FileHandler.fileToString(pFile);
        GXLio gxlio = new GXLio();
        gxlio.gxlToInstance(gxlFromOOF(oof), true);
        DatabaseManager.getInstance().saveOofGraph(gxlFromOOF(oof));
        DatabaseManager.getInstance().saveOofLogs(jsonFromOOF(oof));
    }

}
