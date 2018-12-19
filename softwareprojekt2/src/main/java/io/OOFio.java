package io;

import com.google.inject.Inject;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;

/**
 * The OOF importer/exporter.
 */
public class OOFio {
    /**
     * The log dao object, for accessing the log data.
     */
    @Inject
    private LogDao logDao;
    /**
     * The graph dao object, for accessing the graph data.
     */
    @Inject
    private GraphDao graphDao;

    /**
     * Creates a new OOFio object.
     */
    public OOFio(){
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an OOF out of the GXL and the JSON.
     *
     * @param pGXL The GXL string representation of the graph that gets written in the OOF file.
     * @param pJSON The protocol as JSON string of the user that gets written in the OOF file.
     * @return The OOF file as string.
     */
    private String createOOF(String pGXL, String pJSON){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the GXL String from the OOF string.
     *
     * @param pOOF The OOF containing a GXL string.
     * @return The GXL string.
     */
    private String gxlFromOOF(String pOOF){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the JSON string from the OOF string.
     *
     * @param pOOF The OOF containing a JSON string.
     * @return The JSON string.
     */
    private String jsonFromOOF(String pOOF){
        throw new UnsupportedOperationException();
    }

    /**
     * Export the current graph and protocol as OOF file.
     *
     * @param pPath The path where the file will be saved/exported.
     * @param pName The name of the exported file.
     */
    public void exportAsOOF(String pPath, String pName){
        throw new UnsupportedOperationException();
    }

    /**
     * Import the graph and protocol from an OOF file.
     *
     * @param pPath The path to the file that gets imported.
     */
    public void importOOF(String pPath){
        throw new UnsupportedOperationException();
    }

}
