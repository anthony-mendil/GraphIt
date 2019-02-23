package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseTypeSeveral;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AnalysisFocusTextFieldListener implements ChangeListener<Boolean> {
    private final Controller c;
    private final TextField textField;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;

    public AnalysisFocusTextFieldListener(TextField pTextField, Controller pC){
        textField = pTextField;
        c = pC;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(!newValue){
            if(!textField.getText().isEmpty()){
                if(analysisPredecessor.isSelected()){
                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(textField.getText()));
                    analysisGraphAction.action();
                }
                if(analysisSuccessor.isSelected()){
                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(textField.getText()));
                    analysisGraphAction.action();
                }
            }else{
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();
            }
        }
    }
}
