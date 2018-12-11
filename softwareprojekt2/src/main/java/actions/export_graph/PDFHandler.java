package actions.export_graph;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import org.freehep.graphicsbase.util.export.ExportDialog;

public class PDFHandler {

    private VisualizationViewer vv;

    /**
     * Constructs a new PDFHandler of the the current graph visualization.
     *
     * @param pVv The VisualizationViewer of the current graph
     * @param pName The name of the Syndrom, that will become the filename
     */
    public PDFHandler(VisualizationViewer pVv, String pName){
        vv=pVv;
    }

    /**
     * Creates a PDF of the current graph visualization
     * TODO: PDF AS RETURN VALUE?
     */
    private void createPDF(){

    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     * TODO: JUST AS PDF?
     */
    public void exportPDF(){
        ExportDialog export = new ExportDialog();
        export.showExportDialog(vv, "Export view as ...", vv, "export");
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF
     */
    public void printPDF(){

    }
}
