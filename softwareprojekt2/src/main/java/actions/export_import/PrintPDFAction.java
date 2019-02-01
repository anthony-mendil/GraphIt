package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.PDFio;

/**
 * Prints the current graph visualization as a PDF file.
 */
public class PrintPDFAction extends GraphAction {

    /**
     * Action handling for printing the graph as PDF file.
     */
    public PrintPDFAction() {
        if (!Syndrom.getInstance().getGraph().getSpheres().isEmpty()) {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        }
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        PDFio pdfio = new PDFio(Syndrom.getInstance().getVv());
        pdfio.printPDF();
    }

    /**
     * Disables the undo-funktion for the oof export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-funktion for the oof export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
