package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.SizeChange;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVerticesSizeParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the size of a vertex or vertices.
 * @author Clement Phung, Nina Unterberg
 */
public class EditVerticesSizeLogAction extends LogAction {
    /**
     * Indicator whether the vertices will shrink or enlarge.
     */
    private SizeChange sizeChange;

    /**
     * Changes the size of the vertices.
     *
     * @param sizeChange: the type of size change for the vertex
     */
    public EditVerticesSizeLogAction(SizeChange sizeChange) {
        super(LogEntryName.EDIT_VERTICES_SIZE);
        this.sizeChange = sizeChange;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVerticesSizeParam The vertices object that contains every vertices that is needed.
     */
    private EditVerticesSizeLogAction(EditVerticesSizeParam pEditVerticesSizeParam) {
        super(LogEntryName.EDIT_VERTICES_SIZE);
        parameters = pEditVerticesSizeParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState1 = vv.getPickedSphereState();
        pickedState1.clear();
        if (parameters == null) {
            PickedState<Vertex> pickedState = vv.getPickedVertexState();
            Map<Vertex, Integer> oldVertices = new HashMap<>();
            Map<Vertex, Integer> newVertices = new HashMap<>();
            editSize(pickedState, oldVertices, newVertices);
            createParameter(oldVertices, newVertices);
        } else {
            Map<Vertex, Integer> oldVertices = ((EditVerticesSizeParam) parameters).getOldVertices();
            Map<Vertex, Integer> newVertices = ((EditVerticesSizeParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Integer> entry : oldVertices.entrySet()) {
                entry.getKey().setSize(newVertices.get(entry.getKey()));
            }
        }

        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    /**
     * Changes the size of the vertex.
     * @param pickedState The vertices picked.
     * @param oldVertices The old state of the vertices.
     * @param newVertices The new vertices with their new size.
     */
    private void editSize(PickedState<Vertex> pickedState, Map<Vertex, Integer> oldVertices, Map<Vertex, Integer> newVertices) {
        for (Vertex vertex : pickedState.getPicked()) {
            if (!vertex.isLockedStyle()) {
                if (sizeChange == SizeChange.ENLARGE) {
                    oldVertices.put(vertex, vertex.getSize());
                    vertex.setSize(vertex.getSize() + 5);
                    newVertices.put(vertex, vertex.getSize());
                } else {
                    if (vertex.getSize() > 45) {
                        oldVertices.put(vertex, vertex.getSize());
                        vertex.setSize(vertex.getSize() - 5);
                        newVertices.put(vertex, vertex.getSize());
                    }
                }
            } else {
                helper.setActionText("EDIT_VERTICES_SITE_ALERT", true, true);
                actionHistory.removeLastEntry();
                return;
            }
        }
    }

    @Override
    public void undo() {
        Map<Vertex, Integer> oldVertices = ((EditVerticesSizeParam) parameters).getOldVertices();
        Map<Vertex, Integer> newVertices = ((EditVerticesSizeParam) parameters).getNewVertices();
        EditVerticesSizeParam editVerticesSizeParam = new EditVerticesSizeParam(newVertices, oldVertices);
        EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(editVerticesSizeParam);
        editVerticesSizeLogAction.action();
    }

    /**
     * Creates the parameter-object for this action.
     *
     * @param oldVertices The old vertices and their old size.
     * @param newVertices The new vertices and their new size.
     */
    public void createParameter(Map<Vertex, Integer> oldVertices, Map<Vertex, Integer> newVertices) {
        parameters = new EditVerticesSizeParam(oldVertices, newVertices);
    }

}
