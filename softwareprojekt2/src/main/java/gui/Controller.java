package gui;

import actions.ActionHistory;
import actions.ObserverSyndrom;
import actions.edit.EditEdgesStrokeLogAction;
import actions.edit.EditEdgesTypeLogAction;
import actions.edit.color.EditEdgesColorLogAction;
import actions.edit.color.EditSphereColorLogAction;
import actions.edit.color.EditVerticesDrawColorLogAction;
import actions.edit.color.EditVerticesFillColorLogAction;
import actions.edit.font.EditFontSizeSphereLogAction;
import actions.edit.font.EditFontSizeVerticesLogAction;
import actions.edit.font.EditFontSphereLogAction;
import actions.edit.font.EditFontVerticesLogAction;
import actions.edit.form.EditVerticesFormLogAction;
import actions.edit.size.EditSphereSizeLogAction;
import actions.edit.size.EditVerticesSizeLogAction;
import actions.export_graph.*;
import actions.layout.LayoutSphereGraphLogAction;
import actions.layout.LayoutVerticesGraphLogAction;
import actions.other.CreateGraphAction;
import actions.remove.RemoveSphereLogAction;
import actions.remove.RemoveVerticesLogAction;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.transform.BidirectionalTransformer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import impl.org.controlsfx.skin.AutoCompletePopup;
import io.GXLio;
import io.OOFio;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import log_management.dao.LogDao;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javax.swing.*;
import java.awt.*;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Contains most of the gui elements, calls most of the actions and acts as interface between
 * the gui and the internal components of the application.
 */
public class Controller implements ObserverSyndrom {


    /* General Stuff */

    /**
     * The swing node that displays the JUNG-graph and allows to interact with it.
     */
    @FXML
    private SwingNode canvas;

    @FXML
    private SwingNode satellite;

    /**
     * The swing node that displays the zoom window.
     */
    private SwingNode zoomWindow;

    /**
     * The tilted pane that contains the treeview object and filter options.
     */
    private TitledPane tiltedPane;

    /**
     * The treeview displays the overview of the graph that also allows the user to edit the graph
     * through it.
     */
    @FXML
    private TreeView treeView;

    /**
     * The text that displays the current action.
     */
    private Text actionText;

    /**
     * The slider that allows the user to zoom out or in.
     */
    @FXML
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

    /**
     * The button to change the gui layout to edit-mode
     */
    @FXML
    private Button editButton;

    /**
     * The button to change the gui layout to analysis-mode
     */
    @FXML
    private Button analysisButton;

    /* Template Options */

    /**
     * The button that opens the template window to set the rules
     */
    @FXML
    private Button templateButton;

    /**
     * A separator in the menu bar
     */
    @FXML
    private Separator menubarSeparator3;

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
    @FXML
    private TextField amountSymptomTextField;

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
    @FXML
    private TextField amountEdgeTextField;

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
    @FXML
    private TextField sphereSizeTextField;

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
    @FXML
    private TextField symptomSizeTextField;

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
    @FXML
    private ColorPicker edgeColour;

    /**
     * The menuitem for changing the stroke type of edges to dashed.
     */
    @FXML
    private MenuItem edgeStrokeDashed;

    /**
     * The menuitem for changing the stroke type of edges to weighted dashed.
     */
    private MenuItem edgeStrokeDashedWeight;

    /**
     * The menuitem for changing the stroke type of edges to dotted.
     */
    @FXML
    private MenuItem edgeStrokeDotted;

    /**
     * The menuitem for changing the stroke type of edges to weighted dotted.
     */
    private MenuItem edgeStrokeDottedWeight;

    /**
     * The menuitem for changing the stroke type of edges to basic.
     */
    @FXML
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
    @FXML
    private MenuItem edgeArrowExtenuating;

    /**
     * The menuitem for changing the arrow type of edges to neutral.
     */
    @FXML
    private MenuItem edgeArrowNeutral;

    @FXML
    private Text currentActionText;
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

    //Analysis GUI

    /**
     * The vbox that contains all graph information in the analysis mode.
     */
    @FXML
    private VBox vBoxGraphStats;

    /**
     * A separator for the vboxes in analysis mode.
     */
    @FXML
    private Separator separator3;

    /**
     * A separator for the vboxes in analysis mode.
     */
    @FXML
    private Separator separator4;

