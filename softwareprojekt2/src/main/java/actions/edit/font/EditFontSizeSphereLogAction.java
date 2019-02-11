package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontSizeSphereParam;

import java.util.List;

/**
 * Changes the font-size of annotations of a sphere.
 */
public class EditFontSizeSphereLogAction extends LogAction {
    /**
     * Temporary size vertices.
     */
    private int size;
    /**
     * Constructor in case the user changes the font-size of a sphere annotation.
     * @param pSize The size of the font.
     *
     */
    public EditFontSizeSphereLogAction(Integer pSize) {
        super(LogEntryName.EDIT_SPHERE_FONT_SIZE);
        size = pSize;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeSphereParam The EditFontSizeSphereParam object containing the font size and the sphere.
     */
    public EditFontSizeSphereLogAction(EditFontSizeSphereParam pEditFontSizeSphereParam) {
        super(LogEntryName.EDIT_SPHERE_FONT_SIZE);
        parameters = pEditFontSizeSphereParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        if(parameters == null) {
            Sphere lockedSphere = null;
            for (Sphere sp : pickedState.getPicked()) {
                if(!sp.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    sp.setFontSize(size);
                    createParameter(sp, sp.getFontSize(), size);
                }else{
                    helper.setActionText("Die Größe der Schrift der Sphäre darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedSphere = sp;
                }
            }
        }else{
            Sphere sphere = ((EditFontSizeSphereParam)parameters).getSphere();
            int newFontSize = ((EditFontSizeSphereParam)parameters).getNewFontSize();
            sphere.setFontSize(newFontSize);
        }
        vv.repaint();
        syndrom.getVv2().repaint();


        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        int oldFontSize = ((EditFontSizeSphereParam)parameters).getOldFontSize();
        int newFontSize = ((EditFontSizeSphereParam)parameters).getNewFontSize();
        Sphere sphere = ((EditFontSizeSphereParam)parameters).getSphere();
        EditFontSizeSphereParam editFontSizeSphereParam = new EditFontSizeSphereParam(sphere, newFontSize, oldFontSize);
        EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(editFontSizeSphereParam);
        editFontSizeSphereLogAction.action();
    }


    public void createParameter(Sphere sphere, int oldFontSize, int newFontSize) {
        parameters = new EditFontSizeSphereParam(sphere,oldFontSize,newFontSize);
    }
}
