package gui;

import actions.ActionHistory;
import actions.ObserverSyndrom;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.edit.form.EditVerticesFormLogAction;
import actions.edit.size.EditSphereSizeLogAction;
import actions.export_graph.ExportGxlAction;
import actions.export_graph.ExportOofAction;
import actions.export_graph.ExportPdfAction;
import actions.layout.LayoutGraphLogAction;
import actions.other.CreateGraphAction;
import actions.remove.RemoveSphereLogAction;
import graph.graph.SphereSizeChange;
import graph.graph.Syndrom;
import io.GXLio;
import io.OOFio;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import log_management.dao.LogDao;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Contains most of the gui elements, calls most of the actions and acts as interface between
 * the gui and the internal components of the application.
 */
public class Controller implements ObserverSyndrom{


    /* General Stuff */

    /**
     *  The swing node that displays the JUNG-graph and allows to interact with it.
     */
    @FXML
    private SwingNode canvas;

    /**
     *  The swing node that displays the zoom window.
     */
    private SwingNode zoomWindow;

    /**
     *  The tilted pane that contains the treeview object and filter options.
     */
    private TitledPane tiltedPane;

    /**
     *  The treeview displays the overview of the graph that also allows the user to edit the graph
     *  through it.
     */
    @FXML
    private TreeView treeView;

    /**
     *  The text that displays the current action.
     */
    private Text actionText;

    /**
     *  The slider that allows the user to zoom out or in.
     */
    private Slider zoomSlider;

    /* Menu Bar */
    /**
     * The menuitem under the menu "File.." for creating a new file.
     */
    @FXML
    private MenuItem newFile;

    /**
     * The menuitem under the menu "File.." for opening a file.
     */
    private MenuItem openFile;

    /**
     * The menuitem under the menu "File.." for importing a GXL file.
     */
    private MenuItem importGxl;

    /**
     * The menuitem under the menu "File.." for saving under a specified location.
     */
    private MenuItem saveLocation;

    /**
     * The menuitem under the menu "File.. &gt; Export as.." for exporting the file as pdf.
     */
    private MenuItem exportPDF;

    /**
     * The menuitem under the menu "File.. &gt; Export as.." for exporting the file as template.
     */
    private MenuItem exportGXL;

    /**
     * The menuitem under the menu "File.. &gt; Export as.." for exporting the file as different formats (e.g. png,svg,etc.).
     */
    private MenuItem exportDifferentFormats;

    /**
     * The menuitem under the menu "File.." for printing the file.
     */
    private MenuItem print;

    /**
     * The menuitem under the menu "Options &gt; Language" for changing the language of the gui to german.
     */
    private MenuItem languageGerman;

    /**
     * The menuitem under the menu "Options &gt; Language" for changing the language of the gui to english.
     */
    private MenuItem languageEnglisch;

    /**
     * The menuitem under the menu "Help" for opening the documention.
     */
    private MenuItem documentation;

    /* Template Options */

    /**
     * The textfield for setting the template rule "maximum numbers of spheres in the graph".
     */
    private TextField maxSphereField;

    /**
     * The textfield for setting the template rule "maximum numbers of symptoms in the graph".
     */
    private TextField maxSymptomField;

    /**
     * The textfield for setting the template rule "maximum numbers of symptoms in a sphere in the graph".
     */
    private TextField maxSymptominSphereField;

    /**
     * The textfield for setting the template rule "maximum numbers of edges in the graph".
     */
    private TextField maxEdgesField;

    /**
     * The checkbox for setting the template rule "allowing reinforced edges".
     */
    private CheckBox reinforcedBox;

    /**
     * The checkbox for setting the template rule "allowing extenuating edges".
     */
    private CheckBox extenuatingBox;

    /**
     * The checkbox for setting the template rule "allowing neutral edges".
     */
    private CheckBox neutralBox;

    /* Analyse Mode */

    //Information
    /**
     * The text that displays the scope of the graph in the analysis/interpreter mode.
     */
    private Text scope;

    /**
     * The text that displays the networking index of the graph in the analysis/interpreter mode.
     */
    private Text networkingIndex;

    /**
     * The text that displays the structure index of the graph in the analysis/interpreter mode.
     */
    private Text structureIndex;

