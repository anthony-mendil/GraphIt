package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SphereSizeChange;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import log_management.parameters.edit.EditSphereSizeParam;

/**
 * Changes the sphere size.
 */
public class EditSphereSizeLogAction extends LogAction {
    private SphereSizeChange sphereSizeChange;
    /**
     * Constructor which will be used to realize the undo-method of itself.
     * @param parameters The parameter object containing the sphere and size.
     */
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new action to change the size of a a sphere.
     */
    public EditSphereSizeLogAction(SphereSizeChange sphereSizeChange){
        super(LogEntryName.EDIT_SPHERE_SIZE);
        this.sphereSizeChange = sphereSizeChange;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        for (Sphere sp: pickedState.getPicked()) {
            if (sphereSizeChange == SphereSizeChange.ENLARGE) {
                sp.setHeight(sp.getHeight()+10);
                sp.setWidth(sp.getWidth()+10);
            } else {
                if (sp.getHeight() > 20 && sp.getWidth() > 20){
                    sp.setHeight(sp.getHeight()-10);
                    sp.setWidth(sp.getWidth()-10);
                }
            }
        }
        vv.repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the parameter object.
     */
    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
