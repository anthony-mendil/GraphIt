package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

import javax.swing.*;

public class ZoomMenuItemHandler implements EventHandler<ActionEvent> {
    private Controller c;
    public ZoomMenuItemHandler(Controller c){
        this.c = c;
    }
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