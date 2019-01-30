package actions.export_import;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.GXLio;

import java.io.File;

/**
 * Exports a GXL file with the current syndrom graph.
 */
public class ExportGxlAction extends GraphAction {

    /**
     * The File the gxl get's written into
     */
    private File file;

    /**
     * Constructs action handling for exporting the graph as GXL file.
     *
     * @param pFile The destination of the gxl-file
     */
    public ExportGxlAction(File pFile) {
        file=pFile;
        if(!Syndrom.getInstance().getGraph().getSpheres().isEmpty()) {
            Syndrom.getInstance().getVv().getPickedSphereState().clear();
        }    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        GXLio gxlio = new GXLio();
        gxlio.exportGXL(file);
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
