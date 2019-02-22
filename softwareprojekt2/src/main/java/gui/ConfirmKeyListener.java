package gui;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ConfirmKeyListener implements EventHandler<KeyEvent> {
    private Controller c;
    private TextField amountSymptomTextField;
    private CheckBox analysisSuccessor;
    private CheckBox analysisPredecessor;


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
                    //CALL SUCCESSOR METHOD
                }
                if(analysisSuccessor.isSelected()){
                    //CALL PREDECESSOR METHOD
                }
            }
            c.getRoot().requestFocus();
        }
    }

}
