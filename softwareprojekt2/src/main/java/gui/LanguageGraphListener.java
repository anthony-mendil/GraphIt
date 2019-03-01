package gui;

import actions.other.ChangeGraphLanguageAction;
import gui.properties.Language;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;

/**
 * Listens to the selected menuitem in the language menu and changes the graph language accordingly to the selected
 * menuitem.
 */
public class LanguageGraphListener implements ChangeListener<Boolean> {
    /**
     * The menuitem that the listener is assigned to.
     */
    private CheckMenuItem checkMenuItem;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private Controller c;

    /**
     * Calls the change graph language action with the given argument.
     * @param language The desired language.
     */
    private void changeLanguage(Language language) {
        c.getValues().setGraphLanguage(language);
        ChangeGraphLanguageAction changeGraphLanguageAction = new ChangeGraphLanguageAction();
        changeGraphLanguageAction.action();
    }

    public LanguageGraphListener(Controller pC, CheckMenuItem checkMenuItem) {
        c = pC;
        this.checkMenuItem = checkMenuItem;
    }

    /**
     * Change the graph language accordingly to the selected menuitem and deselect the other menuitem.
     *
     * @param observable Is the menuitem selected or not.
     * @param oldValue   Was is it selected before or not.
     * @param newValue   Is it selected now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (checkMenuItem.getId().equals("languageGraphGerman") && newValue) {
            c.getLanguageGraphEnglish().setSelected(false);
            changeLanguage(Language.GERMAN);
        } else if (checkMenuItem.getId().equals("languageGraphEnglish") && newValue) {
            c.getLanguageGraphGerman().setSelected(false);
            changeLanguage(Language.ENGLISH);
        }
    }
}
