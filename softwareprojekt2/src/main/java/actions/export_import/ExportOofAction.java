package actions.export_import;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import io.OOFio;

import java.io.File;

/**
 * Exports an OOF file with the current syndrom graph.
 * @author Jonah Jaeger
 */
public class ExportOofAction extends GraphAction {

    /**
     * The File the oof get's written into.
     */
    private File file;

    /**
     * Constructs action handling for exporting the graph as OOF file.
     *
     * @param pFile The destination of the oof-file.
     */
    public ExportOofAction(File pFile) {
        file = pFile;
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) Syndrom.getInstance().getVv().getGraphLayout().getGraph();
        if (!graph.getSpheres().isEmpty()) {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        }
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        OOFio oofio = new OOFio();
        oofio.exportAsOOF(file);
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
