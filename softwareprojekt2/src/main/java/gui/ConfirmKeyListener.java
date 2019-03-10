package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseType;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * When the enter-key was pressed it confirms the input of the textfield.
 * The input will be evaluated and calls the action or drops the input and resets the textfield.
 */
public class ConfirmKeyListener implements EventHandler<KeyEvent> {
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;
    /**
     * The textfield for the analysis successor/predecessor option.
     */
    private final TextField amountSymptomTextField;
    /**
     * The checkbox for the analysis successor option.
     */
    private final CheckBox analysisSuccessor;
    /**
     * The checkbox for the analysis predecessor option.
     */
    private final CheckBox analysisPredecessor;

    /**
     * Creates a new ConfirmKeyListener and sets this Controller, amountSymptomTextField and analysisSuccessor/Predecessor.
     * @param pC The Controller that is needed to handle the actions.
     * @param pTextField The TextField that gets set to this amountSymptomTextField.
     */
    ConfirmKeyListener(Controller pC, TextField pTextField) {
        c = pC;
        amountSymptomTextField = pTextField;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    /**
     * The Listener only reacts to the enter-key.
     * It evaluates the input of the textfield and act accordingly if the input is valid or not.
     * If it is valid, it will call the actions accordingly to the selected checkboxes.
     * If it isn't, it will resets the textfield to the last valid input.
     *
     * @param event The key event that gets fired after a key was pressed while using the textfield.
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            checkBoxBehavior();
            c.getRoot().requestFocus();
        }
    }

    /**
     * Initializes the behavior of the checkboxes for key events.
     */
    private void checkBoxBehavior() {
        if (!amountSymptomTextField.getText().isEmpty() && c.isAtLeastOnePicked()) {
            if (analysisPredecessor.isSelected() && analysisSuccessor.isSelected()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            } else {
                if (analysisPredecessor.isSelected()) {
                    ResetVvAction resetAction = new ResetVvAction();
                    resetAction.action();

                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                    analysisGraphAction.action();
                }
                if (analysisSuccessor.isSelected()) {
                    ResetVvAction resetAction = new ResetVvAction();
                    resetAction.action();

                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                    analysisGraphAction.action();
                }
            }
        } else {
            c.disableAllCheckBoxes();
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
        }
    }

}