    /**
     * A separator for the vboxes in analysis mode.
     */
    @FXML
    private Separator separator5;

    /**
     * The vbox that contains analysis options for symptoms.
     */
    @FXML
    private VBox vBoxAnalysisSymptom;

    /**
     * The vbox that contains analysis options for edges.
     */
    @FXML
    private VBox vBoxAnalysisEdge;

    /**
     * The vbox that contains analysis options for the graph.
     */
    @FXML
    private VBox vBoxAnalysisOption;

    //Edit GUI

    /**
     * A separator for the vboxes in edit mode.
     */
    @FXML
    private Separator separator1;

    /**
     * A separator for the vboxes in edit mode.
     */
    @FXML
    private Separator separator2;

    /**
     * The vbox that contains sphere options in edit mode.
     */
    @FXML
    private VBox vBoxEditSphere;

    /**
     * The vbox that ocntains symptom options in edit mode.
     */
    @FXML
    private VBox vBoxEditSymptom;

    /**
     * The vbox that contains edge options in edit mode.
     */
    @FXML
    private VBox vBoxEditEdge;

    /**
     * Checking if gui is in edit mode
     */
    private boolean editMode = true;

    /**
     * Checking if gui is in analysis mode
     */
    private boolean analysisMode = false;

    @FXML
    private SwingNode swing;

    @FXML
    private ScrollPane paneSwingNode;

    @FXML
    private BorderPane root;

    private Stage mainStage;

    private Stage templateStage = new Stage();

    private String currentSize = "";

    @FXML
    private ComboBox sizeSphereComboBox;

    @FXML
    private ComboBox sizeSymptomComboBox;

    @FXML
    private Text prozent;


    public Controller() {
    }

    public void setStage(Stage pStage) {
        mainStage = pStage;
        templateStage.initOwner(mainStage);
        templateStage.initModality(Modality.APPLICATION_MODAL);
    }

