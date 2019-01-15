package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditVerticesFillColorParam;

import java.awt.*;

/**
 * Changes the color of a single/several vertices.
 */
public class EditVerticesFillColorLogAction extends LogAction {
    private Color color;
    /**
     * Constructor in case the user changes the color of a single/multiple vertices.
     * Gets the vertices though pick support.
     * @param pColor The color to paint the edges.
     */
    public EditVerticesFillColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
        color = pColor;
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesFillColorParam The EditVertexAnnotationParam object containing the new vertex annotation.
     */
    public EditVerticesFillColorLogAction(EditVerticesFillColorParam pEditVerticesFillColorParam) {
        super(LogEntryName.EDIT_VERTICES_FILL_COLOR);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();

        for (Vertex vertex: pickedState.getPicked()) {
            vertex.setFillPaint(color);
        }
        vv.repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
