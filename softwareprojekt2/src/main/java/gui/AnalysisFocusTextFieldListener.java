package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AnalysisFocusTextFieldListener implements ChangeListener<Boolean> {
    private Controller c;
    private TextField textField;
    private CheckBox analysisSuccessor;
    private CheckBox analysisPredecessor;

    public AnalysisFocusTextFieldListener(TextField pTextField, Controller pC){
        textField = pTextField;
        c = pC;
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPredecessor = c.getAnalysisPredecessor();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(!newValue){
            if(analysisPredecessor.isSelected()){
                System.out.println("Predecessor is selected.");
            }
            if(analysisSuccessor.isSelected()){
                System.out.println("Successor is selected.");
            }
        }
    }
}
