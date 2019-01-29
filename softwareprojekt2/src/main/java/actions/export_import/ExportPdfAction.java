package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.PDFio;

import java.io.File;

/**
 * Exports an PDF file with the current graph visualization.
 */
public class ExportPdfAction extends GraphAction {

    /**
     * The File the pdf get's written into
     */
    private File file;

    /**
     * Action handling for exporting the graph as PDF file.
     *
     * @param pFile The destination File
     *
     */
    public ExportPdfAction(File pFile) {
        file=pFile;
        if(!Syndrom.getInstance().getGraph().getSpheres().isEmpty()) {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        }
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        PDFio pdfio = new PDFio(Syndrom.getInstance().getVv());
        pdfio.exportPDF(file);
    }

    /**
     * Disables the undo-funktion for the pfd export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-funktion for the pfd export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
