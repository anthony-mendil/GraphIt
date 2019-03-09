package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * Listens to the selected menuitem in the menubutton and changes the text of the menubutton accordingly.
 */
public class AnalysisItemHandler implements EventHandler<ActionEvent> {
    /**
     * The menubutton that the handler is assigned to.
     */
    private final MenuButton menuButton;

    /**
     * TODO
     * @param pMenuButton
     */
    AnalysisItemHandler(MenuButton pMenuButton) {
        menuButton = pMenuButton;
    }

    /**
     * Changes the text of the menubutton to the text of the selected menuitem.
     *
     * @param evt the event that gets fired after a menuitem was selected.
     */
    @Override
    public void handle(ActionEvent evt) {
        MenuItem mnItm = (MenuItem) evt.getSource();
        String newText = mnItm.getText();
        menuButton.setText(newText);
    }
}
