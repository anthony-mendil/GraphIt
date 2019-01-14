package actions.edit.form;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditVerticesFormParam;

/**
 * Changes the form of the selected vertices.
 */
public class EditVerticesFormLogAction extends LogAction {
    private VertexShapeType type;
    /**
     * Constructor in case the user changes the form of the selected vertices.
     * Gets the picked vertices though pick support.
     * @param pVertexShapeType  The vertices shape type.
     */
    public EditVerticesFormLogAction(VertexShapeType pVertexShapeType) {
        super(LogEntryName.EDIT_VERTICES_FORM);
        type = pVertexShapeType;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesFormParam The parameter object that contains every parameter that is needed.
     */
    public EditVerticesFormLogAction(EditVerticesFormParam pEditVerticesFormParam) {
        super(LogEntryName.EDIT_VERTICES_FORM);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();

        for (Vertex vertex: pickedState.getPicked()) {
            vertex.setShape(type);
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
