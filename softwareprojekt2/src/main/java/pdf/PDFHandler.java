package pdf;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import org.freehep.graphicsbase.util.export.ExportDialog;

public class PDFHandler {

    /**
     * Constructs a new PDFHandler
     *
     */
    public PDFHandler(){

    }

    /**
     * Creates a PDF of the current graph visualization
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     * TODO: PDF AS RETURN VALUE?
     */
    private void createPDF(VisualizationViewer pVv, String pName){

    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     * TODO: JUST AS PDF?
     */
    public void exportPDF(VisualizationViewer pVv, String pName){
        ExportDialog export = new ExportDialog();
        export.showExportDialog(pVv, "Export view as ...", pVv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the syndrom becoming the filename
     */
    public void printPDF(VisualizationViewer pVv, String pName){
        //createPDF(pVv, pName);

    }
}
