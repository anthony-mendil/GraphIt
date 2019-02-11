package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVertexAnnotationParam;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
            List<Vertex> lockedVertices = new LinkedList<>();
            for (Vertex v : pickedState.getPicked()) {
                if(!v.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    Map<String, String> annotation = v.getAnnotation();
                    createParameter(v, v.getAnnotation().get(Language.GERMAN.name()), text);
                    annotation.put(Language.GERMAN.name(), text);
                    v.setAnnotation(annotation);
                }else{
                    helper.setActionText("Der Titel des Knotens darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedVertices.add(v);
                }
            }
        }else{
            Vertex vertex = ((EditVertexAnnotationParam)parameters).getVertex();
            String newString = ((EditVertexAnnotationParam)parameters).getNewAnnotation();
            Map<String,String> newAnnotation = new HashMap<>();
            newAnnotation.put(Language.GERMAN.name(),newString);
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

