package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereSizeParam;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Changes the sphere size.
 */
public class EditSphereSizeLogAction extends LogAction {
    /**
     * The option of the change of the sphere-size.(either shrink or enlarge)
     */
    private SizeChange sizeChange;
    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer();

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters The vertices object containing the sphere and size.
     */
    public EditSphereSizeLogAction(EditSphereSizeParam parameters) {
        super(LogEntryName.EDIT_SPHERE_SIZE);
        this.parameters = parameters;
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
        if(parameters == null) {
            Sphere lockedSphere = null;
            for (Sphere sp : pickedState.getPicked()) {
                if(!sp.isLockedStyle()){
                if (sizeChange == SizeChange.ENLARGE) {
                    double newHeight = sp.getHeight() + 10;
                    double newWidth = sp.getWidth() + 10;
                    SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
                    double x = sp.getCoordinates().getX();
                    double y = sp.getCoordinates().getY();
                    boolean enlarge = true;

                    Rectangle2D newShape = new Rectangle2D.Double(x, y, newWidth, newHeight);

                    for (Sphere s : graph.getSpheres()) {
                        Shape sphereShape = sphereShapeTransformer.transform(s);
                        if (!s.equals(sp) && sphereShape.intersects(newShape)) {
                            enlarge = false;
                            break;
                        }
                    }
                    if (enlarge) {
                        Sphere spWithOldValues = new Sphere(sp.getId(), sp.getColor(), sp.getCoordinates(),
                                sp.getWidth(), sp.getHeight(), sp.getAnnotation(), sp.getFont(), sp.getFontSize());
                        sp.setHeight(newHeight);
                        sp.setWidth(newWidth);
                        createParameter(spWithOldValues, true);

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
                        Sphere spWithOldValues = new Sphere(sp.getId(), sp.getColor(), sp.getCoordinates(),
                                sp.getWidth(), sp.getHeight(), sp.getAnnotation(), sp.getFont(), sp.getFontSize());
                        sp.setHeight(sp.getHeight() - 10);
                        sp.setWidth(sp.getWidth() - 10);
                        createParameter(spWithOldValues, false);

                    }
                }
            } else{
                    helper.setActionText("Die Größe der Sphäre darf aufgrund der Vorlageregeln nicht geändert werden.", true);
                    lockedSphere = sp;
                }
            }
        }else{
            boolean enlarged = ((EditSphereSizeParam)parameters).isEnlarge();
            if (enlarged) {
                Sphere sphere = ((EditSphereSizeParam) parameters).getSphere();
                sphere.setWidth(sphere.getWidth() + 10);
                sphere.setHeight(sphere.getHeight() + 10);
            } else {
                Sphere sphere = ((EditSphereSizeParam) parameters).getSphere();
                sphere.setWidth(sphere.getWidth() - 10);
                sphere.setHeight(sphere.getHeight() - 10);
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        boolean enlarged = ((EditSphereSizeParam)parameters).isEnlarge();
        Sphere sphere = ((EditSphereSizeParam)parameters).getSphere();
        if (enlarged) {
            EditSphereSizeParam editSphereSizeParam = new EditSphereSizeParam(sphere, false);
            EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(editSphereSizeParam);
            editSphereSizeLogAction.action();
        } else {
            EditSphereSizeParam editSphereSizeParam = new EditSphereSizeParam(sphere, true);
            EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(editSphereSizeParam);
            editSphereSizeLogAction.action();
        }
    }

    /**
     * Creates the vertices object.
     */
    public void createParameter(Sphere sphere, boolean enlarge) {
        parameters = new EditSphereSizeParam(sphere, enlarge);
    }
}
