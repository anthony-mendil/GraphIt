package gui;

import actions.ActionHistory;
import actions.GraphAction;
import actions.LogEntryName;
import actions.ObserverSyndrom;
import actions.activate.ActivateAnchorPointsFadeoutAction;
import actions.activate.ActivateFadeoutAction;
import actions.activate.ActivateHighlightAction;
import actions.add.AddFadeoutElementAction;
import actions.add.AddHighlightElementAction;
import actions.analyse.*;
import actions.deactivate.DeactivateAnchorPointsFadeoutAction;
import actions.deactivate.DeactivateFadeoutAction;
import actions.deactivate.ResetVvAction;
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
import actions.export_import.*;
import actions.layout.LayoutSphereGraphLogAction;
import actions.layout.LayoutVerticesGraphLogAction;
import actions.other.ChangeGraphLanguageAction;
import actions.other.CreateGraphAction;
import actions.other.LoadGraphAction;
import actions.other.SwitchModeAction;
import actions.remove.*;
import actions.template.RulesTemplateAction;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import log_management.DatabaseManager;
import log_management.LogToStringConverter;
import log_management.dao.LogDao;
import log_management.tables.Log;
import lombok.Data;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Contains most of the gui elements, calls most of the actions and acts as interface between
 * the gui and the internal components of the application.
 */
