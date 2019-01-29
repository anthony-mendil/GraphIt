package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.OOFio;

import java.io.File;

/**
 * Exports an OOF file with the current syndrom graph.
 */
public class ExportOofAction extends GraphAction {

    /**
     * The File the oof get's written into
     */
    private File file;

    /**
     * Constructs action handling for exporting the graph as OOF file.
     *
     * @param pFile The destination of the oof-file
     */
    public ExportOofAction(File pFile) {
        file = pFile;
        try {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        } catch (NullPointerException e) {
            e.printStackTrace();
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
     * Disables the undo-funktion for the oof export
     */
    @Override
    public void undo() {
    }

    /**
     * Disables the redo-funktion for the oof export
     */
    @Override
    public void redo() {
    }
}
