package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AnalysisCheckBoxListener implements ChangeListener<Boolean> {
    private Controller c;
    private CheckBox checkBox;
    private TextField amountSymptomTextField;
    private CheckBox analysisSuccessor;
    private CheckBox analysisPredecessor;

    public AnalysisCheckBoxListener(CheckBox pCheckBox, Controller pC){
        checkBox = pCheckBox;
        c = pC;
        amountSymptomTextField = c.getAmountSymptomTextField();
        analysisPredecessor = c.getAnalysisPredecessor();
        analysisSuccessor = c.getAnalysisSuccessor();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue){
            if(amountSymptomTextField.isDisabled()){
                amountSymptomTextField.setDisable(false);
            }
            if(!amountSymptomTextField.getText().isEmpty()){
                if(checkBox.getId().equals("analysisPredecessor")){
                    //CALL PREDECESSOR METHOD
                    System.out.println("Predecessor " + amountSymptomTextField.getText());
                }else if(checkBox.getId().equals("analysisSuccessor")){
                    //CALL SUCCESSOR METHOD
                    System.out.println("Sucessor " + amountSymptomTextField.getText());
                }
            }
        }else{
            if(!analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()){
                amountSymptomTextField.setDisable(true);
            }
        }
    }
}