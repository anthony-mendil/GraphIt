package actions.export_import;

import actions.Action;
import actions.GraphAction;
import io.GXLio;
import log_management.DatabaseManager;

import java.io.File;

/**
 * Imports a GXL file that includes a graph with editing rules in gxl notation.
 */
public class ImportTemplateGxlAction  extends GraphAction {

    /**
     * The File the gxl get's taken from
     */
    private File file;

    /**
     * Action handling for importing the graph with its editing rules as GXL file.
     *
     * @param pFile The File that the GXL is imported from.
     */
    public ImportTemplateGxlAction(File pFile) {
        file=pFile;
    }

    /**
     * Executes the relevant method calls to cause the import of a graph with editing rules from a gxl file.
     */
    @Override
    public void action() {
        GXLio gxlio = new GXLio();
        gxlio.importGXL(file, false);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Action.attach(databaseManager);
        notifyObserverNewGraph();
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
