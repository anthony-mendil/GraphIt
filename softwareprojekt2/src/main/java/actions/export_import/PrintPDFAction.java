package actions.export_import;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import io.PDFio;

/**
 * Prints the current graph visualization as a PDF file.
 * @author Jonah Jaeger
 */
public class PrintPDFAction extends GraphAction {

    /**
     * Action handling for printing the graph as PDF file.
     */
    public PrintPDFAction() {
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if (!graph.getSpheres().isEmpty()) {
            syndrom.getVv().getPickedSphereState().clear();
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
     * Disables the undo-function for the oof export.
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-function for the oof export.
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
