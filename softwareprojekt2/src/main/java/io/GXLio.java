package io;

import com.google.inject.Inject;
import graph.graph.Syndrom;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;

/**
 * the gxl importer/ exporter
 */
public class GXLio {
    /**
     * the syndrom representation
     */
    @Inject
    private Syndrom syndrom;

    /**
     * the graph dao, for accessing the graph data
     */
    @Inject
    private GraphDao graphDao;

    /**
     * creates a new GXLio
     */
    public GXLio(){
        throw new UnsupportedOperationException();
    }

    /**
     * Writes the GXL representation into the syndrom
     *
     * @param pGXL The GXL that gets written into syndrom
     */
    protected void gxlToInstance(String pGXL){
        throw new UnsupportedOperationException();
    }

    /**
     * Extracts the GXL from our Syndrom and gives it back as String
     *
     * @return The extracted GXL
     */
    public String gxlFromInstance(){
        throw new UnsupportedOperationException();
    }

    /**
     * Save the the GXL representation to a specific location
     *
     * @param pPath The path to the file that gets exported
     * @param pName The name that the file gets as it gets exported
     */
    public void exportGXL(String pPath, String pName){
        throw new UnsupportedOperationException();
    }

    /**
     * Import a GXL file from a specific location to the Syndrom
     *
     * @param pPath The path to the file that gets imported
     */

    public void importGXL(String pPath){
        throw new UnsupportedOperationException();
    }
}
