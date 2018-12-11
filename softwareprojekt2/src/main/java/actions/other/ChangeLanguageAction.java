package actions.other;


import actions.Action;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    Aktionsleiste -> Optionen -> Sprache ändern -> Englisch
    Aktionsleiste -> Optionen -> Sprache ändern -> Deutsch
    Aktionsleiste -> Optionen -> Sprache ändern -> <andere Sprachen>
 */

/**
 * Changes the language of the whole GUI of the program.
 */
public class ChangeLanguageAction extends Action{
    /**
     * Constructor in case the user changes the language.
     * @param pLanguage
     * The new Language.
     */
    public ChangeLanguageAction(String pLanguage){

    }
     @Override
    public void action() {
         throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void redo() {
        throw new NotImplementedException();
    }
}
