package gui;

import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;

public class LanguageListener implements ChangeListener<Boolean> {
    private final CheckMenuItem checkMenuItem;
    private final Controller controller;
    private final LoadLanguage loadLanguage;
    private final Values values;
    private final CheckMenuItem languageEnglish;
    private final CheckMenuItem languageGerman;

    private void changeLanguage(Language language) {
        loadLanguage.changeLanguage(language);
        loadLanguage.changeStringsLanguage(controller);
        values.setGuiLanguage(language);
        controller.treeViewUpdate();
        controller.loadTables();
        controller.sortFilterLogs();
    }

    public LanguageListener(CheckMenuItem checkMenuItem, Controller controller) {
        this.checkMenuItem = checkMenuItem;
        this.controller = controller;
        loadLanguage = controller.getLoadLanguage();
        values = controller.getValues();
        languageEnglish = controller.getLanguageEnglish();
        languageGerman = controller.getLanguageGerman();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (checkMenuItem.getId().equals("languageGerman") && newValue) {
            languageEnglish.setSelected(false);
            changeLanguage(Language.GERMAN);
        } else if (checkMenuItem.getId().equals("languageEnglish") && newValue) {
            languageGerman.setSelected(false);
            changeLanguage(Language.ENGLISH);
        }
    }
}