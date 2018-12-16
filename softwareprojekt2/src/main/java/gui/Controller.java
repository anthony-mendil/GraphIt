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

/**
 * Contains most of the gui elements, calls most of the actions and acts as interface between
 * the gui and the internal components of the application
 */
public class Controller implements ObserverSyndrom{

    /**
     * Standard constructor that is getting loaded by the fxmlloader
     */
    public Controller(){
        throw new UnsupportedOperationException();
    }

    /* General Stuff */

    /**
     *  The swingnode that displays the graph and allows to interact with it
     */
    private SwingNode canvas;

    /**
     *  The swingnode that displays the zoom window
     */
    private SwingNode zoomWindow;

    /**
     *  The tiltedpane that contains the treeview object and filter options
     */
    private TitledPane tiltedPane;

    /**
     *  The treeview displays the overview of the graph that also allows the user to interact wit it
     *  through it
     */
    private TreeView treeView;

    /**
     *  The text that displays the current action
     */
    private Text actionText;

    /**
     *  The slider that allows the user to zoom out or in the graph canvas
     */
    private Slider zoomSlider;

    /* Menu Bar */
    /**
     * The menuitem under the menu "File.." for creating a new file
     */
    private MenuItem newFile;

    /**
     * The menuItem under the menu "File.." for opening a file
     */
    private MenuItem openFile;

    /**
     * The menuItem under the menu "File.." for saving under a specified location
     */
    private MenuItem saveLocation;

    /**
     * The menuItem under the menu "File.. > Export as.." for exporting the file as pdf
     */
    private MenuItem exportPDF;

    /**
     * The menuItem under the menu "File.. > Export as.." for exporting the file as template
     */
    private MenuItem exportTemplate;

    /**
     * The menuItem under the menu "File.. > Export as.." for exporting the file as different formats (e.g. png,svg,etc.)
     */
    private MenuItem exportDifferentFormats;

    /**
     * The menuItem under the menu "File.." for printing the file
     */
    private MenuItem print;

    /**
     * The menuItem under the menu "Options > Language" for changing the language of the gui to german
     */
    private MenuItem languageGerman;

    /**
     * The menuItem under the menu "Options > Language" for changing the language of the gui to english
     */
    private MenuItem languageEnglisch;

    /**
     * The menuItem under the menu "Help" for opening the documention
     */
    private MenuItem documentation;

    /* Template Options */

    /**
     * The textfield for setting the template rule "maximum numbers of spheres in the graph"
     */
    private TextField maxSphereField;

    /**
     * The textfield for setting the template rule "maximum numbers of symptoms in the graph"
     */
    private TextField maxSymptomField;

    /**
     * The textfield for setting the template rule "maximum numbers of symptoms in a sphere in the graph"
     */
    private TextField maxSymptominSphereField;

    /**
     * The textfield for setting the template rule "maximum numbers of edges in the graph"
     */
    private TextField maxEdgesField;

    /**
     * The checkbox for setting the template rule "allowing reinforced edges"
     */
    private CheckBox reinforcedBox;

    /**
     * The checkbox for setting the template rule "allowing extenuating edges"
     */
    private CheckBox extenuatingBox;

    /**
     * The checkbox for setting the template rule "allowing neutral edges"
     */
    private CheckBox neutralBox;

    /* Analyse Mode */

    //Information
    /**
     * The text that displays the scope of the graph in the analysis/interpreter mode
     */
    private Text scope;

    /**
     * The text that displays the networking index of the graph in the analysis/interpreter mode
     */
    private Text networkingIndex;

    /**
     * The text that displays the structure index of the graph in the analysis/interpreter mode
     */
    private Text structureIndex;

    //Sphere

    /**
     * The checkbox that allows the user to see all predecessor symptoms in the analysis/interpreter mode
     */
    private CheckBox predecessorBox;

    /**
     * The checkbox that allows the user to see all successor symptoms in the analysis/interpreter mode
     */
    private CheckBox successorBox;

    /**
     * The textfield that sets the amount of predecessor/successor symptoms in the analysis/interpreter mode
     */
    private TextField amountSphereField;

