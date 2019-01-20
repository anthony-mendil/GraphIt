package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.SizeChange;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditVerticesSizeParam;

/**
 * Changes the size of a vertex or vertices.
 */
public class EditVerticesSizeLogAction extends LogAction {
    private SizeChange sizeChange;
    /**
     * Changes the size of the vertices.
     */
    public EditVerticesSizeLogAction(SizeChange sizeChange) {
        super(LogEntryName.EDIT_VERTICES_SIZE);
        this.sizeChange = sizeChange;
    }
    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesSizeParam The parameter object that contains every parameter that is needed.
     */
    public EditVerticesSizeLogAction(EditVerticesSizeParam pEditVerticesSizeParam) {
        super(LogEntryName.EDIT_VERTICES_SIZE);
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();

        for (Vertex vertex: pickedState.getPicked()) {
            if (sizeChange == SizeChange.ENLARGE){
                vertex.setSize(vertex.getSize()+5);
                System.out.println("layout" +vv.getGraphLayout().transform(vertex));
            } else {
                if (vertex.getSize() > 45){
                    vertex.setSize(vertex.getSize()-5);
                }
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the parameter object.
     */
    public void createParameter() {
        throw new UnsupportedOperationException();
    }

}
