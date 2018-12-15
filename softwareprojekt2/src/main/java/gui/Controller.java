package gui;

import actions.ObserverSyndrom;
import com.google.inject.Inject;
import graph.graph.Syndrom;
import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import log_management.dao.LogDao;

import javax.swing.*;

public class Controller implements ObserverSyndrom{

    public Controller(){
        throw new UnsupportedOperationException();
    }

    /* General Stuff */
    private SwingNode canvas;
    private SwingNode zoomWindow;
    private TitledPane tiltedPane;
    private TreeView treeView;
    private Text actionText;
    private Slider zoomSlider;

    /* Menu Bar */
    private MenuItem newFile;
    private MenuItem openFile;
    private MenuItem save;
    private MenuItem saveLocation;
    private MenuItem exportPDF;
    private MenuItem exportTemplate;
    private MenuItem exportDifferentFormats;
    private MenuItem print;

    private MenuItem languageGerman;
    private MenuItem languageEnglisch;

    private MenuItem documentation;

    /* Template Options */
    private TextField maxSphereField;
    private TextField maxSymptomField;
    private TextField maxSymptominSphereField;
    private TextField maxEdgesField;

    private CheckBox reinforcedBox;
    private CheckBox extenuatingBox;
    private CheckBox neutralBox;

    /* Analyse Mode */

    //Information
    private Text scope;
    private Text networkingIndex;
    private Text structureIndex;

    //Sphere
    private CheckBox predecessorBox;
    private CheckBox successorBox;
    private TextField amountSphereField;

    //Edge
    private CheckBox incomingEdgeBox;
    private CheckBox outcomingEdgeBox;
    private CheckBox edgeArrowTypeBox;
    private MenuItem analysisReinforced;
    private MenuItem analysisExtenuating;
    private MenuItem analysisNeutral;
    private TextField amountEdgeField;

    //Filter
    private CheckBox analysisBox;
    private MenuItem chainOfEdges;
    private MenuItem convergentBranches;
    private MenuItem divergentBranches;
    private MenuItem branches;
    private MenuItem cycles;

    //Treeview-Filter
    private CheckBox treeViewArrowtype;
    private MenuItem treeViewReinforced;
    private MenuItem treeViewExtenuating;
    private MenuItem treeViewNeutral;

    private CheckBox regularExpressionBox;
    private TextField regularExpressionField;

    private CheckBox showFadedOutObjects;

    /* Sphere */
    private ColorPicker sphereBackground;
    private ColorPicker sphereBorder;

    private TextField sphereFontField;
    private MenuItem sphereFont1;
    private MenuItem sphereFont2;
    private MenuItem sphereFont3;
    private MenuItem sphereFont4;
    private MenuItem sphereFont5;

    private TextField sphereSize;
    private MenuItem sphereSize1;
    private MenuItem sphereSize2;
    private MenuItem sphereSize3;
    private MenuItem sphereSize4;
    private MenuItem sphereSize5;
    private MenuItem sphereSize6;
    private MenuItem sphereSize7;
    private MenuItem sphereSize8;

    /* Symptom */

    private ColorPicker symptomBackground;
    private ColorPicker symptomBorder;

    private MenuItem symptomRectangle;
    private MenuItem symptomCircle;
    private MenuItem symptomEllipse;

    private TextField symptomFontField;
    private MenuItem symptomFont1;
    private MenuItem symptomFont2;
    private MenuItem symptomFont3;
    private MenuItem symptomFont4;
    private MenuItem symptomFont5;

    private TextField symptomSizeField;
    private MenuItem symptomSize1;
    private MenuItem symptomSize2;
    private MenuItem symptomSize3;
    private MenuItem symptomSize4;
    private MenuItem symptomSize5;
    private MenuItem symptomSize6;
    private MenuItem symptomSize7;
    private MenuItem symptomSize8;

    /* Edge */

    private ColorPicker edgeColour;

    private MenuItem edgeStrokeDashed;
    private MenuItem edgeStrokeDashedWeight;
    private MenuItem edgeStrokeDotted;
    private MenuItem edgeStrokeDottedWeight;
    private MenuItem edgeStrokeBasic;
    private MenuItem edgeStrokeBasicWeight;

    private MenuItem edgeArrowReinforced;
    private MenuItem edgeArrowExtenuating;
    private MenuItem edgeArrowNeutral;

    /* Internal */
    private LogDao protocol;
    @Inject
    private Values values;
    @Inject
    private Syndrom syndrom;
    //private ActionHistory cmdHistory;

    /* ----------------ADD---------------------- */

    /**
     * Executes the addSphereLog-Action
     */
    public void addSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the addVertexLog-Action
     */
    public void addVertex() {
        throw new UnsupportedOperationException();
    }

    /* ----------------ANALYSE---------------------- */

