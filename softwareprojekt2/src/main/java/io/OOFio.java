package io;

import com.google.inject.Inject;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;

/**
 * the oof importer/ exporter
 */
public class OOFio {
    /**
     * the log dao, for accessing the log data
     */
    @Inject
    private LogDao logDao;
    /**
     * the graph dao, for accessing the graph data
     */
    @Inject
    private GraphDao graphDao;

    /**
     * creates a new OOFio
     */
    public OOFio(){
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an OOF of the GXL and the JSON
     *
     * @param pGXL The GXL String representation of the Graph that gets written in the OOF
     * @param pJSON The protocol as JSON String of the user that gets written in the OOF
     * @return The OOF file as String
     */
    private String createOOF(String pGXL, String pJSON){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the GXL String from the OOF String
     *
     * @param pOOF The OOF containing an GXL String
     * @return The GXL String
     */
    private String gxlFromOOF(String pOOF){
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the JSON String from the OOF String
     *
     * @param pOOF The OOF containing an JSON String
     * @return The JSON String
     */
    private String jsonFromOOF(String pOOF){
        throw new UnsupportedOperationException();
    }

    /**
     * Export the current graph and protocol as OOF file
     *
     * @param pPath The path to the file that gets imported
     * @param pName The name that the file gets as it gets exported
     */
    public void exportAsOOF(String pPath, String pName){
        throw new UnsupportedOperationException();
    }

    /**
     * Import the graph and protocol from an OOF file
     *
     * @param pPath The path to the file that gets imported
     */
    public void importOOF(String pPath){
        throw new UnsupportedOperationException();
    }

}
