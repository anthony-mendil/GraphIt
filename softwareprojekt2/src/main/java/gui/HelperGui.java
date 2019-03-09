package gui;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * TODO
 */
class HelperGui {
    /**
     * TODO
     */
    private HelperGui() {
        throw new IllegalStateException("utility class");
    }

    /**
     * TODO
     * @param imageString
     * @param menuItem
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