    //Sphere

    /**
     * The checkbox that allows the user to see all predecessor symptoms in the analysis/interpreter mode.
     */
    private CheckBox predecessorBox;

    /**
     * The checkbox that allows the user to see all successor symptoms in the analysis/interpreter mode.
     */
    private CheckBox successorBox;

    /**
     * The textfield that sets the amount of predecessor/successor symptoms in the analysis/interpreter mode.
     */
    private TextField amountSphereField;

    //Edge

    /**
     * The checkbox that highlights all incoming edges.
     */
    private CheckBox incomingEdgeBox;

    /**
     * The checkbox that highlights all originating edges.
     */
    private CheckBox originatingEdgeBox;

    /**
     * The checkbox that highlights edges with a specific arrowtype.
     */
    private CheckBox edgeArrowTypeBox;

    /**
     * The menuitem under "edgeArrowType" that highlights reinforced edges.
     */
    private MenuItem analysisReinforced;

    /**
     * The menuitem under "edgeArrowType" that highlights extenuating edges.
     */
    private MenuItem analysisExtenuating;

    /**
     * The menuitem under "edgeArrowTyp" that highlights neutral edges.
     */
    private MenuItem analysisNeutral;

    /**
     * The textfield that sets the amount of originating or incoming edges.
     */
    private TextField amountEdgeField;

    //Filter
    /**
     * The checkbox that allows filter options.
     */
    private CheckBox analysisBox;

    /**
     * The menuitem under "analysis" that shows chains of edges.
     */
    private MenuItem chainOfEdges;

    /**
     * The menuitem under "analysis" that shows convergent branches.
     */
    private MenuItem convergentBranches;

    /**
     * The menuitem under "analysis" that shows divergent branches.
     */
    private MenuItem divergentBranches;

    /**
     * The menuitem under "analysis" that shows convergent and divergent branches.
     */
    private MenuItem branches;

    /**
     * The menuitem under "analysis" that shows cycles in the graph.
     */
    private MenuItem cycles;

    //Treeview-Filter
    /**
     * The checkbox that allows to filter the overview after arrow types.
     */
    private CheckBox treeViewArrowtype;

    /**
     * The menuitem under "Treeviewarrowtype" that filters the overview after reinforced edges.
     */
    private MenuItem treeViewReinforced;

    /**
     * The menuitem under "Treeviewarrowtypes" that filters the overview after extenuating edges.
     */
    private MenuItem treeViewExtenuating;

    /**
     * The menuitem under "Treeviewarrowtypes" that filters the overview after neutral edges.
     */
    private MenuItem treeViewNeutral;

    /**
     * The checkbox that allows to filter the overview after regular expressions.
     */
    private CheckBox regularExpressionBox;

    /**
     * The textfield that gets the argument to filter the overview after regular expressions.
     */
    private TextField regularExpressionField;

    /**
     * The checkbox that filters the overview after fadedout objects.
     */
    private CheckBox showFadedOutObjects;

    /* Sphere */
    /**
     * The colorpicker for changing the background color of sphere.
     */
    @FXML
    private ColorPicker sphereBackgroundColour;


    /**
     * The textfield for changing the font of the sphere text.
     */
    private TextField sphereFontField;

    /**
     * The menuitem for changing the font of the sphere text to a specific font.
     */
    private MenuItem sphereFont1;

    /**
     * The menuitem for changing the font of the sphere text to a specific font.
     */
    private MenuItem sphereFont2;

    /**
     * The menuitem for changing the font of the sphere text to a specific font.
     */
    private MenuItem sphereFont3;

    /**
     * The menuitem for changing the font of the sphere text to a specific font.
     */
    private MenuItem sphereFont4;

    /**
     * The menuitem for changing the font of the sphere text to a specific font.
     */
    private MenuItem sphereFont5;

    /**
     * The textfield for changing the size of the sphere text.
     */
    private TextField sphereSize;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize1;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize2;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize3;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize4;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize5;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize6;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize7;

    /**
     * The menuitem for changing the size of the sphere text to a specific size.
     */
    private MenuItem sphereSize8;

    /* Symptom */

