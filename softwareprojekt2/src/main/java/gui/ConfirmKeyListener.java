package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import graph.algorithmen.AnalyseTypeSeveral;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ConfirmKeyListener implements EventHandler<KeyEvent> {
    private final Controller c;
    private final TextField amountSymptomTextField;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;


    public ConfirmKeyListener(Controller pC, TextField pTextField){
        c = pC;
        amountSymptomTextField = pTextField;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            if(!amountSymptomTextField.getText().isEmpty()){
                if(analysisPredecessor.isSelected()){
                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                    analysisGraphAction.action();
                }
                if(analysisSuccessor.isSelected()){
                    AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                    analysisGraphAction.action();
                }
            }else{
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();
            }
            c.getRoot().requestFocus();
        }
    }

}
