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
                System.out.println("shortestpath");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.SHORTESTPATH);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("analysisAllPaths"))){
                System.out.println("allpaths");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.ALLPATHS);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterChainOfEdges"))){
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.EDGE_CHAINS);
                System.out.println("chainofedges");
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterConvergentBranches"))){
                System.out.println("convergentbranches");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CONVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterDivergentBranches"))){
                System.out.println("divergentbranches");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.DIVERGENT_BRANCHES);
                analysisGraphAction.action();
            }else if(menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterBranches"))){
                System.out.println("branches");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.BRANCHES);
                analysisGraphAction.action();
            }else if((menuButton.getText().equals(LoadLanguage.getInstance().getResource().getString("filterCycles")))){
                System.out.println("cycles");
                AnalysisGraphAction analysisGraphAction = new AnalysisGraphAction(AnalyseTypeSingle.CYCLEN);
                analysisGraphAction.action();
            }
        }else{
            //Deactivate Current Selected Analysis Option
            //Or just clean the whole graph
        }
    }
}
