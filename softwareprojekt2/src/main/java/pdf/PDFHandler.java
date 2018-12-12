package pdf;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.io.FileInputStream;

public class PDFHandler {

    /**
     * Constructs a new PDFHandler
     */
    public PDFHandler() {

    }

    /**
     * Creates a PDF of the current graph visualization
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     * @return A FileInputStream of the PDF of the current graph
     */
    private FileInputStream createPDF(VisualizationViewer pVv, String pName) {
        throw new UnsupportedOperationException();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public void exportPDF(VisualizationViewer pVv, String pName) {
        throw new UnsupportedOperationException();
        //ExportDialog export = new ExportDialog();
        //export.showExportDialog(pVv, "Export view as ...", pVv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     *
     * @param pVv   The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public void printPDF(VisualizationViewer pVv, String pName) {
        throw new UnsupportedOperationException();
        //createPDF(pVv, pName);

    }
}