@Data
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
    private TreeView<Object> treeView;

    /**
     * The text that displays the current action.
     */
    private Text actionText;

    /**
     * The slider that allows the user to zoom out or in.
     */
    @FXML
    private Slider zoomSlider;

    @FXML
    private Menu fileMenu;

    /* Menu Bar */
    /**
     * The menuitem under the menu "File.." for creating a new fileMenu.
     */
    @FXML
    private MenuItem newFile;

    /**
     * The menuitem under the menu "File.." for opening a fileMenu.
     */
    @FXML
    private MenuItem openFile;

    /**
     * The menuitem under the menu "File.." for importing a GXL fileMenu.
     */
    @FXML
    private MenuItem importGXL;

    /**
     * The menuitem under the menu "File.." for saving under a specified location.
     */
    @FXML
    private MenuItem saveLocation;

    /**
     * The menuitem under the menu "File.. &gt; Export as.." for exporting the fileMenu as pdf.
     */
    @FXML
    private MenuItem exportPDF;
    @FXML
    private MenuItem exportGXLWithTemplate;
    @FXML
    private MenuItem exportLogs;

    /**
     * The menuitem under the menu "File.." for printing the fileMenu.
     */
    @FXML
    private MenuItem print;
    @FXML
    private Menu options;
    @FXML
    private Menu languages;
    @FXML
    private Menu help;

    @FXML
    private Menu languagesGraph;
    @FXML
    private CheckMenuItem languageGraphGerman;
    @FXML
    private CheckMenuItem languageGraphEnglish;
    /**
     * The menuitem under the menu "Options &gt; Language" for changing the language of the gui to german.
     */
    @FXML
    private CheckMenuItem languageGerman;

    /**
     * The menuitem under the menu "Options &gt; Language" for changing the language of the gui to english.
     */
    @FXML
    private CheckMenuItem languageEnglish;

    /**
     * The menuitem under the menu "Help" for opening the documention.
     */
    @FXML
    private MenuItem documentation;
    @FXML
    private MenuItem about;

    /**
     * The button to undo an action.
     */
    @FXML
    private Button undoButton;

    /**
     * The button to redo an action.
     */
    @FXML
    private Button redoButton;

    /**
     * The separator between redo/undo and edit/analysis mode
     */
    @FXML
    private Separator toolBarSeparator1;

    /**
     * The button to change the gui layout to create-mode
     */
    @FXML
    private Button createButton;

    /**
     * The button to change the gui layout to analysis-mode
     */
    @FXML
    private Button analysisButton;

    /**
     * The button to change the gui layout to edit-mode
     */
    @FXML
    private Button editButton;

    /* Template Options */

    /**
     * The textfield for setting the template rule "maximum numbers of spheres in the graph".
     */
    @FXML
    private TextField maxSphereField;

    /**
     * The textfield for setting the template rule "maximum numbers of symptoms in the graph".
     */
    @FXML
    private TextField maxSymptomField;

    /**
     * The textfield for setting the template rule "maximum numbers of edges in the graph".
     */
    @FXML
    private TextField maxEdgesField;

    /**
     * The checkbox for setting the template rule "allowing reinforced edges".
     */
    @FXML
    private CheckBox reinforcedBox;

    /**
     * The checkbox for setting the template rule "allowing extenuating edges".
     */
    @FXML
    private CheckBox extenuatingBox;

    /**
     * The checkbox for setting the template rule "allowing neutral edges".
     */
    @FXML
    private CheckBox neutralBox;

    /**
     * The textfield that sets the amount of predecessor/successor symptoms in the analysis/interpreter mode.
     */
    @FXML
    private TextField amountSymptomTextField;

    /**
     * The menuitem under "analysis" that shows convergent and divergent branches.
     */
    @FXML
    private MenuItem branches;

    /**
     * The menuitem under "analysis" that shows cycles in the graph.
     */
    @FXML
    private MenuItem cycles;

    /**
     * The checkbox that allows to filter the overview after arrow types.
     */
    @FXML
    private CheckBox treeViewArrowType;

    /**
     * The checkbox that allows to filter the overview after regular expressions.
     */
    @FXML
    private CheckBox regularExpressionBox;

    /**
     * The textfield that gets the argument to filter the overview after regular expressions.
     */
    @FXML
    private TextField regularExpressionField;

    /**
     * Nina
     * The checkbox that filters the overview after fadedout objects.
     */
    @FXML
    private CheckBox showFadedOutObjects;

    /* Sphere */
    /**
     * The colorpicker for changing the background color of sphere.
     */
    @FXML
    private ColorPicker sphereBackgroundColour;

    /**
     * The combobox for changing the font of the symptom text.
     */
    @FXML
    private ComboBox<String> fontSphereComboBox;

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
     * The menubutton for changing the form of a symptom.
     */
    @FXML
    private MenuButton sphereFormMenuButton;

    /**
     * The menuitem for changing the form of a symptom to a circle.
     */
    @FXML
    private MenuItem symptomCircle;

    /**
     * The menuitem for changing the form of a symptom to a rectangle.
     */
    @FXML
    private MenuItem symptomRectangle;

    /**
     * The combobox for changing the font of the symptom text.
     */
    @FXML
    private ComboBox<String> fontSymptomComboBox;

    /**
     * The colorpicker for changing the color of edges.
     */
    @FXML
    private ColorPicker edgeColour;

    /**
     * The menubutton for changing the stroke type of edges.
     */
    @FXML
    private MenuButton edgeStrokeMenuButton;

    /**
     * The menuitem for changing the stroke type to the dashed stroke type.
     */
    @FXML
    private MenuItem edgeStrokeDashed;

    /**
     * The menuitem for changing the stroke type to the dashed weighted stroke type.
     */
    @FXML
    private MenuItem edgeStrokeDashedWeight;

    /**
     * The menuitem for changing the stroke type to the dotted stroke type.
     */
    @FXML
    private MenuItem edgeStrokeDotted;

    /**
     * The menuitem for changing the stroke type to the dotted weighted stroke type.
     */
    @FXML
    private MenuItem edgeStrokeDottedWeight;

    /**
     * The menuitem for changing the stroke type to the basic stroke type.
     */
    @FXML
    private MenuItem edgeStrokeBasic;

    /**
     * The menuitem for changing the stroke type to the basic weighted stroke type.
     */
    @FXML
    private MenuItem edgeStrokeBasicWeight;

    /**
     * The menubutton for changing the arrow type of edges.
     */
    @FXML
    private MenuButton edgeArrowMenuButton;

    /**
     * The menuitem for changing the arrow type of edges to reinforced.
     */
    @FXML
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
    private LogDao logDao = new LogDao();

    private LogToStringConverter logToStringConverter = new LogToStringConverter();

    /**
     * The values object that gets all the arguments from the gui for the actions.
     */
    private Values values = Values.getInstance();

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
     * The vbox that contains analysis options for symptoms.
     */
    @FXML
    private VBox vBoxAnalysisSymptom;

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
    private Separator separator0;

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
     * The vbox that contains the select button in edit mode.
     */
    @FXML
    private VBox vBoxSelect;

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

    @FXML
    private SwingNode swing;

    @FXML
    private ScrollPane paneSwingNode;

    @FXML
    private BorderPane root;

    private Stage mainStage;

    private String currentSize = "" + values.getDefaultSizeVertex();
    private String currentFont = values.getFontSphere();


    /**
     * The combobox for changing the size of the sphere text.
     */
    @FXML
    private ComboBox<String> sizeSphereComboBox;

    /**
     * The combobox for changing the size of the symptom text.
     */
    @FXML
    private ComboBox<String> sizeSymptomComboBox;

    private static final String SIZE_SPHERE_COMBO_BOX = "sizeSphereComboBox";
    private static final String SIZE_SYMPTOM_COMBO_BOX = "sizeSymptomComboBox";
    private static final String FONT_SYMPTOM_COMBO_BOX = "fontSymptomComboBox";
    private static final String FONT_SPHERE_COMBO_BOX = "fontSphereComboBox";
    @FXML
    private Menu prozent;
    @FXML
    private HBox textBox;
    @FXML
    private ButtonBar currentActionBox;
    @FXML
    private ToggleButton anchorPointsButton;
    @FXML
    private ToggleButton highlight;
    @FXML
    private Button highlightElements;
    @FXML
    private Button dehighlightElements;
    @FXML
    private Button fadeoutElements;
    @FXML
    private Button deleteFadeoutElements;
    @FXML
    private ToggleButton fadeout;
    @FXML
    private Text selection;
    @FXML
    private Text selectionSphere;
    @FXML
    private Button handVertex;
    @FXML
    private Button addSphere;
    @FXML
    private Button deleteSphere;
    @FXML
    private Button sphereEnlarge;
    @FXML
    private Button sphereShrink;
    @FXML
    private Button sphereAutoLayout;
    @FXML
    private Text selectionSymptom;
    @FXML
    private Button addVertex;
    @FXML
    private Button deleteVertex;
    @FXML
    private Button vertexEnlarge;
    @FXML
    private Button vertexShrink;
    @FXML
    private Button verticesAutoLayout;
    @FXML
    private Text selectionEdge;
    @FXML
    private Button removeEdges;
    @FXML
    private Button removeAnchor;
    @FXML
    private Text analysisGraphInfo;
    @FXML
    private Text analysisScope;
    @FXML
    private Text analysisScopeNumber;
    @FXML
    private Text analysisNetworkingIndex;
    @FXML
    private Text analysisNetworkingIndexNumber;
    @FXML
    private Text analysisStructureIndex;
    @FXML
    private Text analysisStructureIndexNumber;
    @FXML
    private Text analysisSymptom;
    @FXML
    private CheckBox analysisPredecessor;
    @FXML
    private CheckBox analysisSuccessor;
    @FXML
    private Text analysisSymptomAmount;
    @FXML
    private CheckBox analysisPathCheckBox;
    @FXML
    private MenuButton analysisPathMenuButton;
    @FXML
    private MenuItem analysisShortestPath;
    @FXML
    private MenuItem analysisAllPaths;
    @FXML
    private CheckBox filterArrowTypeCheckBox;
    @FXML
    private MenuButton analysisArrowMenuButton;
    @FXML
    private Text analysisOption;
    @FXML
    private CheckBox analysisOptions;
    @FXML
    private MenuItem chainOfEdges;
    @FXML
    private MenuItem convergent;
    @FXML
    private MenuItem divergent;
    @FXML
    private Text templateMaxSphere;
    @FXML
    private Text templateMaxSymptom;
    @FXML
    private Text templateMaxEdge;
    @FXML
    private Text templateChoose;
    @FXML
    private TitledPane templateSpheres;
    @FXML
    private TitledPane templateSymptom;
    @FXML
    private TitledPane templateEdge;
    @FXML
    private MenuButton filterAnalysis;
    @FXML
    private Accordion overViewAccordion;
    @FXML
    private TitledPane overViewTitledPane;
    @FXML
    private TitledPane templateTitledPane;
    @FXML
    private TitledPane historyTitledPane;
    @FXML
    private TableView<Sphere> sphereTableView;
    @FXML
    private TableColumn sphereCol;
    @FXML
    private TableColumn<Sphere, Boolean> titleSphereCol;
    @FXML
    private TableColumn<Sphere, Boolean> positionSphereCol;
    @FXML
    private TableColumn<Sphere, Boolean> styleSphereCol;
    @FXML
    private TableColumn<Sphere, Boolean> verticesSphereCol;
    /**
     * The tablecolumn for setting the template rule "maximum numbers of symptoms in the sphere".
     */
    @FXML
    private TableColumn<Sphere, String> maxAmountSphereCol;
    @FXML
    private TableView<Vertex> symptomTableView;
    @FXML
    private TableColumn symptomCol;
    @FXML
    private TableColumn<Vertex, Boolean> titleSymptomCol;
    @FXML
    private TableColumn<Vertex, Boolean> positionSymptomCol;
    @FXML
    private TableColumn<Vertex, Boolean> styleSymptomCol;
    @FXML
    private TableView<Edge> edgeTableView;
    @FXML
    private TableColumn<Edge, String> edgeCol;
    @FXML
    private TableColumn<Edge, Boolean> positionEdgeCol;
    @FXML
    private TableColumn<Edge, Boolean> styleEdgeCol;
    @FXML
    private TableColumn<Edge, Boolean> edgetypeEdgeCol;
    @FXML
    private MenuItem zoomMenuItem25;
    @FXML
    private MenuItem zoomMenuItem50;
    @FXML
    private MenuItem zoomMenuItem75;
    @FXML
    private MenuItem zoomMenuItem100;
    @FXML
    private MenuItem zoomMenuItem125;
    @FXML
    private MenuItem zoomMenuItem150;
    @FXML
    private MenuItem zoomMenuItem175;
    @FXML
    private MenuItem zoomMenuItem200;
    @FXML
    private StackPane overviewStackPane;
    @FXML
    private MenuButton filterEdgeType;
    @FXML
    private MenuItem filterEdgeTypeReinforced;
    @FXML
    private MenuItem filterEdgeTypeExtenuating;
    @FXML
    private MenuItem filterEdgeTypeNeutral;
    @FXML
    private ResourceBundle resources;
    @FXML
    private TreeView<Object> protocol;
    @FXML
    private CheckBox filterProtocol;
    @FXML
    private MenuItem logAddSphere;
    @FXML
    private MenuItem logAddVertex;
    @FXML
    private MenuItem logAddEdge;
    @FXML
    private MenuButton filterLogType;
    @FXML
    private MenuItem logEditFontVertices;
    @FXML
    private MenuItem logDeactivateFadeout;
    @FXML
    private MenuItem logEditSphereColor;
    @FXML
    private MenuItem logEditEdgesStroke;
    @FXML
    private MenuItem logEditSphereSize;
    @FXML
    private MenuItem logEditFontSphere;
    @FXML
    private MenuItem logEditEdgesColor;
    @FXML
    private MenuItem logRemoveVertices;
    @FXML
    private MenuItem logEditEdgesType;
    @FXML
    private MenuItem logRemoveSphere;
    @FXML
    private MenuItem logMoveVertices;
    @FXML
    private MenuItem logMoveSphere;
    @FXML
    private MenuItem logActivateAnchorPointsFadeout;
    @FXML
    private MenuItem logAddAnchorPoints;
    @FXML
    private MenuItem logActivateFadeout;
    @FXML
    private MenuItem logActivateHighlight;
    @FXML
    private MenuItem logEditVerticesForm;
    @FXML
    private MenuItem logRemoveEdges;
    @FXML
    private MenuItem logEditVerticesSize;
    @FXML
    private MenuItem logRemoveAnchorPoints;
    @FXML
    private MenuItem logEditSphereFontSize;
    @FXML
    private MenuItem logEditSphereAnnotation;
    @FXML
    private MenuItem logEditVertexAnnotation;
    @FXML
    private MenuItem logEditVerticesFontSize;
    @FXML
    private MenuItem logEditVerticesDrawColor;
    @FXML
    private MenuItem logEditVerticesFillColor;
    @FXML
    private MenuItem logDeactivateHighlight;
    @FXML
    private MenuItem logDeactivateAnchorPointsFadeout;
    @FXML
    private MenuItem logEditSpheresLayout;
    @FXML
    private MenuItem logEditVerticesLayout;
    @FXML
    private MenuItem logAll;
    @FXML private Label infoAnalysis;
    @FXML private Label infoZoom;
    private Tooltip tooltipInfoAnalysis = new Tooltip();
    private Tooltip tooltipInfoZoom = new Tooltip();
    @FXML
    private Text positionMouseX;
    @FXML
    private Text positionMouseY;
    private static final String SPHERE_TITLE = "SphereTitle";
    private static final String SPHERE_POSITION = "SpherePosition";
    private static final String SPHERE_STYLE = "SphereStyle";
    private static final String SPHERE_VERTICES = "SphereVertices";
    private static final String VERTEX_TITLE = "SymptomTitle";
    private static final String VERTEX_POSITION = "SymptomPosition";
    private static final String VERTEX_STYLE = "SymptomStyle";
    private static final String VERTEX_VERTICES = "VertexVertices";
    private static final String EDGE_TITLE = "EdgeTitle";
    private static final String EDGE_POSITION = "EdgePosition";
    private static final String EDGE_STYLE = "EdgeStyle";
    private static final String EDGE_VERTICES = "EdgeVertices";
    private static final String EDGE_EDGE_TYPE = "EDGE_EDGE_TYPE";
    private static final String GXL = "*.gxl";
    private static final String PDF = "*.pdf";
    private static final String OOF = "*.oof";
    private static final String TXT = "*.txt";
    private static final String GXL_FILE = "GXL files (*.gxl)";
    private static final String TIMES_NEW_ROMAN = "Times New Roman";
    private static final String COMIC_SANS_MS = "Comic Sans Ms";

    private ObservableList<String> fonts =
            FXCollections.observableArrayList(
                    "AveriaSansLibre",
                    "Kalam",
                    "Mali",
                    "Roboto",
                    "RobotoSlab"
            );
    private ObservableList<String> sizes =
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

    private static Logger logger = Logger.getLogger(Controller.class);

    private EdgeArrowType filterEdgeArrowType = EdgeArrowType.REINFORCED;
    private LogEntryName analysisLogEntryName = null;
    private LoadLanguage loadLanguage;

    public void filterEdgeTypeReinforced() {
        filterEdgeArrowType = EdgeArrowType.REINFORCED;
    }

    public void filterEdgeTypeExtenuating() {
        filterEdgeArrowType = EdgeArrowType.EXTENUATING;
    }

    public void filterEdgeTypeNeutral() {
        filterEdgeArrowType = EdgeArrowType.NEUTRAL;
    }

    @SuppressWarnings("unused")
    public Text getCurrentActionText() {
        return currentActionText;
    }

    /* ----------------ADD---------------------- */

    /**
     * Creates an AddSphereLogAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void addSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an AddVerticesLogAction-object and executes the action with the action history.
     */
    public void addVertex() {
        values.setGraphButtonType(GraphButtonType.ADD_VERTEX);
    }

    public void handVertex() {
        values.setGraphButtonType(GraphButtonType.NONE);
    }

    /* ----------------ANALYSE---------------------- */

    /**
     * Creates an AnalysisGraphAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void analysisGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EvaluationGraphAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void evaluationGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an FilterGraphAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void filterGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an GraphDimensionAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void graphDimension() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an SelectionAnalysisGraphAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void selectionAnalysisGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------DEACTIVATE---------------------- */

    /**
     * Creates an DeactivateFadeoutAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void deactivateFadeout() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an ResetVvAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void deactivateHighlight() {
        throw new UnsupportedOperationException();
    }

    /* ----------------EDIT---------------------- */

    /**
     * Creates an EditEdgesStrokeLogAction-object and executes the action with the action history.
     */
    private void editEdgesStroke(StrokeType stroke) {
        EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(stroke);
        history.execute(editEdgesStrokeLogAction);
    }

    public void edgeStrokeBasic() {
        values.setStrokeEdge(StrokeType.BASIC);
        editEdgesStroke(StrokeType.BASIC);
    }

    public void edgeStrokeBasicWeighted() {
        values.setStrokeEdge(StrokeType.BASIC_WEIGHT);
        editEdgesStroke(StrokeType.BASIC_WEIGHT);
    }

    public void edgeStrokeDotted() {
        values.setStrokeEdge(StrokeType.DOTTED);
        editEdgesStroke(StrokeType.DOTTED);
    }

    public void edgeStrokeDottedWeighted() {
        values.setStrokeEdge(StrokeType.DOTTED_WEIGHT);
        editEdgesStroke(StrokeType.DOTTED_WEIGHT);
    }

    public void edgeStrokeDashed() {
        values.setStrokeEdge(StrokeType.DASHED);
        editEdgesStroke(StrokeType.DASHED);
    }

    public void edgeStrokeDashedWeighted() {
        values.setStrokeEdge(StrokeType.DASHED_WEIGHT);
        editEdgesStroke(StrokeType.DASHED_WEIGHT);
    }

    /**
     * Creates an EditEdgesTypeLogAction-object and executes the action with the action history.
     */
    private void editEdgesType(EdgeArrowType type) {
        EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(type);
        history.execute(editEdgesTypeLogAction);
    }

    public void edgeReinforced() {
        values.setEdgeArrowType(EdgeArrowType.REINFORCED);
        editEdgesType(EdgeArrowType.REINFORCED);
    }

    public void edgeExtenuating() {
        values.setEdgeArrowType(EdgeArrowType.EXTENUATING);
        editEdgesType(EdgeArrowType.EXTENUATING);
    }

    public void edgeNeutral() {
        values.setEdgeArrowType(EdgeArrowType.NEUTRAL);
        editEdgesType(EdgeArrowType.NEUTRAL);
    }

    /* ......regex..... */

    /**
     * Creates an EditSphereAnnotationLogAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void editSphereAnnotation() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates an EditVertexAnnotationLogAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
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
    }

    public void anchorPointsEdge() {
        if (anchorPointsButton.isSelected()) {
            DeactivateAnchorPointsFadeoutAction deactivateAnchorPointsFadeoutAction = new DeactivateAnchorPointsFadeoutAction();
            deactivateAnchorPointsFadeoutAction.action();
        } else {
            ActivateAnchorPointsFadeoutAction activateAnchorPointsFadeoutAction = new ActivateAnchorPointsFadeoutAction();
            activateAnchorPointsFadeoutAction.action();
        }
    }

    public void removeAnchor() {
        RemoveAnchorPointsLogAction removeAnchorPointsLogAction = new RemoveAnchorPointsLogAction();
        removeAnchorPointsLogAction.action();
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
    private void editFontSphere(String font) {
        values.setFontSphere(font);
        EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(font);
        history.execute(editFontSphereLogAction);
    }

    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    private void editFontVertex(String font) {
        values.setFontVertex(font);
        EditFontVerticesLogAction editFontVertexLogAction = new EditFontVerticesLogAction(font);
        history.execute(editFontVertexLogAction);
    }

    public void sphereAutoLayout() {
        LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction();
        history.execute(layoutSphereGraphLogAction);
    }

    public void verticesAutoLayout() {
        LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction();
        history.execute(layoutVerticesGraphLogAction);
    }


    /**
     * Creates an EditFontVerticesLogAction-object and executes the action with the action history.
     */
    @SuppressWarnings("unused")
    public void editFontSizeVertices(int size) {
        values.setFontSizeVertex(size);
        EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(size);
        history.execute(editFontSizeVerticesLogAction);
    }

    /* ......form..... */

    /**
     * Creates an EditVerticesFormLogAction-object and executes the action with the action history.
     */
    private void editVerticesForm(VertexShapeType type) {
        values.setShapeVertex(type);
        EditVerticesFormLogAction editVerticesFormLogAction = new EditVerticesFormLogAction(type);
        history.execute(editVerticesFormLogAction);
    }

    public void verticesCircle() {
        editVerticesForm(VertexShapeType.CIRCLE);
    }

    public void verticesRectangle() {
        editVerticesForm(VertexShapeType.RECTANGLE);
    }

    /* ----------------EXPORT---------------------- */

    /**
     * Creates an ExportGxlAction-object and executes the action with the action history.
     */
    public void exportGXL() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            ExportGxlAction exportGxlAction = new ExportGxlAction(file);
            exportGxlAction.action();
        }
    }

    /**
     * Creates an ExportTemplateGxlAction-object and executes the action with the action history.
     */
    public void exportGXLWithTemplate() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            ExportTemplateGxlAction exportTemplateGxlAction = new ExportTemplateGxlAction(file);
            exportTemplateGxlAction.action();
        }

    }

    /**
     * Creates an ExportPdfAction-object and executes the action with the action history.
     */
    public void exportPDF() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", PDF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File exportDialog = fileChooser.showSaveDialog(mainStage);
        if (exportDialog != null) {
            ExportPdfAction exportPdfAction = new ExportPdfAction(exportDialog);
            exportPdfAction.action();
        }
    }

    public void exportProtocol() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file (*.txt)", TXT);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File exportDialog = fileChooser.showSaveDialog(mainStage);
        if (exportDialog != null) {
            ExportReadableProtocolAction exportReadableProtocolAction = new ExportReadableProtocolAction(exportDialog);
            exportReadableProtocolAction.action();
        }
    }

    /**
     * Creates an ExportOofAction-object and executes the action with the action history.
     */
    public void exportOOF() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", OOF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            ExportOofAction exportOofAction = new ExportOofAction(file);
            exportOofAction.action();
        }
    }

    /**
     * Opens the selected OOF-fileMenu after choosing it in the fileMenu chooser, creates an ImportOofAction-object
     * and executes the action with the action history.
     */
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", OOF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File dialog = fileChooser.showOpenDialog(mainStage);
        if (dialog != null) {
            ImportOofAction importOofAction = new ImportOofAction(dialog);
            importOofAction.action();
            zoomSlider.setValue(100);
            canvas.setContent(syndrom.getVv());
            satellite.setContent(syndrom.getVv2());
        }
        templateToFields();
    }

    /**
     * Opens the selected GXL-fileMenu after choosing it in the fileMenu chooser, creates an ImportGxlAction-object
     * and executes the action with the action history.
     */
    public void importGXL() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            ImportGxlAction importGxlAction = new ImportGxlAction(file);
            importGxlAction.action();
            zoomSlider.setValue(100);
            canvas.setContent(syndrom.getVv());
            satellite.setContent(syndrom.getVv2());
        }
    }

    /**
     * Opens the selected GXL-fileMenu after choosing it in the fileMenu chooser, creates an ImportGxlAction-object
     * and executes the action with the action history.
     */
    public void importGXLWithTemplate() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            ImportTemplateGxlAction importTemplateGxlAction = new ImportTemplateGxlAction(file);
            importTemplateGxlAction.action();
            zoomSlider.setValue(100);
            canvas.setContent(syndrom.getVv());
            satellite.setContent(syndrom.getVv2());
        }
        templateToFields();
    }

    /**
     * Sets the Template values into the fields (usage: importing template)
     */
    private void templateToFields() {
        Template currentTemplate = syndrom.getTemplate();
        if (currentTemplate.getMaxSpheres() != Integer.MAX_VALUE) {
            maxSphereField.setText("" + currentTemplate.getMaxSpheres());
        } else {
            maxSphereField.setText("");
        }
        if (currentTemplate.getMaxVertices() != Integer.MAX_VALUE) {
            maxSymptomField.setText("" + currentTemplate.getMaxVertices());
        } else {
            maxSymptomField.setText("");
        }
        if (currentTemplate.getMaxEdges() != Integer.MAX_VALUE) {
            maxEdgesField.setText("" + currentTemplate.getMaxEdges());
        } else {
            maxEdgesField.setText("");
        }
        reinforcedBox.setSelected(currentTemplate.isReinforcedEdgesAllowed());
        extenuatingBox.setSelected(currentTemplate.isExtenuatingEdgesAllowed());
        neutralBox.setSelected(currentTemplate.isNeutralEdgesAllowed());
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
    @SuppressWarnings("unused")
    public void layoutGraph() {
        throw new UnsupportedOperationException();
    }

    /* ----------------OTHER---------------------- */

    /**
     * Creates an SwitchModeAction-object for changing to the editor mode
     * and executes the action with the action history.
     */
    public void switchModeCreator() {
        analysisMode(false);
        createOrEditMode(false, true);
        createOrEditMode(true, false);
        editButton.setDisable(false);
        analysisButton.setDisable(false);
        createButton.setDisable(true);
        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.TEMPLATE);
        switchModeAction.action();
    }

    /**
     * Creates an SwitchModeAction-object for changing to the analyse mode
     * and executes the action with action history.
     */
    public void switchModiAnalysis() {
        values.setGraphButtonType(GraphButtonType.NONE);
        createOrEditMode(false, false);
        createOrEditMode(false, true);
        analysisMode(true);
        createButton.setDisable(false);
        editButton.setDisable(false);
        analysisButton.setDisable(true);

        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();


        analysisScopeNumber.setText("" + graphDimensionAction.getScope());
        analysisNetworkingIndexNumber.setText("" + graphDimensionAction.getNetworkIndex());
        analysisStructureIndexNumber.setText("" + graphDimensionAction.getStructureIndex());

        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.ANALYSE);
        switchModeAction.action();
    }

    /**
     * Creates an SwitchModeEditorAction-object for changing to the interpreter mode
     * and executes the action with action history.
     */
    public void switchModeEdit() {
        values.setGraphButtonType(GraphButtonType.NONE);
        analysisMode(false);
        createOrEditMode(false, false);
        createOrEditMode(true, true);
        createButton.setDisable(false);
        analysisButton.setDisable(false);
        editButton.setDisable(true);

        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.EDIT);
        switchModeAction.action();
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
    @SuppressWarnings("unused")
    public void openDocumentation() {
        throw new UnsupportedOperationException();
    }

    /**
     * Zooms in or out of the graph canvas while using the slider.
     */
    @SuppressWarnings("unused")
    public void zoomGraphCanvas() {
        throw new UnsupportedOperationException();
    }

    /**
     * Shows the current used action.
     */
    @SuppressWarnings("unused")
    public void currentAction() {
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog window after pressing "open fileMenu", "import gxl" or "create new graph", that asks if the user wants to
     * export their current opened fileMenu.
     */
    @SuppressWarnings("unused")
    private void openExportConfirmationDialogWindow() {
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog window after pressing "create new graph", that allows the user to name the
     * graph.
     */
    @SuppressWarnings("unused")
    private void openNewGraphTextInputDialogWindow() {
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a fileMenu search window after pressing "open fileMenu" or "import gxl".
     */
    @SuppressWarnings("unused")
    private void openSearchFileChooserWindow() {
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a directory window to save the fileMenu under the desired location after pressing "saving under..".
     */
    @SuppressWarnings("unused")
    private void openSaveUnderChooserWindow() {
        throw new UnsupportedOperationException();
    }


    /* ----------------REMOVE---------------------- */

    /**
     * Creates an RemoveEdgesLogAction-object and executes the action with the action history.
     */
    public void removeEdges() {
        RemoveEdgesLogAction removeEdgesLogAction = new RemoveEdgesLogAction();
        history.execute(removeEdgesLogAction);
    }

    /**
     * Creates an RemoveSphereLogAction-object and executes the action with the action history.
     */
    public void removeSphere() {
        RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
        history.execute(removeSphereLogAction);
    }

    /**
     * Creates an RemoveVerticesLogAction-object and executes the action with the action history.
     */
    public void removeVertices() {
        RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
        history.execute(removeVerticesLogAction);

    }

    /* ----------------TEMPLATE---------------------- */

    /**
     * Creates an RulesTemplateAction-object and executes the action with the action history.
     */
    private void rulesTemplate() {
        int mSph = valueFromTextField(maxSphereField);
        int mSym = valueFromTextField(maxSymptomField);
        int mEdg = valueFromTextField(maxEdgesField);
        boolean rein = reinforcedBox.isSelected();
        boolean ext = extenuatingBox.isSelected();
        boolean neut = neutralBox.isSelected();

        Template temp = new Template(mSph, mSym, mEdg, rein, ext, neut);

        RulesTemplateAction rulesTemplateAction = new RulesTemplateAction(temp);
        rulesTemplateAction.action();
    }

    /**
     * Gets a TextField and returns its numeric content
     *
     * @param pTextField The TextField that contains the count
     * @return -1 if there is no number set, -2 if the input is not valid, the number otherwise
     */
    private int getValidatedContent(TextField pTextField) {
        String content = pTextField.getText().trim();
        try {
            return Integer.parseInt(content);
        } catch (NumberFormatException e) {
            if (content.isEmpty()) {
                return -1;
            } else {
                return -2;
            }
        }
    }

    /**
     * Gets a TextField and returns its numeric content if its valid
     *
     * @param pTextField The Textfield that contains the count
     * @return The number if it's valid, Integer.Max_Value otherwise
     */
    private int valueFromTextField(TextField pTextField) {
        int ret = Integer.MAX_VALUE;
        int cont = getValidatedContent(pTextField);
        if (cont == -1) {
            pTextField.setStyle("-fx-background-color: white");
        } else if (cont == -2) {
            pTextField.setStyle("-fx-background-color: rgba(255,0,0,0.25)");
        } else {
            pTextField.setStyle("-fx-background-color: white");
            ret = cont;
        }
        return ret;
    }

    /**
     * Deletes all Rules that were set before.
     */
    @SuppressWarnings("unused")
    public void deleteTemplateRules() {
        throw new UnsupportedOperationException();
    }

    public void highlight() {
        if (highlight.isSelected()) {
            ActivateHighlightAction activateHighlightAction = new ActivateHighlightAction();
            activateHighlightAction.action();
        } else {
            ResetVvAction resetVvAction = new ResetVvAction();
            resetVvAction.action();
        }
    }

    public void highlightElements() {
        AddHighlightElementAction addHighlightElementAction = new AddHighlightElementAction();
        addHighlightElementAction.action();
    }

    public void deHighlightElements() {
        RemoveHighlightElementAction removeHighlightElementAction = new RemoveHighlightElementAction();
        removeHighlightElementAction.action();
    }

    public void fadeout() {
        if (!fadeout.isSelected()) {
            DeactivateFadeoutAction deactivateFadeoutAction = new DeactivateFadeoutAction();
            deactivateFadeoutAction.action();
        } else {
            ActivateFadeoutAction activateFadeoutAction = new ActivateFadeoutAction();
            activateFadeoutAction.action();
        }
    }

    public void fadeoutElements() {
        AddFadeoutElementAction addFadeoutElementAction = new AddFadeoutElementAction();
        addFadeoutElementAction.action();
    }

    public void deFadeoutElements() {
        RemoveFadeoutElementAction removeFadeoutElementAction = new RemoveFadeoutElementAction();
        removeFadeoutElementAction.action();
    }

    /* ----------------INTERNAL---------------------- */

    /**
     * Loads the swingnodes and sets the event handlers for menuitems and color pickers.
     */
    public void initialize() {
        initFonts();
        rulesTemplate();

        syndrom = Syndrom.getInstance();
        history = ActionHistory.getInstance();

        values.setCanvas(canvas);
        values.setHBox(textBox);
        values.setCurrentActionText(currentActionText);
        values.setPositionMouseX(positionMouseX);
        values.setPositionMouseY(positionMouseY);
        //nina

        values.setMode(FunctionMode.TEMPLATE);


        sphereBackgroundColour.setValue(convertFromAWT(Values.getInstance().getFillPaintSphere()));
        symptomBorder.setValue(convertFromAWT(Values.getInstance().getDrawPaintVertex()));
        symptomBackground.setValue(convertFromAWT(Values.getInstance().getFillPaintVertex()));
        analysisMode(false);
        createButton.setDisable(true);
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        overViewAccordion.setExpandedPane(overViewTitledPane);

        paneSwingNode.widthProperty().addListener(widthListener);
        paneSwingNode.heightProperty().addListener(heightListener);
        paneSwingNode.focusedProperty().addListener((observable, oldValue, newValue) -> root.requestFocus());

        loadSizeComboBox(sizeSphereComboBox);
        loadSizeComboBox(sizeSymptomComboBox);
        loadMenuItem();
        loadFontComboBox(fontSphereComboBox);
        loadFontComboBox(fontSymptomComboBox);
        loadTemplateTextFields();
        loadTemplateCheckBox();
        loadAnalysisElements();

        zoomSlider.setMin(20);
        zoomSlider.setMax(200);
        zoomSlider.setValue(100);
        zoomSlider.setBlockIncrement(20);
        zoomSlider.setMajorTickUnit(20);
        zoomSlider.setMinorTickCount(5);
        zoomSlider.setSnapToTicks(true);
        zoomSlider.valueProperty().addListener(changeZoom);
        prozent.textProperty().bind(zoomSlider.valueProperty().asString("%.0f").concat(" %"));

        setZoomMenu();

        edgeColour.setValue(convertFromAWT(Values.getInstance().getEdgePaint()));
        textBox.prefHeightProperty().bind(currentActionBox.prefHeightProperty());

        OneTimeStackPaneListener onetime = new OneTimeStackPaneListener();
        overviewStackPane.widthProperty().addListener(onetime);

        DatabaseManager databaseManager = DatabaseManager.getInstance();


        GraphAction action = databaseManager.databaseEmpty() ? new CreateGraphAction("New Graph", this) : new LoadGraphAction(this);


        action.action();
        canvas.setContent(syndrom.getVv());
        satellite.setContent(syndrom.getVv2());
        zoomSlider.setValue(100);
        templateToFields();

        initTree();

        regularExpressionField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilterGraphAction filterGraphAction = new FilterGraphAction(newValue, regularExpressionBox.isSelected());
            filterGraphAction.action();
        });

        initLanguage();
        initProtocolTree();
        initGraphLanguage();
        initInfoText();


    }

    private void initInfoText() {
        infoText(tooltipInfoAnalysis, "INFO_ANALYSIS", infoAnalysis, 15, 0);
        infoText(tooltipInfoZoom, "INFO_ZOOM", infoZoom, 15, -20);
    }

    private void infoText(Tooltip tooltip, String text, Label label, int x, int y){
        tooltip.setPrefWidth(200);
        tooltip.setText(loadLanguage.loadLanguagesKey(text));
        tooltip.setWrapText(true);
        tooltip.setAutoFix(false);
        tooltip.setAutoHide(false);
        tooltip.setStyle("-fx-background-color:\n" +
                "            #000000,\n" +
                "            linear-gradient(#7ebcea, #2f4b8f),\n" +
                "            linear-gradient(#426ab7, #263e75),\n" +
                "            linear-gradient(#395cab, #223768);\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;");

        label.setOnMouseMoved(event -> tooltip.show(label, label.localToScene(label.getBoundsInLocal()).getMaxX() + x, label.localToScene(label.getBoundsInLocal()).getMaxY() + y));
        label.setOnMouseExited(event -> tooltip.hide());
    }

    public void initButtonShortcuts() {
        KeyCombination plus = new KeyCodeCombination(KeyCode.PLUS);
        KeyCombination minus = new KeyCodeCombination(KeyCode.MINUS);
        KeyCombination strgZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        KeyCombination strgY = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
        KeyCombination strgD = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        KeyCombination strgA = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
        KeyCombination one = new KeyCodeCombination(KeyCode.DIGIT1);
        KeyCombination two = new KeyCodeCombination(KeyCode.DIGIT2);
        KeyCombination three = new KeyCodeCombination(KeyCode.DIGIT3);
        KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);

        mainStage.getScene().setOnKeyPressed((KeyEvent event) -> {
            if (plus.match(event)) {
                sphereEnlarge();
                vertexEnlarge();
            } else if (minus.match(event)) {
                sphereShrink();
                vertexShrink();
            } else if (strgZ.match(event)) {
                executeUndo();
            } else if (strgY.match(event)) {
                executeRedo();
            } else if (strgD.match(event)) {
                removeEdges();
                removeSphere();
                removeVertices();

            } else if (strgA.match(event)) {
                for (Vertex v : syndrom.getLayout().getGraph().getVertices()) {
                    syndrom.getVv().getPickedVertexState().pick(v, true);
                }
                for (Edge e : syndrom.getLayout().getGraph().getEdges()) {
                    syndrom.getVv().getPickedEdgeState().pick(e, true);
                }
            } else if (one.match(event)) {
                switchModeCreator();
            } else if (two.match(event)) {
                switchModiAnalysis();
            } else if (three.match(event)) {
                switchModeEdit();
            } else if (esc.match(event)) {
                syndrom.getVv().getPickedSphereState().clear();
                syndrom.getVv().getPickedVertexState().clear();
                syndrom.getVv().getPickedEdgeState().clear();
                handVertex();
            }
        });
    }

    public void setStage(Stage pStage) {
        mainStage = pStage;

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                optionExitWindow();
            }
        });
    }

    private void initGraphLanguage() {
        languageGraphEnglish.selectedProperty().addListener(new LanguageGraphListener(languageGraphEnglish, this));
        languageGraphGerman.selectedProperty().addListener(new LanguageGraphListener(languageGraphGerman, this));
        languageGraphEnglish.setSelected(false);
        languageGraphGerman.setSelected(true);
    }

    public static final Comparator<MenuItem> menuItemCompare = Comparator.comparing(MenuItem::getText);

    private void sortFilterLogs() {
        ArrayList<MenuItem> f = new ArrayList<>(filterLogType.getItems());
        f.sort(menuItemCompare);
        filterLogType.getItems().removeAll(f);

        Platform.runLater(() -> {
            for (MenuItem item : f) {
                filterLogType.getItems().add(item);
            }

        });
    }

    private void initProtocolTree() {
        sortFilterLogs();

        for (MenuItem item : filterLogType.getItems()) {
            item.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterLogType));
        }


        historyTitledPane.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            filterLogs(analysisLogEntryName);
        });
    }

    private void initLanguage() {
        loadLanguage = LoadLanguage.getInstance();
        languageEnglish.selectedProperty().addListener(new LanguageListener(languageEnglish, this));
        languageGerman.selectedProperty().addListener(new LanguageListener(languageGerman, this));
        languageEnglish.setSelected(false);
        languageGerman.setSelected(true);
    }

    private void initTree() {
        HelperFunctions helper = new HelperFunctions();
        treeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<Object>>) (observable, oldValue, newValue) -> {
            if (newValue != null) {
                helper.pickElement(((TreeItem<Object>) newValue).getValue());
            }
        });


        treeView.setOnMouseClicked(e -> {
            if (Values.getInstance().getMode() != FunctionMode.ANALYSE) {
                if (e.getButton() == MouseButton.SECONDARY) {
                    Node node = e.getPickResult().getIntersectedNode();
                    if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                        TreeItem<Object> selected = treeView.getSelectionModel().getSelectedItem();
                        Object val = selected.getValue();

                        ContextMenu contextMenu = helper.openContextMenu(val);
                        if (contextMenu != null) {
                            treeView.setContextMenu(contextMenu);
                            contextMenu.show(treeView, e.getScreenX(), e.getScreenY());
                        }
                    } else {
                        treeView.setContextMenu(null);
                    }
                } else if (treeView.getContextMenu() != null) {
                    treeView.getContextMenu().hide();
                }
            }
        });
    }

    private void initFonts() {
        try {
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/regular/Roboto-Regular.ttf"));
            values.setRoboto(roboto);
            Font robotoSlab = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/regular/RobotoSlab-Regular.ttf"));
            values.setRobotoSlab(robotoSlab);
            Font averiaSansLibre = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/regular/AveriaSansLibre-Regular.ttf"));
            values.setAveriaSansLibr(averiaSansLibre);
            Font kalam = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/regular/Kalam-Regular.ttf"));
            values.setKalam(kalam);
            Font mali = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/regular/Mali-Regular.ttf"));
            values.setMali(mali);

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }


    ChangeListener<Number> changeZoom = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            if (zoomSlider.isValueChanging()) {
                int value = newValue.intValue();
                int oldV = oldValue.intValue();

                SwingUtilities.invokeLater(() -> {
                    if (value != 0 && oldV != value) {
                        values.setScale(value);
                        syndrom.scale(value);
                    }
                });
            }

        }
    };


    ChangeListener<Number> widthListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (canvas.getContent() != null) {
                SwingUtilities.invokeLater(() -> {
                    SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
                    vv.setPreferredSize(new Dimension(root.getCenter().layoutXProperty().getValue().intValue(), root.getCenter().layoutYProperty().getValue().intValue()));
                });
            }
        }
    };

    ChangeListener<Number> heightListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (canvas.getContent() != null) {
                SwingUtilities.invokeLater(() -> {
                    VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
                    Dimension old = vv.getPreferredSize();
                    old.setSize(old.getWidth(), newValue.intValue());
                    vv.setPreferredSize(old);
                });
            }
        }
    };

    private class OnlyNumberComboBoxListener implements ChangeListener<String> {
        private final ComboBox<String> comboBox;

        private OnlyNumberComboBoxListener(ComboBox<String> pComboBox) {
            this.comboBox = pComboBox;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*"))
                comboBox.getEditor().setText(oldValue);

            if (comboBox.getEditor().getText().length() > 3)
                comboBox.getEditor().setText(comboBox.getEditor().getText(0, 3));
        }
    }

    private class OnlyLettersSpacesComboBoxListener implements ChangeListener<String> {
        private final ComboBox<String> comboBox;

        private OnlyLettersSpacesComboBoxListener(ComboBox<String> pComboBox) {
            this.comboBox = pComboBox;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("[a-zA-Z ]*"))
                comboBox.getEditor().setText(oldValue);
        }
    }

    public void editFontSizeSphere(int size) {
        values.setFontSizeSphere(size);
        EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(size);
        history.execute(editFontSizeSphereLogAction);
    }

    /*private class ComboBoxValueListener implements ChangeListener<String> {
        private final ComboBox<String> comboBox;

        private ComboBoxValueListener(ComboBox<String> pComboBox) {
            this.comboBox = pComboBox;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)) {
                if (newValue.chars().allMatch(Character::isDigit) && !newValue.isEmpty()) {
                    currentSize = newValue;
                    editFontSizeSphere(Integer.parseInt(currentSize));
                }
            } else if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)) {
                if (fonts.contains(newValue)) {
                    currentFont = newValue;
                    editFontSphere(currentFont);
                }
            } else if (comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
                if (newValue.chars().allMatch(Character::isDigit) && !newValue.isEmpty()) {
                    currentSize = newValue;
                    editFontSizeVertices(Integer.parseInt(currentSize));
                }
            } else if (comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                if (fonts.contains(newValue)) {
                    currentFont = newValue;
                    editFontVertex(currentFont);
                }
            }
            root.requestFocus();
        }
    }*/



    private class ComboBoxFocusListener implements ChangeListener<Boolean> {
        private final ComboBox<String> comboBox;

        private ComboBoxFocusListener(ComboBox<String> pComboBox) {
            this.comboBox = pComboBox;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
            if (newPropertyValue) {
                comboBox.show();
                if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX) || comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
                    currentSize = comboBox.getEditor().getText();
                } else if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX) || comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                    currentFont = comboBox.getEditor().getText();
                }
            } else {
                if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX) || comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
                    comboBox.getEditor().setText(currentSize);
                } else if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX) || comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                    comboBox.getEditor().setText(currentFont);
                }
            }
        }
    }


    private void loadMenuItem() {
        symptomCircle.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(sphereFormMenuButton));
        symptomRectangle.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(sphereFormMenuButton));
        edgeStrokeBasic.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeStrokeBasicWeight.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeStrokeDashed.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeStrokeDashedWeight.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeStrokeDotted.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeStrokeDottedWeight.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeStrokeMenuButton));
        edgeArrowReinforced.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeArrowMenuButton));
        edgeArrowExtenuating.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeArrowMenuButton));
        edgeArrowNeutral.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(edgeArrowMenuButton));

        filterEdgeTypeReinforced.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(filterEdgeType));
        filterEdgeTypeExtenuating.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(filterEdgeType));
        filterEdgeTypeNeutral.addEventHandler(ActionEvent.ACTION, new MenuItemHandler(filterEdgeType));
        filterEdgeTypeReinforced.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(EdgeArrowType.REINFORCED));
        filterEdgeTypeExtenuating.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(EdgeArrowType.EXTENUATING));
        filterEdgeTypeNeutral.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(EdgeArrowType.NEUTRAL));

        for (MenuItem item : filterLogType.getItems()) {
            item.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterLogType));
        }

        logAddSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ADD_SPHERE));
        logAddVertex.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ADD_VERTICES));
        logAddEdge.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ADD_EDGES));
        logEditFontVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_FONT_VERTICES));
        logDeactivateFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.DEACTIVATE_FADEOUT));
        logEditSphereColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_SPHERE_COLOR));
        logEditEdgesStroke.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_EDGES_STROKE));
        logEditSphereSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_SPHERE_SIZE));
        logEditFontSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_FONT_SPHERE));
        logEditEdgesColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_EDGES_COLOR));
        logRemoveVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.REMOVE_VERTICES));
        logEditEdgesType.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_EDGES_TYPE));
        logRemoveSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.REMOVE_SPHERE));
        logMoveVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.MOVE_VERTICES));
        logMoveSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.MOVE_SPHERE));
        logActivateAnchorPointsFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT));
        logAddAnchorPoints.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ADD_ANCHOR_POINTS));
        logActivateFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ACTIVATE_FADEOUT));
        logActivateHighlight.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.ACTIVATE_HIGHLIGHT));
        logEditVerticesForm.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_FORM));
        logRemoveEdges.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.REMOVE_EDGES));
        logEditVerticesSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_SIZE));
        logRemoveAnchorPoints.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.REMOVE_ANCHOR_POINTS));
        logEditSphereFontSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_SPHERE_FONT_SIZE));
        logEditSphereAnnotation.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_SPHERE_ANNOTATION));
        logEditVertexAnnotation.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTEX_ANNOTATION));
        logEditVerticesFontSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_FONT_SIZE));
        logEditVerticesDrawColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_DRAW_COLOR));
        logEditVerticesFillColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_FILL_COLOR));
        logDeactivateHighlight.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.DEACTIVATE_HIGHLIGHT));
        logDeactivateAnchorPointsFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT));
        logEditSpheresLayout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_SPHERES_LAYOUT));
        logEditVerticesLayout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(LogEntryName.EDIT_VERTICES_LAYOUT));
        logAll.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(null));
    }

    public void loadFontComboBox(ComboBox<String> comboBox) {

        if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)) {
            comboBox.getEditor().setText(values.getFontSphere());
            currentFont = values.getFontSphere();
        } else if (comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
            comboBox.getEditor().setText(values.getFontVertex());
            currentFont = values.getFontVertex();
        }
        loadFonts(comboBox);
        comboBox.focusedProperty().addListener(new ComboBoxFocusListener(comboBox));
        comboBox.getEditor().textProperty().addListener(new OnlyLettersSpacesComboBoxListener(comboBox));
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            comboBox.hide();
            if(event.getCode() == KeyCode.ENTER){
                String tmpFont = comboBox.getEditor().getText();
                if(fonts.contains(tmpFont)){
                    if(comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)){
                        currentFont = tmpFont;
                        editFontSphere(tmpFont);
                    }else if(comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)){
                        currentFont = tmpFont;
                        editFontVertex(tmpFont);
                    }
                }
                root.requestFocus();
            }
        });
    }


    private void loadSizeComboBox(ComboBox<String> comboBox) {
        if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)) {
            comboBox.getEditor().setText("" + values.getFontSizeSphere());
            currentSize = "" + values.getFontSizeSphere();
        } else if (comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
            comboBox.getEditor().setText("" + values.getFontSizeVertex());
            currentSize = "" + values.getFontSizeVertex();
        }

        loadSizes(comboBox);
        comboBox.getEditor().textProperty().addListener(new OnlyNumberComboBoxListener(comboBox));
        comboBox.focusedProperty().addListener(new ComboBoxFocusListener(comboBox));
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            comboBox.hide();
            if(event.getCode() == KeyCode.ENTER){
                String tmpSize = comboBox.getEditor().getText();
                if(tmpSize.chars().allMatch(Character::isDigit)){
                    int size = Integer.parseInt(tmpSize);
                    if(size > 3 && size <= 96){
                        if(comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)){
                            currentSize = tmpSize;
                            editFontSizeSphere(size);
                        }else if(comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)){
                            currentSize = tmpSize;
                            editFontSizeVertices(size);
                        }
                    }
                }
                root.requestFocus();
            }
        });
    }

    private void loadFonts(ComboBox comboBox) {

        comboBox.setCellFactory(lv -> {
            ListCell<MenuItem> cell = new ListCell<MenuItem>() {
                @Override
                protected void updateItem(MenuItem item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getText());
                }
            };

            cell.setOnMousePressed(e -> {
                if (!cell.isEmpty()) {
                    currentFont = cell.getItem().getText();
                    comboBox.getEditor().setText(currentFont);
                    if(comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)){
                        editFontSphere(currentFont);
                    }else if(comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)){
                        editFontVertex(currentFont);
                    }
                    root.requestFocus();
                }
            });
            return cell ;
        });

        ObservableList<MenuItem> fontMenuItems = FXCollections.observableArrayList();
        for (String font : fonts) {
            MenuItem fontMenuItem = new MenuItem(font);
            fontMenuItems.add(fontMenuItem);
        }
        comboBox.setItems(fontMenuItems);
    }

    private void loadSizes(ComboBox comboBox) {

        comboBox.setCellFactory(lv -> {
            ListCell<MenuItem> cell = new ListCell<MenuItem>(){
                @Override
                protected void updateItem(MenuItem item, boolean empty){
                    super.updateItem(item, empty);
                    setText(empty ? null : item.getText());
                }
            };

            cell.setOnMousePressed(e -> {
                if(!cell.isEmpty()){
                    currentSize = cell.getItem().getText();
                    int size = Integer.parseInt(currentSize);
                    comboBox.getEditor().setText(currentSize);
                    if(comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)){
                        editFontSizeSphere(size);
                    }else if(comboBox.getId().equals((SIZE_SYMPTOM_COMBO_BOX))){
                        editFontSizeVertices(size);
                    }
                    root.requestFocus();
                }
            });
            return cell;
        });

        ObservableList<MenuItem> sizeMenuItems = FXCollections.observableArrayList();
        for (String size : sizes) {
            MenuItem sizeMenuItem = new MenuItem(size);
            sizeMenuItems.add(sizeMenuItem);
        }
        comboBox.setItems(sizeMenuItems);
    }

    /**
     * The event handler that replace the images visible in the menubutton to the latest selected image.
     */
    private class MenuItemHandler implements EventHandler<ActionEvent> {

        private final MenuButton menuButton;

        MenuItemHandler(MenuButton pMenuButton) {
            menuButton = pMenuButton;
        }

        @Override
        public void handle(ActionEvent evt) {
            MenuItem mnItm = (MenuItem) evt.getSource();
            ImageView newImage = (ImageView) mnItm.getGraphic();
            ImageView currentImage = (ImageView) menuButton.getGraphic();
            currentImage.setImage(newImage.getImage());
        }
    }

    private class AnalysisItemHandler implements EventHandler<ActionEvent> {

        private final MenuButton menuButton;

        AnalysisItemHandler(MenuButton pMenuButton) {
            menuButton = pMenuButton;
        }

        @Override
        public void handle(ActionEvent evt) {
            MenuItem mnItm = (MenuItem) evt.getSource();
            String newText = mnItm.getText();
            menuButton.setText(newText);
        }
    }

    private class LanguageListener implements ChangeListener<Boolean> {
        private CheckMenuItem checkMenuItem;
        private Controller controller;

        private void changeLanguage(Language language) {
            loadLanguage.changeLanguage(language);
            loadLanguage.changeStringsLanguage(controller);
            values.setGuiLanguage(language);
            treeViewUpdate();
            sortFilterLogs();
        }

        LanguageListener(CheckMenuItem checkMenuItem, Controller controller) {
            this.checkMenuItem = checkMenuItem;
            this.controller = controller;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (checkMenuItem.getId().equals("languageGerman") && newValue) {
                languageEnglish.setSelected(false);
                changeLanguage(Language.GERMAN);
            } else if (checkMenuItem.getId().equals("languageEnglish") && newValue) {
                languageGerman.setSelected(false);
                changeLanguage(Language.ENGLISH);
            }
        }
    }

    private class LanguageGraphListener implements ChangeListener<Boolean> {
        private CheckMenuItem checkMenuItem;

        private void changeLanguage(Language language) {
            values.setGraphLanguage(language);
            ChangeGraphLanguageAction changeGraphLanguageAction = new ChangeGraphLanguageAction();
            changeGraphLanguageAction.action();
        }

        LanguageGraphListener(CheckMenuItem checkMenuItem, Controller controller) {
            this.checkMenuItem = checkMenuItem;
        }

        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (checkMenuItem.getId().equals("languageGraphGerman") && newValue) {
                languageGraphEnglish.setSelected(false);
                changeLanguage(Language.GERMAN);
            } else if (checkMenuItem.getId().equals("languageGraphEnglish") && newValue) {
                languageGraphGerman.setSelected(false);
                changeLanguage(Language.ENGLISH);
            }
        }
    }

    private class FilterTypeHandler implements EventHandler<ActionEvent> {
        private final EdgeArrowType type;

        FilterTypeHandler(EdgeArrowType type) {
            this.type = type;
        }

        @Override
        public void handle(ActionEvent evt) {
            FilterGraphAction filterGraphAction = new FilterGraphAction(type, treeViewArrowType.isSelected());
            filterGraphAction.action();
        }
    }

    private class AnalysisTypeHandler implements EventHandler<ActionEvent> {
        private final LogEntryName type;

        AnalysisTypeHandler(LogEntryName type) {
            this.type = type;
        }

        @Override
        public void handle(ActionEvent evt) {
            filterLogs(type);
        }
    }

    private void analysisMode(Boolean active) {

        vBoxGraphStats.setVisible(active);
        vBoxGraphStats.setManaged(active);

        separator3.setVisible(active);
        separator3.setManaged(active);

        separator4.setVisible(active);
        separator4.setManaged(active);

        vBoxAnalysisSymptom.setVisible(active);
        vBoxAnalysisSymptom.setManaged(active);


        vBoxAnalysisOption.setVisible(active);
        vBoxAnalysisOption.setManaged(active);

        if (active) {
            overViewAccordion.getPanes().add(historyTitledPane);
        } else {
            overViewAccordion.getPanes().remove(historyTitledPane);
            analysisPredecessor.setSelected(false);
            analysisSuccessor.setSelected(false);
            analysisPathCheckBox.setSelected(false);
            analysisOptions.setSelected(false);
            filterArrowTypeCheckBox.setSelected(false);
        }
    }

    private void createOrEditMode(Boolean active, Boolean editMode) {

        separator0.setVisible(active);
        separator0.setManaged(active);

        separator1.setVisible(active);
        separator1.setManaged(active);

        separator2.setVisible(active);
        separator2.setManaged(active);

        vBoxSelect.setVisible(active);
        vBoxSelect.setManaged(active);

        vBoxEditSphere.setVisible(active);
        vBoxEditSphere.setManaged(active);

        vBoxEditSymptom.setVisible(active);
        vBoxEditSymptom.setManaged(active);

        vBoxEditEdge.setVisible(active);
        vBoxEditEdge.setManaged(active);

        redoButton.setDisable(!active);
        undoButton.setDisable(!active);

        if (editMode) {
            if (active) {
                overViewAccordion.getPanes().add(historyTitledPane);
            } else {
                overViewAccordion.getPanes().remove(historyTitledPane);
            }
        } else {
            if (active) {
                overViewAccordion.getPanes().add(templateTitledPane);
            } else {
                overViewAccordion.getPanes().remove(templateTitledPane);
            }
        }
    }

    @SuppressWarnings("unused")
    private void disableEditMode(Boolean disable) {
        separator1.setDisable(disable);
        separator2.setDisable(disable);
        vBoxEditSphere.setDisable(disable);
        vBoxEditSymptom.setDisable(disable);
        vBoxEditEdge.setDisable(disable);
        redoButton.setDisable(disable);
        undoButton.setDisable(disable);
        toolBarSeparator1.setDisable(disable);
        zoomSlider.setDisable(disable);
        overViewAccordion.setDisable(disable);
    }

    @SuppressWarnings("unused")
    private void disableAnalysisMode(Boolean disable) {
        vBoxGraphStats.setDisable(disable);
        separator3.setDisable(disable);
        separator4.setDisable(disable);
        vBoxAnalysisSymptom.setDisable(disable);
        vBoxAnalysisOption.setDisable(disable);
    }

    @SuppressWarnings("unused")
    private void optionExitWindow() {
        ButtonType ok = new ButtonType(loadLanguage.loadLanguagesKey("EXIT_WINDOW_CLOSE"), ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType(loadLanguage.loadLanguagesKey("EXIT_WINDOW_CANCEL"), ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, loadLanguage.loadLanguagesKey("EXIT_WINDOW_QUESTION"), ok, close);
        alert.setTitle("GraphIt");
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/GraphItLogo.png"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                // ... user chose "One"
                logger.debug("SCHLIEßEN");
                System.exit(0);
                Platform.exit();
            } else {
                // ... user chose CANCEL or closed the dialog
                logger.debug("CANCEL");
            }
        }
    }

    public void openInfoDialogCreateGraph() {
        openDialogInfo(newFile);
    }

    public void openInfoDialogPDF() {
        openDialogInfo(exportPDF);
    }

    public void openInfoDialogImportGXL() {
        openDialogInfo(importGXL);
    }

    public void openInfoDialogOpenFile() {
        openDialogInfo(openFile);
    }

    public void openInfoDialogPrint() {
        openDialogInfo(print);
    }

    private void openDialogInfo(MenuItem menuItem) {
        String okText = "EXIT_WINDOW_CLOSE_PDF";
        String cancel = "EXIT_WINDOW_CANCEL_PDF";
        String info = "";
        if (menuItem.getId().equals(importGXL.getId())) {
            info = "INFO_DIALOG_IMPORT";
        } else if (menuItem.getId().equals(openFile.getId())) {
            info = "INFO_DIALOG_OPEN";
        } else if (menuItem.getId().equals(print.getId())) {
            info = "PRINT_EXPORT_INFO_DIALOG";
        } else if (menuItem.getId().equals(newFile.getId())) {
            info = "INFO_DIALOG_NEW_FILE";
        } else if (menuItem.getId().equals(exportPDF.getId())) {
            info = "PDF_EXPORT_INFO_DIALOG";
        }

        ButtonType ok = new ButtonType(loadLanguage.loadLanguagesKey(okText), ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType(loadLanguage.loadLanguagesKey(cancel), ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, loadLanguage.loadLanguagesKey(info), ok, close);
        alert.setTitle("GraphIt");
        alert.setHeaderText(null);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/GraphItLogo.png"));

        Platform.runLater(() -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                if (menuItem.getId().equals(importGXL.getId())) {
                    importGXL();
                } else if (menuItem.getId().equals(openFile.getId())) {
                    openFile();
                } else if (menuItem.getId().equals(print.getId())) {
                    printPDF();
                } else if (menuItem.getId().equals(newFile.getId())) {
                    createGraph();
                } else if (menuItem.getId().equals(exportPDF.getId())) {
                    exportPDF();
                }
            }
        });
    }


    /**
     * SPRACHE ÄNDERN, AUCH IN SPHERE.TOSTRING(), VERTEX.TOSTRING() BEACHTEN
     */
    //@SuppressWarnings("unchecked")
    public void treeViewUpdate() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> spheres = graph.getSpheres();

        TreeItem<Object> rootItem = new TreeItem<>();

        for (Sphere sphere : spheres) {
            TreeItem<Object> sphereItem = new TreeItem<>(sphere);
            for (Vertex vertex : sphere.getVertices()) {
                if (syndrom.getVv().getRenderContext().getVertexIncludePredicate().evaluate(Context.getInstance(syndrom.getVv().getGraphLayout().getGraph(), vertex))) {
                    TreeItem<Object> vertexItem = new TreeItem<>(vertex);
                    for (Edge edge : graph.getOutEdges(vertex)) {
                        if (syndrom.getVv().getRenderContext().getEdgeIncludePredicate().evaluate(Context.getInstance(syndrom.getVv().getGraphLayout().getGraph(), edge))) {
                            TreeItem<Object> edgeItem = new TreeItem<>(edge);
                            vertexItem.getChildren().add(edgeItem);
                        }
                    }
                    sphereItem.getChildren().add(vertexItem);
                }
            }
            rootItem.getChildren().add(sphereItem);
        }

        rootItem.setExpanded(true);
        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);
    }


    /*
     * Uses the provided swingnode to display the zoom window on it.
     *
     * @param swingNode The swingnode, that the fxml fileMenu provides.

    private void createSwingZoomWindow(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
    */

    /*
     * Uses the provided swingnode to display the graph canvas on it.
     *
     * @param swingNode The swingnode, that the fxml fileMenu provides.

    private void createSwingCanvas(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }
    */

    public void createGraph() {
        //values.setDefaultLayoutSize(new Dimension(root.getCenter().layoutXProperty().intValue()-50, root.getCenter().layoutYProperty().intValue()-50));

        //optionSaveWindow();
        CreateGraphAction action = new CreateGraphAction("First Graph", this);
        action.action();
        canvas.setContent(syndrom.getVv());
        satellite.setContent(syndrom.getVv2());
        zoomSlider.setValue(100);
    }

    public void sphereEnlarge() {
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.ENLARGE);
        history.execute(editSphereSizeLogAction);
    }

    public void sphereShrink() {
        EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.SHRINK);
        history.execute(editSphereSizeLogAction);
    }

    public void vertexEnlarge() {
        EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.ENLARGE);
        history.execute(editVerticesSizeLogAction);
    }

    public void vertexShrink() {
        EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.SHRINK);
        history.execute(editVerticesSizeLogAction);
    }

    public void addSphereButton() {
        values.setGraphButtonType(GraphButtonType.ADD_SPHERE);
    }

    /*
     * The event handler that provides the arguments, needed to use the actions after choosing a colour.

    private class ColorPickerHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
        }
    }
     */


    public void loadTables() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> spheres = graph.getSpheres();
        Collection<Vertex> vertices = graph.getVertices();
        Collection<Edge> edges = graph.getEdges();

        if (!spheres.isEmpty()) {
            loadSpheresTable(spheres);
        }

        if (!vertices.isEmpty()) {
            loadVerticesTable(vertices);
        }

        if (!edges.isEmpty()) {
            loadEdgesTable(edges);
        }
    }

    private void loadSpheresTable(List<Sphere> spheres) {
        sphereCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Sphere, Map<String, String>>, ObservableValue<String>>) data -> {
            String name = "";
            if (values.getGuiLanguage() == Language.GERMAN) {
                name = data.getValue().getAnnotation().get(Language.GERMAN.name());
            } else if (values.getGuiLanguage() == Language.ENGLISH) {
                name = data.getValue().getAnnotation().get(Language.ENGLISH.name());
            }
            return new ReadOnlyStringWrapper(name);
        });

        // ==== MAX AMOUNT (TEXT FIELD)
        maxAmountSphereCol.setCellValueFactory(new PropertyValueFactory<>("lockedMaxAmountVertices"));
        maxAmountSphereCol.setCellFactory(TextFieldTableCell.forTableColumn());
        maxAmountSphereCol.setMinWidth(200);

        //On Cell edit commit (for MaxAmount column)
        maxAmountSphereCol.setOnEditCommit((CellEditEvent<Sphere, String> event) -> {
            String maxAmount = event.getNewValue();
            TablePosition<Sphere, String> pos = event.getTablePosition();
            int row = pos.getRow();
            Sphere sphere = event.getTableView().getItems().get(row);

            if (maxAmount.chars().allMatch(Character::isDigit)) {
                sphere.setLockedMaxAmountVertices(maxAmount);
            }
            sphereTableView.getColumns().remove(maxAmountSphereCol);
            sphereTableView.getColumns().add(maxAmountSphereCol);
        });


        setSphereRadioButtonTableColumn(titleSphereCol, SPHERE_TITLE);
        setSphereRadioButtonTableColumn(positionSphereCol, SPHERE_POSITION);
        setSphereRadioButtonTableColumn(styleSphereCol, SPHERE_STYLE);
        setSphereRadioButtonTableColumn(verticesSphereCol, SPHERE_VERTICES);

        sphereTableView.setItems(FXCollections.observableArrayList(spheres));
    }

    private void setSphereRadioButtonTableColumn(TableColumn<Sphere, Boolean> pTableColumn, String pLocked) {
        pTableColumn.setCellValueFactory(param -> {
            Sphere sphere = param.getValue();
            SimpleBooleanProperty booleanProp;
            switch (pLocked) {
                case SPHERE_TITLE:
                    booleanProp = new SimpleBooleanProperty(sphere.isLockedAnnotation());
                    break;
                case SPHERE_POSITION:
                    booleanProp = new SimpleBooleanProperty(sphere.isLockedPosition());
                    break;
                case SPHERE_STYLE:
                    booleanProp = new SimpleBooleanProperty(sphere.isLockedStyle());
                    break;
                case SPHERE_VERTICES:
                    booleanProp = new SimpleBooleanProperty(sphere.isLockedVertices());
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            //When "Boolean" column change
            booleanProp.addListener((observable, oldValue, newValue) -> {
                switch (pLocked) {
                    case SPHERE_TITLE:
                        sphere.setLockedAnnotation(newValue);
                        break;
                    case SPHERE_POSITION:
                        sphere.setLockedPosition(newValue);
                        break;
                    case SPHERE_STYLE:
                        sphere.setLockedStyle(newValue);
                        break;
                    case SPHERE_VERTICES:
                        sphere.setLockedVertices(newValue);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            });
            return booleanProp;
        });

        pTableColumn.setCellFactory(param -> {
            CheckBoxTableCell<Sphere, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
    }

    private void loadVerticesTable(Collection<Vertex> vertices) {
        symptomCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Vertex, Map<String, String>>, ObservableValue<String>>) data -> {
            String name = "";
            if (values.getGuiLanguage() == Language.GERMAN) {
                name = data.getValue().getAnnotation().get(Language.GERMAN.name());
            } else if (values.getGuiLanguage() == Language.ENGLISH) {
                name = data.getValue().getAnnotation().get(Language.ENGLISH.name());
            }
            return new ReadOnlyStringWrapper(name);
        });

        setSymptomRadioButtonTableColumn(titleSymptomCol, VERTEX_TITLE);
        setSymptomRadioButtonTableColumn(positionSymptomCol, VERTEX_POSITION);
        setSymptomRadioButtonTableColumn(styleSymptomCol, VERTEX_STYLE);

        symptomTableView.setItems(FXCollections.observableArrayList(vertices));
    }

    private void setSymptomRadioButtonTableColumn(TableColumn<Vertex, Boolean> pTableColumn, String pLocked) {
        pTableColumn.setCellValueFactory(param -> {
            Vertex vertex = param.getValue();
            SimpleBooleanProperty booleanProp;
            switch (pLocked) {
                case VERTEX_TITLE:
                    booleanProp = new SimpleBooleanProperty(vertex.isLockedAnnotation());
                    break;
                case VERTEX_POSITION:
                    booleanProp = new SimpleBooleanProperty(vertex.isLockedPosition());
                    break;
                case VERTEX_STYLE:
                    booleanProp = new SimpleBooleanProperty(vertex.isLockedStyle());
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            //When "Boolean" column change
            booleanProp.addListener((observable, oldValue, newValue) -> {
                switch (pLocked) {
                    case VERTEX_TITLE:
                        vertex.setLockedAnnotation(newValue);
                        break;
                    case VERTEX_POSITION:
                        vertex.setLockedPosition(newValue);
                        break;
                    case VERTEX_STYLE:
                        vertex.setLockedStyle(newValue);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            });
            return booleanProp;
        });

        pTableColumn.setCellFactory(param -> {
            CheckBoxTableCell<Vertex, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
    }

    private void loadEdgesTable(Collection<Edge> edges) {
        edgeCol.setCellValueFactory(data -> {
            String name = data.getValue().toString();
            return new ReadOnlyStringWrapper(name);
        });

        setEdgeRadioButtonTableColumn(positionEdgeCol, EDGE_POSITION);
        setEdgeRadioButtonTableColumn(styleEdgeCol, EDGE_STYLE);
        setEdgeRadioButtonTableColumn(edgetypeEdgeCol, EDGE_EDGE_TYPE);

        edgeTableView.setItems(FXCollections.observableArrayList(edges));
    }

    private void setEdgeRadioButtonTableColumn(TableColumn<Edge, Boolean> pTableColumn, String pLocked) {
        pTableColumn.setCellValueFactory(param -> {
            Edge edge = param.getValue();
            SimpleBooleanProperty booleanProp;
            switch (pLocked) {
                case EDGE_POSITION:
                    booleanProp = new SimpleBooleanProperty(edge.isLockedPosition());
                    break;
                case EDGE_STYLE:
                    booleanProp = new SimpleBooleanProperty(edge.isLockedStyle());
                    break;
                case EDGE_EDGE_TYPE:
                    booleanProp = new SimpleBooleanProperty(edge.isLockedEdgeType());
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            //When "Boolean" column change
            booleanProp.addListener((observable, oldValue, newValue) -> {
                switch (pLocked) {
                    case EDGE_POSITION:
                        edge.setLockedPosition(newValue);
                        break;
                    case EDGE_STYLE:
                        edge.setLockedStyle(newValue);
                        break;
                    case EDGE_EDGE_TYPE:
                        edge.setLockedEdgeType(newValue);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            });
            return booleanProp;
        });

        pTableColumn.setCellFactory(param -> {
            CheckBoxTableCell<Edge, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
    }

    private void setZoomMenu() {
        EventHandler<ActionEvent> zoomHandler = new ZoomMenuItemHandler(this);
        zoomMenuItem25.setOnAction(zoomHandler);
        zoomMenuItem50.setOnAction(zoomHandler);
        zoomMenuItem75.setOnAction(zoomHandler);
        zoomMenuItem100.setOnAction(zoomHandler);
        zoomMenuItem125.setOnAction(zoomHandler);
        zoomMenuItem150.setOnAction(zoomHandler);
        zoomMenuItem175.setOnAction(zoomHandler);
        zoomMenuItem200.setOnAction(zoomHandler);
    }

    private class OneTimeStackPaneListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> arg0, Number oldPropertyValue, Number newPropertyValue) {
            overviewStackPane.setMinWidth(0);
            overviewStackPane.widthProperty().removeListener(this);
        }
    }

    private void loadTemplateTextFields() {

        maxSphereField.textProperty().addListener(new OnlyNumberTextFieldListener(maxSphereField));
        maxSymptomField.textProperty().addListener(new OnlyNumberTextFieldListener(maxSymptomField));
        maxEdgesField.textProperty().addListener(new OnlyNumberTextFieldListener(maxEdgesField));

        FocusTemplateTextFieldListener focusTFListener = new FocusTemplateTextFieldListener();
        maxSphereField.focusedProperty().addListener(focusTFListener);
        maxSymptomField.focusedProperty().addListener(focusTFListener);
        maxEdgesField.focusedProperty().addListener(focusTFListener);
    }

    private class OnlyNumberTextFieldListener implements ChangeListener<String> {
        private TextField textField;

        OnlyNumberTextFieldListener(TextField pTextField) {
            textField = pTextField;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (!newValue.matches("\\d*"))
                textField.setText(oldValue);


        }
    }

    private class FocusTemplateTextFieldListener implements ChangeListener<Boolean> {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue) {
                rulesTemplate();
            }
        }
    }

    private void loadTemplateCheckBox() {
        reinforcedBox.selectedProperty().addListener(new TemplateCheckBoxListener(reinforcedBox, this));
        extenuatingBox.selectedProperty().addListener(new TemplateCheckBoxListener(extenuatingBox, this));
        neutralBox.selectedProperty().addListener(new TemplateCheckBoxListener(neutralBox, this));
        treeViewArrowType.selectedProperty().addListener(new TemplateCheckBoxListener(treeViewArrowType, this));
        regularExpressionBox.selectedProperty().addListener(new TemplateCheckBoxListener(regularExpressionBox, this));
        showFadedOutObjects.selectedProperty().addListener(new TemplateCheckBoxListener(showFadedOutObjects, this));
    }

    private void loadAnalysisElements() {
        amountSymptomTextField.textProperty().addListener(new OnlyNumberTextFieldListener(amountSymptomTextField));
        amountSymptomTextField.focusedProperty().addListener(new AnalysisFocusTextFieldListener(amountSymptomTextField, this));
        amountSymptomTextField.addEventHandler(KeyEvent.KEY_PRESSED, new ConfirmKeyListener(this, amountSymptomTextField));

        analysisSuccessor.selectedProperty().addListener(new AnalysisCheckBoxListener(analysisSuccessor, this));
        analysisPredecessor.selectedProperty().addListener(new AnalysisCheckBoxListener(analysisPredecessor, this));

        analysisPathCheckBox.selectedProperty().addListener(new AnalysisOptionsCheckBoxListener(this, analysisPathCheckBox, analysisPathMenuButton));
        analysisOptions.selectedProperty().addListener(new AnalysisOptionsCheckBoxListener(this, analysisOptions, filterAnalysis));

        analysisShortestPath.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(analysisPathMenuButton));
        analysisAllPaths.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(analysisPathMenuButton));

        chainOfEdges.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterAnalysis));
        convergent.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterAnalysis));
        divergent.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterAnalysis));
        branches.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterAnalysis));
        cycles.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterAnalysis));
    }

    @FXML
    public void shortestpath() {
        //Clean up Method needed
        if (analysisPathCheckBox.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphShortestPathAction analysisGraphAction = new AnalysisGraphShortestPathAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void allpaths() {
        //Clean up Method needed
        if (analysisPathCheckBox.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphAllPathsAction analysisGraphAction = new AnalysisGraphAllPathsAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void chainOfEdges() {
        //Clean up Method needed
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphEdgeChainsAction analysisGraphAction = new AnalysisGraphEdgeChainsAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void convergentBranches() {
        //Clean up Method needed
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphConvergentBranchesAction analysisGraphAction = new AnalysisGraphConvergentBranchesAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void divergentBranches() {
        //Clean up Method needed
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphDivergentBranchesAction analysisGraphAction = new AnalysisGraphDivergentBranchesAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void branches() {
        //Clean up Method needed
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphBranchesAction analysisGraphAction = new AnalysisGraphBranchesAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void analysisCycles() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphCyclesAction analysisGraphAction = new AnalysisGraphCyclesAction();
            analysisGraphAction.action();
        }
    }

    @FXML
    public void synAnalysis() {
        filterLogs(analysisLogEntryName);
    }

    @Override
    public void updateGraph() {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                treeViewUpdate();
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();

    }

    @Override
    public void updateFunctionMode(FunctionMode mode) {
        // nothing to do
    }

    @Override
    public void updateEditMode() {
        // nothing to do
    }

    @Override
    public void updateNewGraph() {
        treeViewUpdate();
    }

    private void filterLogs(LogEntryName entryName) {
        analysisLogEntryName = entryName;
        logToStringConverter.resetIncrementer();
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {
                            try {
                                TreeItem<Object> rootItem = new TreeItem<>();
                                List<Log> filterLog = (entryName == null) ? logDao.getAll() : logDao.getLogType(entryName);
                                for (Log log : filterLog) {
                                    String s = logToStringConverter.convert(log);
                                    TreeItem<Object> logItem = new TreeItem<>(s);
                                    rootItem.getChildren().add(logItem);
                                }
                                rootItem.setExpanded(true);
                                protocol.setRoot(rootItem);
                                protocol.setShowRoot(false);
                            } finally {
                                latch.countDown();
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        service.start();
    }
}
