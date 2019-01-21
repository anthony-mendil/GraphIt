package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereAnnotationParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * The new annotation of the sphere.
     */
    private String text;
    /**
     * Constructor in case the user changes the annotation of a sphere.
     *
     * @param pText The new sphere annotation.
     */
    public EditSphereAnnotationLogAction(String pText) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
        text = pText;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereAnnotationParam The param object containing the sphere and annotation to change to.
     */
    public EditSphereAnnotationLogAction(EditSphereAnnotationParam pEditSphereAnnotationParam) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
        parameters = pEditSphereAnnotationParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        if(parameters == null) {
            for (Sphere sp : pickedState.getPicked()) {
                createParameter(sp, sp.getAnnotation().get("de"), text);
                Map<String, String> annotation = sp.getAnnotation();
                if(annotation.get("de") != null){
                    annotation.remove("de");
                    sp.setAnnotation(annotation);
                }
                annotation.put("de", text);
                sp.setAnnotation(annotation);
            }
        }else{
            Sphere sphere = ((EditSphereAnnotationParam)parameters).getSphere();
            String newAnnotation = ((EditSphereAnnotationParam)parameters).getNewAnnotation();
            Map<String,String> newAnno = new HashMap<>();
            newAnno.put("de", newAnnotation);
            sphere.setAnnotation(newAnno);

        }
        vv.repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
        syndrom.getVv2().repaint();

    }

    @Override
    public void undo() {
        String oldAnnotation = ((EditSphereAnnotationParam)parameters).getOldAnnotation();
        String newAnnotation = ((EditSphereAnnotationParam)parameters).getNewAnnotation();
        Sphere sphere = ((EditSphereAnnotationParam)parameters).getSphere();
        EditSphereAnnotationParam editSphereAnnotationParam = new EditSphereAnnotationParam(sphere, newAnnotation, oldAnnotation);
        EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(editSphereAnnotationParam);
        editSphereAnnotationLogAction.action();
    }

    public void createParameter(Sphere sphere, String oldAnnotation, String newAnnotation) {
        parameters = new EditSphereAnnotationParam(sphere, oldAnnotation, newAnnotation);
    }
}
