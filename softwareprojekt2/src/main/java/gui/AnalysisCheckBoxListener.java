package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
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

    public AnalysisCheckBoxListener(CheckBox pCheckBox, Controller pC) {
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
        if (newValue) {
            if (isAtLeastOnePicked()) {
                analysisOptions.setSelected(false);
                analysisPathCheckBox.setSelected(false);
                amountSymptomTextField.setDisable(false);

                if (!amountSymptomTextField.getText().isEmpty()) {
                    if (analysisPredecessor.isSelected() && analysisSuccessor.isSelected()) {
                        System.out.println("dew");
                        ResetVvAction resetAction = new ResetVvAction();
                        resetAction.action();

                        AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphAction.action();
                    } else if (checkBox.getId().equals("analysisPredecessor")) {
                        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphNeighborsAction.action();
                    } else if (checkBox.getId().equals("analysisSuccessor")) {
                        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphNeighborsAction.action();
                    }
                }
            } else {
                disableAllCheckBoxes();
                checkBox.setSelected(oldValue);
            }
        } else {
            if (!analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()) {
                amountSymptomTextField.setDisable(true);
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();
            } else if (!analysisSuccessor.isSelected() && analysisPredecessor.isSelected()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            } else if (analysisSuccessor.isSelected() && !analysisPredecessor.isSelected()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            } else {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphNeighborsAction.action();
            }
        }
    }

    private void disableAllCheckBoxes() {
        analysisSuccessor.setSelected(false);
        analysisPredecessor.setSelected(false);

        analysisOptions.setSelected(false);
        analysisPathCheckBox.setSelected(false);
        amountSymptomTextField.setDisable(false);
    }

    /**
     * Sets the vertex for the neighbor algorithms. It checks if at least one vertex is picked.
     */
    public boolean isAtLeastOnePicked() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() < 1) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("ANALYSIS_ALERT", true, true);
            return false;
        }
        return true;
    }
}