package gui;

import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HelperGui {
    private HelperGui() {
        throw new IllegalStateException("utility class");
    }

    public static void setImage(String imageString, MenuItem menuItem) {
        Image image = new Image(imageString);
        ImageView iconRemove = new ImageView();
        iconRemove.setImage(image);
        iconRemove.setFitWidth(15);
        iconRemove.setFitHeight(15);
        menuItem.setGraphic(iconRemove);
    }


}
