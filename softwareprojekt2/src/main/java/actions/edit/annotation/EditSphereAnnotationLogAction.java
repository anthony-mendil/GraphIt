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
import java.util.Map;

/**
 * Changes the annotation of a selected sphere.
 */
public class EditSphereAnnotationLogAction extends LogAction {
    /**
     * The new annotation of the sphere.
     */
    private Map<Language,String> text;
    private Language language;
    /**
     * Constructor in case the user changes the annotation of a sphere.
     *
     * @param pText The new sphere annotation.
     */
    public EditSphereAnnotationLogAction(Map<Language,String> pText) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
        text = pText;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereAnnotationParam The param object containing the sphere and annotation to change to.
     */
    private EditSphereAnnotationLogAction(EditSphereAnnotationParam pEditSphereAnnotationParam) {
        super(LogEntryName.EDIT_SPHERE_ANNOTATION);
        parameters = pEditSphereAnnotationParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        if(parameters == null) {
            for (Sphere sp : pickedState.getPicked()) {
                if(!sp.isLockedAnnotation() || values.getMode() == FunctionMode.TEMPLATE) {
                    createParameter(sp, sp.getAnnotation().get(Language.ENGLISH.name()), text.get(Language.ENGLISH), sp.getAnnotation().get(Language.GERMAN.name()), text.get(Language.GERMAN));
                    Map<String, String> annotation = sp.getAnnotation();
                    annotation.put(Language.GERMAN.name(), text.get(Language.GERMAN));
                    annotation.put(Language.ENGLISH.name(), text.get(Language.ENGLISH));
                    sp.setAnnotation(annotation);
                } else{
                    helper.setActionText("EDIT_SPERE_ANNOTATION_ALERT", true, true);
                    lockedSpheres.add(sp);
                    actionHistory.removeLastEntry();
                }
            }
        }else{
            Sphere sphere = ((EditSphereAnnotationParam)parameters).getSphere();
            Map<String,String> newAnno = new HashMap<>();
            newAnno.put(Language.ENGLISH.name(), ((EditSphereAnnotationParam)parameters).getNewAnnotationEnglish());
            newAnno.put(Language.GERMAN.name(), ((EditSphereAnnotationParam)parameters).getNewAnnotationGerman());
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
        String oldAnnotationDe = ((EditSphereAnnotationParam)parameters).getOldAnnotationEnglish();
        String newAnnotationDe = ((EditSphereAnnotationParam)parameters).getNewAnnotationEnglish();
        String oldAnnotationEn = ((EditSphereAnnotationParam)parameters).getOldAnnotationEnglish();
        String newAnnotationEn = ((EditSphereAnnotationParam)parameters).getNewAnnotationEnglish();
        Sphere sphere = ((EditSphereAnnotationParam)parameters).getSphere();

        EditSphereAnnotationParam editSphereAnnotationParam = new EditSphereAnnotationParam(sphere, newAnnotationEn, oldAnnotationEn, newAnnotationDe, oldAnnotationDe);
        EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(editSphereAnnotationParam);
        editSphereAnnotationLogAction.action();
    }

    public void createParameter(Sphere sphere, String oldAnnotationEn, String newAnnotationEn, String oldAnnotationDe, String newAnnotationDe) {
        parameters = new EditSphereAnnotationParam(sphere, oldAnnotationEn, newAnnotationEn, oldAnnotationDe, newAnnotationDe);
    }
}
