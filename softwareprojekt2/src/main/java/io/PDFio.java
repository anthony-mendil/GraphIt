package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;
<<<<<<< HEAD:softwareprojekt2/src/main/java/pdf/PDFHandler.java

=======
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/PDFio.java
import java.io.FileInputStream;

public class PDFio {

    private VisualizationViewer vv;

    private String name;

    /**
<<<<<<< HEAD:softwareprojekt2/src/main/java/pdf/PDFHandler.java
     * Constructs a new PDFHandler
     */
    public PDFHandler() {

=======
     * Constructs a new PDFio
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public PDFio(VisualizationViewer pVv, String pName){
        vv=pVv;
        name=pName;
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/PDFio.java
    }

    /**
     * Updates the PDFio handler to the current VisualizationViewer and syndrom name
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
<<<<<<< HEAD:softwareprojekt2/src/main/java/pdf/PDFHandler.java
     * @return A FileInputStream of the PDF of the current graph
     */
    private FileInputStream createPDF(VisualizationViewer pVv, String pName) {
=======
     */
    public void udpate(VisualizationViewer pVv, String pName){
        vv=pVv;
        name=pName;
    }

    /**
     * Creates a PDF of the current graph visualization
     *
     *  @return A FileInputStream of the PDF of the current graph
     */
    private FileInputStream createPDF(){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/PDFio.java
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
<<<<<<< HEAD:softwareprojekt2/src/main/java/pdf/PDFHandler.java
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public void exportPDF(VisualizationViewer pVv, String pName) {
=======
     */
    public void exportPDF(){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/PDFio.java
        throw new UnsupportedOperationException();
        //ExportDialog export = new ExportDialog();
        //export.showExportDialog(pVv, "Export view as ...", pVv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
<<<<<<< HEAD:softwareprojekt2/src/main/java/pdf/PDFHandler.java
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public void printPDF(VisualizationViewer pVv, String pName) {
=======
     */
    public void printPDF(){
>>>>>>> 9a9717b3beb4b7808690c34f6ea19d4c2eff07c7:softwareprojekt2/src/main/java/io/PDFio.java
        throw new UnsupportedOperationException();
        //createPDF(pVv, pName);

    }
}
