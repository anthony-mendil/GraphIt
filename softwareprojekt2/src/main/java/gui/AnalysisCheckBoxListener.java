package gui;

import actions.analyse.AnalysisGraphAction;
import actions.deactivate.ResetVvAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AnalysisCheckBoxListener implements ChangeListener<Boolean> {
    private final Controller c;
    private final CheckBox checkBox;
    private final TextField amountSymptomTextField;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;
    private final CheckBox analysisPathCheckBox;
    private final CheckBox analysisOptions;
    private boolean error = false;

    public AnalysisCheckBoxListener(CheckBox pCheckBox, Controller pC){
        checkBox = pCheckBox;
        c = pC;
        amountSymptomTextField = c.getAmountSymptomTextField();
        analysisPredecessor = c.getAnalysisPredecessor();
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPathCheckBox = c.getAnalysisPathCheckBox();
        analysisOptions = c.getAnalysisOptions();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue){
            if(isAtLeastOnePicked()){
                analysisOptions.setSelected(false);
                analysisPathCheckBox.setSelected(false);
                amountSymptomTextField.setDisable(false);

                if(!amountSymptomTextField.getText().isEmpty()){
                    if(checkBox.getId().equals("analysisPredecessor")){
                        AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphAction.action();
                    }else if(checkBox.getId().equals("analysisSuccessor")){
                        AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphAction.action();
                    }
                }
            }else{
                checkBox.setSelected(oldValue);
            }
        }else{
            if(!analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()){
                amountSymptomTextField.setDisable(true);
                    ResetVvAction resetAction = new ResetVvAction();
                    resetAction.action();
            }else if(!analysisSuccessor.isSelected() && analysisPredecessor.isSelected()){
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            }else if(analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()){
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            }
        }
    }

    /**
     * Sets the vertex for the neighbor algorithms. It checks if at least one vertex is picked.
     */
    public boolean isAtLeastOnePicked(){
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() < 1) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst mindestens einen Knoten markieren.", true);
            return false;
        }
        return true;
    }
}