package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditFontSphereParam;

/**
 * Changes the font of annotations.
 */
public class EditFontSphereLogAction extends LogAction {
    private String font;
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont The font-name.
     */
    public EditFontSphereLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_SPHERE);
        font = pFont;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSphereParam The EditFontSphereParam object containing the new font and the sphere.
     */
    public EditFontSphereLogAction(EditFontSphereParam pEditFontSphereParam) {
        super(LogEntryName.EDIT_FONT_SPHERE);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();

        for (Sphere sp: pickedState.getPicked()) {
            sp.setFont(font);
        }
        vv.repaint();
        syndrom.getVv2().repaint();

    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
