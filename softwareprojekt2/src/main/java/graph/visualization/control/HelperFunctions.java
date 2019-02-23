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
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

public class HelperFunctions {
    private static Logger logger = Logger.getLogger(HelperFunctions.class);
    private final Values values;
    private LoadLanguage lang = LoadLanguage.getInstance();

    public HelperFunctions() {
        values = Values.getInstance();
    }

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
        service.start();
    }

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


        DialogPane dialogPane = null;
        try {
            dialogPane = fxmlLoader.load();
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }

        TitlesDialogPaneController c = fxmlLoader.getController();
        c.setPrompt(old);

        Dialog<EnumMap<Language, String>> d = new Dialog<>();

        d.setDialogPane(dialogPane);
        d.setResultConverter(button -> {
            if (button == TitlesDialogPaneController.SAVE_TYPE) {
                EnumMap<Language, String> map = new EnumMap<>(Language.class);

                String germanTitle = c.getGerman().getText();
                if (germanTitle.length() > 100) {
                    germanTitle = germanTitle.substring(0, 99);
                }
                map.put(Language.GERMAN, germanTitle);

                String englishTitle = c.getEnglish().getText();
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
}