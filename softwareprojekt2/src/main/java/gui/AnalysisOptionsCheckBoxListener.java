package gui;

import actions.analyse.AnalysisGraphAction;
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

import java.util.Iterator;

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
                    AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.SHORTESTPATH);
                    analysisGraphAction.action();
                }else{
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("analysisAllPaths"))){
                if(calculateEndpoints()){
                    disableOtherCheckBoxes();
                    AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.ALLPATHS);
                    analysisGraphAction.action();
                }else{
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterChainOfEdges"))){
                disableOtherCheckBoxes();
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.EDGE_CHAINS);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterConvergentBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CONVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterDivergentBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.DIVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterBranches"))){
                disableOtherCheckBoxes();
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.BRANCHES);
                analysisGraphAction.action();
            }else if((menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterCycles")))){
                disableOtherCheckBoxes();
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CYCLEN);
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
            helperFunctions.setActionText("ANALYSIS_OPTION_ALERT", true);
            return false;
        }
        return true;
    }
}
