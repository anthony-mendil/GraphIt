package gui.properties;

import gui.Controller;
import lombok.Data;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class changes every description of the gui elements to the desired language.
 */
@Data
public class LoadLanguage {
    private ResourceBundle resource = ResourceBundle.getBundle("UIResources", new Locale("de"));
    private Locale de = new Locale("de");
    private Locale en = new Locale("en");
    private Language currentResource = Language.GERMAN;

    /**
     * Calling this method with the desired language will translate all gui descriptions
     * to that language.
     */
    private static LoadLanguage instance = null;

    public static LoadLanguage getInstance(){
        if (instance == null){
            instance = new LoadLanguage();
        }
        return instance;
    }

    public void changeLanguage(Language language){
        Locale lang;
        if (language == Language.GERMAN){
            lang = de;
        } else {
            lang = en;
        }
        currentResource = language;
        resource = ResourceBundle.getBundle("UIResources", lang);
    }

    public void changeStringsLanguage(Controller c){
        String templateName = "templateName";
        String templateStyle = "templateStyle";
        String templatePosition = "templatePosition";
        String analysisSuccessor = "analysisSuccessor";
        String sectionSymptom = "sectionSymptom";
        String sectionEdge = "sectionEdge";
        c.getFileMenu().setText(loadLanguagesKey("file"));
        c.getNewFile().setText(loadLanguagesKey("newFile"));
        c.getOpenFile().setText(loadLanguagesKey("openFile"));
        c.getImportGXL().setText(loadLanguagesKey("gxlImport"));
        c.getSaveLocation().setText(loadLanguagesKey("saveAs"));
        c.getExportPDF().setText(loadLanguagesKey("exportAs"));
        c.getExportGXLWithTemplate().setText(loadLanguagesKey("template"));
        c.getExportLogs().setText(loadLanguagesKey("logs"));
        c.getPrint().setText(loadLanguagesKey("print"));
        c.getOptions().setText(loadLanguagesKey("options"));
        c.getLanguages().setText(loadLanguagesKey("languageGui"));
        c.getLanguageGerman().setText(loadLanguagesKey("german"));
        c.getLanguageEnglish().setText(loadLanguagesKey("english"));
        c.getHelp().setText(loadLanguagesKey("help"));
        c.getDocumentation().setText(loadLanguagesKey("documentation"));
        c.getAbout().setText(loadLanguagesKey("aboutUs"));
        c.getCreateButton().getTooltip().setText(loadLanguagesKey("toolbarCreatorMode"));
        c.getAnalysisButton().getTooltip().setText(loadLanguagesKey("toolbarAnalysisMode"));
        c.getEditButton().getTooltip().setText(loadLanguagesKey("toolbarInterpreterMode"));
        c.getUndoButton().getTooltip().setText(loadLanguagesKey("toolbarUndo"));
        c.getRedoButton().getTooltip().setText(loadLanguagesKey("toolbarRedo"));
        c.getHighlight().getTooltip().setText(loadLanguagesKey("toolbarHighlight"));
        c.getHighlightElements().getTooltip().setText(loadLanguagesKey("toolbarHighlight"));
        c.getDehighlightElements().getTooltip().setText(loadLanguagesKey("toolbarNotHighlightElements"));
        c.getFadeout().getTooltip().setText(loadLanguagesKey("toolbarFadeout"));
        c.getFadeoutElements().getTooltip().setText(loadLanguagesKey("toolbarFadeoutElement"));
        c.getDeleteFadeoutElements().getTooltip().setText(loadLanguagesKey("toolbarFadeoutDeleteElement"));
        c.getSelection().setText(loadLanguagesKey("selectorHand"));
        c.getHandSelector().getTooltip().setText(loadLanguagesKey("selectorHandTooltip"));
        c.getSelectionSphere().setText(loadLanguagesKey("sectionSphere"));
        c.getAddSphere().getTooltip().setText(loadLanguagesKey("addSphereTooltip"));
        c.getDeleteSphere().getTooltip().setText(loadLanguagesKey("deleteSphereTooltip"));
        c.getSphereEnlarge().getTooltip().setText(loadLanguagesKey("sizeChangeSphereTooltip"));
        c.getSphereShrink().getTooltip().setText(loadLanguagesKey("sizeMinusChangeSphereTooltip"));
        c.getSphereBackgroundColour().getTooltip().setText(loadLanguagesKey("sphereColourTooltip"));
        c.getFontSphereComboBox().setPromptText(loadLanguagesKey("font"));
        c.getFontSphereComboBox().hide();
        c.getFontSphereComboBox().getTooltip().setText(loadLanguagesKey("sphereFontTooltip"));
        c.getSizeSphereComboBox().setPromptText(loadLanguagesKey("fontSize"));
        c.getSizeSphereComboBox().hide();
        c.getSizeSphereComboBox().getTooltip().setText(loadLanguagesKey("sphereFontSizeTooltip"));
        c.getSphereAutoLayout().getTooltip().setText(loadLanguagesKey("toolbarLayout"));
        c.getSelectionSymptom().setText(loadLanguagesKey(sectionSymptom));
        c.getAddVertex().getTooltip().setText(loadLanguagesKey("addSymptomTooltip"));
        c.getDeleteVertex().getTooltip().setText(loadLanguagesKey("deleteSymptom"));
        c.getVertexEnlarge().getTooltip().setText(loadLanguagesKey("enlargeSymptom"));
        c.getVertexShrink().getTooltip().setText(loadLanguagesKey("shrinkSymptom"));
        c.getSymptomBackground().getTooltip().setText(loadLanguagesKey("symptomBackgroundColourTooltip"));
        c.getSymptomBorder().getTooltip().setText(loadLanguagesKey("symptomBorderColourTooltip"));
        c.getSphereFormMenuButton().getTooltip().setText(loadLanguagesKey("symptomFormTooltip"));
        c.getVerticesAutoLayout().getTooltip().setText(loadLanguagesKey("toolbarLayout"));
        c.getFontSymptomComboBox().setPromptText(loadLanguagesKey("font"));
        c.getFontSymptomComboBox().hide();
        c.getFontSymptomComboBox().getTooltip().setText(loadLanguagesKey("symptomFontTooltip"));
        c.getSizeSymptomComboBox().setPromptText(loadLanguagesKey("fontSize"));
        c.getSizeSymptomComboBox().hide();
        c.getSizeSymptomComboBox().getTooltip().setText(loadLanguagesKey("symptomFontSizeTooltip"));
        c.getSelectionEdge().setText(loadLanguagesKey(sectionEdge));
        c.getAnchorPointsButton().getTooltip().setText(loadLanguagesKey("edgeAnchorPoint"));
        c.getRemoveEdges().getTooltip().setText(loadLanguagesKey("deleteEdge"));
        c.getRemoveAnchor().getTooltip().setText(loadLanguagesKey("edgeAnchorPointDelete"));
        c.getEdgeColour().getTooltip().setText(loadLanguagesKey("edgeColour"));
        c.getEdgeStrokeMenuButton().getTooltip().setText(loadLanguagesKey("edgeStrokeType"));
        c.getEdgeArrowMenuButton().getTooltip().setText(loadLanguagesKey("edgeArrowTypeTooltip"));
        c.getAnalysisGraphInfo().setText(loadLanguagesKey("analysisGraphInfo"));
        c.getAnalysisScope().setText(loadLanguagesKey("analysisScope"));
        c.getAnalysisNetworkingIndex().setText(loadLanguagesKey("analysisNetworkingIndex"));
        c.getAnalysisStructureIndex().setText(loadLanguagesKey("analysisStructureIndex"));
        c.getAnalysisSymptom().setText(loadLanguagesKey(sectionSymptom));
        c.getAnalysisPredecessor().getTooltip().setText(loadLanguagesKey("analysisPredecessorTooltip"));
        c.getAnalysisSuccessor().getTooltip().setText(loadLanguagesKey("analysisSuccessorTooltip"));
        c.getAnalysisPredecessor().setText(loadLanguagesKey("analysisPredecessor"));
        c.getAnalysisSuccessor().setText(loadLanguagesKey(analysisSuccessor));
        c.getAmountSymptomTextField().setPromptText(loadLanguagesKey("analysisSymptomAmount"));
        c.getAmountSymptomTextField().getTooltip().setText(loadLanguagesKey("analysisSymptomAmountTooltip"));
        c.getAnalysisPathMenuButton().setText(loadLanguagesKey("analysisPathMenuButton"));
        c.getAnalysisPathMenuButton().getTooltip().setText(loadLanguagesKey("analysisPathToolTip"));
        c.getAnalysisShortestPath().setText(loadLanguagesKey("analysisShortestPath"));
        c.getAnalysisAllPaths().setText(loadLanguagesKey("analysisAllPaths"));
        c.getAnalysisOption().setText(loadLanguagesKey("analysisOptions"));
        c.getAnalysisOptions().getTooltip().setText(loadLanguagesKey("analysisOptionsTooltip"));
        c.getChainOfEdges().setText(loadLanguagesKey("filterChainOfEdges"));
        c.getConvergent().setText(loadLanguagesKey("filterConvergentBranches"));
        c.getDivergent().setText(loadLanguagesKey("filterDivergentBranches"));
        c.getBranches().setText(loadLanguagesKey("filterBranches"));
        c.getCycles().setText(loadLanguagesKey("filterCycles"));
        c.getOverViewTitledPane().setText(loadLanguagesKey("treeView"));
        c.getFilterEdgeType().getTooltip().setText(loadLanguagesKey("treeViewEdgeTypeTooltip"));
        c.getTemplateTitledPane().setText(loadLanguagesKey("toolbarTemplate"));
        c.getFilterAnalysis().setText(loadLanguagesKey("filterChainOfEdges"));
        c.getTemplateMaxSphere().setText(loadLanguagesKey("templateMaxSphere"));
        c.getTemplateMaxSymptom().setText(loadLanguagesKey("templateMaxSymptom"));
        c.getTemplateMaxEdge().setText(loadLanguagesKey("templateMaxEdge"));
        c.getReinforcedBox().setText(loadLanguagesKey("templateReinforced"));
        c.getExtenuatingBox().setText(loadLanguagesKey("templateExtenuating"));
        c.getNeutralBox().setText(loadLanguagesKey("templateNeutral"));
        c.getTemplateChoose().setText(loadLanguagesKey("templateChoose"));
        c.getTemplateSpheres().setText(loadLanguagesKey("sectionSphere"));
        c.getSphereCol().setText(loadLanguagesKey(templateName));
        c.getTitleSphereCol().setText(loadLanguagesKey("templateTitle"));
        c.getPositionSphereCol().setText(loadLanguagesKey(templatePosition));
        c.getStyleSphereCol().setText(loadLanguagesKey(templateStyle));
        c.getVerticesSphereCol().setText(loadLanguagesKey("templateDeleteAdd"));
        c.getMaxAmountSphereCol().setText(loadLanguagesKey("templateMaxElements"));
        c.getTemplateSymptom().setText(loadLanguagesKey(sectionSymptom));
        c.getSymptomCol().setText(loadLanguagesKey(templateName));
        c.getTitleSymptomCol().setText(loadLanguagesKey("templateTitle"));
        c.getPositionSymptomCol().setText(loadLanguagesKey(templatePosition));
        c.getStyleSymptomCol().setText(loadLanguagesKey(templateStyle));
        c.getTemplateEdge().setText(loadLanguagesKey(sectionEdge));
        c.getEdgeCol().setText(loadLanguagesKey(templateName));
        c.getPositionEdgeCol().setText(loadLanguagesKey(templatePosition));
        c.getStyleEdgeCol().setText(loadLanguagesKey(templateStyle));
        c.getEdgetypeEdgeCol().setText(loadLanguagesKey("templateEdgeType"));
        c.getHistoryTitledPane().setText(loadLanguagesKey("log"));
        c.getFilterLogType().setText(loadLanguagesKey("LOG_ALL"));
        c.getLogEditFontVertices().setText(loadLanguagesKey("EDIT_FONT_VERTICES"));
        c.getLogDeactivateFadeout().setText(loadLanguagesKey("DEACTIVATE_FADEOUT"));
        c.getLogEditSphereColor().setText(loadLanguagesKey("EDIT_SPHERE_COLOR"));
        c.getLogEditEdgesStroke().setText(loadLanguagesKey("EDIT_EDGES_STROKE"));
        c.getLogEditSphereSize().setText(loadLanguagesKey("EDIT_SPHERE_SIZE"));
        c.getLogEditFontSphere().setText(loadLanguagesKey("EDIT_FONT_SPHERE"));
        c.getLogEditEdgesColor().setText(loadLanguagesKey("EDIT_EDGES_COLOR"));
        c.getLogRemoveVertices().setText(loadLanguagesKey("REMOVE_VERTICES"));
        c.getLogEditEdgesType().setText(loadLanguagesKey("EDIT_EDGES_TYPE"));
        c.getLogRemoveSphere().setText(loadLanguagesKey("REMOVE_SPHERE"));
        c.getLogMoveVertices().setText(loadLanguagesKey("MOVE_VERTICES"));
        c.getLogMoveSphere().setText(loadLanguagesKey("MOVE_SPHERE"));
        c.getLogActivateAnchorPointsFadeout().setText(loadLanguagesKey("ACTIVATE_ANCHOR_POINTS_FADEOUT"));
        c.getLogAddAnchorPoints().setText(loadLanguagesKey("ADD_ANCHOR_POINTS"));
        c.getLogActivateFadeout().setText(loadLanguagesKey("ACTIVATE_FADEOUT"));
        c.getLogActivateHighlight().setText(loadLanguagesKey("ACTIVATE_HIGHLIGHT"));
        c.getLogEditVerticesForm().setText(loadLanguagesKey("EDIT_VERTICES_FORM"));
        c.getLogRemoveEdges().setText(loadLanguagesKey("REMOVE_EDGES"));
        c.getLogEditVerticesSize().setText(loadLanguagesKey("EDIT_VERTICES_SIZE"));
        c.getLogRemoveAnchorPoints().setText(loadLanguagesKey("REMOVE_ANCHOR_POINTS"));
        c.getLogEditSphereFontSize().setText(loadLanguagesKey("EDIT_SPHERE_FONT_SIZE"));
        c.getLogEditSphereAnnotation().setText(loadLanguagesKey("EDIT_SPHERE_ANNOTATION"));
        c.getLogEditVertexAnnotation().setText(loadLanguagesKey("EDIT_VERTEX_ANNOTATION"));
        c.getLogEditVerticesFontSize().setText(loadLanguagesKey("EDIT_VERTICES_FONT_SIZE"));
        c.getLogEditVerticesDrawColor().setText(loadLanguagesKey("EDIT_VERTICES_DRAW_COLOR"));
        c.getLogEditVerticesFillColor().setText(loadLanguagesKey("EDIT_VERTICES_FILL_COLOR"));
        c.getLogDeactivateHighlight().setText(loadLanguagesKey("DEACTIVATE_HIGHLIGHT"));
        c.getLogDeactivateAnchorPointsFadeout().setText(loadLanguagesKey("DEACTIVATE_ANCHOR_POINTS_FADEOUT"));
        c.getLogEditSpheresLayout().setText(loadLanguagesKey("EDIT_SPHERES_LAYOUT"));
        c.getLogEditVerticesLayout().setText(loadLanguagesKey("EDIT_VERTICES_LAYOUT"));
        c.getLogAll().setText(loadLanguagesKey("LOG_ALL"));
        c.getLogAddSphere().setText(loadLanguagesKey("ADD_SPHERE"));
        c.getLogAddEdge().setText(loadLanguagesKey("ADD_EDGES"));
        c.getLogAddVertex().setText(loadLanguagesKey("ADD_VERTICES"));
        c.getTooltipInfoAnalysis().setText(loadLanguagesKey("INFO_ANALYSIS"));
        c.getTooltipInfoZoom().setText(loadLanguagesKey("INFO_ZOOM"));
    }

    public String loadLanguagesKey(String key)  {
        return resource.getString(key);
    }

    public String loadLanguagesKey(String key, Object[] arr){
        MessageFormat form = new MessageFormat(loadLanguagesKey(key));
        return form.format(arr);
    }
}
