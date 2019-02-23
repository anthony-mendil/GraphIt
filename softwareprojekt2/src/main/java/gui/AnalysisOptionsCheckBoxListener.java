package gui;

import actions.analyse.*;
import actions.deactivate.ResetVvAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.algorithmen.AnalyseTypeSingle;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.LoadLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class AnalysisOptionsCheckBoxListener implements ChangeListener<Boolean> {
    private final Controller c;
    private final MenuButton menuButton;
    private final CheckBox checkBox;
    private final CheckBox analysisSuccessor;
    private final CheckBox analysisPredecessor;
    private final CheckBox analysisPathCheckBox;
    private final CheckBox analysisOptions;
    private final LoadLanguage currentLanguage;

    public AnalysisOptionsCheckBoxListener(Controller pC, CheckBox pCheckBox, MenuButton pMenuButton){
        c = pC;
        menuButton = pMenuButton;
        checkBox = pCheckBox;
        analysisPredecessor = c.getAnalysisPredecessor();
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPathCheckBox = c.getAnalysisPathCheckBox();
        analysisOptions = c.getAnalysisOptions();
        currentLanguage = LoadLanguage.getInstance();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            //Activate Current Selected Analysis Option
            if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("analysisShortestPath"))){
                if(calculateEndpoints()){
                    disableOtherCheckBoxes();
                    AnalysisGraphShortestPathAction analysisGraphAction = new AnalysisGraphShortestPathAction();
                    analysisGraphAction.action();
                }else{
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("analysisAllPaths"))){
                if(calculateEndpoints()){
                    disableOtherCheckBoxes();
                    AnalysisGraphAllPathsAction analysisGraphAction = new AnalysisGraphAllPathsAction();
                    analysisGraphAction.action();
                }else{
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterChainOfEdges"))){
                disableOtherCheckBoxes();
                AnalysisGraphEdgeChainsAction analysisGraphAction = new AnalysisGraphEdgeChainsAction();
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterConvergentBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphConvergentBranchesAction analysisGraphAction = new AnalysisGraphConvergentBranchesAction();
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterDivergentBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphDivergentBranchesAction analysisGraphAction = new AnalysisGraphDivergentBranchesAction();
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphBranchesAction analysisGraphAction = new AnalysisGraphBranchesAction();
                analysisGraphAction.action();
            }else if((menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterCycles")))){
                disableOtherCheckBoxes();
                AnalysisGraphCyclesAction analysisGraphAction = new AnalysisGraphCyclesAction();
                analysisGraphAction.action();
            }
        }else{
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
        }
    }

    private void disableOtherCheckBoxes(){
        analysisSuccessor.setSelected(false);
        analysisPredecessor.setSelected(false);
        if(checkBox.getId().equals("analysisOptions")){
            analysisPathCheckBox.setSelected(false);
        }else if(checkBox.getId().equals("analysisPathCheckBox")){
            analysisOptions.setSelected(false);
        }
    }

    /**
     * Calculates the Endpoints in the graph. It checks, whether two vertices are selected.
     */
    private boolean calculateEndpoints() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() != 2) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst zwei Knoten markieren.", true);
            return false;
        }
        return true;
    }
}
