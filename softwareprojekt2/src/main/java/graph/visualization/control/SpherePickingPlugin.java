package graph.visualization.control;

import actions.ActionHistory;
import actions.add.AddSphereLogAction;
import actions.edit.annotation.EditSphereAnnotationLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.remove.RemoveSphereLogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractGraphMousePlugin;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.transformer.sphere.SphereShapeTransformer;
import gui.GraphButtonType;
import gui.Values;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class SpherePickingPlugin extends AbstractGraphMousePlugin
        implements MouseListener, MouseMotionListener {
    private Point2D spherePickedCoord = null;
    private Point2D downFirst = null;
    private Map<Integer, Point2D> points = null;
    private final Values values;
    private ContextMenu contextMenu;
    private final HelperFunctions helper;


    /**
     * create an instance with passed values
     */
    public SpherePickingPlugin() {
        super(InputEvent.BUTTON3_MASK | InputEvent.BUTTON1_MASK);
        this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        values = Values.getInstance();
        helper = new HelperFunctions();
    }
    {
        contextMenu = createContextMenu();

    }

    private ContextMenu createContextMenu(){
        contextMenu = new ContextMenu();
        MenuItem remove = new MenuItem("Entfernen");
        Image image = new Image("/icons2/008-rubbish-bin.png");
        ImageView iconRemove = new ImageView();
        iconRemove.setImage(image);
        iconRemove.setFitWidth(15);
        iconRemove.setFitHeight(15);
        remove.setGraphic(iconRemove);

        remove.setOnAction(event -> {
            RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
            history.execute(removeSphereLogAction);
        });
        MenuItem annotation = new MenuItem("Titel");

        annotation.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("Sphäre Titel");

            dialog.setHeaderText("Titel Sphäre eingeben:");
            dialog.setContentText("Titel:");
            dialog.setTitle("Sphäre Titel");

            Optional<String> result = dialog.showAndWait();

            result.ifPresent(title -> {
                if (title.length() > 100){
                    title = title.substring(0, 99);
                }
                EditSphereAnnotationLogAction editSphereAnnotationLogAction = new EditSphereAnnotationLogAction(title);
                history.execute(editSphereAnnotationLogAction);
            });

        });

        MenuItem color = new MenuItem("Farbe");
        color.setOnAction(event ->{

            EditSphereColorLogAction editSphereColorLogAction = new EditSphereColorLogAction(values.getFillPaintSphere());
            history.execute(editSphereColorLogAction);
                });

        MenuItem text = new MenuItem("Schriftart");
        text.setOnAction(event ->{
            EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(values.getFontSphere());
            history.execute(editFontSphereLogAction);
        });

        MenuItem size = new MenuItem("Schriftgröße");
        size.setOnAction(event ->{
            EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(values.getFontSizeSphere());
            history.execute(editFontSizeSphereLogAction);
        });

        contextMenu.getItems().addAll(remove, annotation, color, text, size);
        contextMenu.setAutoHide(true);
        return contextMenu;
    }



    /**
     * The ActionHistory
     */
    private ActionHistory history = ActionHistory.getInstance();

    @Override
    @SuppressWarnings("unchecked")
    public void mouseClicked(MouseEvent e) {
        SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        Point2D p = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(e.getPoint());
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        Vertex vertex = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());

        if (SwingUtilities.isLeftMouseButton(e)) {
            List<Sphere> list = graph.getSpheres();
            Rectangle2D newRec = new Rectangle2D.Double(p.getX(), p.getY(), values.getDefaultWidthSphere(),
                    values.getDefaultHeightSphere());
            boolean addSphere = true;
            if (values.getGraphButtonType() == GraphButtonType.ADD_SPHERE && sp == null && vertex == null) {
                for (Object s : list) {
                    Sphere sphere = (Sphere) s;
                    double startY = sphere.getCoordinates().getY();
                    for (int j = (int) startY; j <= startY + sphere.getHeight(); j++) {
                        if (newRec.contains(sphere.getCoordinates().getX(), j)) {
                            addSphere = false;
                        }
                    }
                    double startX = sphere.getCoordinates().getX();
                    for (int i = (int) startX; i <= startX + sphere.getWidth(); i++) {
                        if (newRec.contains(i, sphere.getCoordinates().getY())) {
                            addSphere = false;
                        }
                    }
                }
                if (addSphere) {
                    AddSphereLogAction addSphereLogAction = new AddSphereLogAction(p);
                    history.execute(addSphereLogAction);
                }
            }

            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();

        }

        if (SwingUtilities.isRightMouseButton(e)){
            if (vertex == null && sp != null){
                helper.showSideMenu(e.getLocationOnScreen(), contextMenu);
                PickedState<Sphere> spheres = vv.getPickedSphereState();
                spheres.clear();
                spheres.pick(sp, true);
            }
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();

        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mousePressed(MouseEvent e) {
        helper.hideMenu(contextMenu);
        SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(e.getX(), e.getY());
        Vertex vert = (Vertex) pickSupport.getVertex(vv.getGraphLayout(), e.getX(), e.getY());
        down = e.getPoint();

        if (SwingUtilities.isLeftMouseButton(e)) {
            PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();
            if (sp != null && vert == null) {
                spherePickedCoord = sp.getCoordinates();
                points = new LinkedHashMap<>();
                for (Vertex v: sp.getVertices()){
                    points.put(v.getId(), v.getCoordinates());
                }
                if (!pickedSphereState.isPicked(sp)) {
                    pickedSphereState.clear();
                    pickedSphereState.pick(sp, true);
                }
                vv.repaint();
                Syndrom.getInstance().getVv2().repaint();

            } else {
                pickedSphereState.clear();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseReleased(MouseEvent e) {
         if (SwingUtilities.isLeftMouseButton(e) && spherePickedCoord != null) {
             SyndromVisualisationViewer vv = (SyndromVisualisationViewer) e.getSource();
             PickedState<Sphere> spherePickedState = vv.getPickedSphereState();
             Set<Sphere> spheres = spherePickedState.getPicked();
             SyndromGraph graph = (SyndromGraph) vv.getGraphLayout().getGraph();
             List<Sphere> allSpheres = graph.getSpheres();
             SphereShapeTransformer sphereShapeTransformer = new SphereShapeTransformer();

             for (Sphere s : spheres) {
                 Shape sShape = sphereShapeTransformer.transform(s);
                 boolean move = true;
                 for (Sphere sphere: allSpheres){
                     if (!s.equals(sphere)){
                         Shape sphereShape = sphereShapeTransformer.transform(sphere);
                         if (sphereShape.intersects(sShape.getBounds())){
                             move = false;
                         }
                     }
                 }
                 if (!move){
                     s.setCoordinates(spherePickedCoord);
                     for (Vertex v: s.getVertices()){
                         Point2D vp = points.get(v.getId());
                         v.setCoordinates(vp);
                         vv.getGraphLayout().setLocation(v, vp);
                     }
                 }
             }
             spherePickedCoord = null;
             points = null;
             vv.repaint();

             Syndrom.getInstance().getVv2().repaint();
         }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }

    @Override
    @SuppressWarnings("unchecked")
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            SyndromVisualisationViewer<Vertex, Edge> vv = (SyndromVisualisationViewer<Vertex, Edge>) e.getSource();

            if (spherePickedCoord != null) {
                Point p = e.getPoint();
                Point2D graphPoint = vv.getRenderContext().getMultiLayerTransformer().transform(p);
                Point2D graphDown = vv.getRenderContext().getMultiLayerTransformer().transform(down);
                double dx = graphPoint.getX() - graphDown.getX();
                double dy = graphPoint.getY() - graphDown.getY();
                PickedState<Sphere> spherePickedState = vv.getPickedSphereState();

                for (Sphere s : spherePickedState.getPicked()) {
                    if (downFirst == null) {
                        downFirst = s.getCoordinates();
                    }

                    s.setCoordinates(new Point2D.Double(s.getCoordinates().getX() + dx, s.getCoordinates().getY() +
                            dy));
                    for (Vertex vertex : s.getVertices()) {
                        Point2D point = new Point2D.Double(vertex.getCoordinates().getX() + dx, vertex.getCoordinates()
                                .getY() + dy);
                        vertex.setCoordinates(point);
                        Layout layout = vv.getGraphLayout();
                        layout.setLocation(vertex, point);
                    }
                }
                down = p;
                vv.repaint();
                Syndrom.getInstance().getVv2().repaint();

            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }
}
