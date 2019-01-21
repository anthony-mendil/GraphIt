package actions.edit;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditEdgesStrokeParam;

/**
 * Changes the stroke of the selected edges.
 */
public class EditEdgesStrokeLogAction extends LogAction {
    private StrokeType stroke;
    /**
     * Constructor for the EditEdgesStrokeLogAction.
     * @param pStrokeType The new stroke type.
     */
    public EditEdgesStrokeLogAction(StrokeType pStrokeType) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        stroke = pStrokeType;
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditEdgesStrokeParam The parameter object that contains every parameter that is needed.
     */
    public EditEdgesStrokeLogAction(EditEdgesStrokeParam pEditEdgesStrokeParam) {
        super(LogEntryName.EDIT_EDGES_STROKE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Edge> pickedState = vv.getPickedEdgeState();

        for (Edge e: pickedState.getPicked()) {
            e.setStroke(stroke);
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
