package actions.layout;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.move.LayoutSpheresParam;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Layouts the graph according to a previously defined layout.
 */
public class LayoutSphereGraphLogAction extends LogAction {
    /**
     * Constants for the separation space between spheres in x-direction.
     */
    private static final int SEP_X = 15;
    /**
     * Constants for the separation space between spheres in y-direction.
     */
    private static final int SEP_Y = 15;
    /**
     * Defines a comparator for the spheres.
     */
    private static final Comparator<Sphere> sphereCompare = Comparator.comparingDouble(sphere -> sphere.getCoordinates().getX());
    /**
     * Indicator whether the action is an undo action.
     */
    private boolean indicator;
    /**
     * the SyndromVisualisation viewer.
     */
    private SyndromVisualisationViewer<Vertex, Edge> vv;

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutSphereGraphLogAction() {
        super(LogEntryName.EDIT_SPHERES_LAYOUT);
    }

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     *
     * @param pLayoutSpheresParam the param containing a layout to change the current layout to
     */
    private LayoutSphereGraphLogAction(LayoutSpheresParam pLayoutSpheresParam) {
        super(LogEntryName.EDIT_SPHERES_LAYOUT);
        parameters = pLayoutSpheresParam;
    }

    /**
     * Creates the parameter-object for this action.
     *
     * @param oldSpheres  a list containing the old spheres (position) and its width/height.
     * @param oldVertices a list containing the old vertices (position) and its position.
     */
    public void createParameter(Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSpheres, Map<Vertex, Point2D> oldVertices) {
        parameters = new LayoutSpheresParam(oldSpheres, oldVertices);
    }

    /**
     * Finding the smallest x/ y.
     *
     * @param sphereList A list of the spheres.
     * @return The point with the smallest y/ x.
     */
    private Point2D getSmallestXY(List<Sphere> sphereList) {
        double x = 20;
        double y = 20;
        for (Sphere s : sphereList) {
            Point2D point2D = s.getCoordinates();
            if (point2D.getY() < y || point2D.getY() == y && point2D.getX() < x) {
                x = point2D.getX();
                y = point2D.getY();
            }

        }
        return new Point2D.Double(x, y);
    }

