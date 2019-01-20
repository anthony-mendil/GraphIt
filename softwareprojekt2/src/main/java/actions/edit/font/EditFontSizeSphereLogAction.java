package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontSizeSphereParam;
import log_management.tables.Log;

/**
 * Changes the font-size of annotations of a sphere.
 */
public class EditFontSizeSphereLogAction extends LogAction {
    /**
     * Temporary size parameter.
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
            for (Sphere sp : pickedState.getPicked()) {
                sp.setFontSize(size);
            }
        }else{
            Sphere sphere = ((EditFontSizeSphereParam)parameters).getSphereText();
            int newFontSize = ((EditFontSizeSphereParam)parameters).getNewFontSize();
            sphere.setFontSize(newFontSize);
        }
        vv.repaint();
        syndrom.getVv2().repaint();


        DatabaseManager databaseManager = DatabaseManager.getInstance();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
