package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontSizeVerticesParam;

import java.util.HashMap;
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
    public EditFontSizeVerticesLogAction(EditFontSizeVerticesParam pEditFontSizeVerticesParam) {
        super(LogEntryName.EDIT_VERTICES_FONT_SIZE);
        parameters = pEditFontSizeVerticesParam;
    }
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        Map<Vertex,Integer> oldVerticesParam = new HashMap<>();
        Map<Vertex,Integer> newVerticesParam = new HashMap<>();
        if(parameters == null){
            for (Vertex vertex: pickedState.getPicked()) {
                oldVerticesParam.put(vertex, vertex.getFontSize());
                newVerticesParam.put(vertex, size);
                vertex.setFontSize(size);
            }
            createParameter();
        }else{
            Map<Vertex,Integer> oldVertices = ((EditFontSizeVerticesParam)parameters).getOldVertices();
            Map<Vertex,Integer> newVertices = ((EditFontSizeVerticesParam)parameters).getNewVertices();
            for(Map.Entry<Vertex, Integer> entry : oldVertices.entrySet()){
                Vertex vertex = entry.getKey();
                vertex.setFontSize(newVertices.get(vertex));
            }
        }
        vv.repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
