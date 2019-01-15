package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SphereSizeChange;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import log_management.parameters.edit.EditSphereSizeParam;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Changes the sphere size.
 */
public class EditSphereSizeLogAction extends LogAction {
    private SphereSizeChange sphereSizeChange;

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters The parameter object containing the sphere and size.
     */
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new action to change the size of a a sphere.
     *
     * @param sphereSizeChange The SSC Object to change the size of the sphere
     */
    public EditSphereSizeLogAction(SphereSizeChange sphereSizeChange) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        this.sphereSizeChange = sphereSizeChange;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        for (Sphere sp : pickedState.getPicked()) {
            if (sphereSizeChange == SphereSizeChange.ENLARGE) {
                double newHeight = sp.getHeight() + 10;
                double newWidth = sp.getWidth() + 10;
                SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
                double x = sp.getCoordinates().getX();
                double y = sp.getCoordinates().getY();
                boolean enlarge = true;
                for (int j = (int) y; j < newHeight + y; j++) {
                    Point2D val =  vv.getRenderContext().getMultiLayerTransformer().transform( new Point2D.Double
                            (x+newWidth, j));
                    Sphere s = pickSupport.getSphere(val.getX(), val.getY());
                    if (s != null && !s.equals(sp)) {
                        enlarge = false;
                        break;
                    }
                }

                for (int j = (int) y; j < newWidth + y; j++) {
                    Point2D point =  new Point2D.Double
                            ( j, y+newHeight);
                    point =  vv.getRenderContext().getMultiLayerTransformer().transform(point);
                    Sphere s = pickSupport.getSphere(point.getX(), point.getY());
                    if (s != null && !s.equals(sp)) {
                        enlarge = false;
                        break;
                    }
                }

                if (enlarge) {
                    sp.setHeight(newHeight);
                    sp.setWidth(newWidth);
                }


            } else {
                if (sp.getHeight() > 20 && sp.getWidth() > 20) {
                    sp.setHeight(sp.getHeight() - 10);
                    sp.setWidth(sp.getWidth() - 10);
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
