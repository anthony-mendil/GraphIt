package io;

import graph.graph.Syndrom;

public class GXLio {

    public GXLio(){

    }

    /**
     * Creates a GXL instance of the current existing Graph as String from the Database
     *
     * @return The current GXL instance as String
     */
    public static String createGXLString() {
        throw new UnsupportedOperationException();
    }

    /**
     * Writes the GXL representation into the Database
     *
     * @param pGXL The graph as GXL String that gets written into the Database
     */
    public void GXLToDB(String pGXL){
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a GXL instance of the current existing Graph as String from the syndrom instance
     * @param pSyndrom the syndrom create the gxl from
     * @return The current GXL instance as String
     */
    public String createGXLStringFromInstance(Syndrom pSyndrom){
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to save the the GXL representation to a specific location
     * @param pSyndrom the syndrom to export
     */
    public void exportAsGXL(Syndrom pSyndrom){
        //createGXLString to File
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to import a GXL file from a specific location to a layout representation of the current graph
     * @return the new syndrom presentation
     */

    public Syndrom importGXL(){
        throw new UnsupportedOperationException();
    }
}