    /**
     * The colorpicker for changing the background color for a symptom.
     */
    @FXML
    private ColorPicker symptomBackground;

    /**
     * The colorpicker for changing the border color for a symptom.
     */
    @FXML
    private ColorPicker symptomBorder;

    /**
     * The menuitem for changing the form of a symptom to a rectangle.
     */
    private MenuItem symptomRectangle;

    /**
     * The menuitem for changing the form of a symptom to a circle.
     */
    private MenuItem symptomCircle;

    /**
     * The menuitem for changing the form of a symptom to a ellipse.
     */
    private MenuItem symptomEllipse;

    /**
     * The textfield for changing the font of the symptom text.
     */
    private TextField symptomFontField;

    /**
     * The menuitem for changing the font of the symptom text to a specific font.
     */
    private MenuItem symptomFont1;

    /**
     * The menuitem for changing the font of the symptom text to a specific font.
     */
    private MenuItem symptomFont2;

    /**
     * The menuitem for changing the font of the symptom text to a specific font.
     */
    private MenuItem symptomFont3;

    /**
     * The menuitem for changing the font of the symptom text to a specific font.
     */
    private MenuItem symptomFont4;

    /**
     * The menuitem for changing the font of the symptom text to a specific font.
     */
    private MenuItem symptomFont5;

    /**
     * The textfield for changing the size of the symptom text.
     */
    private TextField symptomSizeField;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize1;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize2;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize3;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize4;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize5;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize6;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize7;

    /**
     * The menuitem for changing the size of the symptom text to a specific size.
     */
    private MenuItem symptomSize8;

    /* Edge */

    /**
     * The colorpicker for changing the color of edges.
     */
    private ColorPicker edgeColour;

    /**
     * The menuitem for changing the stroke type of edges to dashed.
     */
    private MenuItem edgeStrokeDashed;

    /**
     * The menuitem for changing the stroke type of edges to weighted dashed.
     */
    private MenuItem edgeStrokeDashedWeight;

    /**
     * The menuitem for changing the stroke type of edges to dotted.
     */
    private MenuItem edgeStrokeDotted;

    /**
     * The menuitem for changing the stroke type of edges to weighted dotted.
     */
    private MenuItem edgeStrokeDottedWeight;

    /**
     * The menuitem for changing the stroke type of edges to basic.
     */
    private MenuItem edgeStrokeBasic;

    /**
     * The menuitem for changing the stroke type of edges to weighted basic.
     */
    private MenuItem edgeStrokeBasicWeight;

    /**
     * The menuitem for changing the arrow type of edges to reinforced.
     */
    private MenuItem edgeArrowReinforced;

    /**
     * The menuitem for changing the arrow type of edges to extenuating.
     */
    private MenuItem edgeArrowExtenuating;

    /**
     * The menuitem for changing the arrow type of edges to neutral.
     */
    private MenuItem edgeArrowNeutral;

    /* Internal */

    /**
     * The logdao object that provides the treeview with the protocol.
     */
    private LogDao protocol;

    /**
     * The values object that gets all the arguments from the gui for the actions.
     */
    private Values values;

    /**
     * The syndrom object that is needed to change the form of spheres, symptoms and edges.
     */
    private Syndrom syndrom;

    /**
     * The action history.
     */
    private ActionHistory history;

    public Controller(){

    }

    /* ----------------ADD---------------------- */

    /**
     * Creates an AddSphereLogAction-object and executes the action with the action history.
     */
    public void addSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an AddVerticesLogAction-object and executes the action with the action history.
     */
    public void addVertex() {
        values.setGraphButtonType(GraphButtonType.ADD_VERTEX);
    }

    /* ----------------ANALYSE---------------------- */

    /**
     * Creates an AnalysisGraphAction-object and executes the action with the action history.
     */
    public void analysisGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EvaluationGraphAction-object and executes the action with the action history.
     */
    public void evaluationGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an FilterGraphAction-object and executes the action with the action history.
     */
    public void filterGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an GraphDimensionAction-object and executes the action with the action history.
     */
    public void graphDimension() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an SelectionAnalysisGraphAction-object and executes the action with the action history.
     */
    public void selectionAnalysisGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------DEACTIVATE---------------------- */

