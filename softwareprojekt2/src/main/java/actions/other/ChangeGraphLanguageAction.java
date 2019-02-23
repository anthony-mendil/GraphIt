package actions.other;

import actions.GraphAction;

/**
 * Changes the mode either to "Ersteller"/"Bearbeiter"/ "Auswerter".
 */
public class ChangeGraphLanguageAction extends GraphAction {

    @Override
    public void action() {
        syndrom.getVv().repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        //
    }
}
