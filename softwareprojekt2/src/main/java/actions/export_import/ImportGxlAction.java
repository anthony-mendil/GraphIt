package actions.export_import;

import actions.Action;
import actions.GraphAction;
import io.GXLio;
import log_management.DatabaseManager;

import java.io.File;

/**
 * Imports a GXL file that includes a graph in gxl notation.
 */
public class ImportGxlAction extends GraphAction {

    /**
     * The File the gxl get's taken from
     */
    private File file;

    public boolean templateFound;
    private GXLio gxlio;

    /**
     * Action handling for importing the graph as GXL file.
     *
     * @param pFile The File that the GXL is imported from.
     */
    public ImportGxlAction(File pFile) {
        file = pFile;
        gxlio = new GXLio();
    }


    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        gxlio.importGXL(file, false);
        templateFound=gxlio.isTemplateFoundFlag();
        if(templateFound){
            System.out.println("tf: "+templateFound);
            return;
        }
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