    /**
     * Creates an DeactivateFadeoutLogAction-object and executes the action with the action history.
     */
    public void deactivateFadeout() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an DeactivateHighlightLogAction-object and executes the action with the action history.
     */
    public void deactivateHighlight() {
        throw new UnsupportedOperationException();
    }

    /* ----------------EDIT---------------------- */

    /**
     * Creates an EditEdgesStrokeLogAction-object and executes the action with the action history.
     */
    public void editEdgesStroke() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EditEdgesTypeLogAction-object and executes the action with the action history.
     */
    public void editEdgesType() {
        throw new UnsupportedOperationException();
    }

    /* ......annotation..... */

    /**
     * Creates an EditSphereAnnotationLogAction-object and executes the action with the action history.
     */
    public void editSphereAnnotation() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EditVertexAnnotationLogAction-object and executes the action with the action history.
     */
    public void editVertexAnnotation() {
        throw new UnsupportedOperationException();
    }

    /* ......color..... */

    /**
     * Creates an EditEdgesColorLogAction-object and executes the action with the action history.
     */
    public void editEdgesColor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EditSphereColorLogAction-object and executes the action with the action history.
     */
    public void editSphereColor() {
        Color color = convertToAWT(sphereBackgroundColour.getValue());
        Values.getInstance().setFillPaintSphere(color);
        EditSphereColorLogAction colorLogAction = new EditSphereColorLogAction(color);
        colorLogAction.action();
        //history.execute(colorLogAction);
    }

    private Color convertToAWT(javafx.scene.paint.Color fx){
        return new java.awt.Color((float) fx.getRed(),
                (float) fx.getGreen(),
                (float) fx.getBlue(),
                (float) fx.getOpacity());
    }

    private javafx.scene.paint.Color convertFromAWT(java.awt.Color awt){
        int r = awt.getRed();
        int g = awt.getGreen();
        int b = awt.getBlue();
        int a = awt.getAlpha();
        double opacity = a / 255.0;
        return javafx.scene.paint.Color.rgb(r, g, b, opacity);
    }

    /**
     * Creates an EditVerticesDrawColorLogAction-object and executes the action with the action history.
     */
    public void editVerticesDrawColor() {
        Color color = convertToAWT(symptomBorder.getValue());
        Values.getInstance().setDrawPaintVertex(color);
        EditVerticesDrawColorLogAction colorLogAction = new EditVerticesDrawColorLogAction(color);
        colorLogAction.action();
    }

    /**
     * Creates an EditVerticesFillColorLogAction-object and executes the action with the action history.
     */
    public void editVerticesFillColor(){
        Color color = convertToAWT(symptomBackground.getValue());
        Values.getInstance().setFillPaintVertex(color);
        EditVerticesFillColorLogAction colorLogAction = new EditVerticesFillColorLogAction(color);
        colorLogAction.action();
    }

    /* ......font..... */

    /**
     * Creates an EditFontSphereLogAction-object and executes the action with the action history.
     * @param font The font as String that the Sphere text gets changed to
     */
    public void editFontSphere(String font) {
        values.setFontSphere(font);
        EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(font);
        editFontSphereLogAction.action();
    }

    public void sphereFont1(){
        editFontSphere("Times New Roman");
    }

    public void sphereFont2(){
        editFontSphere("Comic Sans Ms");
    }

    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    public void editFontVertex(String font) {
        values.setFontVertex(font);
        EditFontVerticesLogAction editFontSphereLogAction = new EditFontVerticesLogAction(font);
        editFontSphereLogAction.action();
    }

    public void vertexFont1(){
        editFontVertex("Times New Roman");
    }

    public void vertexFont2(){
        editFontVertex("Comic Sans Ms");
    }
    /**
     * Creates an EditFontSizeSphereLogAction-object and executes the action with the action history.
     * @param size The new size of the sphere text
     */
    public void editFontSizeSphere(int size){
        values.setFontSizeSphere(size);
        EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(size);
        editFontSizeSphereLogAction.action();
    }

    public void fontSize2(){
        editFontSizeSphere(14);
    }

    public void fontSize1(){
        editFontSizeSphere(13);
    }

    public void sphereAutoLayout(){
        LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction();
        layoutSphereGraphLogAction.action();
    }

