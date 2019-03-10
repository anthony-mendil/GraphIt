package actions.export_import;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import io.Protocolio;

import java.io.File;

/**
 * Exports an readable file with the current protocol.
 * @author Jonah Jaeger
 */
public class ExportReadableProtocolAction extends GraphAction {

    /**
     * The File the protocol get's written into.
     */
    private File file;

    /**
     * Constructs action handling for exporting the protocol as readable file.
     *
     * @param pFile The destination of the protocol-file.
     */
    public ExportReadableProtocolAction(File pFile) {
        file = pFile;
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
        Protocolio protocolio = new Protocolio();
        protocolio.exportAsReadableProtocol(file);
    }

    /**
     * Disables the undo-function for the protocol export.
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-function for the protocol export.
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