    /**
     * Executes the analysisGraph-Action
     */
    public void analysisGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the evaluationGraph-Action
     */
    public void evaluationGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the filterGraph-Action
     */
    public void filterGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the graphDimension-Action
     */
    public void graphDimension() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the selectionAnalysisGraph-Action
     */
    public void selectionAnalysisGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------DEACTIVATE---------------------- */

    /**
     * Executes the deactivateFadeout-Action
     */
    public void deactivateFadeout() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the deactivateHighlight-Action
     */
    public void deactivateHighlight() {
        throw new UnsupportedOperationException();
    }

    /* ----------------EDIT---------------------- */

    /**
     * Executes the editEdgesStroke-Action
     */
    public void editEdgesStroke() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editEdgesType-Action
     */
    public void editEdgesType() {
        throw new UnsupportedOperationException();
    }

    /* ......annotation..... */

    /**
     * Executes the editSphereAnnotation-Action
     */
    public void editSphereAnnotation() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVertexAnnotation-Action
     */
    public void editVertexAnnotation() {
        throw new UnsupportedOperationException();
    }

    /* ......color..... */

    /**
     * Execute the editEdgesColor-Action
     */
    public void editEdgesColor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editSphereColor-Action
     */
    public void editSphereColor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVerticesColor-Action
     */
    public void editVerticesColor() {
        throw new UnsupportedOperationException();
    }

    /*
    public void editVerticesBorderColor(){ throw new UnsupportedOperationException();}

    public void editSphereBorderColor(){throw new UnsupportedOperationException();} */

    /* ......font..... */

    /**
     * Executes the editFontSphere-Action
     */
    public void editFontSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editFontVertex-Action
     */
    public void editFontVertex() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editFontSizeSphere-Action
     */
    public void editFontSizeSphere(){ throw new UnsupportedOperationException(); }

    /**
     * Executes the editFontSizeVertices-Action
     */
    public void editFontSizeVertices(){ throw new UnsupportedOperationException(); }

    /* ......form..... */

    /**
     * Executes the editVerticesForm-Action
     */
    public void editVerticesForm() {
        throw new UnsupportedOperationException();
    }

    /* ----------------EXPORT---------------------- */

    /**
     * Executes the exportGXL-Action
     */
    public void exportGXL() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the exportPDF-Action
     */
    public void exportPDF() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the exportOOF-Action
     */
    public void exportOOF() {
        throw new UnsupportedOperationException();
    }

    /**
     * Opens the selected File after pressing "Open File" on the File Menu
     */
    public void openFile(){throw new UnsupportedOperationException();}

    /*
    /**
     * Executes the importOOF-Action

    public void importOOF() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the importGXL-Action

    public void importGXL() {
        throw new UnsupportedOperationException();
    }
    */

    /**
     * Executes the printPDF-Action
     */
    public void printPDF() {
        throw new UnsupportedOperationException();
    }

    /* ----------------LAYOUT---------------------- */

    /**
     * Executes the layoutGraph-Action
     */
    public void layoutGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------OTHER---------------------- */

    /**
     * Executes the changeLanguage-Action
     */
    public void changeLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the switchModiEditor-Action
     */
    public void switchModiEditor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the createGraph-Action
     */
    public void createGraph(){throw new UnsupportedOperationException();}

    /**
     * Opens the selected Graph under the "recently-edited Graphs Menu"
     */
    public void loadGraph(){throw new UnsupportedOperationException();}

    /**
     * Executes the undo-Action
     */
    public void undoAction(){throw new UnsupportedOperationException();}

    /**
     * Executes the redo-Action
     */
    public void redoAction(){throw new UnsupportedOperationException();}

    /**
     * Opens the documentation about the application
     */
    public void openDocumentation(){throw new UnsupportedOperationException();}

    /**
     * Zooms in or out of the graph canvas while using the slider
     */
    public void zoomGraphCanvas(){throw new UnsupportedOperationException();}

    /**
     *  Shows the current used action
     */
    public void currentAction(String action){throw new UnsupportedOperationException();}

    /* ----------------REMOVE---------------------- */

    /**
     * Executes the removeEdges-Action
     */
    public void removeEdges() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the removeSphere-Action
     */
    public void removeSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the removeVertices-Action
     */
    public void removeVertices() {
        throw new UnsupportedOperationException();
    }

    /* ----------------TEMPLATE---------------------- */

    /**
     * Executes the rulesTemplate-Action
     */
    public void rulesTemplate() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a Window that allows you to set Rules for your Template
     */
    public void createTemplateWindow(){
        throw new UnsupportedOperationException();
    }

    /* ----------------INTERNAL---------------------- */

    public void initialize(){
    }

    private void createSwingZoomWindow(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void createSwingCanvas(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private class MenuItemHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt){
        }
    }

    private class ColorPickerHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt){
        }
    }

    @Override
    public void updateGraph() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFunctionMode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateEditMode() {

    }

    @Override
    public void updateNewGraph() {
        throw new UnsupportedOperationException();
    }
}
