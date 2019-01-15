package io;

import com.google.inject.Inject;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;

import java.io.File;


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
        return pGXL+"\0"+pJSON;
    }

    /**
     * Gets the GXL String from the OOF string.
     *
     * @param pOOF The OOF containing a GXL string.
     * @return The GXL string.
     */
    private String gxlFromOOF(String pOOF){
        String[] splits = pOOF.split("\0");
        return splits[0];
    }

    /**
     * Gets the JSON string from the OOF string.
     *
     * @param pOOF The OOF containing a JSON string.
     * @return The JSON string.
     */
    private String jsonFromOOF(String pOOF){
        String[] splits = pOOF.split("\0");
        return splits[1];
    }

    /**
     * Export the current graph and protocol as OOF file.
     *
     * @param pFile The destination file
     */
    public void exportAsOOF(File pFile){
        throw new UnsupportedOperationException();
    }

    /**
     * Import the graph and protocol from an OOF file.
     *
     * @param pFile The file to import
     */
    public void importOOF(File pFile){
        throw new UnsupportedOperationException();
    }

}
