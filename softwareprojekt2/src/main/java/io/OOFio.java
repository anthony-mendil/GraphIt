package io;

import com.google.inject.Inject;
import log_management.DatabaseManager;
import log_management.dao.LogDao;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * The OOF importer/exporter.
 */
public class OOFio {
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

    private static Logger logger = Logger.getLogger(OOFio.class);

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
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(pFile), StandardCharsets.UTF_8)) {
            writer.write(oof);
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
        StringBuilder oof = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(pFile), StandardCharsets.UTF_8))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                oof.append(line);
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        GXLio gxlio = new GXLio();

        gxlio.gxlToInstance(gxlFromOOF(oof.toString()), true);

        DatabaseManager.getInstance().saveOofGraph(gxlFromOOF(oof.toString()));

        DatabaseManager.getInstance().saveOofLogs(jsonFromOOF(oof.toString()));
    }

}
