package actions.edit.font;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditFontSphereParam;

/**
 * Changes the font of annotations.
 */
public class EditFontSphereLogAction extends LogAction {
    /**
     * The new font.
     */
    private String font;
    /**
     * Constructor in case the user wants to change the font.
     *
     * @param pFont The font-name.
     */
    public EditFontSphereLogAction(String pFont) {
        super(LogEntryName.EDIT_FONT_SPHERE);
        font = pFont;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditFontSphereParam The EditFontSphereParam object containing the new font and the sphere.
     */
    public EditFontSphereLogAction(EditFontSphereParam pEditFontSphereParam) {
        super(LogEntryName.EDIT_FONT_SPHERE);
        parameters = pEditFontSphereParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        if(parameters == null) {
            for (Sphere sp : pickedState.getPicked()) {
                createParameter(sp, sp.getFont(), font);
                sp.setFont(font);
            }
        }else{
            Sphere sphere = ((EditFontSphereParam)parameters).getSphere();
            String newFont = ((EditFontSphereParam)parameters).getNewFont();
            sphere.setFont(newFont);
        }
 
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Sphere sphere = ((EditFontSphereParam)parameters).getSphere();
        String oldFont = ((EditFontSphereParam)parameters).getOldFont();
        String newFont = ((EditFontSphereParam)parameters).getNewFont();
        EditFontSphereParam editFontSphereParam = new EditFontSphereParam(sphere, oldFont, newFont);
        EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(editFontSphereParam);
        editFontSphereLogAction.action();
    }

    public void createParameter(Sphere  sphere, String oldFont, String newFont) {
        parameters = new EditFontSphereParam(sphere, oldFont, newFont);
    }
}