    public void verticesAutoLayout(){
        LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction();
        layoutVerticesGraphLogAction.action();
    }


    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    public void editFontSizeVertices(int size){
        values.setFontSizeVertex(size);
        EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(size);
        editFontSizeVerticesLogAction.action();
    }

    public void fontSizeVertex1(){
        editFontSizeVertices(13);
    }

    public void fontSizeVertex2(){
        editFontSizeVertices(14);
    }

    /* ......form..... */

    /**
     * Creates an EditVerticesFormLogAction-object and executes the action with the action history.
     */
    public void editVerticesForm(VertexShapeType type) {
        values.setShapeVertex(type);
        EditVerticesFormLogAction editVerticesFormLogAction = new EditVerticesFormLogAction(type);
        editVerticesFormLogAction.action();
    }

    public void verticesForm1(){
        editVerticesForm(VertexShapeType.CIRCLE);
    }

    public void verticesForm2(){
        editVerticesForm(VertexShapeType.RECTANGLE);
    }

    /* ----------------EXPORT---------------------- */

    /**
     * Creates an ExportGxlAction-object and executes the action with the action history.
     */
    public void exportGXL() {
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("GXL files (*.gxl)","*.gxl");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(null);
        ExportGxlAction exportGxlAction = new ExportGxlAction(file);
        exportGxlAction.action();
    }

