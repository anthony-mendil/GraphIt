package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontSizeVerticesParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Changes the font-size of vertex annotations.
 */
public class EditFontSizeVerticesLogAction extends LogAction {
    /**
     * The new font size of the vertices.
     */
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
    private EditFontSizeVerticesLogAction(EditFontSizeVerticesParam pEditFontSizeVerticesParam) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
        parameters = pEditFontSizeVerticesParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        Map<Vertex, Integer> oldVerticesParam = new HashMap<>();
        Map<Vertex, Integer> newVerticesParam = new HashMap<>();
        if (parameters == null) {
            List<Vertex> lockedVertices = new LinkedList<>();
            for (Vertex vertex : pickedState.getPicked()) {
                if (!vertex.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    oldVerticesParam.put(vertex, vertex.getFontSize());
                    vertex.setFontSize(size);
                    newVerticesParam.put(vertex, size);
                } else {
                    helper.setActionText("EDIT_VERTICES_FONT_SIZE_ALERT", true, true);
                    lockedVertices.add(vertex);
                }
            }
            if(lockedVertices.size() > 0 && lockedVertices.size() == pickedState.getPicked().size()){
                actionHistory.removeLastEntry();
                return;
            }
            createParameter(oldVerticesParam, newVerticesParam);
        } else {
            Map<Vertex, Integer> oldVertices = ((EditFontSizeVerticesParam) parameters).getOldVertices();
            Map<Vertex, Integer> newVertices = ((EditFontSizeVerticesParam) parameters).getNewVertices();
            for (Map.Entry<Vertex, Integer> entry : oldVertices.entrySet()) {
                Vertex vertex = entry.getKey();
                vertex.setFontSize(newVertices.get(vertex));
            }
        }
        vv.repaint();

        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Map<Vertex, Integer> oldVertices = ((EditFontSizeVerticesParam) parameters).getOldVertices();
        Map<Vertex, Integer> newVertices = ((EditFontSizeVerticesParam) parameters).getNewVertices();
        EditFontSizeVerticesParam editFontSizeVerticesParam = new EditFontSizeVerticesParam(newVertices, oldVertices);
        EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(editFontSizeVerticesParam);
        editFontSizeVerticesLogAction.action();
    }

    /**
     * Creates a new parameter-object of this action.
     *
     * @param oldVertices The old vertices and their new font-size.
     * @param newVertices The new vertices and their new font-size.
     */
    public void createParameter(Map<Vertex, Integer> oldVertices, Map<Vertex, Integer> newVertices) {
        parameters = new EditFontSizeVerticesParam(oldVertices, newVertices);
    }
}
