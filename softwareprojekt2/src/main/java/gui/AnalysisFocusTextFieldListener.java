package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Listens to the focus change of the analysis predecessor/successor textfield.
 * If the textfield loses the focus but a number was typed in, the number will be set and the actions will be called
 * accordingly to the selected checkboxes.
 *
 * @author Jacky Philipp Mach
 */
public class AnalysisFocusTextFieldListener implements ChangeListener<Boolean> {
    /**
     * The textfield that this listener is assigned to.
     */
    private final TextField textField;
    /**
     * The checkbox for the analysis successor option.
     */
    private final CheckBox analysisSuccessor;
    /**
     * The checkbox for the analysis predecessor option.
     */
    private final CheckBox analysisPredecessor;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;

    AnalysisFocusTextFieldListener(TextField pTextField, Controller pC) {
        textField = pTextField;
        c = pC;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    /**
     * Gets called when the textfield gets or loses the focus.
     * When the textfield loses the focus and a number was typed in, the actions will be called accordingly to the
     * associated checkboxes.
     * If the textfield is empty the highlighting will be reset.
     *
     * @param observable Is the textfield is focused or not.
     * @param oldValue   Was it focused before or not.
     * @param newValue   Is it focused now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            if ((!textField.getText().isEmpty()) && c.isAtLeastOnePicked()) {
                determinesAction();
            } else {
                c.disableAllCheckBoxes();
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();
            }
        }
    }

    /**
     * Calls the right action accordingly if the successor and predecessor is selected.
     */
    private void determinesAction() {
        if (analysisSuccessor.isSelected() && analysisPredecessor.isSelected()) {
            analysisOption(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR);
        } else {
            if (analysisPredecessor.isSelected()) {
                analysisOption(AnalyseType.NEIGHBOUR_PREDECESSOR);
            }
            if (analysisSuccessor.isSelected()) {
                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(textField.getText()));
                analysisGraphAction.action();
            }
        }
    }

    /**
     * Calls the associated action to the analysis type.
     *
     * @param type The type of analysis option.
     */
    private void analysisOption(AnalyseType type) {
        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();

        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(type, Integer.parseInt(textField.getText()));
        analysisGraphNeighborsAction.action();
    }
}