    //Edge

    /**
     * The checkbox that highlights all incoming edges
     */
    private CheckBox incomingEdgeBox;

    /**
     * The checkbox that highlights all originating edges
     */
    private CheckBox originatingEdgeBox;

    /**
     * The checkbox that highlights edges with a specific arrowtype
     */
    private CheckBox edgeArrowTypeBox;

    /**
     * The menuitem under "edgeArrowType" that highlights reinforced edges
     */
    private MenuItem analysisReinforced;

    /**
     * The menuitem under "edgeArrowType" that highlights extenuating edges
     */
    private MenuItem analysisExtenuating;

    /**
     * The menuitem under "edgeArrowTyp" that highlights neutral edges
     */
    private MenuItem analysisNeutral;

    /**
     * The textfield that sets the amount of originating or incoming edges
     */
    private TextField amountEdgeField;

    //Filter
    /**
     * The checkbox that allows filter options
     */
    private CheckBox analysisBox;

    /**
     * The menuitem under "analysis" that shows chains of edges
     */
    private MenuItem chainOfEdges;

    /**
     * The menuitem under "analysis" that shows convergent branches
     */
    private MenuItem convergentBranches;

    /**
     * The menuitem under "analysis" that shows divergent branches
     */
    private MenuItem divergentBranches;

    /**
     * The menuitem under "analysis" that shows convergent and divergent branches
     */
    private MenuItem branches;

    /**
     * The menuitem under "analysis" that shows cycles in the graph
     */
    private MenuItem cycles;

    //Treeview-Filter
    /**
     * The checkbox that allows to filter the overview after arrow types
     */
    private CheckBox treeViewArrowtype;

    /**
     * The menuitem under "Treeviewarrowtype" that filters the overview after reinforced edges
     */
    private MenuItem treeViewReinforced;

    /**
     * The menuitem under "Treeviewarrowtypes" that filters the overview after extenuating edges
     */
    private MenuItem treeViewExtenuating;

    /**
     * The menuitem under "Treeviewarrowtypes" that filters the overview after neutral edges
     */
    private MenuItem treeViewNeutral;

    /**
     * The checkbox that allows to filter the overview after regular expressions
     */
    private CheckBox regularExpressionBox;

    /**
     * The textfield that gets the argument to filter the overview after regular expressions
     */
    private TextField regularExpressionField;

    /**
     * The checkbox that filters the overview after fadedout objects
     */
    private CheckBox showFadedOutObjects;

    /* Sphere */
    /**
     * The colorpicker for changing the background color of sphere
     */
    private ColorPicker sphereBackground;

    /**
     * The textfield for changing the font of the sphere text
     */
    private TextField sphereFontField;

    /**
     * The menuitem for changing the font of the sphere text to a specific font
     */
    private MenuItem sphereFont1;

    /**
     * The menuitem for changing the font of the sphere text to a specific font
     */
    private MenuItem sphereFont2;

    /**
     * The menuitem for changing the font of the sphere text to a specific font
     */
    private MenuItem sphereFont3;

    /**
     * The menuitem for changing the font of the sphere text to a specific font
     */
    private MenuItem sphereFont4;

    /**
     * The menuitem for changing the font of the sphere text to a specific font
     */
    private MenuItem sphereFont5;

    /**
     * The textfield for changing the size of the sphere text
     */
    private TextField sphereSize;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize1;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize2;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize3;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize4;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize5;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize6;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize7;

    /**
     * The menuitem for changing the size of the sphere text to a specific size
     */
    private MenuItem sphereSize8;

    /* Symptom */

    /**
     * The colorpicker for changing the background color for a symptom
     */
    private ColorPicker symptomBackground;

    /**
     * The colorpicker for changing the border color for a symptom
     */
    private ColorPicker symptomBorder;

    /**
     * The menuitem for changing the form of a symptom to a rectangle
     */
    private MenuItem symptomRectangle;

    /**
     * The menuitem for changing the form of a symptom to a circle
     */
    private MenuItem symptomCircle;

