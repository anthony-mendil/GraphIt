package graph.visualization.control;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import gui.*;
import gui.properties.Language;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class HelperFunctions {
    private final Values values;
    public HelperFunctions(){
        values = Values.getInstance();
    }
    void hideMenu(ContextMenu contextMenu){
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
                                contextMenu.show(node, point.getX() , point.getY()  );

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

    public void setActionText(String string, boolean isAlert) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                values.getCurrentActionText().setText(string);
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

    public void pickElement(Object object){
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedVertexState = vv.getPickedVertexState();
        PickedState<Sphere> pickedSphereState = vv.getPickedSphereState();
        PickedState<Edge> pickedEdgeState = vv.getPickedEdgeState();
        pickedVertexState.clear();
        pickedSphereState.clear();
        pickedEdgeState.clear();
        if (object instanceof Vertex){
            Vertex vertex = (Vertex) object;
            pickedVertexState.pick(vertex, true);
        } else if (object instanceof Sphere){
            Sphere sphere = (Sphere) object;
            pickedSphereState.pick(sphere, true);
        } else if (object instanceof Edge){
            Edge edge = (Edge) object;
            pickedEdgeState.pick(edge, true);
        } else {
            throw new IllegalArgumentException();
        }
        vv.repaint();
    }

    public ContextMenu openContextMenu(Object object, double x, double y){
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

    public Dialog<Map<Language, String>> getDialog(){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/titlePane.fxml"));
            Stage titleStage = new Stage();
            titleStage.setResizable(false);
            titleStage.setTitle("Title");
            titleStage.getIcons().add(new Image(
                    getClass().getResourceAsStream("/GraphItLogo.png")));

            DialogPane dialogPane = null;
            try {
                dialogPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Dialog<Map<Language, String>> d = new Dialog<>();

            dialogPane.getButtonTypes().addAll(
                    ButtonType.OK
            );
            d.setDialogPane(dialogPane);
            d.setResultConverter(button -> button == ButtonType.OK ? new HashMap<>() : null);
        return  d;
    }
}