    public Text getCurrentActionText() {
        return currentActionText;
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
    public void editEdgesStroke(StrokeType stroke) {
        EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(stroke);
        history.execute(editEdgesStrokeLogAction);
    }

    public void edgeStrokeBasic(){
        values.setStrokeEdge(StrokeType.BASIC);
        editEdgesStroke(StrokeType.BASIC);
    }

    public void edgeStrokeDotted(){
        values.setStrokeEdge(StrokeType.DOTTED_WEIGHT);
        editEdgesStroke(StrokeType.DOTTED_WEIGHT);
    }

    public void edgeStrokeDashed(){
        values.setStrokeEdge(StrokeType.DASHED_WEIGHT);
        editEdgesStroke(StrokeType.DASHED_WEIGHT);
    }

    /**
     * Creates an EditEdgesTypeLogAction-object and executes the action with the action history.
     */
    public void editEdgesType(EdgeArrowType type) {
        EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(type);
        history.execute(editEdgesTypeLogAction);
    }

    public void edgeExtenuating(){
        values.setEdgeArrowType(EdgeArrowType.EXTENUATING);
        editEdgesType(EdgeArrowType.EXTENUATING);
    }

    public void edgeNeutral(){
        values.setEdgeArrowType(EdgeArrowType.NEUTRAL);
        editEdgesType(EdgeArrowType.NEUTRAL);
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
        Color color = convertToAWT(edgeColour.getValue());
        Values.getInstance().setEdgePaint(color);
        EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(color);
        history.execute(editEdgesColorLogAction);
        System.out.println("here: "+values.getEdgePaint());
    }

    /**
     * Creates an EditSphereColorLogAction-object and executes the action with the action history.
     */
    public void editSphereColor() {
        Color color = convertToAWT(sphereBackgroundColour.getValue());
        Values.getInstance().setFillPaintSphere(color);
        EditSphereColorLogAction colorLogAction = new EditSphereColorLogAction(color);
        history.execute(colorLogAction);
    }

    private Color convertToAWT(javafx.scene.paint.Color fx) {
        return new java.awt.Color((float) fx.getRed(),
                (float) fx.getGreen(),
                (float) fx.getBlue(),
                (float) fx.getOpacity());
    }

    private javafx.scene.paint.Color convertFromAWT(java.awt.Color awt) {
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
        history.execute(colorLogAction);
    }

    /**
     * Creates an EditVerticesFillColorLogAction-object and executes the action with the action history.
     */
    public void editVerticesFillColor() {
        Color color = convertToAWT(symptomBackground.getValue());
        Values.getInstance().setFillPaintVertex(color);
        EditVerticesFillColorLogAction colorLogAction = new EditVerticesFillColorLogAction(color);
        history.execute(colorLogAction);
    }

    /* ......font..... */

    /**
     * Creates an EditFontSphereLogAction-object and executes the action with the action history.
     *
     * @param font The font as String that the Sphere text gets changed to
     */
    public void editFontSphere(String font) {
        values.setFontSphere(font);
        EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(font);
        history.execute(editFontSphereLogAction);
    }

    public void sphereFont1() {
        editFontSphere("Times New Roman");
    }

    public void sphereFont2() {
        editFontSphere("Comic Sans Ms");
    }

    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    public void editFontVertex(String font) {
        values.setFontVertex(font);
        EditFontVerticesLogAction editFontSphereLogAction = new EditFontVerticesLogAction(font);
        history.execute(editFontSphereLogAction);
    }

    public void vertexFont1() {
        editFontVertex("Times New Roman");
    }

    public void vertexFont2() {
        editFontVertex("Comic Sans Ms");
    }

    /**
     * Creates an EditFontSizeSphereLogAction-object and executes the action with the action history.
     *
     * @param size The new size of the sphere text
     */
    public void editFontSizeSphere(int size) {
        values.setFontSizeSphere(size);
        EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(size);
        history.execute(editFontSizeSphereLogAction);
    }

    public void fontSize2() {
        editFontSizeSphere(14);
    }

    public void fontSize1() {
        editFontSizeSphere(13);
    }

    public void sphereAutoLayout() {
        LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction();
        layoutSphereGraphLogAction.action();
    }

    public void verticesAutoLayout() {
        LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction();
        layoutVerticesGraphLogAction.action();
    }


    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    public void editFontSizeVertices(int size) {
        values.setFontSizeVertex(size);
        EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(size);
        history.execute(editFontSizeVerticesLogAction);
    }

    public void fontSizeVertex1() {
        editFontSizeVertices(13);
    }

    public void fontSizeVertex2() {
        editFontSizeVertices(30);
    }

    /* ......form..... */

    /**
     * Creates an EditVerticesFormLogAction-object and executes the action with the action history.
     */
    public void editVerticesForm(VertexShapeType type) {
        values.setShapeVertex(type);
        EditVerticesFormLogAction editVerticesFormLogAction = new EditVerticesFormLogAction(type);
        history.execute(editVerticesFormLogAction);
    }

    public void verticesForm1() {
        editVerticesForm(VertexShapeType.CIRCLE);
    }

    public void verticesForm2() {
        editVerticesForm(VertexShapeType.RECTANGLE);
    }

    /* ----------------EXPORT---------------------- */

    /**
     * Creates an ExportGxlAction-object and executes the action with the action history.
     */
    public void exportGXL() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("GXL files (*.gxl)", "*.gxl");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        ExportGxlAction exportGxlAction = new ExportGxlAction(file);
        exportGxlAction.action();
    }

