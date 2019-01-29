package gui.properties;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import lombok.Data;

/**
 * This class changes every description of the gui elements to the desired language.
 */
@Data
public class LoadLanguage {

    /**
     * The controller that is needed to translate gui elements used by the controller class.
     */
    private Controller control ;

    /**
     * The menu on the taskbar for file.
     */
    private Menu file ;

    /**
     * The menu on the taskbar for options.
     */
    private Menu options;

    /**
     * The menu on the taskbar for help.
     */
    private Menu help;

    /**
     * The tooltip for the undo-button.
     */
    private Tooltip undo;

    /**
     * The tooltip for the redo-button.
     */
    private Tooltip redo;

    /**
     * The tooltip for the creator-mode-button.
     */
    private Tooltip creatormode;

    /**
     * The tooltip for the analysis-mode-button.
     */
    private Tooltip analysismode;

    /**
     * The tooltip for the interpreter-mode-button.
     */
    private Tooltip interpretermode;

    /**
     * The tooltip for the layout-button.
     */
    private Tooltip layout;

    /**
     * The tooltip for the fadeout-button.
     */
    private Tooltip fadeout;

    /**
     * The tooltip for the highlight-button.
     */
    private Tooltip highlight;

    /**
     * The tooltip for the template-button.
     */
    private Tooltip template;

    /**
     * The heading for the sphere actions.
     */
    private Text sphereText;

    /**
     * The tooltip for the add-sphere-button.
     */
    private Tooltip addSphere;

    /**
     * The tooltip for the delete-sphere-button.
     */
    private Tooltip deleteSphere;

    /**
     * The tooltip for the sphere-colour-button.
     */
    private Tooltip spherecolour;

    /**
     * The tooltip for the sphere-font-menubutton.
     */
    private Tooltip spherefont;

    /**
     * The tooltip for the sphere-font-size-menubutton.
     */
    private Tooltip spherefontsize;

    /**
     * The heading for symptom actions.
     */
    private Text symptomText;

    /**
     * The tooltip for the add-symptom-button.
     */
    private Tooltip addSymptom;

    /**
     * The tooltip for the delete-symptom-button.
     */
    private Tooltip deleteSymptom;

    /**
     * The tooltip for the change-symptom-background-colour-button.
     */
    private Tooltip symptombackgroundcolour;

    /**
     * The tooltip for the change-symptom-border-colour-button.
     */
    private Tooltip symptombordercolour;

    /**
     * The tooltip for the change-symptom-form-menubutton.
     */
    private Tooltip symptomform;

    /**
     * The tooltip for the change-symptom-form-rectangle-menuitem.
     */
    private Tooltip symptomrectangle;

    /**
     * The tooltip for the change-symptom-form-circle-menuitem.
     */
    private Tooltip symptomcircle;

    /**
     * The tooltip for the change-symptom-form-ellipse-menuitem.
     */
    private Tooltip symptomellipse;

    /**
     * The tooltip for the change-symptom-font-menubutton.
     */
    private Tooltip symptomfont;

    /**
     * The tooltip for the change-symptom-fontsize-menubutton.
     */
    private Tooltip symptomfontsize;

    /**
     * The heading for the edge actions.
     */
    private Text edgetext;

    /**
     * The tooltip for the delete-edge-button.
     */
    private Tooltip deleteEdge;

    /**
     * The tooltip for the change-edge-colour-button.
     */
    private Tooltip edgeColour;

    /**
     * The tooltip for the change-edge-stroketype-menubutton.
     */
    private Tooltip edgeStroketype;

    /**
     * The tooltip for the change-stroketype-dashed-menuitem.
     */
    private Tooltip edgeStrokedashed;

    /**
     * The tooltip for the change-stroketype-dashedweight-menuitem.
     */
    private Tooltip edgeStrokedashedweight;

    /**
     * The tooltip for the change-stroketype-dotted-menuitem.
     */
    private Tooltip edgeStrokedotted;

    /**
     * The tooltip for the change-stroketype-dottedweight-menuitem.
     */
    private Tooltip edgeStrokedottedweight;

    /**
     * The tooltip for the change-stroketype-basic-menuitem.
     */
    private Tooltip edgeStrokebasic;

    /**
     * The tooltip for the change-stroketype-basicweight-menuitem.
     */
    private Tooltip edgeStrokebasicweight;

    /**
     * The tooltip for the change-edge-arrowtype-menubutton.
     */
    private Tooltip edgeArrowType;

    /**
     * The tooltip for the change-arrowtype-reinforced-menuitem.
     */
    private Tooltip edgeReinforced;

    /**
     * The tooltip for the change-arrowtype-extenuating-menuitem.
     */
    private Tooltip edgeExtenuating;

    /**
     * The tooltip for the change-arrowtype-neutral-menuitem.
     */
    private Tooltip edgeNeutral;