    /**
     * Creates an ExportPdfAction-object and executes the action with the action history.
     */
    public void exportPDF() {
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("PDF files (*.pdf)","*.pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(null);
        ExportPdfAction exportPdfAction =new ExportPdfAction(file);
        exportPdfAction.action();
    }

    /**
     * Creates an ExportOofAction-object and executes the action with the action history.
     */
    public void exportOOF() {
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("OOF files (*.oof)","*.oof");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(null);
        ExportOofAction exportOofAction = new ExportOofAction(file);
        exportOofAction.action();
    }

    /**
     * Opens the selected OOF-file after choosing it in the file chooser, creates an ImportOofAction-object
     * and executes the action with the action history.
     */
    public void openFile(){
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("OOF files (*.oof)","*.oof");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        OOFio oofio = new OOFio();
        oofio.importOOF(file);
    }

    /**
     * Opens the selected GXL-file after choosing it in the file chooser, creates an ImportGxlAction-object
     * and executes the action with the action history.
     */
    public void importGXL(){
        FileChooser fileChooser= new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("GXL files (*.gxl)","*.gxl");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(null);
        GXLio gxlio = new GXLio(file);
        gxlio.importGXL(file);
    }

    /**
     * Creates an PrintPDFAction-object and executes the action with the action history.
     */
    public void printPDF() {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        printerJob.showPrintDialog(null);
    }

    /* ----------------LAYOUT---------------------- */

    /**
     * Creates an LayoutSphereGraphLogAction-object and executes the action with the action history.
     */
    public void layoutGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------OTHER---------------------- */

    /**
     *  Calls the loadLanguages-method from the LoadLanguage class to change the language.
     */
    public void changeLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an SwitchModiEditorAction-object for changing to the editor mode
     * and executes the action with the action history.
     */
    public void switchModiEditor() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an SwitchModiEditorAction-object for changing to the analyse mode
     * and executes the action with action history.
     */
    public void switchModiAnalysis(){throw new UnsupportedOperationException();}

    /**
     * Creates an SwitchModeEditorAction-object for changing to the interpreter mode
     * and executes the action with action history.
     */
    public void switchModiInterpreter(){throw new UnsupportedOperationException();}

    /**
     * Creates an CreateGraphAction-object and executes the action with the action history.
     */
    public void createGraph(){throw new UnsupportedOperationException();}

    /**
     *  Calls the undo-method from the action history.
     */
    public void executeUndo(){throw new UnsupportedOperationException();}

    /**
     *  Calls the redo-method from the action history.
     */
    public void executeRedo(){throw new UnsupportedOperationException();}

    /**
     * Opens the documentation about the application.
     */
    public void openDocumentation(){throw new UnsupportedOperationException();}

    /**
     * Zooms in or out of the graph canvas while using the slider.
     */
    public void zoomGraphCanvas(){throw new UnsupportedOperationException();}

    /**
     *  Shows the current used action.
     */
    public void currentAction(){throw new UnsupportedOperationException();}

    /**
     *  Opens a dialog window after pressing "open file", "import gxl" or "create new graph", that asks if the user wants to
     *  export their current opened file.
     */
    private void openExportConfirmationDialogWindow(){throw new UnsupportedOperationException();}

    /**
     *  Opens a dialog window after pressing "create new graph", that allows the user to name the
     *  graph.
     */
    private void openNewGraphTextInputDialogWindow(){throw new UnsupportedOperationException();}

    /**
     *  Opens a file search window after pressing "open file" or "import gxl".
     */
    private void openSearchFileChooserWindow(){throw new UnsupportedOperationException();}

    /**
     *  Opens a directory window to save the file under the desired location after pressing "saving under..".
     */
    private void openSaveUnderChooserWindow(){throw new UnsupportedOperationException();}

    /* ----------------REMOVE---------------------- */

    /**
     * Creates an RemoveEdgesLogAction-object and executes the action with the action history.
     */
    public void removeEdges() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an RemoveSphereLogAction-object and executes the action with the action history.
     */
    public void removeSphere() {
        values.setGraphButtonType(GraphButtonType.REMOVE_SPHERE);
        RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
        removeSphereLogAction.action();
    }

    /**
     * Creates an RemoveVerticesLogAction-object and executes the action with the action history.
     */
    public void removeVertices() {
        RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
        removeVerticesLogAction.action();
    }

    /* ----------------TEMPLATE---------------------- */

    /**
     * Creates an RulesTemplateAction-object and executes the action with the action history.
     */
    public void rulesTemplate() {
        throw new UnsupportedOperationException();
    }

    /**
     * Deletes all Rules that were set before.
     */
    public void deleteTemplateRules(){throw new UnsupportedOperationException();}

    /**
     * Creates a Window that allows you to set Rules for your Template.
     */
    public void createTemplateWindow(){
        throw new UnsupportedOperationException();
    }

    /* ----------------INTERNAL---------------------- */

    /**
     * Loads the swingnodes and sets the event handlers for menuitems and color pickers.
     */
    public void initialize(){
        syndrom = Syndrom.getInstance();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        sphereBackgroundColour.setValue(convertFromAWT(Values.getInstance().getFillPaintSphere()));
        symptomBorder.setValue(convertFromAWT(Values.getInstance().getDrawPaintVertex()));
        symptomBackground.setValue(convertFromAWT(Values.getInstance().getFillPaintVertex()));
    }

    /**
     * Uses the provided swingnode to display the zoom window on it.
     * @param swingNode The swingnode, that the fxml file provides.
     */
    private void createSwingZoomWindow(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /**
     * Uses the provided swingnode to display the graph canvas on it.
     * @param swingNode The swingnode, that the fxml file provides.
     */
    private void createSwingCanvas(final SwingNode swingNode){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public void buttonClicked2(ActionEvent actionEvent) {
        CreateGraphAction action = new CreateGraphAction("First Graph");
        history.execute(action);
        canvas.setContent(syndrom.getGzsp());
    }

    public void sphereEnlarge(ActionEvent actionEvent){
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SphereSizeChange.ENLARGE);
        editSphereSizeLogAction.action();
    }

    public void sphereShrink(ActionEvent actionEvent){
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SphereSizeChange.SHRINK);
        editSphereSizeLogAction.action();
    }

    public void buttonClicked3(ActionEvent actionEvent) {
        throw new UnsupportedOperationException();
    }

    public void buttonClicked4(ActionEvent actionEvent) {
        throw  new UnsupportedOperationException();
    }

    public void buttonClicked(ActionEvent actionEvent) {
        values.setGraphButtonType(GraphButtonType.ADD_SPHERE);
    }

    /**
     * The event handler that provides the arguments, needed to use the actions after clicking on a menuitem.
     */
    private class MenuItemHandler implements EventHandler<Event>{
        @Override
        public void handle(Event evt){
        }
    }

    /**
     * The event handler that provides the arguments, needed to use the actions after choosing a colour.
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
    public void updateFunctionMode(FunctionMode mode) {
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
