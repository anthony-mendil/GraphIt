package gui;

import actions.analyse.*;
import actions.deactivate.ResetVvAction;
import gui.properties.LoadLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

/**
 * Listens to the changes, which checkbox is selected and handles that only the successor and predecessor option
 * can be selected at the same time. Also calls the actions accordingly to the selected checkboxes and their associated
 * menu buttons.
 *
 * @author Jacky Philipp Mach
 */
public class AnalysisOptionsCheckBoxListener implements ChangeListener<Boolean> {
    /**
     * A constant to reference a specific string.
     */
    private static final String SHORTEST_PATH = "analysisShortestPath";
    /**
     * A constant to reference a specific string.
     */
    private static final String ALL_PATHS = "analysisAllPaths";
    /**
     * A constant to reference a specific string.
     */
    private static final String CHAIN_OF_EDGES = "filterChainOfEdges";
    /**
     * A constant to reference a specific string.
     */
    private static final String CONVERGENT_BRANCHES = "filterConvergentBranches";
    /**
     * A constant to reference a specific string.
     */
    private static final String DIVERGENT_BRANCHES = "filterDivergentBranches";
    /**
     * A constant to reference a specific string.
     */
    private static final String BRANCHES = "filterBranches";
    /**
     * A constant to reference a specific string.
     */
    private static final String CYCLES = "filterCycles";

    /**
     * The checkbox that the listener is assigned to.
     */
    private final CheckBox checkBox;
    /**
     * The associated menu button to the checkbox.
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
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;

    /**
     * Creates a checkbox-Listener for the analysis methods.
     * @param pC            The unique controller.
     * @param pCheckBox     The specific checkbox.
     * @param pMenuButton   The menu button.
     */
    AnalysisOptionsCheckBoxListener(Controller pC, CheckBox pCheckBox, MenuButton pMenuButton) {
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
            String s = menuButton.getText();
            if (currentLanguage.loadLanguagesKey(SHORTEST_PATH).equals(s)) {
                shortestPath(oldValue);
            } else if (currentLanguage.loadLanguagesKey(ALL_PATHS).equals(s)) {
                allPaths(oldValue);
            } else if (currentLanguage.loadLanguagesKey(CHAIN_OF_EDGES).equals(s)) {
                disableOtherCheckBoxes();
                AnalysisGraphEdgeChainsAction analysisGraphAction = new AnalysisGraphEdgeChainsAction();
                analysisGraphAction.action();
            } else if (currentLanguage.loadLanguagesKey(CONVERGENT_BRANCHES).equals(s)) {
                disableOtherCheckBoxes();
                AnalysisGraphConvergentBranchesAction analysisGraphAction = new AnalysisGraphConvergentBranchesAction();
                analysisGraphAction.action();
            } else if (currentLanguage.loadLanguagesKey(DIVERGENT_BRANCHES).equals(s)) {
                disableOtherCheckBoxes();
                AnalysisGraphDivergentBranchesAction analysisGraphAction = new AnalysisGraphDivergentBranchesAction();
                analysisGraphAction.action();
            } else if (currentLanguage.loadLanguagesKey(BRANCHES).equals(s)) {
                disableOtherCheckBoxes();
                AnalysisGraphBranchesAction analysisGraphAction = new AnalysisGraphBranchesAction();
                analysisGraphAction.action();
            } else if (currentLanguage.loadLanguagesKey(CYCLES).equals(s)) {
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
     * Calculates the shortest path between two selected symptoms, highlights them and change the behaviour of the
     * checkboxes accordingly to it.
     *
     * @param oldValue The old value of the checkbox.
     */
    private void shortestPath(Boolean oldValue) {
        if (c.calculateEndpoints()) {
            disableOtherCheckBoxes();
            AnalysisGraphShortestPathAction analysisGraphAction = new AnalysisGraphShortestPathAction();
            analysisGraphAction.action();
        } else {
            disableOtherCheckBoxes();
            checkBox.setSelected(oldValue);
        }
    }

    /**
     * Calculates all paths between two selected symptoms, highlights them and change the behaviour of the checkboxes
     * accordingly to it.
     *
     * @param oldValue The old value of the checkbox.
     */
    private void allPaths(Boolean oldValue) {
        if (c.calculateEndpoints()) {
            disableOtherCheckBoxes();
            AnalysisGraphAllPathsAction analysisGraphAction = new AnalysisGraphAllPathsAction();
            analysisGraphAction.action();
        } else {
            disableOtherCheckBoxes();
            checkBox.setSelected(oldValue);
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

}
