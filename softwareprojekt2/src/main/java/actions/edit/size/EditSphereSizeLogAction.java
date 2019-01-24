package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import log_management.parameters.edit.EditSphereSizeParam;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Changes the sphere size.
 */
public class EditSphereSizeLogAction extends LogAction {
    private SizeChange sizeChange;
    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer();

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
     * @param sizeChange The SSC Object to change the size of the sphere
     */
    public EditSphereSizeLogAction(SizeChange sizeChange) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        this.sizeChange = sizeChange;
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Sphere> pickedState = vv.getPickedSphereState();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
      //  if(parameters ==)
        for (Sphere sp : pickedState.getPicked()) {
            if (sizeChange == SizeChange.ENLARGE) {
                double newHeight = sp.getHeight() + 10;
                double newWidth = sp.getWidth() + 10;
                SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
                double x = sp.getCoordinates().getX();
                double y = sp.getCoordinates().getY();
                boolean enlarge = true;

                Rectangle2D newShape = new Rectangle2D.Double(x,y,newWidth, newHeight);

                for (Sphere s : graph.getSpheres()){
                    Shape sphereShape = sphereShapeTransformer.transform(s);
                    if (!s.equals(sp) && sphereShape.intersects( newShape)){
                        enlarge = false;
                        break;
                    }
                }

                if (enlarge) {
                    sp.setHeight(newHeight);
                    sp.setWidth(newWidth);
                }

            } else {
                boolean add = true;
                SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer<Sphere>();
                Shape sphereShape = sphereShapeTransformer.transform(sp);
                for (Vertex v : sp.getVertices()) {
                    if (!sphereShape.contains(v.getCoordinates())) {
                        add = false;
                        break;
                    }
                }
                if (add && sp.getHeight() > 20 && sp.getWidth() > 20) {
                    sp.setHeight(sp.getHeight() - 10);
                    sp.setWidth(sp.getWidth() - 10);
                }
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates the parameter object.
     */
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
