package actions.export_import;

import actions.GraphAction;
import io.GXLio;

import java.io.File;

/**
 * Imports a GXL file.
 */
public class ImportGxlAction extends GraphAction {

    /**
     * The File the gxl get's taken from
     */
    private File file;

    /**
     * Action handling for importing the graph as GXL file.
     *
     * @param pFile The File that the GXL is imported from.
     */
    public ImportGxlAction(File pFile) {
        file=pFile;
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        GXLio gxlio = new GXLio();
        gxlio.importGXL(file);
    }

    /**
     * Disables the undo-funktion for the gxl export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Disables the redo-funktion for the gxl export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
