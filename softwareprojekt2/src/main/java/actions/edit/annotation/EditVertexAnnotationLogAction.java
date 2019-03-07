package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.properties.Language;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditVertexAnnotationParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Changes the annotation of a selected Vertex.
 */
public class EditVertexAnnotationLogAction extends LogAction {
    /**
     * The annotation of the vertex.
     */
    private Map<Language, String> text;

    /**
     * Constructor in case the user wants to change the annotation of vertex.
     *
     * @param pText The new vertex annotation.
     */
    public EditVertexAnnotationLogAction(Map<Language, String> pText) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
        text = pText;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditVertexAnnotationParam The vertices object containing the new vertex annotation.
     */
    private EditVertexAnnotationLogAction(EditVertexAnnotationParam pEditVertexAnnotationParam) {
        super(LogEntryName.EDIT_VERTEX_ANNOTATION);
        parameters = pEditVertexAnnotationParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (parameters == null) {
            List<Vertex> lockedVertices = new ArrayList<>();
            for (Vertex v : pickedState.getPicked()) {
                if (!v.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    createParameter(v, v.getAnnotation().get(Language.ENGLISH.name()), text.get(Language.ENGLISH), v.getAnnotation().get(Language.GERMAN.name()), text.get(Language.GERMAN));
                    Map<String, String> annotation = v.getAnnotation();
                    annotation.put(Language.GERMAN.name(), text.get(Language.GERMAN));
                    annotation.put(Language.ENGLISH.name(), text.get(Language.ENGLISH));
                    v.setAnnotation(annotation);
                } else {
                    helper.setActionText("EDIT_VERTEX_ANNOTATION_ALERT", true, true);
                    lockedVertices.add(v);
                }
            }
            if (lockedVertices.size() == pickedState.getPicked().size() && !lockedVertices.isEmpty()) {
                actionHistory.removeLastEntry();
                return;
            }
        } else {
            Vertex vertex = ((EditVertexAnnotationParam) parameters).getVertex();
            Map<String, String> newAnnotation = new HashMap<>();
            newAnnotation.put(Language.ENGLISH.name(), (((EditVertexAnnotationParam) parameters).getNewAnnotationEnglish()));
            newAnnotation.put(Language.GERMAN.name(), ((EditVertexAnnotationParam) parameters).getNewAnnotationGerman());
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
        Vertex vertex = ((EditVertexAnnotationParam) parameters).getVertex();
        String oldAnnotationEn = ((EditVertexAnnotationParam) parameters).getOldAnnotationEnglish();
        String newAnnotationEn = ((EditVertexAnnotationParam) parameters).getNewAnnotationEnglish();
        String oldAnnotationDe = ((EditVertexAnnotationParam) parameters).getOldAnnotationGerman();
        String newAnnotationDe = ((EditVertexAnnotationParam) parameters).getNewAnnotationGerman();
        EditVertexAnnotationParam editVertexAnnotationParam = new EditVertexAnnotationParam(vertex, newAnnotationEn, oldAnnotationEn, newAnnotationDe, oldAnnotationDe);
        EditVertexAnnotationLogAction editVertexAnnotationLogAction = new EditVertexAnnotationLogAction(editVertexAnnotationParam);
        editVertexAnnotationLogAction.action();
    }

    /**
     * Creates a parameter object for thea action.
     *
     * @param vertex               The selected vertex.
     * @param oldAnnotationEnglish The old annotation in english.
     * @param newAnnotationEnglish The new annotation in english.
     * @param oldAnnotationGerman  The old annotation in german.
     * @param newAnnotationGerman  The new annotation in german.
     */
    public void createParameter(Vertex vertex, String oldAnnotationEnglish, String newAnnotationEnglish, String oldAnnotationGerman, String newAnnotationGerman) {
        parameters = new EditVertexAnnotationParam(vertex, oldAnnotationEnglish, newAnnotationEnglish, oldAnnotationGerman, newAnnotationGerman);
    }
}

