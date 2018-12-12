package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.io.FileInputStream;

public class PDFio {

    private VisualizationViewer vv;

    private String name;

    /**
     * Constructs a new PDFio
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public PDFio(VisualizationViewer pVv, String pName){
        vv=pVv;
        name=pName;
    }

    /**
     * Updates the PDFio handler to the current VisualizationViewer and syndrom name
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
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
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     */
    public void exportPDF(){
        throw new UnsupportedOperationException();
        //ExportDialog export = new ExportDialog();
        //export.showExportDialog(pVv, "Export view as ...", pVv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     */
    public void printPDF(){
        throw new UnsupportedOperationException();
        //createPDF(pVv, pName);

    }
}
