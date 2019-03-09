package gui;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Sets the icons on the gui.
 */
class HelperGui {
    /**
     * Constructor, which shouldn't be called.
     */
    private HelperGui() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Sets the icons and the image.
     * @param imageString The name of the image.
     * @param menuItem    The menuItem.
     */
    static void setImage(String imageString, MenuItem menuItem) {
        Image image = new Image(imageString);
        ImageView iconRemove = new ImageView();
        iconRemove.setImage(image);
        iconRemove.setFitWidth(15);
        iconRemove.setFitHeight(15);
        menuItem.setGraphic(iconRemove);
    }
}
