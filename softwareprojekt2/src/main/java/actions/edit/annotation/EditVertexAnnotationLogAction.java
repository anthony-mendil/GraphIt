package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVertexAnnotationParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the annotation of a selected Vertex.
 */
public class EditVertexAnnotationLogAction extends LogAction {
    /**
     * The annotation of the vertex.
     */
    private String text;
    /**
     * Constructor in case the user wants to change the annotation of vertex.
     *
     * @param pText The new vertex annotation.
     */
    public EditVertexAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
        text = pText;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVertexAnnotationParam The vertices object containing the new vertex annotation.
     */
    public EditVertexAnnotationLogAction(EditVertexAnnotationParam pEditVertexAnnotationParam) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
        parameters = pEditVertexAnnotationParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if(parameters == null) {
            for (Vertex v : pickedState.getPicked()) {
                Map<String, String> annotation = v.getAnnotation();
                createParameter(v, v.getAnnotation().get("de"),text);
                annotation.put("de", text);
                v.setAnnotation(annotation);
            }
        }else{
            Vertex vertex = ((EditVertexAnnotationParam)parameters).getVertex();
            String newString = ((EditVertexAnnotationParam)parameters).getNewAnnotation();
            Map<String,String> newAnnotation = new HashMap<>();
            newAnnotation.put("de",newString);
            vertex.setAnnotation(newAnnotation);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Vertex vertex = ((EditVertexAnnotationParam)parameters).getVertex();
        String oldAnnotation = ((EditVertexAnnotationParam)parameters).getOldAnnotation();
        String newAnnotation = ((EditVertexAnnotationParam)parameters).getNewAnnotation();
        EditVertexAnnotationParam editVertexAnnotationParam = new EditVertexAnnotationParam(vertex,newAnnotation,oldAnnotation);
        EditVertexAnnotationLogAction editVertexAnnotationLogAction = new EditVertexAnnotationLogAction(editVertexAnnotationParam);
        editVertexAnnotationLogAction.action();
    }

    public void createParameter(Vertex vertex, String oldAnnotation, String newAnnotation) {
        parameters = new EditVertexAnnotationParam(vertex, oldAnnotation, newAnnotation);
    }
}

