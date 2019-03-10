package actions.other;

import actions.GraphAction;

/**
 * Changes the language to either german or english.
 * @author Clement Phung, Nina Unterberg
 */
public class ChangeGraphLanguageAction extends GraphAction {

    @Override
    public void action() {
        syndrom.getVv().repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        //There is no undo operation for this action.
    }
}
