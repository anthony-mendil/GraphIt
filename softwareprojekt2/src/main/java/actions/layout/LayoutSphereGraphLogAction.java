package actions.layout;

import actions.LogAction;
import actions.LogEntryName;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Layouts the graph according to a previously defined layout.
 */
public class LayoutSphereGraphLogAction extends LogAction {

    /**
     * Layouts the graph (including all vertices) according to the defined layout.
     */
    public LayoutSphereGraphLogAction() {
        super(LogEntryName.EDIT_SPHERES_LAYOUT);
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> sphereList = graph.getSpheres();
        if (!sphereList.isEmpty()){
            double y = 20;
            double x = 20;

            double height = sphereList.get(0).getHeight();
            double minHeight = sphereList.get(0).getHeight();
            for (Sphere sp: sphereList) {
                double spHeight = sp.getHeight();
                if (height < spHeight){
                    height = spHeight;
                }
                if (minHeight > spHeight){
                    minHeight = spHeight;
                }
            }
            int maxI = (int) ((double) values.getDefaultLayoutVVSize().height / minHeight);
            ArrayList<ArrayList<Sphere>> sphereRows = new ArrayList<>(maxI);
            for(int i = 0; i < maxI; i++){
                sphereRows.add(new ArrayList<>());
            }
            for(Sphere sp: sphereList){
                for(int i = 0; i<maxI; i++){
                    if (sp.getCoordinates().getY() >= y+(height*i ) - height/2 && sp.getCoordinates().getY() < y+
                            (height*(i+1))- height/2){
                        sphereRows.get(i).add(sp);
                    }
                }
            }

            int sepX = 15;
            int sepY = 15;
            double yCoord = y;

            for(ArrayList<Sphere> sphereRow : sphereRows){
                sphereRow.sort(sphereCompare);
            }

            for (ArrayList<Sphere> sphereRow : sphereRows) {
                double xCoord = x;

                for (Sphere s : sphereRow) {
                    double dx = xCoord - s.getCoordinates().getX();
                    double dy = yCoord - s.getCoordinates().getY();
                    s.setHeight(height);
                    s.setWidth(height);
                    s.setCoordinates(new Point2D.Double(xCoord, yCoord));
                    xCoord = xCoord + height + sepX;
                    for(Vertex v : s.getVertices()){
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
        }
        vv.repaint();
        syndrom.getVv2().repaint();
    }

    public static Comparator<Sphere> sphereCompare = new Comparator<Sphere>() {
        @Override
        public int compare(Sphere sphere1, Sphere sphere2) {
                return Integer.compare((int)sphere1.getCoordinates().getX(), (int) sphere2.getCoordinates().getX());
        }
    };

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
