package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditFontSizeSphereParam;

/**
 * Changes the font-size of annotations of a sphere.
 */
public class EditFontSizeSphereLogAction extends LogAction {
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
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();

        for (Sphere sp: pickedState.getPicked()) {
            sp.setFontSize(size);
        }
        vv.repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
