package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereAnnotationParam;
import log_management.parameters.edit.EditSphereColorParam;

import java.awt.*;
import java.net.CookieHandler;


/**
 * Changes the color of the selected sphere and also the vertices which belongs to it.
 */

public class EditSphereColorLogAction extends LogAction {
    /**
     * temporary parameter for the color.
     */
    private Color color;
    /**
     * Constructor in case the user clicks on a sphere to change the color.
     * Gets the sphere through pick support.
     * @param pColor The color to paint the sphere.
     */
    public EditSphereColorLogAction(Color pColor) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
        color = pColor;
    }

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param pEditSphereColorParam The parameter object containing the sphere and color.
     */
    public EditSphereColorLogAction(EditSphereColorParam pEditSphereColorParam) {
        super(LogEntryName.EDIT_SPHERE_COLOR);
        parameters = pEditSphereColorParam;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
    if(parameters == null) {
        for (Sphere sp : pickedState.getPicked()) {
            sp.setFillPaint(color);
            createParameter(sp, sp.getFillPaint(), color);
        }
    }else{
        Sphere sphere = ((EditSphereColorParam)parameters).getSphere();
        Paint newColor = ((EditSphereColorParam)parameters).getNewColor();
            sphere.setFillPaint(newColor);
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Paint oldColor = ((EditSphereColorParam)parameters).getOldColor();
        Paint newColor = ((EditSphereColorParam)parameters).getNewColor();
        Sphere sphere  = ((EditSphereColorParam)parameters).getSphere();
        EditSphereColorParam editSphereColorParam = new EditSphereColorParam(sphere, oldColor,newColor);
        EditSphereColorLogAction editSphereColorLogAction = new EditSphereColorLogAction(editSphereColorParam);
        editSphereColorLogAction.action();
    }


    public void createParameter(Sphere sphere, Paint oldColor, Paint newColor) {
        parameters = new EditSphereColorParam(sphere,oldColor, newColor);
    }
}
