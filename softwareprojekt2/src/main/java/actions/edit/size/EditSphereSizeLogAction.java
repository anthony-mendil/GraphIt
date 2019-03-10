package actions.edit.size;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.edit.EditSphereSizeParam;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Changes the sphere size.
 * @author Clement Phung, Nina Unterberg
 */
public class EditSphereSizeLogAction extends LogAction {
    /**
     * The option of the change of the sphere-size.(either shrink or enlarge)
     */
    private SizeChange sizeChange;
    /**
     * Transforms the size of the sphere.
     */
    @SuppressWarnings("unchecked")
    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer();

    /**
     * Constructor which will be used to realize the undo-method of itself.
     *
     * @param parameters The vertices object containing the sphere and size.
     */
    private EditSphereSizeLogAction(EditSphereSizeParam parameters) {
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
        PickedState<Vertex> pickedState1 = vv.getPickedVertexState();
        pickedState1.clear();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        if (parameters == null) {
            for (Sphere sp : pickedState.getPicked()) {
                if (!handleSphere(sp, graph)) {
                    return;
                }
            }
        } else {
            Pair<Double, Double> newSize = ((EditSphereSizeParam) parameters).getNewSize();
            Sphere sphere = ((EditSphereSizeParam) parameters).getSphere();
            sphere.setWidth(newSize.getValue());
            sphere.setHeight(newSize.getKey());
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        Pair<Double, Double> oldSize = ((EditSphereSizeParam) parameters).getOldSize();
        Pair<Double, Double> newSize = ((EditSphereSizeParam) parameters).getNewSize();
        Sphere sphere = ((EditSphereSizeParam) parameters).getSphere();
        EditSphereSizeParam editSphereSizeParam = new EditSphereSizeParam(sphere, newSize, oldSize);
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(editSphereSizeParam);
        editSphereSizeLogAction.action();
    }

    /**
     * Changes sphere size according to template rules.
     *
     * @param sp    The Sphere to change the size.
     * @param graph The current SyndromGraph.
     * @return true if the Sphere was handled successfully.
     */
    private boolean handleSphere(Sphere sp, SyndromGraph<Vertex, Edge> graph) {
        if (!sp.isLockedStyle() || values.getMode() == FunctionMode.TEMPLATE) {
            if (sizeChange == SizeChange.ENLARGE) {
                if (!doEnlarge(sp, graph)) {
                    return false;
                }
            } else {
                if (!doShrink(sp)) {
                    return false;
                }

            }
        } else {
            helper.setActionText(loadLanguage.loadLanguagesKey("EDIT_SPHERE_SIZE_ALERT"), true, false);
            actionHistory.removeLastEntry();
            return false;
        }
        return true;
    }

    /**
     * Shrinks the sphere.
     * @param sp The sphere.
     * @return True: The action was successful | False: The action was unsuccessful.
     */
    private boolean doShrink(Sphere sp) {
        Shape sphereShape = sphereShapeTransformer.transform(sp);
        for (Vertex v : sp.getVertices()) {
            if (!sphereShape.contains(new Point2D.Double(v.getCoordinates().getX() + 10, v.getCoordinates().getY() + 10))) {
                actionHistory.removeLastEntry();
                return false;
            }
        }
        if (sp.getHeight() > 20 && sp.getWidth() > 20) {
            Pair<Double, Double> oldSize = new Pair<>(sp.getWidth(), sp.getHeight());
            sp.setHeight(sp.getHeight() - 10);
            sp.setWidth(sp.getWidth() - 10);
            Pair<Double, Double> newSize = new Pair<>(sp.getWidth(), sp.getHeight());
            createParameter(sp, oldSize, newSize);

        } else {
            actionHistory.removeLastEntry();
            return false;
        }
        return true;
    }
    /**
     * Enlarges the sphere.
     * @param sp The sphere.
     * @param graph The graph to work on.
     * @return True: The action was successful | False: The action was unsuccessful.
     */
    private boolean doEnlarge(Sphere sp, SyndromGraph<Vertex, Edge> graph) {
        double newHeight = sp.getHeight() + 10;
        double newWidth = sp.getWidth() + 10;
        double x = sp.getCoordinates().getX();
        double y = sp.getCoordinates().getY();

        Rectangle2D newShape = new Rectangle2D.Double(x, y, newWidth, newHeight);

        for (Sphere s : graph.getSpheres()) {
            Shape sphereShape = sphereShapeTransformer.transform(s);
            if (!s.equals(sp) && sphereShape.intersects(newShape)) {
                actionHistory.removeLastEntry();
                return false;
            }
        }

        Pair<Double, Double> oldSize = new Pair<>(sp.getWidth(), sp.getHeight());
        sp.setHeight(newHeight);
        sp.setWidth(newWidth);
        Pair<Double, Double> newSize = new Pair<>(sp.getWidth(), sp.getHeight());
        createParameter(sp, oldSize, newSize);
        return true;
    }

    /**
     * Creates the parameter-object for this action.
     *
     * @param sphere  The selected sphere.
     * @param oldSize The old size of the sphere.
     * @param newSize The new size of the sphere.
     */
    public void createParameter(Sphere sphere, Pair<Double, Double> oldSize, Pair<Double, Double> newSize) {
        parameters = new EditSphereSizeParam(sphere, oldSize, newSize);
    }
}