    /**
     * The heading for the template window.
     */
    private Text templateWindowText;

    /**
     * The description for the template rule "max spheres".
     */
    private Text templateMaxSphereText;

    /**
     * The description for the template rule "max symptoms".
     */
    private Text templateMaxSymptomText;

    /**
     * The description for the template rule "max symptoms in a sphere".
     */
    private Text templateMaxSymptonInSphereText;

    /**
     * The description for the template rule "max edges".
     */
    private Text templateMaxEdgeText;

    /**
     * The button for saving the template rules.
     */
    private Button templateSaveRule;

    /**
     * The button for deleting the template rules.
     */
    private Button templateDeleteRule;

    /**
     * The heading for the graph-information-area in analysis mode.
     */
    private Text analysisGraphInfoText;

    /**
     * The description for the scope-information in analysis mode.
     */
    private Text analysisScopeText;

    /**
     * The tooltip for the scope-information in analysis mode.
     */
    private Tooltip analysisScopeTooltip;

    /**
     * The description for the networkingindex-information in analysis mode.
     */
    private Text analysisNetworkingIndexText;

    /**
     * The tooltip for the networkingindex-information in analysis mode.
     */
    private Tooltip analysisNetworkingIndexTooltip;

    /**
     * The description for the structureindex-information in analysis mode.
     */
    private Text analysisStructureIndexText;

    /**
     * The tooltip for the structureindex-information in analysis mode.
     */
    private Tooltip analysisStructureIndexTooltip;

    /**
     * The heading of the symptom-filter-actions in analysis mode.
     */
    private Text analysisSymptom;

    /**
     * The tooltip for the predecessor-checkbox.
     */
    private Tooltip analysisPredecessorTooltip;

    /**
     * The tooltip for the successor-checkbox.
     */
    private Tooltip analysisSuccessorTooltip;

    /**
     * The description for the symptom-amount-option.
     */
    private Text symptomAmountText;

    /**
     * The tooltip for the symptom-amount-option.
     */
    private Tooltip analysisSymptomAmountTooltip;

    /**
     * The heading of the edge-filter-actions in analysis mode.
     */
    private Text analysisEdge;

    /**
     * The tooltip for the edge-incoming-checkbox.
     */
    private Tooltip analysisIncomingTooltip;

    /**
     * The tooltip for the edge-originating-checkbox.
     */
    private Tooltip analysisOriginatingTooltip;

    /**
     * The description for the edge-amount-option.
     */
    private Text edgeAmountText;

    /**
     * The tooltip for the edge-amount-option.
     */
    private Tooltip edgeAmountTooltip;

    /**
     * The tooltip for the filter-edge-arrowtype-menubutton.
     */
    private Tooltip analysisArrowTypeTooltip;

    /**
     * The tooltip for the filter-arrowtype-reinforced-menuitem in analysis mode.
     */
    private Tooltip analysisReinforced;

    /**
     * The tooltip for the filter-arrowtype-extenuating-menuitem in analysis mode.
     */
    private Tooltip analysisExtenuating;

    /**
     * The tooltip for the filter-arrowtype-neutral-menuitem in analysis mode.
     */
    private Tooltip analysisNeutral;

    /**
     * The tooltip for the filter-edgetype-checkbox in the treeview.
     */
    private Tooltip treeViewEdgeTypeTooltip;

    /**
     * The tooltip for the filter-reinforced-edge-menuitem in the treeview.
     */
    private Tooltip treeViewReinforced;

    /**
     * The tooltip for the filter-extenuating-edge-menuitem in the treeview.
     */
    private Tooltip treeViewExtenuating;

    /**
     * The tooltip for the filter-neutral-edge-menuitem in the treeview.
     */
    private Tooltip treeViewNeutral;

    /**
     * The tooltip for the filter-regular-expressions-checkbox in the treeview.
     */
    private Tooltip treeViewRegularExpressionTooltip;

    /**
     * The tooltip for the filter-actions-checkbox.
     */
    private Tooltip filterBoxTooltip;

    /**
     * The tooltip for the filter-chainofedges-menuitem.
     */
    private Tooltip filterChainOfEdgesTooltip;

    /**
     * The tooltip for the filter-convergentbranches-menuitem.
     */
    private Tooltip filterConvergentBranchesTooltip;

    /**
     * The tooltip for the filter-divergentbranches-menuitem.
     */
    private Tooltip filterDivergentBranchesTooltip;

    /**
     * The tooltip for the filter-branches-menuitem.
     */
    private Tooltip filterBranchesTooltip;

    /**
     * The tooltip for the filter-cycles-menuitem.
     */
    private Tooltip filterCyclesTooltip;

    /**
     * Calling this method with the desired language will translate all gui descriptions
     * to that language.
     *
     * @param lang The desired language.
     */
    public void loadLanguages(String lang){
         throw new UnsupportedOperationException();
    }
}
