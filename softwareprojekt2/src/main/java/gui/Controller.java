package gui;

import actions.ActionHistory;
import actions.ObserverSyndrom;
import graph.graph.Syndrom;
import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import log_management.dao.Dao;
import log_management.dao.LogDao;

import javax.swing.*;

public class Controller implements ObserverSyndrom{
    @Inject
    private Values values;
    @Inject
    privte Syndrom syndrom;

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
    private Syndrom syndrom;
    //private ActionHistory cmdHistory;
>>>>>>> 67b0aaa95b87632404b93ad539810e9878d3bbaa

    /* ----------------ACTIVATE---------------------- */

    /**
     * Executes the activateAnchorPointsFadeout-Action
     */
    public void activateAnchorPointsFadeOut() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the activateFadeoutLog-Action
     */
    public void activateFadeout() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the highlightLog-Action
     */
    public void activateHighlight() {
        throw new UnsupportedOperationException();
    }

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
     * Executes the deactivateAnchorPointsFadeout-Action
     */
    public void deactivateAnchorPointsFadeout() {
        throw new UnsupportedOperationException();
    }

    /* nur über Drop-Down-Menü */

    /**
     * Executes the deactivateFadeout-Action
     */
    public void deactivateFadeout() {
        throw new UnsupportedOperationException();
    }

    /* nur über Drop-Down-Menü */

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
     * Execute the editEdgeColor-Action
     */
    public void editEdgeColor() {
        throw new UnsupportedOperationException();
    }

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
     * Executes the editVertexColor-Action
     */
    public void editVertexColor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVerticesColor-Action
     */
    public void editVerticesColor() {
        throw new UnsupportedOperationException();
    }

    /* ......font..... */

    /**
     * Executes the editFont-Action
     */
    public void editFont() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editFontSize-Action
     */
    public void editFontSize() {
        throw new UnsupportedOperationException();
    }

    /* ......form..... */

    /**
     * Executes the editVertexForm-Action
     */
    public void editVertexForm() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVerticesForm-Action
     */
    public void editVerticesForm() {
        throw new UnsupportedOperationException();
    }

    /* ......size..... */

    /**
     * Executes the editSphereSize-Action
     */
    public void editSphereSize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVertexSize-Action
     */
    public void editVertexSize() {
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
     * Executes the importOOF-Action
     */
    public void importOOF() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the importGXL-Action
     */
    public void importGXL() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the printPDF-Action
     */
    public void printPDF() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the exportTemplate-Action
     */
    public void exportTemplate() {
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
    public void updateNewGraph() {
        throw new UnsupportedOperationException();
    }
}
