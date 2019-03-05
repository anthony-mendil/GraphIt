package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import javax.swing.*;

/**
 * Sets the slider value accordingly to the selected menuitem value and resizes the zoom window.
 */
public class ZoomMenuItemHandler implements EventHandler<ActionEvent> {
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private Controller c;

    ZoomMenuItemHandler(Controller c) {
        this.c = c;
    }

    /**
     * Sets the slider value accordingly to the selected menuitem and resizes the zoom window.
     *
     * @param evt the event that gets called after a menuitem was selected.
     */
    @Override
    public void handle(ActionEvent evt) {
        MenuItem mnItmn = (MenuItem) evt.getSource();
        String percent = mnItmn.getText();
        percent = percent.replaceAll("%", "");
        int per = Integer.parseInt(percent);

        c.getZoomSlider().setValue(per);
        SwingUtilities.invokeLater(() -> c.getSyndrom().scale(per));

    }
}