package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import java.io.FileInputStream;

public class PDFio {

    /**
     * The VisualizationViewer of the current graph
     */
    private VisualizationViewer vv;


    /**
     * Constructs a new PDFio
     *
     * @param pVv The VisualizationViewer of the current graph
     */
    public PDFio(VisualizationViewer pVv){
        vv=pVv;
    }

    /**
     * Creates a PDF of the current graph visualization
     *
     *  @return A FileInputStream of the PDF of the current graph
     */
    protected FileInputStream createPDF(){
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     */
    public void exportPDF() {
        throw new UnsupportedOperationException();
        //ExportDialog export = new ExportDialog();
        //export.showExportDialog(pVv, "Export view as ...", pVv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     */
    public void printPDF() {
        throw new UnsupportedOperationException();
    }
}
