package gui;

import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;

/**
 * Listens to the selected menuitem in the language menu and changes the gui language accordingly to the selected
 * menuitem.
 */
public class LanguageListener implements ChangeListener<Boolean> {
    /**
     * The menuitem that the listener is assigned to.
     */
    private final CheckMenuItem checkMenuItem;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller controller;
    /**
     * The language object for changing the descriptions of the gui elements accordingly to the current language.
     */
    private final LoadLanguage loadLanguage;
    /**
     * The values object that contains all default attributes and current attributes of spheres, symptoms and edges.
     */
    private final Values values;
    /**
     * The menuitem that changes the gui language to english.
     */
    private final CheckMenuItem languageEnglish;
    /**
     * The menuitem that changes the gui language to german.
     */
    private final CheckMenuItem languageGerman;

    /**
     * Calls the change gui language action with the given argument.
     *
     * @param language The desired language.
     */
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

    /**
     * Gets called when a menuitem in the language menu was selected.
     * When called, it evaluates which menuitem was selected and changes the language accordingly to it.
     *
     * @param observable Is the menuitem selected or not.
     * @param oldValue   Was is it selected before or not.
     * @param newValue   Is it selected now or not.
     */
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