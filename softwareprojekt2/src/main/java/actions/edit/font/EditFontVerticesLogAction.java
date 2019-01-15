package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditFontVerticesParam;

/**
 * Changes the font of annotations.
 */
public class EditFontVerticesLogAction extends LogAction {
    private String font;
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont The font-name.
     */
    public EditFontVerticesLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_VERTICES);
        font = pFont;
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontVerticesParam The parameter object that contains every parameter that is needed.
     */
    public EditFontVerticesLogAction(EditFontVerticesParam pEditFontVerticesParam) {
        super(LogEntryName.EDIT_FONT_VERTICES);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();

        for (Vertex vertex: pickedState.getPicked()) {
            vertex.setFont(font);
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
