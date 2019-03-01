package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * The event handler that replace the images visible in the associated menubutton to the image of the latest selected
 * menuitem.
 */
public class MenuItemHandler implements EventHandler<ActionEvent> {

    /**
     * The menubutton that is associated to the menuitem.
     */
    private final MenuButton menuButton;

    public MenuItemHandler(MenuButton pMenuButton) {
        menuButton = pMenuButton;
    }

    /**
     * Gets called when an associated menuitem from the menubutton is selected.
     * When it gets called, the function changes the image of the associated menubutton to the image of the selected
     * menuitem.
     *
     * @param evt The event that gets called after the menuitem was selected.
     */
    @Override
    public void handle(ActionEvent evt) {
        MenuItem mnItm = (MenuItem) evt.getSource();
        ImageView newImage = (ImageView) mnItm.getGraphic();
        ImageView currentImage = (ImageView) menuButton.getGraphic();
        currentImage.setImage(newImage.getImage());
    }
}