    @Override
    public void action() {
        vv = syndrom.getVv();
        AggregateLayout<Vertex, Edge> layout = syndrom.getLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> sphereList = graph.getSpheres();
        if (parameters == null || indicator) {
            if (!sphereList.isEmpty()) {
                Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSphereMap = new HashMap<>();
                Point2D xY = getSmallestXY(sphereList);
                double x = xY.getX();
                double y = xY.getY();
                double height = getHeights(sphereList, oldSphereMap).getFirst();
                double minHeight = getHeights(sphereList, oldSphereMap).getSecond();
                double smallestY = getYs(sphereList, oldSphereMap).getFirst();
                double largestY = getYs(sphereList, oldSphereMap).getSecond();

                int maxI = (int) ((largestY - smallestY) / minHeight) + 5;

                ArrayList<ArrayList<Sphere>> sphereRows = new ArrayList<>(maxI);
                setSphereRows(sphereRows, sphereList, maxI, height, y);

                for (ArrayList<Sphere> sphereRow : sphereRows) {
                    sphereRow.sort(sphereCompare);
                }
                Map<Vertex, Point2D> verticesCoordinates = new HashMap<>();
                layoutSpheres(sphereRows, x, height, y, verticesCoordinates);
                indicator = true;
                createParameter(oldSphereMap, verticesCoordinates);
            }
        } else {
            setLayoutWithParameters(layout, graph);
        }
        vv.repaint();
        syndrom.getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    /**
     * Finds the max/min height of a sphere.
     *
     * @param sphereList   A list containing all spheres.
     * @param oldSphereMap The old spheres map (parameters).
     * @return The max height and min height.
     */
    private edu.uci.ics.jung.graph.util.Pair<Double> getHeights(List<Sphere> sphereList,
                                                                Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSphereMap) {
        double height = sphereList.get(0).getHeight();
        double minHeight = sphereList.get(0).getHeight();

        for (Sphere sp : sphereList) {
            double spHeight = sp.getHeight();
            oldSphereMap.put(sp, new Pair<>(new Pair<>(sp.getWidth(), sp.getHeight()), sp.getCoordinates()));
            if (height < spHeight) {
                height = spHeight;
            }
            if (minHeight > spHeight) {
                minHeight = spHeight;
            }
        }
        return new edu.uci.ics.jung.graph.util.Pair<>(height, minHeight);
    }

    /**
     * Finds the max/min y position of a sphere.
     *
     * @param sphereList   A list containing all spheres.
     * @param oldSphereMap The old spheres map (parameters).
     * @return The min/ max Y position.
     */
    private edu.uci.ics.jung.graph.util.Pair<Double> getYs(List<Sphere> sphereList,
                                                           Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSphereMap) {
        double smallestY = sphereList.get(0).getCoordinates().getY();
        double largestY = sphereList.get(0).getCoordinates().getY();
        for (Sphere sp : sphereList) {
            oldSphereMap.put(sp, new Pair<>(new Pair<>(sp.getWidth(), sp.getHeight()), sp.getCoordinates()));

            double sphereY = sp.getCoordinates().getY();
            if (smallestY > sphereY) {
                smallestY = sphereY;
            }

            if (sphereY > largestY) {
                largestY = sphereY;
            }
        }
        return new edu.uci.ics.jung.graph.util.Pair<>(smallestY, largestY);
    }

    /**
     * Layouts the spheres.
     *
     * @param sphereRows          The spheres sorted in rows according to its position.
     * @param x                   The current x value.
     * @param size                The height/ width value.
     * @param yCoordinate         The y coordinate.
     * @param verticesCoordinates A list with vertices nd its positions (parameters).
     */
    private void layoutSpheres(ArrayList<ArrayList<Sphere>> sphereRows, double x, double size, double yCoordinate, Map<Vertex, Point2D> verticesCoordinates) {
        for (ArrayList<Sphere> sphereRow : sphereRows) {
            double xCoordinate = x;

            for (Sphere s : sphereRow) {
                double dx = xCoordinate - s.getCoordinates().getX();
                double dy = yCoordinate - s.getCoordinates().getY();
                s.setHeight(size);
                s.setWidth(size);
                s.setCoordinates(new Point2D.Double(xCoordinate, yCoordinate));
                xCoordinate = xCoordinate + size + SEP_X;
                for (Vertex v : s.getVertices()) {
                    verticesCoordinates.put(v, v.getCoordinates());

                    Point2D point = new Point2D.Double(v.getCoordinates().getX() + dx, v.getCoordinates()
                            .getY() + dy);
                    v.setCoordinates(point);
                    vv.getGraphLayout().setLocation(v, point);
                }
            }
            if (!sphereRow.isEmpty()) {
                yCoordinate = yCoordinate + size + SEP_Y;
            }
        }
    }

    /**
     * Sorts the spheres in rows according to ist current coordinates.
     *
     * @param sphereRows The list, which will be containing the spheres sorted in rows.
     * @param sphereList A list, containing all spheres.
     * @param maxI       The max iteration value.
     * @param height     The height/ width of the spheres.
     * @param y          The y coordinate.
     */
    private void setSphereRows(ArrayList<ArrayList<Sphere>> sphereRows, List<Sphere> sphereList, int maxI, double height, double y) {
        for (int i = 0; i < maxI; i++) {
            sphereRows.add(new ArrayList<>());
        }

        for (Sphere sp : sphereList) {
            for (int i = 0; i < maxI; i++) {
                if (sp.getCoordinates().getY() >= y + (height * i) - height / 2 && sp.getCoordinates().getY() < y +
                        (height * (i + 1)) - height / 2) {
                    sphereRows.get(i).add(sp);
                }
            }
        }
    }

    /**
     * Sets the position of the vertices to the layout.
     *
     * @param layout The current layout.
     * @param graph  The current graph.
     */
    private void setLayoutWithParameters(AggregateLayout<Vertex, Edge> layout, SyndromGraph<Vertex, Edge> graph) {
        Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSpheres = ((LayoutSpheresParam) parameters).getOldPosition();
        Map<Vertex, Point2D> oldVertices = ((LayoutSpheresParam) parameters).getOldVerticesMap();
        for (Map.Entry<Sphere, Pair<Pair<Double, Double>, Point2D>> entry : oldSpheres.entrySet()) {
            Pair<Pair<Double, Double>, Point2D> info = entry.getValue();
            entry.getKey().setWidth(info.getKey().getKey());
            entry.getKey().setHeight(info.getKey().getValue());
            entry.getKey().setCoordinates(info.getValue());
        }
        for (Map.Entry<Vertex, Point2D> entry : oldVertices.entrySet()) {
            entry.getKey().setCoordinates(oldVertices.get(entry.getKey()));
        }
        layout.removeAll();

        for (Sphere s : graph.getSpheres()) {
            for (Vertex v : s.getVertices()) {
                layout.setLocation(v, v.getCoordinates());
            }
        }
    }

    @Override
    public void undo() {
        LayoutSpheresParam layoutSpheresParam = (LayoutSpheresParam) parameters;
        LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction(layoutSpheresParam);
        layoutSphereGraphLogAction.action();
    }
}
