package gui;

import actions.analyse.*;
import actions.deactivate.ResetVvAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
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

/**
 * Listens to the changes, which checkbox is selected and handles that only the successor and predecessor option
 * can be selected at the same time. Also calls the actions accordingly to the selected checkboxes and their associated
 * menubuttons.
 */
public class AnalysisOptionsCheckBoxListener implements ChangeListener<Boolean> {
    /**
     * The checkbox that the listener is assigned to.
     */
    private final CheckBox checkBox;
    /**
     * The associated menubutton to the checkbox.
     */
    private final MenuButton menuButton;
    /**
     * The checkbox for the analysis successor option.
     */
    private final CheckBox analysisSuccessor;
    /**
     * The checkbox for the analysis predecessor option.
     */
    private final CheckBox analysisPredecessor;
    /**
     * The checkbox for the analysis path options.
     */
    private final CheckBox analysisPathCheckBox;
    /**
     * The checkbox for the general analysis options.
     */
    private final CheckBox analysisOptions;
    /**
     * The language object for changing the descriptions of the gui elements accordingly to the current language.
     */
    private final LoadLanguage currentLanguage;

    AnalysisOptionsCheckBoxListener(Controller pC, CheckBox pCheckBox, MenuButton pMenuButton) {
        final Controller c;
        c = pC;
        menuButton = pMenuButton;
        checkBox = pCheckBox;
        analysisPredecessor = c.getAnalysisPredecessor();
        analysisSuccessor = c.getAnalysisSuccessor();
        analysisPathCheckBox = c.getAnalysisPathCheckBox();
        analysisOptions = c.getAnalysisOptions();
        currentLanguage = LoadLanguage.getInstance();
    }

    /**
     * Gets called after the checkbox was selected or deselected.
     * When called, it determines what checkbox it is and calls the associated actions accordingly.
     * It also deselects every other analysis option.
     *
     * @param observable Is the checkbox selected or not.
     * @param oldValue   Was it selected before or not.
     * @param newValue   Is it selected now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            //Activate Current Selected Analysis Option
            if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("analysisShortestPath"))) {
                if (calculateEndpoints()) {
                    disableOtherCheckBoxes();
                    AnalysisGraphShortestPathAction analysisGraphAction = new AnalysisGraphShortestPathAction();
                    analysisGraphAction.action();
                } else {
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            } else if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("analysisAllPaths"))) {
                if (calculateEndpoints()) {
                    disableOtherCheckBoxes();
                    AnalysisGraphAllPathsAction analysisGraphAction = new AnalysisGraphAllPathsAction();
                    analysisGraphAction.action();
                } else {
                    disableOtherCheckBoxes();
                    checkBox.setSelected(oldValue);
                }
            } else if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterChainOfEdges"))) {
                disableOtherCheckBoxes();
                AnalysisGraphEdgeChainsAction analysisGraphAction = new AnalysisGraphEdgeChainsAction();
                analysisGraphAction.action();
            } else if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterConvergentBranches"))) {
                disableOtherCheckBoxes();
                AnalysisGraphConvergentBranchesAction analysisGraphAction = new AnalysisGraphConvergentBranchesAction();
                analysisGraphAction.action();
            } else if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterDivergentBranches"))) {
                disableOtherCheckBoxes();
                AnalysisGraphDivergentBranchesAction analysisGraphAction = new AnalysisGraphDivergentBranchesAction();
                analysisGraphAction.action();
            } else if (menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterBranches"))) {
                disableOtherCheckBoxes();
                AnalysisGraphBranchesAction analysisGraphAction = new AnalysisGraphBranchesAction();
                analysisGraphAction.action();
            } else if ((menuButton.getText().equals(currentLanguage.loadLanguagesKey("filterCycles")))) {
                disableOtherCheckBoxes();
                AnalysisGraphCyclesAction analysisGraphAction = new AnalysisGraphCyclesAction();
                analysisGraphAction.action();
            }
        } else {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
        }
    }

    /**
     * Disables all other checkboxes, because only successor and predecessor can be selected at the same time in the
     * analysis-mode.
     */
    private void disableOtherCheckBoxes() {
        analysisSuccessor.setSelected(false);
        analysisPredecessor.setSelected(false);
        if (checkBox.getId().equals("analysisOptions")) {
            analysisPathCheckBox.setSelected(false);
        } else if (checkBox.getId().equals("analysisPathCheckBox")) {
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
            helperFunctions.setActionText("ANALYSIS_OPTION_ALERT", true, true);
            return false;
        }
        return true;
    }
}
