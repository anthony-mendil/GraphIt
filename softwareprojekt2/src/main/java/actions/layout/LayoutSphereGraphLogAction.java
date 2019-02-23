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
    final public static Comparator<Sphere> sphereCompare = new Comparator<Sphere>() {
        @Override
        public int compare(Sphere sphere1, Sphere sphere2) {
            return Integer.compare((int) sphere1.getCoordinates().getX(), (int) sphere2.getCoordinates().getX());
        }
    };
    /**
     * Indicator whether the action is an undo action.;
     */
    private boolean indicator;

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutSphereGraphLogAction() {
        super(LogEntryName.EDIT_SPHERES_LAYOUT);
    }


    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutSphereGraphLogAction(LayoutSpheresParam pLayoutSpheresParam) {
        super(LogEntryName.EDIT_SPHERES_LAYOUT);
        parameters = pLayoutSpheresParam;
    }

    public void createParameter(Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSpheres, Map<Vertex, Point2D> oldVertices) {
        parameters = new LayoutSpheresParam(oldSpheres, oldVertices);
    }

    private Point2D getSmallestXY(List<Sphere> sphereList) {
        double x = 20;
        double y = 20;
        for (Sphere s : sphereList) {
            Point2D point2D = s.getCoordinates();
            if (point2D.getY() < y) {
                x = point2D.getX();
                y = point2D.getY();
            }

        }
        return new Point2D.Double(x, y);
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        AggregateLayout<Vertex, Edge> layout = syndrom.getLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> sphereList = graph.getSpheres();
        if (parameters == null || indicator == true) {
            if (!sphereList.isEmpty()) {
                Map<Sphere, Pair<Pair<Double, Double>, Point2D>> oldSphereMap = new HashMap<>();
                Point2D xY = getSmallestXY(sphereList);
                double x = xY.getX();
                double y = xY.getY();

                double height = sphereList.get(0).getHeight();
                double minHeight = sphereList.get(0).getHeight();

                double smallestY = sphereList.get(0).getCoordinates().getY();
                double largestY = sphereList.get(0).getCoordinates().getY();
                for (Sphere sp : sphereList) {
                    double spHeight = sp.getHeight();
                    oldSphereMap.put(sp, new Pair<>(new Pair<>(sp.getWidth(), sp.getHeight()), sp.getCoordinates()));
                    if (height < spHeight) {
                        height = spHeight;
                    }
                    if (minHeight > spHeight) {
                        minHeight = spHeight;
                    }
                    double sphereY = sp.getCoordinates().getY();
                    if (smallestY > sphereY) {
                        smallestY = sphereY;
                    }

                    if (sphereY > largestY) {
                        largestY = sphereY;
                    }
                }

                int maxI = (int) ((largestY - smallestY) / minHeight) + 5;

                ArrayList<ArrayList<Sphere>> sphereRows = new ArrayList<>(maxI);
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

                int sepX = 15;
                int sepY = 15;
                double yCoord = y;

                for (ArrayList<Sphere> sphereRow : sphereRows) {
                    sphereRow.sort(sphereCompare);
                }
                Map<Vertex, Point2D> verticesCoordinates = new HashMap<>();
                for (ArrayList<Sphere> sphereRow : sphereRows) {
                    double xCoord = x;

                    for (Sphere s : sphereRow) {
                        double dx = xCoord - s.getCoordinates().getX();
                        double dy = yCoord - s.getCoordinates().getY();
                        s.setHeight(height);
                        s.setWidth(height);
                        s.setCoordinates(new Point2D.Double(xCoord, yCoord));
                        xCoord = xCoord + height + sepX;
                        for (Vertex v : s.getVertices()) {
                            verticesCoordinates.put(v, v.getCoordinates());

                            Point2D point = new Point2D.Double(v.getCoordinates().getX() + dx, v.getCoordinates()
                                    .getY() + dy);
                            v.setCoordinates(point);
                            vv.getGraphLayout().setLocation(v, point);
                        }
                    }
                    if (!sphereRow.isEmpty()) {
                        yCoord = yCoord + height + sepY;
                    }
                }
                indicator = true;
                createParameter(oldSphereMap, verticesCoordinates);
            }
        } else {
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
        vv.repaint();
        syndrom.getInstance().getVv2().repaint();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        LayoutSpheresParam layoutSpheresParam = (LayoutSpheresParam) parameters;
        LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction(layoutSpheresParam);
        layoutSphereGraphLogAction.action();
    }
}