    /**
     * Creates an ExportPdfAction-object and executes the action with the action history.
     */
    public void exportPDF() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        ExportPdfAction exportPdfAction = new ExportPdfAction(file);
        exportPdfAction.action();
    }

    /**
     * Creates an ExportOofAction-object and executes the action with the action history.
     */
    public void exportOOF() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", "*.oof");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        ExportOofAction exportOofAction = new ExportOofAction(file);
        exportOofAction.action();
    }

    /**
     * Opens the selected OOF-file after choosing it in the file chooser, creates an ImportOofAction-object
     * and executes the action with the action history.
     */
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", "*.oof");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainStage);
        ImportOofAction importOofAction = new ImportOofAction(file);
        importOofAction.action();
    }

    /**
     * Opens the selected GXL-file after choosing it in the file chooser, creates an ImportGxlAction-object
     * and executes the action with the action history.
     */
    public void importGXL() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("GXL files (*.gxl)", "*.gxl");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainStage);
        ImportGxlAction importGxlAction = new ImportGxlAction(file);
        importGxlAction.action();
    }

    /**
     * Creates an PrintPDFAction-object and executes the action with the action history.
     */
    public void printPDF() {
        PrintPDFAction printPDFAction = new PrintPDFAction();
        printPDFAction.action();
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
     * Calls the loadLanguages-method from the LoadLanguage class to change the language.
     */
    public void changeLanguage() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an SwitchModiEditorAction-object for changing to the editor mode
     * and executes the action with the action history.
     */
    public void switchModiEditor() {
        if (analysisMode) {
            analysisMode(false);
            editMode(true);
            editButton.setDisable(true);
            analysisButton.setDisable(false);
            editMode = true;
            analysisMode = false;
        }
    }

    /**
     * Creates an SwitchModiEditorAction-object for changing to the analyse mode
     * and executes the action with action history.
     */
    public void switchModiAnalysis() {
        if (editMode) {
            editMode(false);
            analysisMode(true);
            editButton.setDisable(false);
            analysisButton.setDisable(true);
            editMode = false;
            analysisMode = true;
        }
    }

    /**
     * Creates an SwitchModeEditorAction-object for changing to the interpreter mode
     * and executes the action with action history.
     */
    public void switchModiInterpreter() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an CreateGraphAction-object and executes the action with the action history.
     */
    public void createGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls the undo-method from the action history.
     */
    public void executeUndo() {
        history.undo();
    }

    /**
     * Calls the redo-method from the action history.
     */
    public void executeRedo() {
        history.redo();
    }

    /**
     * Opens the documentation about the application.
     */
    public void openDocumentation() {
        throw new UnsupportedOperationException();
    }

    /**
     * Zooms in or out of the graph canvas while using the slider.
     */
    public void zoomGraphCanvas() {
        throw new UnsupportedOperationException();
    }

    /**
     * Shows the current used action.
     */
    public void currentAction() {
        throw new UnsupportedOperationException();
    }

    /**
     *  Opens a dialog window after pressing "open file", "import gxl" or "create new graph", that asks if the user wants to
     *  export their current opened file.

     private void openExportConfirmationDialogWindow(){throw new UnsupportedOperationException();}

     /**
     *  Opens a dialog window after pressing "create new graph", that allows the user to name the
     *  graph.

     private void openNewGraphTextInputDialogWindow(){throw new UnsupportedOperationException();}

     /**
     *  Opens a file search window after pressing "open file" or "import gxl".

     private void openSearchFileChooserWindow(){throw new UnsupportedOperationException();}

     /**
     *  Opens a directory window to save the file under the desired location after pressing "saving under..".

     private void openSaveUnderChooserWindow(){throw new UnsupportedOperationException();}
     */

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
        history.execute(removeSphereLogAction);
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
    public void deleteTemplateRules() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a Window that allows you to set Rules for your Template.
     */
    public void createTemplateWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/templatedialog.fxml"));
        fxmlLoader.setController(this);
        templateStage.setResizable(false);
        templateStage.setScene(new Scene(fxmlLoader.load()));
        templateStage.setTitle("Vorlagenregeln");
        templateStage.setAlwaysOnTop(true);
        templateStage.show();
    }

    public void closeTemplateWindow() {
        if (templateStage.isShowing()) {
            templateStage.hide();
            templateStage.close();
        }
    }

    /* ----------------INTERNAL---------------------- */

    /**
     * Loads the swingnodes and sets the event handlers for menuitems and color pickers.
     */
    public void initialize() {
        syndrom = Syndrom.getInstance();
        history = ActionHistory.getInstance();
        values = Values.getInstance();
        sphereBackgroundColour.setValue(convertFromAWT(Values.getInstance().getFillPaintSphere()));
        symptomBorder.setValue(convertFromAWT(Values.getInstance().getDrawPaintVertex()));
        symptomBackground.setValue(convertFromAWT(Values.getInstance().getFillPaintVertex()));
        analysisMode(false);
        editButton.setDisable(true);

        paneSwingNode.widthProperty().addListener(widthListener);
        paneSwingNode.heightProperty().addListener(heightListener);

        loadComboBox(sizeSphereComboBox);
        loadComboBox(sizeSymptomComboBox);

        sizeSphereComboBox.getEditor().textProperty().addListener(new ComboBoxListener(sizeSphereComboBox));
        sizeSymptomComboBox.getEditor().textProperty().addListener(new ComboBoxListener(sizeSymptomComboBox));
        TextFields.bindAutoCompletion(sizeSphereComboBox.getEditor(), sizeSphereComboBox.getItems()).setPrefWidth(45);
        TextFields.bindAutoCompletion(sizeSymptomComboBox.getEditor(), sizeSymptomComboBox.getItems()).setPrefWidth(45);

        zoomSlider.setMin(10);
        zoomSlider.setMax(200);
        zoomSlider.setValue(100);
        values.setScale(100);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setBlockIncrement(20);
        zoomSlider.setMajorTickUnit(20);
        zoomSlider.setMinorTickCount(5);
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setSnapToTicks(true);
        zoomSlider.valueProperty().addListener(changeZoom);
        prozent.textProperty().bind(zoomSlider.valueProperty().asString("%.0f").concat(" %"));

        edgeColour.setValue(convertFromAWT(Values.getInstance().getEdgePaint()));
    }



    ChangeListener<Number> changeZoom = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            int value = newValue.intValue();
            int oldV = oldValue.intValue();

            SwingUtilities.invokeLater(() -> {
                if (value != 0 && oldV != value) {
                    values.setScale(value);
                    syndrom.scale(value);
                }
            });
        }
    };


    ChangeListener<Number> widthListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (canvas.getContent() != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        SyndromVisualisationViewer vv = syndrom.getVv();
                        vv.setPreferredSize(new Dimension(root.getCenter().layoutXProperty().getValue().intValue(), root.getCenter().layoutYProperty().getValue().intValue()));
                        //canvas.setContent(syndrom.getGzsp());
                    }
                });


                // GraphZoomScrollPane zoom = syndrom.getGzsp();
                // zoom.setSize(newValue.intValue(), zoom.getHeight());

                /* Update Test1 Scrollbars sind immer noch am arsch
                syndrom.getGzsp().revalidate();
                syndrom.getGzsp().repaint();
                */

                /* Update Test2 kp hat nichts gebracht
                syndrom.getGzsp().updateUI();
                */

                /* Update Test3 "Schwarze Bereiche beim Scrollen"
                canvas.setContent(syndrom.getGzsp());
                */

                /* Die Splitpane die unsere SwingNode(canvas) beinhaltet: paneSwingNode */
            }
        }
    };

    ChangeListener<Number> heightListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (canvas.getContent() != null) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        VisualizationViewer vv = syndrom.getVv();
                        Dimension old = vv.getPreferredSize();
                        old.setSize(old.getWidth(), newValue.intValue());
                        vv.setPreferredSize(old);
                    }
                });
                //    GraphZoomScrollPane zoom = syndrom.getGzsp();
                //   zoom.setSize(zoom.getWidth(), newValue.intValue());


                /* Update Test1 Scrollbars sind immer noch am arsch
                syndrom.getGzsp().revalidate();
                syndrom.getGzsp().repaint();
                */

                /* Update Test2 kp hat nichts gebracht
                syndrom.getGzsp().updateUI();
                */

                /* Update Test3 "Schwarze Bereiche beim Scrollen"
                canvas.setContent(syndrom.getGzsp());
                */
            }
        }
    };

    private class ComboBoxListener implements ChangeListener<String>{
        private final ComboBox comboBox;
        private ComboBoxListener(ComboBox pComboBox){
            this.comboBox = pComboBox;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            if(!newValue.matches("\\d*"))
                comboBox.getEditor().setText(oldValue);

            if(comboBox.getEditor().getText().length() > 3)
                comboBox.getEditor().setText(comboBox.getEditor().getText(0, 3));
        }
    }



    private class ComboBoxValueListener implements ChangeListener<String>{
        private final ComboBox comboBox;
        private ComboBoxValueListener(ComboBox pComboBox){ this.comboBox = pComboBox; }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
            currentSize = newValue;
            root.requestFocus();
            if(comboBox.getId().equals("sizeSphereComboBox")){
                editFontSizeSphere(Integer.parseInt(currentSize));
            }else if(comboBox.getId().equals("sizeSymptomComboBox")){
                editFontSizeVertices(Integer.parseInt(currentSize));
            }
        }
    }

    private class ComboBoxFocusListener implements ChangeListener<Boolean>{
        private final ComboBox comboBox;
        private ComboBoxFocusListener(ComboBox pComboBox){ this.comboBox = pComboBox; }

        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue)
                currentSize = comboBox.getEditor().getText();
            else
                comboBox.getEditor().setText(currentSize);
        }
    }

    private void loadComboBox(ComboBox comboBox) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "14",
                        "18",
                        "24",
                        "30",
                        "36",
                        "48",
                        "60",
                        "72",
                        "96"
                );
        comboBox.setItems(options);
        comboBox.getEditor().textProperty().addListener(new ComboBoxListener(comboBox));
        comboBox.focusedProperty().addListener(new ComboBoxFocusListener(comboBox));
        comboBox.getSelectionModel().selectedItemProperty().addListener(new ComboBoxValueListener(comboBox));
        AutoCompletionBinding autoComplete = TextFields.bindAutoCompletion(comboBox.getEditor(),comboBox.getItems());
        autoComplete.setPrefWidth(45);

        autoComplete.setOnAutoCompleted(new EventHandler<AutoCompletionBinding.AutoCompletionEvent<String>>(){
            @Override
            public void handle(AutoCompletionBinding.AutoCompletionEvent<String> event){
                currentSize = event.getCompletion();
                root.requestFocus();
                if(comboBox.getId().equals("sizeSphereComboBox")){
                    editFontSizeSphere(Integer.parseInt(currentSize));
                }else if(comboBox.getId().equals("sizeSymptomComboBox")){
                    editFontSizeVertices(Integer.parseInt(currentSize));
                }
            }
        });
    }

    private void analysisMode(Boolean active) {

        vBoxGraphStats.setVisible(active);
        vBoxGraphStats.setManaged(active);

        separator3.setVisible(active);
        separator3.setManaged(active);

        separator4.setVisible(active);
        separator4.setManaged(active);

        separator5.setVisible(active);
        separator5.setManaged(active);

        vBoxAnalysisSymptom.setVisible(active);
        vBoxAnalysisSymptom.setManaged(active);

        vBoxAnalysisEdge.setVisible(active);
        vBoxAnalysisEdge.setManaged(active);

        vBoxAnalysisOption.setVisible(active);
        vBoxAnalysisOption.setManaged(active);
    }

    private void editMode(Boolean active) {
        separator1.setVisible(active);
        separator1.setManaged(active);

        separator2.setVisible(active);
        separator2.setManaged(active);

        menubarSeparator3.setVisible(active);
        menubarSeparator3.setManaged(active);

        vBoxEditSphere.setVisible(active);
        vBoxEditSphere.setManaged(active);

        vBoxEditSymptom.setVisible(active);
        vBoxEditSymptom.setManaged(active);

        vBoxEditEdge.setVisible(active);
        vBoxEditEdge.setManaged(active);

        templateButton.setVisible(active);
        templateButton.setManaged(active);
    }

    /**
     * Uses the provided swingnode to display the zoom window on it.
     *
     * @param swingNode The swingnode, that the fxml file provides.
     */
    private void createSwingZoomWindow(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    /**
     * Uses the provided swingnode to display the graph canvas on it.
     *
     * @param swingNode The swingnode, that the fxml file provides.
     */
    private void createSwingCanvas(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    public void buttonClicked2(ActionEvent actionEvent) {
        //values.setDefaultLayoutSize(new Dimension(root.getCenter().layoutXProperty().intValue()-50, root.getCenter().layoutYProperty().intValue()-50));

        CreateGraphAction action = new CreateGraphAction("First Graph");
        history.execute(action);
        canvas.setContent(syndrom.getVv());
        satellite.setContent(syndrom.getVv2());

    }

    public void sphereEnlarge(ActionEvent actionEvent) {
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.ENLARGE);
        editSphereSizeLogAction.action();
    }

    public void sphereShrink(ActionEvent actionEvent) {
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.SHRINK);
        editSphereSizeLogAction.action();
    }

    public void vertexEnlarge() {
        EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.ENLARGE);
        history.execute(editVerticesSizeLogAction);
    }

    public void vertexShrink() {
        EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.SHRINK);
        history.execute(editVerticesSizeLogAction);
    }

    public void buttonClicked3(ActionEvent actionEvent) {
        throw new UnsupportedOperationException();
    }

    public void buttonClicked4(ActionEvent actionEvent) {
        throw new UnsupportedOperationException();
    }

    public void buttonClicked(ActionEvent actionEvent) {
        values.setGraphButtonType(GraphButtonType.ADD_SPHERE);
    }

    /**
     * The event handler that provides the arguments, needed to use the actions after clicking on a menuitem.
     */
    private class MenuItemHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
        }
    }

    /**
     * The event handler that provides the arguments, needed to use the actions after choosing a colour.
     */
    private class ColorPickerHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
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
