package graph.visualization.control;

import gui.Values;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.geom.Point2D;
import java.util.concurrent.CountDownLatch;

class HelperFunctions {
    private final Values values;
    HelperFunctions(){
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
                                Node node = (Node) values.getNamespace().get("canvas");
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
                                Text text = (Text) values.getNamespace().get("currentActionText");
                                HBox hBox = (HBox) values.getNamespace().get("textBox");
                                Color color = Color.WHITE;
                                Font font = values.getActionTextAlert();
                                if (isAlert) {
                                    String style = "-fx-background-color: rgba(160, 12, 12, 1);";
                                    hBox.setStyle(style);
                                    final Animation animation = new ErrorMessagesTransition(hBox);
                                    animation.play();
                                }
                                text.setFill(color);
                                text.setText(string);
                                text.setFont(font);
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
}
