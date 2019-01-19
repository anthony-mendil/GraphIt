package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontVerticesParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the font of annotations.
 */
public class EditFontVerticesLogAction extends LogAction {
    /**
     * The new font.
     */
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
        parameters = pEditFontVerticesParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if(parameters == null) {
            Map<Vertex,String> oldVertices = new HashMap<>();
            Map<Vertex,String> newVertices = new HashMap<>();
            for (Vertex vertex : pickedState.getPicked()) {
                oldVertices.put(vertex, vertex.getFont());
                vertex.setFont(font);
                newVertices.put(vertex, font);
            }
            createParameter(oldVertices, newVertices);
        }else{
            Map<Vertex,String> oldVertices = ((EditFontVerticesParam)parameters).getOldVertices();
            Map<Vertex,String> newVertices = ((EditFontVerticesParam)parameters).getNewVertices();
                for (Map.Entry<Vertex,String> entry : oldVertices.entrySet()){
                    entry.getKey().setFont(newVertices.get(entry.getKey()));
                }
        }
        vv.repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Map<Vertex, String> oldVertices = ((EditFontVerticesParam)parameters).getOldVertices();
        Map<Vertex, String> newVertices = ((EditFontVerticesParam)parameters).getNewVertices();
        EditFontVerticesParam editFontVerticesParam = new EditFontVerticesParam(newVertices, oldVertices);
        EditFontVerticesLogAction editFontVerticesLogAction = new EditFontVerticesLogAction(editFontVerticesParam);
        editFontVerticesLogAction.action();
    }

    public void createParameter(Map<Vertex,String> oldVertices, Map<Vertex,String> newVertices) {
        parameters = new EditFontVerticesParam(oldVertices, newVertices);
    }
}
