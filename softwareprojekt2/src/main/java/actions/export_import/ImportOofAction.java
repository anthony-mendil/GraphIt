package actions.export_import;

import actions.GraphAction;
import io.OOFio;

import java.io.File;

/**
 * Imports an OOF file.
 */
public class ImportOofAction extends GraphAction {

    /**
     * The File the oof get's taken from.
     */
    private File file;

    /**
     * Action handling for importing the graph as GXL file.
     *
     * @param pFile The File that the oof is imported from.
     */
    public ImportOofAction(File pFile) {
        file = pFile;
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        OOFio oofio = new OOFio();
        oofio.importOOF(file);
        notifyObserverGraph();
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
