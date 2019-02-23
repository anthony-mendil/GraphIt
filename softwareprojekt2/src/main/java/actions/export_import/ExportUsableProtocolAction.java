package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.Protocolio;

import java.io.File;

/**
 * Exports an usable file with the current protocol.
 */
public class ExportUsableProtocolAction extends GraphAction {

    /**
     * The File the protocol get's written into
     */
    private File file;

    /**
     * Constructs action handling for exporting the protocol as usable file.
     *
     * @param pFile The destination of the protocol-file
     */
    public ExportUsableProtocolAction(File pFile) {
        file = pFile;
        if (!Syndrom.getInstance().getGraph().getSpheres().isEmpty()) {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        }
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        Protocolio protocolio = new Protocolio();
        protocolio.exportAsUsableProtocol(file);
    }

    /**
     * Disables the undo-funktion for the protocol export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-funktion for the protocol export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
