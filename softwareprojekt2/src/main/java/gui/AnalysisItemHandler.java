package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class AnalysisItemHandler implements EventHandler<ActionEvent> {

    private final MenuButton menuButton;

    public AnalysisItemHandler(MenuButton pMenuButton) {
        menuButton = pMenuButton;
    }

    @Override
    public void handle(ActionEvent evt) {
        MenuItem mnItm = (MenuItem) evt.getSource();
        String newText = mnItm.getText();
        menuButton.setText(newText);
    }
}
