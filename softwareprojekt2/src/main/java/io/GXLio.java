package io;

import com.google.inject.Inject;
import graph.graph.Syndrom;
import log_management.dao.GraphDao;
import log_management.dao.LogDao;

/**
 * The GXL importer/exporter.
 */
public class GXLio {
    /**
     * The syndrom representation.
     */
    @Inject
    private Syndrom syndrom;

    /**
     * The graph dao object, for accessing the graph data.
     */
    @Inject
    private GraphDao graphDao;

    /**
     * Creates a new GXLio object.
     */
    public GXLio(){

    }

    /**
     * Writes the GXL representation into the syndrom.
     *
     * @param pGXL The GXL representation that gets written into syndrom.
     */
    protected void gxlToInstance(String pGXL){
        throw new UnsupportedOperationException();
    }

    /**
     * Extracts the GXL representation from our syndrom and gives it back as string.
     *
     * @return The extracted GXL represenation.
     */
    public String gxlFromInstance(){
        return "";
    }

    /**
     * Save the GXL representation to a specific location.
     *
     * @param pPath The path where the file will be saved.
     * @param pName The name of the exported GXL file.
     */
    public void exportGXL(String pPath, String pName){
        throw new UnsupportedOperationException();
    }

    /**
     * Import a GXL file from a specific location to the syndrom
     *
     * @param pPath The path to the file that gets imported.
     */

    public void importGXL(String pPath){
        throw new UnsupportedOperationException();
    }
}
