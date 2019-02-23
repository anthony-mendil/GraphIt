package gui;

import actions.analyse.AnalysisGraphAction;
import graph.algorithmen.AnalyseTypeSingle;
import gui.properties.LoadLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class AnalysisOptionsCheckBoxListener implements ChangeListener<Boolean> {
    private MenuButton menuButton;

    public AnalysisOptionsCheckBoxListener( MenuButton pMenuButton){
        menuButton = pMenuButton;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue) {
            //Activate Current Selected Analysis Option
            if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("analysisShortestPath"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.SHORTESTPATH);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("analysisAllPaths"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.ALLPATHS);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterChainOfEdges"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.EDGE_CHAINS);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterConvergentBranches"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CONVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterDivergentBranches"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.DIVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterBranches"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.BRANCHES);
                analysisGraphAction.action();
            }else if((menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterCycles")))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CYCLEN);
                analysisGraphAction.action();
            }
        }else{
            //Deactivate Current Selected Analysis Option
            //Or just clean the whole graph
        }
    }
}
