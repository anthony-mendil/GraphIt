package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AnalysisFocusTextFieldListener implements ChangeListener<Boolean> {
    private final TextField textField;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;

    AnalysisFocusTextFieldListener(TextField pTextField, Controller pC) {
        final Controller c;
        textField = pTextField;
        c = pC;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (!newValue) {
            if (!textField.getText().isEmpty()) {
                if (analysisSuccessor.isSelected() && analysisPredecessor.isSelected()) {
                    ResetVvAction resetAction = new ResetVvAction();
                    resetAction.action();

                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(textField.getText()));
                    analysisGraphAction.action();
                } else {
                    if (analysisPredecessor.isSelected()) {
                        AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, Integer.parseInt(textField.getText()));
                        analysisGraphAction.action();
                    }
                    if (analysisSuccessor.isSelected()) {
                        AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(textField.getText()));
                        analysisGraphAction.action();
                    }
                }
            } else {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();
            }
        }
    }
}
