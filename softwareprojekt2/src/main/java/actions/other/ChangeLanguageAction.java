package actions.other;


import actions.Action;

/*
    Aktionsleiste -> Optionen -> Sprache ändern -> Englisch
    Aktionsleiste -> Optionen -> Sprache ändern -> Deutsch
    Aktionsleiste -> Optionen -> Sprache ändern -> <andere Sprachen>
 */

/**
 * Changes the language of the whole GUI of the program.
 */
public class ChangeLanguageAction extends Action {
    /**
     * Constructor in case the user changes the language.
     *
     * @param pLanguage the new Language
     */
    public ChangeLanguageAction(String pLanguage) {

    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