    /**
     * The menuitem for changing the form of a symptom to a ellipse
     */
    private MenuItem symptomEllipse;

    /**
     * The textfield for changing the font of the symptom text
     */
    private TextField symptomFontField;

    /**
     * The menuitem for changing the font of the symptom text to a specific font
     */
    private MenuItem symptomFont1;

    /**
     * The menuitem for changing the font of the symptom text to a specific font
     */
    private MenuItem symptomFont2;

    /**
     * The menuitem for changing the font of the symptom text to a specific font
     */
    private MenuItem symptomFont3;

    /**
     * The menuitem for changing the font of the symptom text to a specific font
     */
    private MenuItem symptomFont4;

    /**
     * The menuitem for changing the font of the symptom text to a specific font
     */
    private MenuItem symptomFont5;

    /**
     * The textfield for changing the size of the symptom text
     */
    private TextField symptomSizeField;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize1;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize2;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize3;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize4;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize5;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize6;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize7;

    /**
     * The menuitem for changing the size of the symptom text to a specific size
     */
    private MenuItem symptomSize8;

    /* Edge */

    /**
     * The colorpicker for changing the color of edges
     */
    private ColorPicker edgeColour;

    /**
     * The menuitem for changing the stroke type of edges to dashed
     */
    private MenuItem edgeStrokeDashed;

    /**
     * The menuitem for changing the stroke type of edges to weighted dashed
     */
    private MenuItem edgeStrokeDashedWeight;

    /**
     * The menuitem for changing the stroke type of edges to dotted
     */
    private MenuItem edgeStrokeDotted;

    /**
     * The menuitem for changing the stroke type of edges to weighted dotted
     */
    private MenuItem edgeStrokeDottedWeight;

    /**
     * The menuitem for changing the stroke type of edges to basic
     */
    private MenuItem edgeStrokeBasic;

    /**
     * The menuitem for changing the stroke type of edges to weighted basic
     */
    private MenuItem edgeStrokeBasicWeight;

    /**
     * The menuitem for changing the arrow type of edges to reinforced
     */
    private MenuItem edgeArrowReinforced;

    /**
     * The menuitem for changing the arrow type of edges to extenuating
     */
    private MenuItem edgeArrowExtenuating;

    /**
     * The menuitem for changing the arrow type of edges to neutral
     */
    private MenuItem edgeArrowNeutral;

    /* Internal */
    /**
     * The logdao object that provides the treeview with the protocol
     */
    private LogDao protocol;

    /**
     * The values object that gets all the arguments from the gui for the actions
     */
    @Inject
    private Values values;

    /**
     * The syndrom object that is needed to change the form of spheres, symptoms and edges
     */
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
     * Executes the editVerticesDrawColor-Action
     */
    public void editVerticesDrawColor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Executes the editVerticesFillColor-Action
     */
    public void editVerticesFillColor(){ throw new UnsupportedOperationException();}

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
     * Deletes all Rules that were set before
     */
    public void deleteTemplateRules(){throw new UnsupportedOperationException();}

    /**
     * Creates a Window that allows you to set Rules for your Template
     */
    public void createTemplateWindow(){
        throw new UnsupportedOperationException();
    }

    /* ----------------INTERNAL---------------------- */

    /**
     * Loads the swingnodes and sets the event handlers for menuitems and color pickers
     */
    public void initialize(){
    }

    /**
     * Uses the provided swingnode to display the zoom window on it
     * @param swingNode the swingnode, that the fxml file provides
     */
    private void createSwingZoomWindow(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /**
     * Uses the provided swingnode to display the graph canvas on it
     * @param swingNode the swingnode, that the fxml file provides
     */
    private void createSwingCanvas(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /**
     * the event handler that provides the arguments, needed to use the actions after clicking on a menuitem
     */
    private class MenuItemHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt){
        }
    }

    /**
     * the event handler that provides the arguments, needed to use the actions after choosing a colour
     */
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
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateNewGraph() {
        throw new UnsupportedOperationException();
    }
}
