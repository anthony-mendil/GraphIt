package gui;

import actions.other.ChangeGraphLanguageAction;
import gui.properties.Language;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;

public class LanguageGraphListener implements ChangeListener<Boolean> {
    private CheckMenuItem checkMenuItem;
    private Controller c;
    private void changeLanguage(Language language) {
        c.getValues().setGraphLanguage(language);
        ChangeGraphLanguageAction changeGraphLanguageAction = new ChangeGraphLanguageAction();
        changeGraphLanguageAction.action();
    }

    public LanguageGraphListener(Controller pC,CheckMenuItem checkMenuItem) {
        c = pC;
        this.checkMenuItem = checkMenuItem;
    }

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
