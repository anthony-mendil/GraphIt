package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.Protocolio;

import java.io.File;

/**
 * Exports an readable file with the current protocol.
 */
public class ExportReadableProtocolAction extends GraphAction {

    /**
     * The File the protocol get's written into
     */
    private File file;

    /**
     * Constructs action handling for exporting the protocol as readable file.
     *
     * @param pFile The destination of the protocol-file
     */
    public ExportReadableProtocolAction(File pFile) {
        file=pFile;
        Syndrom.getInstance().getVv().getPickedSphereState().clear();
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        Protocolio protocolio=new Protocolio();
        protocolio.exportAsReadableProtocol(file);
    }

    /**
     * Disables the undo-funktion for the protocol export
     */
    @Override
    public void undo() {
    }

    /**
     * Disables the redo-funktion for the protocol export
     */
    @Override
    public void redo() {
    }
}