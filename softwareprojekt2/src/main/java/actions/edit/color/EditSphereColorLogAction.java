package actions.edit.color;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import jgrapht.JGraphTHandler;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereColorParam;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


/**
 * Changes the color of the selected sphere and also the vertices which belongs to it.
 */

public class EditSphereColorLogAction extends LogAction {
    /**
     * temporary vertices for the color.
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
     * @param pEditSphereColorParam The vertices object containing the sphere and color.
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
        Sphere lockedSphere = null;
        for (Sphere sp : pickedState.getPicked()) {
            if(!sp.isLockedStyle()|| values.getMode() == FunctionMode.TEMPLATE) {
                createParameter(sp, sp.getColor(), color);
                sp.setColor(color);
            }else{
                helper.setActionText("Die Farbe der Sphäre darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                lockedSphere = sp;
            }
        }
    }else{
        Sphere sphere = ((EditSphereColorParam)parameters).getSphere();
        Color newColor = ((EditSphereColorParam)parameters).getNewColor();
            sphere.setColor(newColor);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

     //   JGraphTHandler jGraphTHandler = new JGraphTHandler(graph.getVertices(),);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Color oldColor = ((EditSphereColorParam)parameters).getOldColor();
        Color newColor = ((EditSphereColorParam)parameters).getNewColor();
        Sphere sphere  = ((EditSphereColorParam)parameters).getSphere();
        EditSphereColorParam editSphereColorParam = new EditSphereColorParam(sphere, newColor,oldColor);
        EditSphereColorLogAction editSphereColorLogAction = new EditSphereColorLogAction(editSphereColorParam);
        editSphereColorLogAction.action();

    }


    public void createParameter(Sphere sphere, Color oldColor, Color newColor) {
        parameters = new EditSphereColorParam(sphere,oldColor, newColor);
    }
}
