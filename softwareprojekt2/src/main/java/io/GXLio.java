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
    public  String createGXLFromDB() {
        throw new UnsupportedOperationException();
    }

    /**
     * Writes the GXL representation into the given syndrom
     *
     * @param pGXL The GXL that gets written into Syndrom
     * @return The Syndrom that the GXL gets written into
     */
    public Syndrom GXLToSyndrom(String pGXL){
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to save the the GXL representation to a specific location
     * @param pGXL The GXL to export
     */
    public void exportAsGXL(String pGXL){
        //createGXLString to File
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to import a GXL file from a specific location to a layout representation of the current graph
     *
     * @return The imported GXL as String
     */

    public String importGXL(){
        throw new UnsupportedOperationException();
        //
    }
}
