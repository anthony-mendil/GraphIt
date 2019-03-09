package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Listens to changes of the analysis checkboxes.
 * If a checkbox is selected, the selected action of the associated menu button will be called.
 * The AnalysisCheckBoxListener functions only for the successor and predecessor analysis checkbox.
 */
public class AnalysisCheckBoxListener implements ChangeListener<Boolean> {
    /**
     * The checkbox that the listener is assigned to.
     */
    private final CheckBox checkBox;
    /**
     * The textfield for the successor/predecessor option.
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
     * The checkbox for the analysis path options.
     */
    private final CheckBox analysisPathCheckBox;
    /**
     * The checkbox for the general analysis options.
     */
    private final CheckBox analysisOptions;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;

    /**
     * TODO
     * @param pCheckBox
     * @param pC
     */
    AnalysisCheckBoxListener(CheckBox pCheckBox, Controller pC) {
        checkBox = pCheckBox;
        c = pC;
        amountSymptomTextField = c.getAmountSymptomTextField();
        analysisPredecessor = c.getAnalysisPredecessor();
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPathCheckBox = c.getAnalysisPathCheckBox();
        analysisOptions = c.getAnalysisOptions();
    }

    /**
     * Gets called after the checkbox was selected or deselected.
     * When called, it checks which checkbox was selected and if the other is also selected.
     * Accordingly which checkbox is selected, the associated action will be called.
     *
     * @param observable Is the checkbox selected.
     * @param oldValue   Was it selected before or not.
     * @param newValue   Is it selected now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            if (c.isAtLeastOnePicked()) {
                selectedAnalysisCheckBox();
            } else {
                c.disableAllCheckBoxes();
                checkBox.setSelected(oldValue);
            }
        } else {
            deselectedAnalysisCheckBox();
        }
    }

    /**
     * Initializes the behavior between the successor and predecessor checkbox after one of them got deselected.
     */
    private void deselectedAnalysisCheckBox() {
        if (!analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()) {
            amountSymptomTextField.setDisable(true);
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
        } else if (!analysisSuccessor.isSelected() && analysisPredecessor.isSelected() && !amountSymptomTextField.getText().isEmpty() && c.isAtLeastOnePicked()) {
            analysisOption(AnalyseType.NEIGHBOUR_PREDECESSOR);
        } else if (analysisSuccessor.isSelected() && !analysisPredecessor.isSelected() && !amountSymptomTextField.getText().isEmpty() && c.isAtLeastOnePicked()) {
            analysisOption(AnalyseType.NEIGHBOUR_SUCCESSOR);
        } else if (!amountSymptomTextField.getText().isEmpty() && c.isAtLeastOnePicked()) {
            analysisOption(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR);
        }
    }

    /**
     * Initializes the behavior between the successor and predecessor checkbox after one of them got selected.
     */
    private void selectedAnalysisCheckBox() {
        analysisOptions.setSelected(false);
        analysisPathCheckBox.setSelected(false);
        amountSymptomTextField.setDisable(false);
        if (analysisPredecessor.isSelected() && analysisSuccessor.isSelected() && c.isAtLeastOnePicked() && !amountSymptomTextField.getText().isEmpty()) {
            analysisOption(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR);
        } else if (checkBox.getId().equals("analysisPredecessor") && !amountSymptomTextField.getText().isEmpty()) {
            AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
            analysisGraphNeighborsAction.action();
        } else if (checkBox.getId().equals("analysisSuccessor") && !amountSymptomTextField.getText().isEmpty()) {
            AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
            analysisGraphNeighborsAction.action();
        }
    }

    /**
     * Filters the graph accordingly to the given argument as option.
     *
     * @param type The desired filter option.
     */
    private void analysisOption(AnalyseType type) {
        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();

        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(type, Integer.parseInt(amountSymptomTextField.getText()));
        analysisGraphNeighborsAction.action();
    }
}