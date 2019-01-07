package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditSphereColorParam;

import java.awt.*;


/**
 * Changes the color of the selected sphere and also the vertices which belongs to it.
 */

public class EditSphereColorLogAction extends LogAction {
    private Color color;
    /**
     * Constructor in case the user clicks on a sphere to change the color.
     * Gets the sphere through pick support.
     * @param pColor The color to paint the sphere.
     */
    public EditSphereColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
        color = pColor;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereColorParam The parameter object containing the sphere and color.
     */
    public EditSphereColorLogAction(EditSphereColorParam pEditSphereColorParam) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();

        for (Sphere sp: pickedState.getPicked()) {
            sp.setFillPaint(color);
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
