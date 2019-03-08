package graph.visualization.control;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.*;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * a class with helper functions
 */
public class HelperFunctions {
    /**
     * the logger for the class
     */
    private static Logger logger = Logger.getLogger(HelperFunctions.class);
    /**
     * the values instance
     */
    private final Values values;
    /**
     * the LoadLanguage class, to get the strings in the right language
     */
    private LoadLanguage lang = LoadLanguage.getInstance();

    public HelperFunctions() {
        values = Values.getInstance();
    }

    /**
     * hides the passed ContextMenu
     *
     * @param contextMenu the ContextMenu to hide
     */
    void hideMenu(ContextMenu contextMenu) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                contextMenu.hide();
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    /**
     * shows the ContextMenu on a passed point
     *
     * @param point       the point
     * @param contextMenu the context menu to show
     */
    void showSideMenu(Point2D point, ContextMenu contextMenu) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                Node node = values.getCanvas();
                                contextMenu.show(node, point.getX(), point.getY());

                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        logger.debug("started context menu");
        service.start();
    }

    /**
     * shows a alert with a passed text
     *
     * @param string          the alert text
     * @param isAlert         defined whether the text is an alert or info
     * @param withPlaceHolder defined whether the passed text is a normal string or if its a placeholder an has to be
     *                        loaded from a properties file
     */
    public void setActionText(String string, boolean isAlert, boolean withPlaceHolder) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                String alert = (withPlaceHolder) ? lang.loadLanguagesKey(string) : string;
                                values.getCurrentActionText().setText(alert);
                                Text text = values.getCurrentActionText();
                                if (isAlert) {
                                    text.setFill(javafx.scene.paint.Color.WHITE);
                                    text.setFont(values.getActionTextAlert());
                                    HBox hBox = values.getHBox();
                                    String style = "-fx-background-color: rgba(160, 12, 12, 1);";
                                    hBox.setStyle(style);
                                    final Animation animation = new ErrorMessagesTransition(hBox);
                                    animation.play();
                                }
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();
    }

    /**
     * picks a passed object if its an instance of edge/ sphere/ vertex
     *
     * @param object the object to pick
     */
    public void pickElement(Object object) {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedVertexState = vv.getPickedVertexState();
        PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();
        PickedState<Edge> pickedEdgeState = vv.getPickedEdgeState();
        pickedVertexState.clear();
        pickedSphereState.clear();
        pickedEdgeState.clear();
        if (object instanceof Vertex) {
            Vertex vertex = (Vertex) object;
            pickedVertexState.pick(vertex, true);
        } else if (object instanceof Sphere) {
            Sphere sphere = (Sphere) object;
            pickedSphereState.pick(sphere, true);
        } else if (object instanceof Edge) {
            Edge edge = (Edge) object;
            pickedEdgeState.pick(edge, true);
        } else {
            throw new IllegalArgumentException();
        }
        vv.repaint();
    }

    /**
     * creates the context menu to a passed object if its an instance of vertex/ sphere/ edge
     *
     * @param object the object to show the context menu too
     * @return the context menu
     */
    public ContextMenu openContextMenu(Object object) {
        ContextMenu contextMenu = null;
        if (object instanceof Sphere) {
            Sphere sp = (Sphere) object;
            contextMenu = new SphereContextMenu(sp).getContextMenu();
        } else if (object instanceof Vertex) {
            Vertex vertex = (Vertex) object;
            contextMenu = new VertexContextMenu(vertex).getContextMenu();
        } else if (object instanceof Edge) {
            Edge edge = (Edge) object;
            contextMenu = new EdgeContextMenu(edge).getContextMenu();
        }
        return contextMenu;
    }

    /**
     * creates the title dialog
     *
     * @param old the old annotation of the object (sphere/ vertex)
     * @return the title dialog
     */
    public Dialog<EnumMap<Language, String>> getDialog(Map<String, String> old) {
        String a;
        switch (values.getGuiLanguage()) {
            case GERMAN:
                a = "de";
                break;
            case ENGLISH:
                a = "en";
                break;
            default:
                a = "de";
        }

        ResourceBundle bundle = ResourceBundle.getBundle("UIResources", new Locale(a));
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/titlePane.fxml"));
        fxmlLoader.setResources(bundle);
        Stage titleStage = new Stage();
        titleStage.setResizable(false);
        titleStage.setTitle(lang.loadLanguagesKey("CONTEXT_DIALOG_TITLE"));
        titleStage.centerOnScreen();

        DialogPane dialogPane = null;
        try {
            dialogPane = fxmlLoader.load();
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        TitlesDialogPaneController c = fxmlLoader.getController();
        c.setPrompt(old);

        Dialog<EnumMap<Language, String>> d = new Dialog<>();
        d.initOwner(values.getMainStage());
        d.setDialogPane(dialogPane);
        d.setResultConverter(button -> {
            if (button == TitlesDialogPaneController.SAVE_TYPE) {
                EnumMap<Language, String> map = new EnumMap<>(Language.class);
                String germanTitle = c.getGerman().getText();
                germanTitle = germanTitle.replace(";", "");
                if (germanTitle.length() > 100) {
                    germanTitle = germanTitle.substring(0, 99);
                }
                map.put(Language.GERMAN, germanTitle);

                String englishTitle = c.getEnglish().getText();
                englishTitle = englishTitle.replace(";", "");
                if (englishTitle.length() > 100) {
                    englishTitle = englishTitle.substring(0, 99);
                }
                map.put(Language.ENGLISH, englishTitle);
                return map;
            } else {
                return null;
            }
        });
        return d;
    }

    /**
     * returns the font to a passed string
     *
     * @param f the string to get the font to
     * @return the font
     */
    public Font returnFont(String f) {
        java.awt.Font newFont;
        switch (f) {
            case "AveriaSansLibre":
                newFont = values.getAveriaSansLibr();
                break;
            case "Kalam":
                newFont = values.getKalam();
                break;
            case "Mali":
                newFont = values.getMali();
                break;
            case "Roboto":
                newFont = values.getRoboto();
                break;
            default:
                newFont = values.getRobotoSlab();
                break;
        }
        return newFont;
    }

    /**
     * sets the passed position to values
     *
     * @param posX the x position
     * @param posY the y position
     */
    void setMouseLocation(String posX, String posY) {
        Platform.runLater(() -> {
            values.getPositionMouseX().setText(posX);
            values.getPositionMouseY().setText(posY);
        });
    }

    /**
     * Checks whether the vertices in the sphere are locked.
     *
     * @param sp the sphere to check its vertices if one of the is locked
     * @return true: locked vertices, false: no locked vertices
     */
    public boolean verticesLocked(Sphere sp) {
        LinkedList<Vertex> vertices = sp.getVertices();
        for (Vertex vertex : vertices) {
            if (vertex.isLockedPosition() || vertex.isLockedAnnotation() || vertex.isLockedStyle()) {
                return true;
            }
        }
        return false;
    }
}