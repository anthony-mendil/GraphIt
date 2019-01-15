package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditFontSizeVerticesParam;

/**
 * Changes the font-size of vertex annotations.
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    private int size;
    /**
     * Constructor in case the user changes the font-size of the annotation.
     *
     * @param pSize The size of the font.
     */
    public EditFontSizeVerticesLogAction(int pSize) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
        size = pSize;
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSizeVerticesParam The EditFontSizeVerticesParam object containing
     *                                   the new font sizes of the Vertices.
     */
    public EditFontSizeVerticesLogAction(EditFontSizeVerticesParam pEditFontSizeVerticesParam) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
    }
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();

        for (Vertex vertex: pickedState.getPicked()) {
            vertex.setFontSize(size);
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
