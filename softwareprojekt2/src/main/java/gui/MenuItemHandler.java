package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * The event handler that replace the images visible in the menubutton to the latest selected image.
 */
public class MenuItemHandler implements EventHandler<ActionEvent> {

    private final MenuButton menuButton;

    public MenuItemHandler(MenuButton pMenuButton) {
        menuButton = pMenuButton;
    }

    @Override
    public void handle(ActionEvent evt) {
        MenuItem mnItm = (MenuItem) evt.getSource();
        ImageView newImage = (ImageView) mnItm.getGraphic();
        ImageView currentImage = (ImageView) menuButton.getGraphic();
        currentImage.setImage(newImage.getImage());
    }
}