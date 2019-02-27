package gui;

import actions.analyse.AnalysisGraphNeighborsAction;
import actions.deactivate.ResetVvAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.algorithmen.AnalyseType;
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
    private final CheckBox checkBox;
    private final TextField amountSymptomTextField;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;
    private final CheckBox analysisPathCheckBox;
    private final CheckBox analysisOptions;

    AnalysisCheckBoxListener(CheckBox pCheckBox, Controller pC) {
        final Controller c;
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
                        ResetVvAction resetAction = new ResetVvAction();
                        resetAction.action();

                        AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphAction.action();
                    } else if (checkBox.getId().equals("analysisPredecessor")) {
                        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                        analysisGraphNeighborsAction.action();
                    } else if (checkBox.getId().equals("analysisSuccessor")) {
                        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
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
            } else if (!analysisSuccessor.isSelected() && analysisPredecessor.isSelected() && !amountSymptomTextField.getText().isEmpty()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            } else if (analysisSuccessor.isSelected() && !analysisPredecessor.isSelected() && !amountSymptomTextField.getText().isEmpty()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphAction.action();
            } else if (!amountSymptomTextField.getText().isEmpty()) {
                ResetVvAction resetAction = new ResetVvAction();
                resetAction.action();

                AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, Integer.parseInt(amountSymptomTextField.getText()));
                analysisGraphNeighborsAction.action();
            }
        }
    }

    private void disableAllCheckBoxes() {
        analysisSuccessor.setSelected(false);
        analysisPredecessor.setSelected(false);
        analysisOptions.setSelected(false);
        analysisPathCheckBox.setSelected(false);
        if (analysisPredecessor.isSelected() || analysisSuccessor.isSelected()) {
            amountSymptomTextField.setDisable(false);
        }
    }

    /**
     * Sets the vertex for the neighbor algorithms. It checks if at least one vertex is picked.
     */
    private boolean isAtLeastOnePicked() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().isEmpty()) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst mindestens einen Knoten markieren.", true, false);
            return false;
        }
        return true;
    }
}