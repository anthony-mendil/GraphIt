package actions.export_graph;

import actions.GraphAction;
import graph.graph.Syndrom;
import io.GXLio;

import java.io.File;

/**
 * Exports a GXL file with the current syndrom graph.
 */
public class ExportGxlTemplateAction extends GraphAction {

    /**
     * The File the gxl template get's written into
     */
    private File file;

    /**
     * Constructs action handling for exporting the graph as GXL template file.
     *
     * @param pFile The destination of the gxl-file
     */
    public ExportGxlTemplateAction(File pFile) {
        file=pFile;
        Syndrom.getInstance().getVv().getPickedSphereState().clear();
    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        GXLio gxlio = new GXLio();
        gxlio.exportGXLTemplate(file);
    }

    /**
     * Disables the undo-funktion for the gxl export
     */
    @Override
    public void undo() {
    }

    /**
     * Disables the redo-funktion for the gxl export
     */
    @Override
    public void redo() {
    }
}
