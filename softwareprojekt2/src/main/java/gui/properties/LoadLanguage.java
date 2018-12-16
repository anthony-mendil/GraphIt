package gui.properties;

import gui.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;

public class LoadLanguage {

    private Controller control;
    private Menu file;
    private Menu options;
    private Menu help;

    private Tooltip undo;
    private Tooltip redo;
    private Tooltip creatormode;
    private Tooltip analysismode;
    private Tooltip interpretermode;
    private Tooltip layout;
    private Tooltip fadeout;
    private Tooltip highlight;
    private Tooltip template;

    private Text sphereText;
    private Tooltip addSphere;
    private Tooltip deleteSphere;
    private Tooltip spherecolour;
    private Tooltip spherefont;
    private Tooltip spherefontsize;

    private Text symptomText;
    private Tooltip addSymptom;
    private Tooltip deleteSymptom;
    private Tooltip symptombackgroundcolour;
    private Tooltip symptombordercolour;
    private Tooltip symptomform;
    private Tooltip symptomrectangle;
    private Tooltip symptomcircle;
    private Tooltip symptomellipse;
    private Tooltip symptomfont;
    private Tooltip symptomfontsize;

    private Text edgetext;
    private Tooltip deleteEdge;
    private Tooltip edgeColour;
    private Tooltip edgeStroketype;
    private Tooltip edgeStrokedashed;
    private Tooltip edgeStrokedashedweight;
    private Tooltip edgeStrokedotted;
    private Tooltip edgeStrokedottedweight;
    private Tooltip edgeStrokebasic;
    private Tooltip edgeStrokebasicweight;
    private Tooltip edgeArrowType;
    private Tooltip edgeReinforced;
    private Tooltip edgeExtenuating;
    private Tooltip edgeNeutral;

    private Text templateWindowText;
    private Text templateMaxSphereText;
    private Text templateMaxSymptomText;
    private Text templateMaxSymptonInSphereText;
    private Text templateMaxEdgeText;
    private Button templateSaveRule;
    private Button templateDeleteRule;

    private Text analysisGraphInfoText;
    private Text analysisScopeText;
    private Tooltip analysisScopeTooltip;
    private Text analysisNetworkingIndexText;
    private Tooltip analysisNetworkingIndexTooltip;
    private Text analysisStructureIndexText;
    private Tooltip analysisStructureIndexTooltip;

    private Text analysisSymptom;
    private Tooltip analysisPredessorTooltip;
    private Tooltip analysisSuccessorTooltip;
    private Text symptomAmountText;
    private Tooltip analysisSymptomAmountTooltip;

    private Text analysisEdge;
    private Tooltip analysisIncomingTooltip;
    private Tooltip analysisOriginatingTooltip;
    private Text edgeAmountText;
    private Tooltip edgeAmountTooltip;
    private Tooltip analysisArrowTypeTooltip;
    private Tooltip analysisReinforced;
    private Tooltip analysisExtenuating;
    private Tooltip analysisNeutral;

    private Tooltip treeViewEdgeTypeTooltip;
    private Tooltip treeViewReinforced;
    private Tooltip treeViewExtenuating;
    private Tooltip treeViewNeutral;
    private Tooltip treeViewRegularExpressionTooltip;

    private Tooltip filterBoxTooltip;
    private Tooltip filterChainOfEdgesTooltip;
    private Tooltip filterConvergentBranchesTooltip;
    private Tooltip filterDivergentBranchesTooltip;
    private Tooltip filterBranchesTooltip;
    private Tooltip filterCyclesTooltip;

    public void loadLanguages(String lang){
        throw new UnsupportedOperationException();
    }
}
