package actions.export_import;

import actions.Action;
import actions.GraphAction;
import io.GXLio;
import log_management.DatabaseManager;
import lombok.Getter;

import java.io.File;

/**
 * Imports a GXL file that includes a graph in gxl notation.
 */
public class ImportTemplateGxlAction extends GraphAction {

    /**
     * The File the gxl get's taken from
     */
    private File file;

    @Getter
    private boolean templateFound;

    private GXLio gxlio;


    /**
     * Action handling for importing the graph as GXL file.
     *
     * @param pFile The File that the GXL is imported from.
     */
    public ImportTemplateGxlAction(File pFile) {
        file = pFile;
        gxlio = new GXLio();
    }

    /**
     * Disables the undo-funktion for the gxl export
     */
    @Override
    public void undo() {
        //there is no undo/redo operation for io
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        gxlio.importGXL(file, true);
        templateFound = gxlio.isTemplateFoundFlag();
        if (!templateFound) {
            return;
        }
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        Action.attach(databaseManager);
        notifyObserverNewGraph();

    }

    /**
     * Disables the redo-funktion for the templategxl export
     */
    @Override
    public void redo() {
        //there is no undo/redo operation for io
    }
}
