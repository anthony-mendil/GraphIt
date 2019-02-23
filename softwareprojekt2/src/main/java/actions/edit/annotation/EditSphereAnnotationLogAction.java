package actions.edit.annotation;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.properties.Language;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereAnnotationParam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * The new annotation of the sphere.
     */
    private String text;
    private Language language;
    /**
     * Constructor in case the user changes the annotation of a sphere.
     *
     * @param pText The new sphere annotation.
     */
    public EditSphereAnnotationLogAction(String pText, Language language) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
        text = pText;
        this.language = language;
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
            Set<Sphere> lockedSpheres = new HashSet<>();
            for (Sphere sp : pickedState.getPicked()) {
                if(!sp.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    Map<String, String> annotation = sp.getAnnotation();
                    Map<String, String> oldAnnotation = new HashMap<>();
                    annotation.forEach((s1, s2) -> oldAnnotation.put(s1, s2));

                    Sphere spWithOldValues = new Sphere(sp.getId(), sp.getColor(), sp.getCoordinates(), sp.getWidth(),
                            sp.getHeight(), oldAnnotation, sp.getFont(), sp.getFontSize());
                    createParameter(spWithOldValues, sp, oldAnnotation.get(language.name()), text);
                    if (annotation.get(language.name()) != null) {
                        annotation.remove(language.name());
                        sp.setAnnotation(annotation);
                    }
                    annotation.put(language.name(), text);
                    sp.setAnnotation(annotation);
                } else{
                    helper.setActionText("EDIT_SPERE_ANNOTATION_ALERT", true);
                    lockedSpheres.add(sp);
                }
            }
        }else{
            Sphere sphere = ((EditSphereAnnotationParam)parameters).getNewSphere();
            String newAnnotation = ((EditSphereAnnotationParam)parameters).getNewAnnotation();
            Map<String,String> newAnno = new HashMap<>();
            newAnno.put(language.name(), newAnnotation);
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
        Sphere sphere = ((EditSphereAnnotationParam)parameters).getNewSphere();

        EditSphereAnnotationParam editSphereAnnotationParam = new EditSphereAnnotationParam(sphere, sphere, newAnnotation, oldAnnotation);
        EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(editSphereAnnotationParam);
        editSphereAnnotationLogAction.action();
    }

    public void createParameter(Sphere sphere, Sphere newSphere, String oldAnnotation, String newAnnotation) {
        parameters = new EditSphereAnnotationParam(sphere, newSphere, oldAnnotation, newAnnotation);
    }
}
