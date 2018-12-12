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
<<<<<<< HEAD:softwareprojekt2/src/main/java/gxl/GXLHandler.java
    public static String createGXLString() {
=======
    public String createGXLString(){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/GXLio.java
        throw new UnsupportedOperationException();
    }

    /**
     * Writes the GXL representation into the Database
     *
     * @param pGXL The graph as GXL String that gets written into the Database
     */
<<<<<<< HEAD:softwareprojekt2/src/main/java/gxl/GXLHandler.java
    public static void exportGXL() {
        //createGXLString to File
=======
    public void GXLToDB(String pGXL){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/GXLio.java
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a GXL instance of the current existing Graph as String from the syndrom instance
     *
     * @return The current GXL instance as String
     */
<<<<<<< HEAD:softwareprojekt2/src/main/java/gxl/GXLHandler.java
    public static void importGXL() {
=======
    public String createGXLStringFromInstance(Syndrom pSyndrom){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/GXLio.java
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to save the the GXL representation to a specific location
     */
    public void exportAsGXL(Syndrom pSyndrom){
        //createGXLString to File
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to import a GXL file from a specific location to a layout representation of the current graph
     */
<<<<<<< HEAD:softwareprojekt2/src/main/java/gxl/GXLHandler.java
    public static void GXLToDB(String pGXL) {
=======
    public Syndrom importGXL(){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/GXLio.java
        throw new UnsupportedOperationException();
    }

}
