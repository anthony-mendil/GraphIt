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
import actions.other.CreateGraphAction;
import actions.other.LoadGraphAction;
import actions.other.SwitchModeAction;
import actions.remove.*;
import actions.template.RulesTemplateAction;
import com.jfoenix.controls.JFXTextField;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import log_management.DatabaseManager;
import log_management.LogToStringConverter;
import log_management.dao.LogDao;
import log_management.tables.Log;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Contains most of the gui elements, calls most of the actions and acts as interface between
 * the gui and the internal components of the application.
 * All gui elements doesn't have javadoc because their names implies their functions.
 *
 * @author Jacky Philipp Mach
 */
@Data
public class Controller implements ObserverSyndrom {

    /**
     * The comparator for sorting all menuitems in the filter menu in the overview.
     */
    public static final Comparator<MenuItem> menuItemCompare = Comparator.comparing(MenuItem::getText);

    /* Constants that are used in Controller */
    private static final String SIZE_SPHERE_COMBO_BOX = "sizeSphereComboBox";
    private static final String SIZE_SYMPTOM_COMBO_BOX = "sizeSymptomComboBox";
    private static final String FONT_SYMPTOM_COMBO_BOX = "fontSymptomComboBox";
    private static final String FONT_SPHERE_COMBO_BOX = "fontSphereComboBox";
    private static final String SPHERE_TITLE = "SphereTitle";
    private static final String SPHERE_POSITION = "SpherePosition";
    private static final String SPHERE_STYLE = "SphereStyle";
    private static final String SPHERE_VERTICES = "SphereVertices";
    private static final String VERTEX_TITLE = "SymptomTitle";
    private static final String VERTEX_POSITION = "SymptomPosition";
    private static final String VERTEX_STYLE = "SymptomStyle";
    private static final String VERTEX_VERTICES = "VertexVertices";
    private static final String EDGE_TITLE = "EdgeTitle";
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
    private static final String IMPORT_GXL = "importGXL";
    private static final String IMPORT_TEMPLATE_GXL = "importTemplateGXL";
    private static final String OPEN_FILE = "openFile";
    private static final String PRINT_FILE = "print";
    private static final String NEW_FILE = "newFile";
    private static final String EXPORT_PDF = "exportPDF";

    /**
     * Our application logo.
     */
    private static final String APPLICATION_LOGO = "/GraphItLogo.png";
    /**
     * Our application title.
     */
    private static final String APPLICATION_TITLE = "GraphIt";
    /**
     * The logger which logs every exception.
     */
    private static Logger logger = Logger.getLogger(Controller.class);
    /* General GUI elements */
    private Controller instance;
    private File lastUsedFilePath;
    @FXML
    private SwingNode canvas;
    @FXML
    private SwingNode satellite;
    private SwingNode zoomWindow;
    private TitledPane tiltedPane;
    @FXML
    private TreeView<Object> treeView;
    private Text actionText;
    @FXML
    private Slider zoomSlider;
    @FXML
    private Menu fileMenu;
    /* Menu Bar */
    @FXML
    private MenuItem newFile;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem importGXL;
    @FXML
    private MenuItem saveLocation;
    @FXML
    private Menu importAs;
    @FXML
    private Menu exportAs;
    @FXML
    private MenuItem importTemplateGXL;
    @FXML
    private MenuItem exportTemplateGXL;
    @FXML
    private MenuItem closeApplication;
    @FXML
    private MenuItem exportPDF;
    @FXML
    private MenuItem exportLogs;
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
    @FXML
    private CheckMenuItem languageGerman;
    @FXML
    private CheckMenuItem languageEnglish;
    @FXML
    private Menu languagesGuiGraph;
    @FXML
    private CheckMenuItem languageGuiGraphGerman;
    @FXML
    private CheckMenuItem languageGuiGraphEnglish;
    @FXML
    private Menu advancedLanguageOptions;
    @FXML
    private MenuItem documentation;
    @FXML
    private MenuItem about;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private Separator toolBarSeparator1;
    @FXML
    private Button createButton;
    @FXML
    private Button analysisButton;
    @FXML
    private Button editButton;
    /* Internal */
    /* Template Options */
    @FXML
    private JFXTextField maxSphereField;
    @FXML
    private TextField maxSymptomField;
    @FXML
    private TextField maxEdgesField;
    @FXML
    private CheckBox reinforcedBox;
    @FXML
    private CheckBox extenuatingBox;
    @FXML
    private CheckBox neutralBox;
    @FXML
    private TextField amountSymptomTextField;
    @FXML
    private MenuItem branches;
    @FXML
    private MenuItem cycles;
    @FXML
    private CheckBox treeViewArrowType;
    @FXML
    private CheckBox regularExpressionBox;
    @FXML
    private TextField regularExpressionField;
    /* Sphere */
    @FXML
    private ColorPicker sphereBackgroundColour;
    @FXML
    private ComboBox<String> fontSphereComboBox;
    @FXML
    private ColorPicker symptomBackground;
    @FXML
    private ColorPicker symptomBorder;
    @FXML
    private MenuButton sphereFormMenuButton;
    @FXML
    private MenuItem symptomCircle;
    @FXML
    private MenuItem symptomRectangle;
    @FXML
    private ComboBox<String> fontSymptomComboBox;
    @FXML
    private ColorPicker edgeColour;
    @FXML
    private MenuButton edgeStrokeMenuButton;
    @FXML
    private MenuItem edgeStrokeDashed;
    @FXML
    private MenuItem edgeStrokeDashedWeight;
    @FXML
    private MenuItem edgeStrokeDotted;
    @FXML
    private MenuItem edgeStrokeDottedWeight;
    @FXML
    private MenuItem edgeStrokeBasic;
    @FXML
    private MenuItem edgeStrokeBasicWeight;
    @FXML
    private MenuButton edgeArrowMenuButton;
    @FXML
    private MenuItem edgeArrowReinforced;
    @FXML
    private MenuItem edgeArrowExtenuating;
    @FXML
    private MenuItem edgeArrowNeutral;
    @FXML
    private Text currentActionText;
    /**
     * The logdao object that provides the treeview with the protocol.
     */
    private LogDao logDao = new LogDao();
    /**
     * The logtostringconverter object that converts the log to strings to display the protocol.
     */
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
    @FXML
    private VBox vBoxGraphStats;
    @FXML
    private Separator separator3;
    @FXML
    private Separator separator4;
    @FXML
    private VBox vBoxAnalysisSymptom;
    @FXML
    private VBox vBoxAnalysisOption;
    //Edit GUI
    @FXML
    private Separator separator0;
    @FXML
    private Separator separator1;
    @FXML
    private Separator separator2;
    @FXML
    private VBox vBoxSelect;
    @FXML
    private VBox vBoxEditSphere;
    @FXML
    private VBox vBoxEditSymptom;
    @FXML
    private VBox vBoxEditEdge;
    @FXML
    private SwingNode swing;
    @FXML
    private ScrollPane paneSwingNode;
    @FXML
    private BorderPane root;
    private Stage mainStage;
    /**
     * The current selected size.
     */
    private String currentSize = "" + Values.DEFAULT_SIZE_VERTEX;
    /**
     * The current selected font.
     */
    private String currentFont = values.getFontSphere();
    @FXML
    private ComboBox<String> sizeSphereComboBox;
    @FXML
    private ComboBox<String> sizeSymptomComboBox;
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
    private ToggleButton handSelector;
    @FXML
    private ToggleButton addSphere;
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
    private ToggleButton addVertex;
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
    @FXML
    private Label infoAnalysis;
    @FXML
    private Label infoTemplate;
    @FXML
    private Label infoZoom;
    private Tooltip tooltipInfoAnalysis = new Tooltip();
    private Tooltip tooltipInfoZoom = new Tooltip();
    private Tooltip tooltipInfoTemplate = new Tooltip();
    @FXML
    private Text positionMouseX;
    @FXML
    private Text positionMouseY;
    @FXML
    private Button syncButton;
    @FXML
    private GridPane templateGridPane;
    @FXML
    private VBox templateVbox;
    /**
     * The list of all fonts.
     */
    private ObservableList<String> fonts =
            FXCollections.observableArrayList(
                    "AveriaSansLibre",
                    "Kalam",
                    "Mali",
                    "Roboto",
                    "RobotoSlab"
            );
    /**
     * The list of all font sizes.
     */
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
    /**
     * The current edge arrow type to filter by.
     */
    private EdgeArrowType filterEdgeArrowType = EdgeArrowType.REINFORCED;
    /**
     * The current log entry to filter by.
     */
    private LogEntryName analysisLogEntryName = null;
    /**
     * The loadlanguage object to change the gui and graph language.
     */
    private LoadLanguage loadLanguage;
    /**
     * Listens to the changes of the zoom slider and scales the visible graph area accordingly.
     */
    private ChangeListener<Number> changeZoom = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        if (zoomSlider.isValueChanging()) {
            int value = newValue.intValue();
            int oldV = oldValue.intValue();

            SwingUtilities.invokeLater(() -> {
                if (value != 0 && oldV != value) {
                    syndrom.scale(value);
                }
            });
        }
    };
    /**
     * Listens to the width change of the swing node and resizes the visualisation viewer accordingly.
     */
    private ChangeListener<Number> widthListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        if (canvas.getContent() != null) {
            SwingUtilities.invokeLater(() -> {
                SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
                vv.setPreferredSize(new Dimension(root.getCenter().layoutXProperty().getValue().intValue(), root.getCenter().layoutYProperty().getValue().intValue()));
            });
        }
    };

    /* ----------------ADD---------------------- */
    /**
     * Listens to the height change of the swing node and resizes the visualisation viewer accordingly.
     */
    private ChangeListener<Number> heightListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        if (canvas.getContent() != null) {
            SwingUtilities.invokeLater(() -> {
                VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
                Dimension old = vv.getPreferredSize();
                old.setSize(old.getWidth(), newValue.intValue());
                vv.setPreferredSize(old);
            });
        }
    };

    /**
     * Sets the filter edge arrow type to the reinforced arrow type.
     */
    public void filterEdgeTypeReinforced() {
        filterEdgeArrowType = EdgeArrowType.REINFORCED;
    }

    /**
     * Sets the filter edge arrow type to the reinforced arrow type.
     */
    public void filterEdgeTypeExtenuating() {
        filterEdgeArrowType = EdgeArrowType.EXTENUATING;
    }

    /* ----------------EDIT---------------------- */

    /**
     * Sets the filter edge arrow type to the reinforced arrow type.
     */
    public void filterEdgeTypeNeutral() {
        filterEdgeArrowType = EdgeArrowType.NEUTRAL;
    }

    /**
     * Sets the graph button type to the add sphere button type.
     */
    public void addSphere() {
        values.setGraphButtonType(GraphButtonType.ADD_SPHERE);
    }

    /**
     * Sets the graph button type to the add symptom button type.
     */
    public void addVertex() {
        values.setGraphButtonType(GraphButtonType.ADD_VERTEX);
    }

    /**
     * Sets the graph button type to the none button type.
     */
    public void handSelector() {
        values.setGraphButtonType(GraphButtonType.NONE);
    }

    /**
     * Changes the edge stroke accordingly to the given argument.
     *
     * @param stroke The desired edge stroke to change the edge to.
     */
    private void editEdgesStroke(StrokeType stroke) {
        if (!syndrom.getVv().getPickedEdgeState().getPicked().isEmpty()) {
            EditEdgesStrokeLogAction editEdgesStrokeLogAction = new EditEdgesStrokeLogAction(stroke);
            history.execute(editEdgesStrokeLogAction);
        }
    }

    /**
     * Changes the edge stroke to basic stroke.
     */
    public void edgeStrokeBasic() {
        values.setStrokeEdge(StrokeType.BASIC);
        editEdgesStroke(StrokeType.BASIC);
    }

    /**
     * Changes the edge stroke to basic weight stroke.
     */
    public void edgeStrokeBasicWeighted() {
        values.setStrokeEdge(StrokeType.BASIC_WEIGHT);
        editEdgesStroke(StrokeType.BASIC_WEIGHT);
    }

    /**
     * Changes the edge stroke to dotted stroke.
     */
    public void edgeStrokeDotted() {
        values.setStrokeEdge(StrokeType.DOTTED);
        editEdgesStroke(StrokeType.DOTTED);
    }

    /**
     * Changes the edge stroke to dotted weight stroke.
     */
    public void edgeStrokeDottedWeighted() {
        values.setStrokeEdge(StrokeType.DOTTED_WEIGHT);
        editEdgesStroke(StrokeType.DOTTED_WEIGHT);
    }

    /**
     * Changes the edge stroke to dashed stroke.
     */
    public void edgeStrokeDashed() {
        values.setStrokeEdge(StrokeType.DASHED);
        editEdgesStroke(StrokeType.DASHED);
    }

    /**
     * Changes the edge stroke to dashed weight stroke.
     */
    public void edgeStrokeDashedWeighted() {
        values.setStrokeEdge(StrokeType.DASHED_WEIGHT);
        editEdgesStroke(StrokeType.DASHED_WEIGHT);
    }

    /* ......color..... */

    /**
     * Changes the edge arrow type accordingly to the given argument.
     *
     * @param type The desired edge arrow type.
     */
    private void editEdgesType(EdgeArrowType type) {
        if (!syndrom.getVv().getPickedEdgeState().getPicked().isEmpty()) {
            EditEdgesTypeLogAction editEdgesTypeLogAction = new EditEdgesTypeLogAction(type);
            history.execute(editEdgesTypeLogAction);
        }
    }

    /**
     * Changes the edge arrow type to reinforced arrow.
     */
    public void edgeReinforced() {
        values.setEdgeArrowType(EdgeArrowType.REINFORCED);
        editEdgesType(EdgeArrowType.REINFORCED);
    }

    /**
     * Changes the edge arrow type to extenuating arrow.
     */
    public void edgeExtenuating() {
        values.setEdgeArrowType(EdgeArrowType.EXTENUATING);
        editEdgesType(EdgeArrowType.EXTENUATING);
    }

    /**
     * Changes the edge arrow type to neutral arrow.
     */
    public void edgeNeutral() {
        values.setEdgeArrowType(EdgeArrowType.NEUTRAL);
        editEdgesType(EdgeArrowType.NEUTRAL);
    }

    /**
     * Sets the edge colour.
     */
    public void editEdgesColor() {
        Color color = convertToAWT(edgeColour.getValue());
        Values.getInstance().setEdgePaint(color);
        if (!syndrom.getVv().getPickedEdgeState().getPicked().isEmpty()) {
            EditEdgesColorLogAction editEdgesColorLogAction = new EditEdgesColorLogAction(color);
            history.execute(editEdgesColorLogAction);
        }
    }

    /**
     * Activate/Deactivate anchor points for edges.
     */
    public void anchorPointsEdge() {
        if (anchorPointsButton.isSelected()) {
            DeactivateAnchorPointsFadeoutAction deactivateAnchorPointsFadeoutAction = new DeactivateAnchorPointsFadeoutAction();
            deactivateAnchorPointsFadeoutAction.action();
        } else {
            ActivateAnchorPointsFadeoutAction activateAnchorPointsFadeoutAction = new ActivateAnchorPointsFadeoutAction();
            activateAnchorPointsFadeoutAction.action();
        }
    }

    /**
     * Removes anchor points of edges.
     */
    public void removeAnchor() {
        RemoveAnchorPointsAction removeAnchorPointsAction = new RemoveAnchorPointsAction();
        removeAnchorPointsAction.action();
    }

    /**
     * Sets the sphere colour.
     */
    public void editSphereColor() {
        Color color = convertToAWT(sphereBackgroundColour.getValue());
        Values.getInstance().setFillPaintSphere(color);
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            EditSphereColorLogAction colorLogAction = new EditSphereColorLogAction(color);
            history.execute(colorLogAction);
        }
    }

    /* ......font..... */

    /**
     * Convert AWT colour to JavaFX colour.
     *
     * @param fx JavaFX colour.
     * @return AWT colour.
     */
    private Color convertToAWT(javafx.scene.paint.Color fx) {
        return new java.awt.Color((float) fx.getRed(),
                (float) fx.getGreen(),
                (float) fx.getBlue(),
                (float) fx.getOpacity());
    }

    /**
     * Convert AWT colour to JavaFX colour.
     *
     * @param awt AWT colour.
     * @return JavaFX colour.
     */
    private javafx.scene.paint.Color convertFromAWT(java.awt.Color awt) {
        int r = awt.getRed();
        int g = awt.getGreen();
        int b = awt.getBlue();
        int a = awt.getAlpha();
        double opacity = a / 255.0;
        return javafx.scene.paint.Color.rgb(r, g, b, opacity);
    }

    /**
     * Sets the symptom border colour.
     */
    public void editVerticesDrawColor() {
        Color color = convertToAWT(symptomBorder.getValue());
        Values.getInstance().setDrawPaintVertex(color);
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditVerticesDrawColorLogAction colorLogAction = new EditVerticesDrawColorLogAction(color);
            history.execute(colorLogAction);
        }
    }

    /**
     * Sets the symptom fill colour.
     */
    public void editVerticesFillColor() {
        Color color = convertToAWT(symptomBackground.getValue());
        Values.getInstance().setFillPaintVertex(color);
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditVerticesFillColorLogAction colorLogAction = new EditVerticesFillColorLogAction(color);
            history.execute(colorLogAction);
        }
    }

    /**
     * Changes the font of the spheres accordingly to the given argument.
     *
     * @param font The font as String that the Sphere text gets changed to.
     */
    private void editFontSphere(String font) {
        values.setFontSphere(font);
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            EditFontSphereLogAction editFontSphereLogAction = new EditFontSphereLogAction(font);
            history.execute(editFontSphereLogAction);
        }
    }

    /**
     * Changes the font of the symptoms accordingly to the given argument.
     *
     * @param font The font as String that the Symptom text gets changed to.
     */
    private void editFontVertex(String font) {
        values.setFontVertex(font);
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditFontVerticesLogAction editFontVertexLogAction = new EditFontVerticesLogAction(font);
            history.execute(editFontVertexLogAction);
        }
    }

    /**
     * Organizes the sphere layout automatically.
     */
    public void sphereAutoLayout() {
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if (!graph.getSpheres().isEmpty()) {
            LayoutSphereGraphLogAction layoutSphereGraphLogAction = new LayoutSphereGraphLogAction();
            history.execute(layoutSphereGraphLogAction);
        }
    }

    /**
     * Organizes the symptom layout automatically.
     */
    public void verticesAutoLayout() {
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if (!graph.getVertices().isEmpty()) {
            LayoutVerticesGraphLogAction layoutVerticesGraphLogAction = new LayoutVerticesGraphLogAction();
            history.execute(layoutVerticesGraphLogAction);
        }
    }

    /* ----------------EXPORT---------------------- */

    /**
     * Changes the font size of symptoms accordingly to the given argument.
     *
     * @param size The desired font size.
     */
    void editFontSizeVertices(int size) {
        values.setFontSizeVertex(size);
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditFontSizeVerticesLogAction editFontSizeVerticesLogAction = new EditFontSizeVerticesLogAction(size);
            history.execute(editFontSizeVerticesLogAction);
        }
    }

    /**
     * Changes the symptom shape accordingly to the given argument.
     *
     * @param type The desired symptom shape.
     */
    private void editVerticesForm(VertexShapeType type) {
        values.setShapeVertex(type);
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditVerticesFormLogAction editVerticesFormLogAction = new EditVerticesFormLogAction(type);
            history.execute(editVerticesFormLogAction);
        }
    }

    /**
     * Changes the symptoms shape to a circle shape.
     */
    public void verticesCircle() {
        editVerticesForm(VertexShapeType.CIRCLE);
    }

    /**
     * Changes the symptoms shape to a rectangle shape.
     */
    public void verticesRectangle() {
        editVerticesForm(VertexShapeType.RECTANGLE);
    }

    /**
     * Export the graph as GXL file.
     */
    public void exportGXL() {
        File file = openGXLExportWindow();
        if (file != null) {
            syndrom.setGraphName(FilenameUtils.removeExtension(file.getName()));
            lastUsedFilePath = file.getParentFile();
            ExportGxlAction exportGxlAction = new ExportGxlAction(file);
            exportGxlAction.action();
        }
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Export the graph as GXL file and as template.
     */
    public void exportTemplateGXL() {
        File file = openGXLExportWindow();
        if (file != null) {
            syndrom.setGraphName(FilenameUtils.removeExtension(file.getName()));
            lastUsedFilePath = file.getParentFile();
            ExportTemplateGxlAction exportTemplateGxlAction = new ExportTemplateGxlAction(file);
            exportTemplateGxlAction.action();
        }
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Opens up a file chooser that the user can use to determine the desired saving path.
     *
     * @return The file with the desired saving path.
     */
    private File openGXLExportWindow() {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        if (syndrom.getGraphName() != null) {
            fileChooser.setInitialFileName(syndrom.getGraphName() + ".gxl");
        } else {
            fileChooser.setInitialFileName("UntitledGraph.gxl");
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showSaveDialog(mainStage);
    }

    /**
     * Opens up a file chooser that the user can use to determine the desired saving path and export the graph
     * as PDF file.
     */
    private void exportPDF() {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        if (syndrom.getGraphName() != null) {
            fileChooser.setInitialFileName(syndrom.getGraphName() + ".pdf");
        } else {
            fileChooser.setInitialFileName("UntitledGraph.pdf");
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", PDF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            syndrom.setGraphName(FilenameUtils.removeExtension(file.getName()));
            lastUsedFilePath = file.getParentFile();
            ExportPdfAction exportPdfAction = new ExportPdfAction(file);
            exportPdfAction.action();
        }
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Opens up a file chooser that the user can use to determine the desired saving path and export the protocol.
     */
    public void exportProtocol() {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        if (syndrom.getGraphName() != null) {
            fileChooser.setInitialFileName(syndrom.getGraphName() + ".txt");
        } else {
            fileChooser.setInitialFileName("UntitledGraph.txt");
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file (*.txt)", TXT);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            lastUsedFilePath = file.getParentFile();
            ExportReadableProtocolAction exportReadableProtocolAction = new ExportReadableProtocolAction(file);
            exportReadableProtocolAction.action();
        }
    }

    /**
     * Opens up a file chooser that the user can use to determine the desired saving path and export the graph
     * as OOF File which contains the graph and the protocol.
     */
    public void exportOOF() {
        rulesTemplate();
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        if (syndrom.getGraphName() != null) {
            fileChooser.setInitialFileName(syndrom.getGraphName() + ".oof");
        } else {
            fileChooser.setInitialFileName("UntitledGraph.oof");
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", OOF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(mainStage);
        if (file != null) {
            syndrom.setGraphName(FilenameUtils.removeExtension(file.getName()));
            lastUsedFilePath = file.getParentFile();
            ExportOofAction exportOofAction = new ExportOofAction(file);
            exportOofAction.action();
        }
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Opens the selected OOF-fileMenu after choosing it in the fileMenu chooser, creates an ImportOofAction-object
     * and executes the action with the action history.
     */
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("OOF files (*.oof)", OOF);
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(mainStage);
        if (file != null) {
            lastUsedFilePath = file.getParentFile();
            ImportOofAction importOofAction = new ImportOofAction(file);
            importOofAction.action();
            zoomSlider.setValue(100);
            canvas.setContent(syndrom.getVv());
            satellite.setContent(syndrom.getVv2());
        }
        templateToFields();
        treeViewUpdate();
        handSelector.requestFocus();
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Opens the selected GXL-fileMenu after choosing it in the fileMenu chooser, creates an ImportGxlAction-object
     * and executes the action with the action history.
     */
    private void importGXL() {
        File file = openGXLImportWindow();
        if (file != null) {
            lastUsedFilePath = file.getParentFile();
            ImportGxlAction importGxlAction = new ImportGxlAction(file);
            importGxlAction.action();
            if (importGxlAction.isTemplateFound()) {
                ButtonType yes = new ButtonType(loadLanguage.loadLanguagesKey("YES"), ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType(loadLanguage.loadLanguagesKey("NO"), ButtonBar.ButtonData.CANCEL_CLOSE);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, loadLanguage.loadLanguagesKey("INFO_TEMPLATE_GXL"), yes, no);
                alert.setTitle(APPLICATION_TITLE);
                alert.setHeaderText(null);
                alert.initOwner(mainStage);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.setResizable(false);
                stage.getIcons().add(new Image(getClass().getResourceAsStream(APPLICATION_LOGO)));
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    ImportTemplateGxlAction importTemplateGxlAction = new ImportTemplateGxlAction(file);
                    importTemplateGxlAction.action();
                    zoomSlider.setValue(100);
                    canvas.setContent(syndrom.getVv());
                    satellite.setContent(syndrom.getVv2());
                } else if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    importGxlAction.action();
                    zoomSlider.setValue(100);
                    canvas.setContent(syndrom.getVv());
                    satellite.setContent(syndrom.getVv2());
                }
            } else {
                zoomSlider.setValue(100);
                canvas.setContent(syndrom.getVv());
                satellite.setContent(syndrom.getVv2());
            }
        }
        templateToFields();
        treeViewUpdate();
        handSelector.requestFocus();
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /* ----------------OTHER---------------------- */

    /**
     * Opens the selected GXL-fileMenu after choosing it in the fileMenu chooser, creates an ImportTemplateGxlAction-object
     * and executes the action with the action history.
     */
    private void importTemplateGXL() {
        File file = openGXLImportWindow();
        if (file != null) {
            lastUsedFilePath = file.getParentFile();
            ImportTemplateGxlAction importTemplateGxlAction = new ImportTemplateGxlAction(file);
            importTemplateGxlAction.action();
            if (!importTemplateGxlAction.isTemplateFound()) {
                ButtonType ok = new ButtonType(loadLanguage.loadLanguagesKey("OKAY"), ButtonBar.ButtonData.OK_DONE);
                Alert alert = new Alert(Alert.AlertType.ERROR, loadLanguage.loadLanguagesKey("ERROR_GXL_NOT_TEMPLATE"), ok);
                alert.setTitle(APPLICATION_TITLE);
                alert.setHeaderText(null);
                alert.initOwner(mainStage);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.setResizable(false);
                stage.getIcons().add(new Image(getClass().getResourceAsStream(APPLICATION_LOGO)));
                alert.showAndWait();
            } else {
                zoomSlider.setValue(100);
                canvas.setContent(syndrom.getVv());
                satellite.setContent(syndrom.getVv2());
            }
        }
        templateToFields();
        treeViewUpdate();
        handSelector.requestFocus();
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Opens up a file chooser that the user can use to import a desired file in the application.
     *
     * @return The desired file that should be imported.
     */
    private File openGXLImportWindow() {
        FileChooser fileChooser = new FileChooser();
        if (lastUsedFilePath != null && lastUsedFilePath.toPath().toFile().exists()) {
            fileChooser.setInitialDirectory(lastUsedFilePath);
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(GXL_FILE, GXL);
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(mainStage);
    }

    /**
     * Sets the Template values into the fields (usage: importing template).
     */
    private void templateToFields() {
        Template currentTemplate = syndrom.getTemplate();
        if (currentTemplate.getMaxSpheres() != Integer.MAX_VALUE) {
            maxSphereField.setText("" + currentTemplate.getMaxSpheres());
        } else {
            maxSphereField.clear();
        }
        if (currentTemplate.getMaxVertices() != Integer.MAX_VALUE) {
            maxSymptomField.setText("" + currentTemplate.getMaxVertices());
        } else {
            maxSymptomField.clear();
        }
        if (currentTemplate.getMaxEdges() != Integer.MAX_VALUE) {
            maxEdgesField.setText("" + currentTemplate.getMaxEdges());
        } else {
            maxEdgesField.clear();
        }
        reinforcedBox.setSelected(currentTemplate.isReinforcedEdgesAllowed());
        extenuatingBox.setSelected(currentTemplate.isExtenuatingEdgesAllowed());
        neutralBox.setSelected(currentTemplate.isNeutralEdgesAllowed());
    }

    /**
     * Prints the graph as PDF.
     */
    private void printPDF() {
        PrintPDFAction printPDFAction = new PrintPDFAction();
        printPDFAction.action();

    }

    /**
     * Switches to the creator mode.
     */
    public void switchModeCreator() {
        values.setShowAnchor(false);
        analysisMode(false);
        createOrEditMode(false, true);
        createOrEditMode(true, false);
        editButton.setDisable(false);
        analysisButton.setDisable(false);
        createButton.setDisable(true);
        edgeArrowReinforced.setDisable(false);
        edgeArrowNeutral.setDisable(false);
        edgeArrowExtenuating.setDisable(false);
        verticesAutoLayout.setDisable(false);
        sphereAutoLayout.setDisable(false);
        if (!overViewAccordion.getPanes().contains(templateTitledPane)) {
            overViewAccordion.getPanes().add(templateTitledPane);
        }
        disableTemplateRules(false);
        updateUndoRedoButton();
        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.TEMPLATE);
        switchModeAction.action();
        satellite.setContent(syndrom.getVv2());
        root.requestFocus();
    }

    /* ----------------REMOVE---------------------- */

    /**
     * Switches to the analysis mode.
     */
    public void switchModeAnalysis() {
        values.setGraphButtonType(GraphButtonType.NONE);
        values.setShowAnchor(false);
        createOrEditMode(false, false);
        createOrEditMode(false, true);
        analysisMode(true);
        createButton.setDisable(false);
        editButton.setDisable(false);
        analysisButton.setDisable(true);
        overViewAccordion.getPanes().remove(templateTitledPane);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();

        DecimalFormat format = new DecimalFormat("####.##");
        analysisScopeNumber.setText(format.format(graphDimensionAction.getScope()));
        if (graphDimensionAction.getNetworkIndex() == -1) {
            analysisNetworkingIndexNumber.setText("NaN");
            analysisStructureIndexNumber.setText("NaN");
        } else {
            analysisNetworkingIndexNumber.setText(format.format(graphDimensionAction.getNetworkIndex()));
            analysisStructureIndexNumber.setText(format.format(graphDimensionAction.getStructureIndex()));
        }
        satellite.setContent(syndrom.getVv2());

        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.ANALYSE);
        switchModeAction.action();
        root.requestFocus();
    }

    /**
     * Switches to the edit mode.
     */
    public void switchModeEdit() {
        values.setGraphButtonType(GraphButtonType.NONE);
        values.setShowAnchor(false);
        analysisMode(false);
        createOrEditMode(false, false);
        createOrEditMode(true, true);
        createButton.setDisable(false);
        analysisButton.setDisable(false);
        editButton.setDisable(true);
        satellite.setContent(syndrom.getVv2());
        if (!overViewAccordion.getPanes().contains(templateTitledPane)) {
            overViewAccordion.getPanes().add(templateTitledPane);
        }
        disableTemplateRules(true);
        updateUndoRedoButton();
        updateAutoLayoutButton();
        updateArrowMenuButton();

        ResetVvAction resetAction = new ResetVvAction();
        resetAction.action();
        SwitchModeAction switchModeAction = new SwitchModeAction(FunctionMode.EDIT);
        switchModeAction.action();
        root.requestFocus();
    }

    /**
     * Checks if the template rules "reinforced, extenuating and neutral relations allowed" is locked.
     * If yes, then it enables/disables the menu items for the reinforced, extenuating and neutral relations.
     */
    private void updateArrowMenuButton() {
        edgeArrowReinforced.setDisable(!syndrom.getTemplate().isReinforcedEdgesAllowed());
        edgeArrowNeutral.setDisable(!syndrom.getTemplate().isNeutralEdgesAllowed());
        edgeArrowExtenuating.setDisable(!syndrom.getTemplate().isExtenuatingEdgesAllowed());

    }

    /**
     * Checks if the template rule "position" is locked.
     * If yes, then it enables/disables the auto layout button accordingly for the gui element(sphere, symptom).
     */
    private void updateAutoLayoutButton() {
        updateSphereAutoLayout();
        updateVertexAutoLayout();
    }

    /**
     * Checks if the template rule "position" is locked for spheres.
     * If yes, then it disabled the auto layout button for spheres.
     */
    private void updateSphereAutoLayout() {
        boolean sphereLocked = false;
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        if (graph != null) {
            for (Sphere s : graph.getSpheres()) {
                if (s.isLockedPosition()) {
                    sphereLocked = true;
                    sphereAutoLayout.setDisable(true);
                    break;
                }
            }
            if (!sphereLocked) {
                sphereAutoLayout.setDisable(false);
            }
        }
    }

    /**
     * Checks if the template rule "position" is locked for vertices.
     * If yes, then it disabled the auto layout button for vertices.
     */
    private void updateVertexAutoLayout() {
        boolean vertexLocked = false;
        if (!syndrom.getLayout().getGraph().getVertices().isEmpty()) {
            for (Vertex v : syndrom.getLayout().getGraph().getVertices()) {
                if (v.isLockedPosition()) {
                    vertexLocked = true;
                    verticesAutoLayout.setDisable(true);
                    break;
                }
            }
            if (!vertexLocked) {
                verticesAutoLayout.setDisable(false);
            }
        } else {
            verticesAutoLayout.setDisable(false);
        }
    }

    /**
     * Calls the undo-method from the action history.
     */
    public void executeUndo() {
        history.undo();
    }

    /* ----------------TEMPLATE---------------------- */

    /**
     * Calls the redo-method from the action history.
     */
    public void executeRedo() {
        history.redo();
    }

    /**
     * Removes the selected edges.
     */
    public void removeEdges() {
        if (!syndrom.getVv().getPickedEdgeState().getPicked().isEmpty()) {
            RemoveEdgesLogAction removeEdgesLogAction = new RemoveEdgesLogAction();
            history.execute(removeEdgesLogAction);
        }
    }

    /**
     * Removes the selected spheres.
     */
    public void removeSphere() {
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            RemoveSphereLogAction removeSphereLogAction = new RemoveSphereLogAction();
            history.execute(removeSphereLogAction);
        }
    }

    /**
     * Removes the selected symptoms.
     */
    public void removeVertices() {
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            RemoveVerticesLogAction removeVerticesLogAction = new RemoveVerticesLogAction();
            history.execute(removeVerticesLogAction);
        }
    }

    /**
     * Saves the selected template rules.
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
            pTextField.setStyle("-fx-background-color: transparent");
        } else if (cont == -2) {
            pTextField.setStyle("-fx-background-color: rgba(255,0,0,0.25)");
        } else {
            pTextField.setStyle("-fx-background-color: transparent");
            ret = cont;
        }
        return ret;
    }

    /**
     * Activates the highlight effect and shows the highlighted elements.
     */
    public void highlight() {
        if (highlight.isSelected()) {
            ActivateHighlightAction activateHighlightAction = new ActivateHighlightAction();
            activateHighlightAction.action();
        } else {
            ResetVvAction resetVvAction = new ResetVvAction();
            resetVvAction.action();
        }
    }

    /**
     * Adds the highlight effect to the selected graph elements.
     */
    public void highlightElements() {
        AddHighlightElementAction addHighlightElementAction = new AddHighlightElementAction();
        addHighlightElementAction.action();
    }

    /* ----------------INTERNAL---------------------- */

    /**
     * Removes the highlight effect from the selected graph elements.
     */
    public void deHighlightElements() {
        RemoveHighlightElementAction removeHighlightElementAction = new RemoveHighlightElementAction();
        removeHighlightElementAction.action();
    }

    /**
     * Activates the fade out effect and hide the selected graph elements.
     */
    public void fadeout() {
        if (!fadeout.isSelected()) {
            DeactivateFadeoutAction deactivateFadeoutAction = new DeactivateFadeoutAction();
            deactivateFadeoutAction.action();
        } else {
            ActivateFadeoutAction activateFadeoutAction = new ActivateFadeoutAction();
            activateFadeoutAction.action();
        }
    }

    /**
     * Adds the fade out effect to the selected graph elements.
     */
    public void fadeoutElements() {
        AddFadeoutElementAction addFadeoutElementAction = new AddFadeoutElementAction();
        addFadeoutElementAction.action();
    }

    /**
     * Removes the fade out effect from the selected graph elements.
     */
    public void deFadeoutElements() {
        RemoveFadeoutElementAction removeFadeoutElementAction = new RemoveFadeoutElementAction();
        removeFadeoutElementAction.action();
    }

    /**
     * Initializes the whole gui with the desired functions.
     */
    @SuppressWarnings("unchecked")
    public void initialize() {
        initFonts();
        rulesTemplate();
        syndrom = Syndrom.getInstance();
        history = ActionHistory.getInstance();

        values.setCanvas(canvas);
        values.setHBox(textBox);
        values.setAnimationFadeout(fadeout);
        values.setCurrentActionText(currentActionText);
        values.setPositionMouseX(positionMouseX);
        values.setPositionMouseY(positionMouseY);

        values.setMode(FunctionMode.TEMPLATE);


        sphereBackgroundColour.setValue(convertFromAWT(Values.getInstance().getFillPaintSphere()));
        symptomBorder.setValue(convertFromAWT(Values.getInstance().getDrawPaintVertex()));
        symptomBackground.setValue(convertFromAWT(Values.getInstance().getFillPaintVertex()));
        treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        overViewAccordion.setExpandedPane(overViewTitledPane);

        paneSwingNode.widthProperty().addListener(widthListener);
        paneSwingNode.heightProperty().addListener(heightListener);
        paneSwingNode.focusedProperty().addListener((observable, oldValue, newValue) -> root.requestFocus());

        loadSizeComboBox(sizeSphereComboBox);
        loadSizeComboBox(sizeSymptomComboBox);
        loadFontComboBox(fontSphereComboBox);
        loadFontComboBox(fontSymptomComboBox);
        loadMenuItem();
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

        overviewStackPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                overviewStackPane.setMinWidth(0);
                overviewStackPane.widthProperty().removeListener(this);
            }
        });

        DatabaseManager databaseManager = DatabaseManager.getInstance();

        GraphAction action = databaseManager.databaseEmpty() ? new CreateGraphAction("UntitledGraph", this) : new LoadGraphAction(this);
        action.action();

        canvas.setContent(syndrom.getVv());
        satellite.setContent(syndrom.getVv2());
        zoomSlider.setValue(100);
        initTree();

        regularExpressionField.textProperty().addListener((observable, oldValue, newValue) -> {
            FilterGraphAction filterGraphAction = new FilterGraphAction(newValue, regularExpressionBox.isSelected());
            filterGraphAction.action();
        });

        initLanguage();
        initProtocolTree();
        initGraphLanguage();
        initInfoText();
        iniSelectionButtons();

        symptomCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Vertex, Map<String, String>>, ObservableValue<String>>) data -> {
            String name = "";
            if (values.getGuiLanguage() == Language.GERMAN) {
                name = data.getValue().getAnnotation().get(Language.GERMAN.name());
            } else if (values.getGuiLanguage() == Language.ENGLISH) {
                name = data.getValue().getAnnotation().get(Language.ENGLISH.name());
            }
            return new ReadOnlyStringWrapper(name);
        });

        sphereCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Sphere, Map<String, String>>, ObservableValue<String>>) data -> {
            String name = "";
            if (values.getGuiLanguage() == Language.ENGLISH) {
                name = data.getValue().getAnnotation().get(Language.ENGLISH.name());
            } else if (values.getGuiLanguage() == Language.GERMAN) {
                name = data.getValue().getAnnotation().get(Language.GERMAN.name());
            }
            return new ReadOnlyStringWrapper(name);
        });

        edgeCol.setCellValueFactory(data -> {
            String name = data.getValue().toString();
            return new ReadOnlyStringWrapper(name);
        });
        templateToFields();
        updateUndoRedoButton();
        analysisMode(false);
        createOrEditMode(true, true);
        createButton.setDisable(true);
        switchModeEdit();
    }

    /**
     * Initializes the toggle buttons (Handselector, AddSphere, AddSymptom).
     */
    private void iniSelectionButtons() {
        handSelector.selectedProperty().addListener(new ToggleButtonListener(this, handSelector));
        addSphere.selectedProperty().addListener(new ToggleButtonListener(this, addSphere));
        addVertex.selectedProperty().addListener(new ToggleButtonListener(this, addVertex));
        handSelector();
    }

    /**
     * Initializes the info boxes.
     */
    private void initInfoText() {
        infoText(tooltipInfoAnalysis, "INFO_ANALYSIS", infoAnalysis);
        infoText(tooltipInfoZoom, "INFO_ZOOM", infoZoom);
        infoText(tooltipInfoTemplate, "INFO_TEMPLATE", infoTemplate);
    }

    /**
     * Creates an info box, sets the text and assigns it to a label.
     *
     * @param tooltip The tooltip that the info text will be assigned to.
     * @param text    The shown text in the info text.
     * @param label   The label in the gui.
     */
    private void infoText(Tooltip tooltip, String text, Label label) {
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

        label.setOnMouseEntered(event -> tooltip.show(label, event.getScreenX() + 10, event.getScreenY()));
        label.setOnMouseExited(event -> tooltip.hide());
    }

    /**
     * Initialize the shortcuts for the application.
     */
    void initButtonShortcuts() {
        mainStage.getScene().setOnKeyPressed(Controller.this::match);
    }

    /**
     * Assigns the different keys to actions.
     *
     * @param event The key event that gets called after every key press.
     */
    private void match(KeyEvent event) {
        KeyCombination plus = new KeyCodeCombination(KeyCode.PLUS);
        KeyCombination minus = new KeyCodeCombination(KeyCode.MINUS);
        KeyCombination strgZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
        KeyCombination strgY = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
        KeyCombination strgD = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        KeyCombination strgA = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
        KeyCombination strgF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN);
        KeyCombination one = new KeyCodeCombination(KeyCode.DIGIT1);
        KeyCombination two = new KeyCodeCombination(KeyCode.DIGIT2);
        KeyCombination three = new KeyCodeCombination(KeyCode.DIGIT3);
        KeyCombination esc = new KeyCodeCombination(KeyCode.ESCAPE);
        KeyCombination entf = new KeyCodeCombination(KeyCode.DELETE);
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
        } else if (entf.match(event) || strgD.match(event)) {
            removeEdges();
            syndrom.getVv().getPickedEdgeState().clear();
            removeVertices();
            syndrom.getVv().getPickedVertexState().clear();
            removeSphere();
            syndrom.getVv().getPickedSphereState().clear();
        } else if (strgA.match(event)) {
            selectAllSymptomsEdges();
        } else if (two.match(event)) {
            switchModeCreator();
        } else if (three.match(event)) {
            switchModeAnalysis();
        } else if (one.match(event)) {
            switchModeEdit();
        } else if (esc.match(event)) {
            syndrom.getVv().getPickedSphereState().clear();
            syndrom.getVv().getPickedVertexState().clear();
            syndrom.getVv().getPickedEdgeState().clear();
            regularExpressionBox.setSelected(false);
            handSelector();
            handSelector.requestFocus();
            handSelector.setSelected(true);
        } else if (strgF.match(event)) {
            regularExpressionBox.setSelected(true);
            regularExpressionField.requestFocus();
        }
    }

    /**
     * Selects all symptoms and edges in the graph.
     */
    private void selectAllSymptomsEdges() {
        for (Vertex v : syndrom.getLayout().getGraph().getVertices()) {
            syndrom.getVv().getPickedVertexState().pick(v, true);
        }
        for (Edge e : syndrom.getLayout().getGraph().getEdges()) {
            syndrom.getVv().getPickedEdgeState().pick(e, true);
        }
    }

    /**
     * Sets the stage of the application so the controller can use it and intialize the close window.
     *
     * @param pStage The main stage of the application.
     */
    void setStage(Stage pStage) {
        mainStage = pStage;

        mainStage.setOnCloseRequest(event -> {
            event.consume();
            optionExitWindow();
        });
    }

    /**
     * Initializes the graph language.
     */
    private void initGraphLanguage() {
        languageGraphEnglish.selectedProperty().addListener(new LanguageGraphListener(this, languageGraphEnglish));
        languageGraphGerman.selectedProperty().addListener(new LanguageGraphListener(this, languageGraphGerman));
        languageGraphEnglish.setSelected(false);
        languageGraphGerman.setSelected(true);
    }

    /**
     * Changes the gui and graph language to german.
     */
    public void loadLanguageGuiGraphGerman() {
        languageGuiGraphEnglish.setSelected(false);
        languageGuiGraphGerman.setSelected(true);
        languageGerman.setSelected(true);
        languageGraphGerman.setSelected(true);
    }

    /**
     * Changes the gui and graph language to english.
     */
    public void loadLanguageGuiGraphEnglish() {
        languageGuiGraphGerman.setSelected(false);
        languageGuiGraphEnglish.setSelected(true);
        languageEnglish.setSelected(true);
        languageGraphEnglish.setSelected(true);
    }

    /**
     * Sorts the filter log menu.
     */
    void sortFilterLogs() {
        ArrayList<MenuItem> f = new ArrayList<>(filterLogType.getItems());
        f.sort(menuItemCompare);
        filterLogType.getItems().removeAll(f);

        Platform.runLater(() -> {
            for (MenuItem item : f) {
                filterLogType.getItems().add(item);
            }

        });
    }

    /**
     * Initializes the protocol.
     */
    private void initProtocolTree() {
        sortFilterLogs();

        for (MenuItem item : filterLogType.getItems()) {
            item.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterLogType));
        }


        historyTitledPane.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> filterLogs(analysisLogEntryName));
    }

    /**
     * Initializes the offered languages.
     */
    private void initLanguage() {
        loadLanguage = LoadLanguage.getInstance();
        languageEnglish.setSelected(false);
        languageGerman.setSelected(true);
        languageEnglish.selectedProperty().addListener(new LanguageListener(languageEnglish, this));
        languageGerman.selectedProperty().addListener(new LanguageListener(languageGerman, this));
    }

    /**
     * Initializes the overview.
     */
    private void initTree() {
        HelperFunctions helper = new HelperFunctions();
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                helper.pickElement(newValue.getValue());
            }
        });


        treeView.setOnMousePressed(new TreeViewMouseHandler(this, helper));
    }

    /**
     * Initializes the offered fonts.
     */
    private void initFonts() {
        try {
            Font roboto = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/regular/Roboto-Regular.ttf"));
            values.setRoboto(roboto);
            Font robotoSlab = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/regular/RobotoSlab-Regular.ttf"));
            values.setRobotoSlab(robotoSlab);
            Font averiaSansLibre = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/regular/AveriaSansLibre-Regular.ttf"));
            values.setAveriaSansLibr(averiaSansLibre);
            Font kalam = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/regular/Kalam-Regular.ttf"));
            values.setKalam(kalam);
            Font mali = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/regular/Mali-Regular.ttf"));
            values.setMali(mali);

        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    /**
     * Changes the font size of the sphere accordingly to the given argument.
     *
     * @param size The desired size.
     */
    void editFontSizeSphere(int size) {
        values.setFontSizeSphere(size);
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            EditFontSizeSphereLogAction editFontSizeSphereLogAction = new EditFontSizeSphereLogAction(size);
            history.execute(editFontSizeSphereLogAction);
        }
    }

    /**
     * Initializes the menu items.
     */
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
        filterEdgeTypeReinforced.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(this, EdgeArrowType.REINFORCED));
        filterEdgeTypeExtenuating.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(this, EdgeArrowType.EXTENUATING));
        filterEdgeTypeNeutral.addEventHandler(ActionEvent.ACTION, new FilterTypeHandler(this, EdgeArrowType.NEUTRAL));

        for (MenuItem item : filterLogType.getItems()) {
            item.addEventHandler(ActionEvent.ACTION, new AnalysisItemHandler(filterLogType));
        }

        logAddSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ADD_SPHERE));
        logAddVertex.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ADD_VERTICES));
        logAddEdge.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ADD_EDGES));
        logEditFontVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_FONT_VERTICES));
        logDeactivateFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.DEACTIVATE_FADEOUT));
        logEditSphereColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_SPHERE_COLOR));
        logEditEdgesStroke.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_EDGES_STROKE));
        logEditSphereSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_SPHERE_SIZE));
        logEditFontSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_FONT_SPHERE));
        logEditEdgesColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_EDGES_COLOR));
        logRemoveVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.REMOVE_VERTICES));
        logEditEdgesType.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_EDGES_TYPE));
        logRemoveSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.REMOVE_SPHERE));
        logMoveVertices.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.MOVE_VERTICES));
        logMoveSphere.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.MOVE_SPHERE));
        logActivateAnchorPointsFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT));
        logAddAnchorPoints.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ADD_ANCHOR_POINTS));
        logActivateFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ACTIVATE_FADEOUT));
        logActivateHighlight.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.ACTIVATE_HIGHLIGHT));
        logEditVerticesForm.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_FORM));
        logRemoveEdges.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.REMOVE_EDGES));
        logEditVerticesSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_SIZE));
        logRemoveAnchorPoints.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.REMOVE_ANCHOR_POINTS));
        logEditSphereFontSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_SPHERE_FONT_SIZE));
        logEditSphereAnnotation.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_SPHERE_ANNOTATION));
        logEditVertexAnnotation.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTEX_ANNOTATION));
        logEditVerticesFontSize.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_FONT_SIZE));
        logEditVerticesDrawColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_DRAW_COLOR));
        logEditVerticesFillColor.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_FILL_COLOR));
        logDeactivateHighlight.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.DEACTIVATE_HIGHLIGHT));
        logDeactivateAnchorPointsFadeout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.DEACTIVATE_ANCHOR_POINTS_FADEOUT));
        logEditSpheresLayout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_SPHERES_LAYOUT));
        logEditVerticesLayout.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, LogEntryName.EDIT_VERTICES_LAYOUT));
        logAll.addEventHandler(ActionEvent.ACTION, new AnalysisTypeHandler(this, null));
    }

    /**
     * Initializes the font comboboxes accordingly to the specific combobox for sphere or symptom.
     *
     * @param comboBox The specific combobox.
     */
    private void loadFontComboBox(ComboBox<String> comboBox) {

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
            if (event.getCode() == KeyCode.ENTER) {
                String tmpFont = comboBox.getEditor().getText();
                if (fonts.contains(tmpFont)) {
                    if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)) {
                        currentFont = tmpFont;
                        editFontSphere(tmpFont);
                    } else if (comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                        currentFont = tmpFont;
                        editFontVertex(tmpFont);
                    }

                }
                root.requestFocus();
            }
        });
    }

    /**
     * Initializes the size comboboxes accordingly to the specific combobox for sphere or symptom.
     *
     * @param comboBox The specific combobox.
     */
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
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, new ConfirmKeyComboBoxListener(this, comboBox));
    }

    /**
     * Initializes the menu for the font comboboxes for sphere and symptom.
     *
     * @param comboBox The specific combobox.
     */
    @SuppressWarnings("unchecked")
    private void loadFonts(ComboBox comboBox) {
        comboBox.setCellFactory(lv -> {
            ListCell<MenuItem> cell = fontSizeMenuItem(comboBox);

            cell.setOnMousePressed(e -> {
                if (!cell.isEmpty()) {
                    currentFont = cell.getItem().getText();
                    comboBox.getEditor().setText(currentFont);
                    if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX)) {
                        editFontSphere(currentFont);
                    } else if (comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
                        editFontVertex(currentFont);
                    }
                    root.requestFocus();
                }
            });
            return cell;
        });
        loadMenuItems(comboBox);
    }

    /**
     * Initializes the menu for the size comboboxes for sphere and symptom.
     *
     * @param comboBox The specific combobox.
     */
    @SuppressWarnings("unchecked")
    private void loadSizes(ComboBox comboBox) {

        comboBox.setCellFactory(lv -> {
            ListCell<MenuItem> cell = fontSizeMenuItem(comboBox);

            cell.setOnMousePressed(e -> {
                if (!cell.isEmpty()) {
                    currentSize = cell.getItem().getText();
                    int size = Integer.parseInt(currentSize);
                    comboBox.getEditor().setText(currentSize);
                    if (comboBox.getId().equals(SIZE_SPHERE_COMBO_BOX)) {
                        editFontSizeSphere(size);
                    } else if (comboBox.getId().equals((SIZE_SYMPTOM_COMBO_BOX))) {
                        editFontSizeVertices(size);
                    }
                    root.requestFocus();
                }
            });
            return cell;
        });
        loadMenuItems(comboBox);
    }

    /**
     * Initializes the menu for the font and font size combobox.
     *
     * @param comboBox The combobox that the menu should be initialized to.
     */
    @SuppressWarnings("unchecked")
    private void loadMenuItems(ComboBox comboBox) {
        ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();
        if (comboBox.getId().equals((SIZE_SPHERE_COMBO_BOX)) || comboBox.getId().equals(SIZE_SYMPTOM_COMBO_BOX)) {
            for (String size : sizes) {
                MenuItem sizeMenuItem = new MenuItem(size);
                menuItems.add(sizeMenuItem);
            }
        } else if (comboBox.getId().equals(FONT_SPHERE_COMBO_BOX) || comboBox.getId().equals(FONT_SYMPTOM_COMBO_BOX)) {
            for (String font : fonts) {
                MenuItem fontMenuItem = new MenuItem(font);
                menuItems.add(fontMenuItem);
            }
        }
        comboBox.setItems(menuItems);
    }

    /**
     * Creates the menuitems for the font and font size combobox.
     *
     * @param comboBox The combobox that the menuitems belong to.
     * @return A listcell of menuitems for the comboboxes.
     */
    private ListCell<MenuItem> fontSizeMenuItem(ComboBox comboBox) {
        ListCell<MenuItem> cell = new ListCell<MenuItem>() {
            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getText());
            }
        };

        cell.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                comboBox.getEditor().setText(cell.getItem().getText());
            }
        });

        return cell;
    }

    /**
     * Reloads the comboboxes of the font and font size options
     */
    void reloadComboBox() {
        fontSphereComboBox.getEditor().setText(values.getFontSphere());
        fontSymptomComboBox.getEditor().setText(values.getFontVertex());
        sizeSphereComboBox.getEditor().setText("" + values.getFontSizeSphere());
        sizeSymptomComboBox.getEditor().setText("" + values.getFontSizeVertex());
    }

    /**
     * Shows or Hides the analyis gui accordingly to the given argument.
     *
     * @param active Determines if it will be shown or hidden.
     */
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
        }
    }

    /**
     * Shows or Hides the create or edit gui accordingly to the given argument.
     *
     * @param active   Determines if it will be shown or hidden.
     * @param editMode Determines if it will be the create or edit mode.
     */
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
                resetToggleButtons();
            } else {
                overViewAccordion.getPanes().remove(historyTitledPane);
            }
        }
    }

    /**
     * Disable the gui elements for editing the template rules.
     *
     * @param disable Disable/Enable the template rules
     */
    private void disableTemplateRules(Boolean disable) {
        templateGridPane.setDisable(disable);
        templateVbox.setDisable(disable);
        sphereTableView.setDisable(disable);
        symptomTableView.setDisable(disable);
        edgeTableView.setDisable(disable);
    }

    /**
     * Resets all toggle buttons and turns on the hand select button.
     */
    private void resetToggleButtons() {
        handSelector.setSelected(true);
        anchorPointsButton.setSelected(false);
        highlight.setSelected(false);
        fadeout.setSelected(false);
    }

    /**
     * Opens the given PDF in the default application for PDF.
     *
     * @param pdfName The name of the PDF with file ending.
     */
    @FXML
    private void showPDF(String pdfName) {
        File pdfDest = Paths.get(System.getProperty("user.home"), ".graphit", pdfName).toFile();
        try {
            boolean createdNew = pdfDest.createNewFile();
            FileUtils.copyInputStreamToFile(getClass().getResourceAsStream("/" + pdfName), pdfDest);
            if (createdNew) {
                logger.debug("PDF created");
            } else {
                logger.debug("PDF already exists");
            }
        } catch (final IOException e) {
            logger.error("Unable to copy pdf");
        }

        SwingUtilities.invokeLater(() -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().open(pdfDest);
                } catch (IOException ex) {
                    logger.error("No default PDF viewing application found");
                }
            }
        });
        pdfDest.deleteOnExit();
    }

    /**
     * Opens the user guide.
     */
    @FXML
    private void showUserGuide() {
        showPDF("userGuide.pdf");
    }

    /**
     * Opens the PDF with information about us.
     */
    @FXML
    private void showAboutUs() {
        showPDF("TeamBlank.pdf");
    }

    /**
     * Opens up the exit window and closes the application if the user wants to.
     */
    @FXML
    private void optionExitWindow() {
        rulesTemplate();
        ButtonType ok = new ButtonType(loadLanguage.loadLanguagesKey("EXIT_WINDOW_CLOSE"), ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType(loadLanguage.loadLanguagesKey("EXIT_WINDOW_CANCEL"), ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, loadLanguage.loadLanguagesKey("EXIT_WINDOW_QUESTION"), ok, close);
        alert.setTitle(APPLICATION_TITLE);
        alert.setHeaderText(null);
        alert.initOwner(mainStage);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Values.LOGO_MAIN)));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                //user chose "One"
                logger.debug("SCHLIEßEN");
                System.exit(0);
                Platform.exit();
            } else {
                //user chose CANCEL or closed the dialog
                logger.debug("CANCEL");
            }
        }
    }

    /**
     * Opens the info dialog when the user creates a new graph.
     */
    public void openInfoDialogCreateGraph() {
        openDialogInfo(newFile);
    }

    /**
     * Opens the info dialog when the user opens a file.
     */
    public void openInfoDialogOpenFile() {
        openDialogInfo(openFile);
    }

    /**
     * Opens the info dialog when the user imports a template GXL file.
     */
    public void openInfoDialogImportTemplateGXL() {
        openDialogInfo(importTemplateGXL);
    }

    /**
     * Opens the info dialog when the user imports a GXL file.
     */
    public void openInfoDialogImportGXL() {
        openDialogInfo(importGXL);
    }

    /**
     * Opens the info dialog when the user exports a PDF file.
     */
    public void openInfoDialogPDF() {
        openDialogInfo(exportPDF);
    }

    /**
     * Opens the info dialog when the user prints the graph as PDF file.
     */
    public void openInfoDialogPrint() {
        openDialogInfo(print);
    }

    /**
     * Opens the info dialog accordingly to the specified menuitem, which is given as argument.
     *
     * @param menuItem The given menuitem.
     */
    private void openDialogInfo(MenuItem menuItem) {
        String okText = "EXIT_WINDOW_CLOSE_PDF";
        String cancel = "EXIT_WINDOW_CANCEL_PDF";
        String info;
        String s = menuItem.getId();
        switch (s) {
            case IMPORT_GXL:
                info = "INFO_DIALOG_IMPORT";
                break;
            case IMPORT_TEMPLATE_GXL:
                info = "INFO_DIALOG_IMPORT";
                break;
            case OPEN_FILE:
                info = "INFO_DIALOG_OPEN";
                break;
            case PRINT_FILE:
                info = "PRINT_EXPORT_INFO_DIALOG";
                break;
            case NEW_FILE:
                info = "INFO_DIALOG_NEW_FILE";
                break;
            case EXPORT_PDF:
                info = "PDF_EXPORT_INFO_DIALOG";
                break;
            default:
                throw new IllegalArgumentException();
        }

        ButtonType ok = new ButtonType(loadLanguage.loadLanguagesKey(okText), ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType(loadLanguage.loadLanguagesKey(cancel), ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, loadLanguage.loadLanguagesKey(info), ok, close);
        alert.setTitle(APPLICATION_TITLE);
        alert.setHeaderText(null);
        alert.initOwner(mainStage);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(Values.LOGO_MAIN)));

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

        Platform.runLater(() -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                switch (menuItem.getId()) {
                    case IMPORT_GXL:
                        importGXL();
                        break;
                    case IMPORT_TEMPLATE_GXL:
                        importTemplateGXL();
                        break;
                    case OPEN_FILE:
                        openFile();
                        break;
                    case PRINT_FILE:
                        printPDF();
                        break;
                    case NEW_FILE:
                        createGraph();
                        break;
                    case EXPORT_PDF:
                        exportPDF();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        });
    }

    /**
     * Updates the Overview.
     */
    void treeViewUpdate() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
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


    /**
     * Creates a new graph.
     */
    private void createGraph() {
        CreateGraphAction action = new CreateGraphAction("UntitledGraph", this);
        action.action();
        canvas.setContent(syndrom.getVv());
        satellite.setContent(syndrom.getVv2());
        zoomSlider.setValue(100);
        templateToFields();
        treeViewUpdate();
        mainStage.setTitle(syndrom.getGraphName() + " - " + APPLICATION_TITLE);
    }

    /**
     * Enlarges the selected spheres.
     */
    public void sphereEnlarge() {
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.ENLARGE);
            history.execute(editSphereSizeLogAction);
        }
    }

    /**
     * Shrinks the selected spheres.
     */
    public void sphereShrink() {
        if (!syndrom.getVv().getPickedSphereState().getPicked().isEmpty()) {
            EditSphereSizeLogAction editSphereSizeLogAction = new EditSphereSizeLogAction(SizeChange.SHRINK);
            history.execute(editSphereSizeLogAction);
        }
    }

    /**
     * Enlarges the selected symptoms.
     */
    public void vertexEnlarge() {
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.ENLARGE);
            history.execute(editVerticesSizeLogAction);
        }
    }

    /**
     * Shrinks the selected symptoms.
     */
    public void vertexShrink() {
        if (!syndrom.getVv().getPickedVertexState().getPicked().isEmpty()) {
            EditVerticesSizeLogAction editVerticesSizeLogAction = new EditVerticesSizeLogAction(SizeChange.SHRINK);
            history.execute(editVerticesSizeLogAction);
        }
    }

    /**
     * Loads all the template tables to set template rules for every single graph element.
     */
    void loadTables() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();

        if (vv == null) {
            return;
        }
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> spheres = graph.getSpheres();
        Collection<Vertex> vertices = graph.getVertices();
        Collection<Edge> edges = graph.getEdges();

        if (!spheres.isEmpty()) {
            loadSpheresTable(spheres);
        } else {
            sphereTableView.getItems().clear();
        }

        if (!vertices.isEmpty()) {
            loadVerticesTable(vertices);
        } else {
            symptomTableView.getItems().clear();
        }

        if (!edges.isEmpty()) {
            loadEdgesTable(edges);
        } else {
            edgeTableView.getItems().clear();
        }
    }

    /**
     * Loads the spheres in the specific sphere table.
     *
     * @param spheres The list of all spheres.
     */
    private void loadSpheresTable(List<Sphere> spheres) {
        if (spheres == null) {
            return;
        }


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

    /**
     * Initializes the radio buttons for the sphere template rules.
     *
     * @param pTableColumn The specific table column where the radio button should be created.
     * @param pLocked      The string which identify each table column.
     */
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

    /**
     * Loads the symptoms in the specific symptom table.
     *
     * @param symptoms The list of all symptoms.
     */
    private void loadVerticesTable(Collection<Vertex> symptoms) {
        if (symptoms == null) {
            return;
        }


        setSymptomRadioButtonTableColumn(titleSymptomCol, VERTEX_TITLE);
        setSymptomRadioButtonTableColumn(positionSymptomCol, VERTEX_POSITION);
        setSymptomRadioButtonTableColumn(styleSymptomCol, VERTEX_STYLE);

        symptomTableView.setItems(FXCollections.observableArrayList(symptoms));
    }

    /**
     * Initializes the radio buttons for the symptom template rules.
     *
     * @param pTableColumn The specific table column where the radio button should be created.
     * @param pLocked      The string which identify each table column.
     */
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

    /**
     * Loads the edges in the specific edge table.
     *
     * @param edges The list of all edges.
     */
    private void loadEdgesTable(Collection<Edge> edges) {
        if (edges == null) {
            return;
        }

        setEdgeRadioButtonTableColumn(styleEdgeCol, EDGE_STYLE);
        setEdgeRadioButtonTableColumn(edgetypeEdgeCol, EDGE_EDGE_TYPE);

        edgeTableView.setItems(FXCollections.observableArrayList(edges));
    }

    /**
     * Initializes the radio buttons for the edge template rules.
     *
     * @param pTableColumn The specific table column where the radio button should be created.
     * @param pLocked      The string which identify each table column.
     */
    private void setEdgeRadioButtonTableColumn(TableColumn<Edge, Boolean> pTableColumn, String pLocked) {
        pTableColumn.setCellValueFactory(param -> {
            Edge edge = param.getValue();
            SimpleBooleanProperty booleanProp;
            switch (pLocked) {
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

    /**
     * Associates the menu for the zoom with the slider.
     */
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

    /**
     * Initializes all the textfields for the max graph elements template rules.
     */
    private void loadTemplateTextFields() {

        maxSphereField.textProperty().addListener(new OnlyNumberTextFieldListener(maxSphereField));
        maxSymptomField.textProperty().addListener(new OnlyNumberTextFieldListener(maxSymptomField));
        maxEdgesField.textProperty().addListener(new OnlyNumberTextFieldListener(maxEdgesField));

        ChangeListener<Boolean> focusTFListener = (observable, oldValue, newValue) -> {
            if (!newValue) {
                rulesTemplate();
            }
        };
        maxSphereField.focusedProperty().addListener(focusTFListener);
        maxSymptomField.focusedProperty().addListener(focusTFListener);
        maxEdgesField.focusedProperty().addListener(focusTFListener);
    }

    /**
     * Initializes all the checkboxes for the arrow type template rules.
     */
    private void loadTemplateCheckBox() {
        reinforcedBox.selectedProperty().addListener(new TemplateCheckBoxListener(reinforcedBox, this));
        extenuatingBox.selectedProperty().addListener(new TemplateCheckBoxListener(extenuatingBox, this));
        neutralBox.selectedProperty().addListener(new TemplateCheckBoxListener(neutralBox, this));
        treeViewArrowType.selectedProperty().addListener(new TemplateCheckBoxListener(treeViewArrowType, this));
        regularExpressionBox.selectedProperty().addListener(new TemplateCheckBoxListener(regularExpressionBox, this));
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

    /**
     * Updates the undo and redo button.
     * It disables and enables the redo and undo button accordingly to the action history.
     */
    private void updateUndoRedoButton() {
        redoButton.setDisable(history.isLast());
        undoButton.setDisable(history.getCurrent() < 0);
    }

    /**
     * Calculates the shortest path between two selected symptoms and highlights them.
     */
    @FXML
    public void shortestPath() {
        if (analysisPathCheckBox.isSelected() && calculateEndpoints()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphShortestPathAction analysisGraphAction = new AnalysisGraphShortestPathAction();
            analysisGraphAction.action();
        } else {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            disableAllCheckBoxes();
        }
    }

    /**
     * Calculates all paths between two selected symptoms and highlights them.
     */
    @FXML
    public void allPaths() {
        if (analysisPathCheckBox.isSelected() && calculateEndpoints()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphAllPathsAction analysisGraphAction = new AnalysisGraphAllPathsAction();
            analysisGraphAction.action();
        } else {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            disableAllCheckBoxes();
        }
    }

    /**
     * Calculates all the chain of edges in the graph and highlights them.
     */
    @FXML
    public void chainOfEdges() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphEdgeChainsAction analysisGraphAction = new AnalysisGraphEdgeChainsAction();
            analysisGraphAction.action();
        }
    }

    /**
     * Calculates all the convergent branches in the graph and highlights them.
     */
    @FXML
    public void convergentBranches() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphConvergentBranchesAction analysisGraphAction = new AnalysisGraphConvergentBranchesAction();
            analysisGraphAction.action();
        }
    }

    /**
     * Calculates all the divergent branches in the graph and highlights them.
     */
    @FXML
    public void divergentBranches() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphDivergentBranchesAction analysisGraphAction = new AnalysisGraphDivergentBranchesAction();
            analysisGraphAction.action();
        }
    }

    /**
     * Calculates all the branches in the graph and highlights them.
     */
    @FXML
    public void branches() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphBranchesAction analysisGraphAction = new AnalysisGraphBranchesAction();
            analysisGraphAction.action();
        }
    }

    /**
     * Calculates all the cycles in the graph and highlights them.
     */
    @FXML
    public void analysisCycles() {
        if (analysisOptions.isSelected()) {
            ResetVvAction resetAction = new ResetVvAction();
            resetAction.action();
            AnalysisGraphCyclesAction analysisGraphAction = new AnalysisGraphCyclesAction();
            analysisGraphAction.action();
        }
    }

    /**
     * Updates the logs accordingly to the selected filter log option.
     */
    @FXML
    public void synAnalysis() {
        filterLogs(analysisLogEntryName);
    }

    /**
     * Updates the graph.
     */
    @Override
    public void updateGraph() {
        Platform.runLater(() -> {
            treeViewUpdate();
            updateUndoRedoButton();
            updateAutoLayoutButton();
            updateArrowMenuButton();
            loadTables();
        });
    }

    @Override
    public void updateFunctionMode(FunctionMode mode) {
        // nothing to do
    }

    @Override
    public void updateEditMode() {
        // nothing to do
    }

    /**
     * Updates the gui if a new graph will be created.
     */
    @Override
    public void updateNewGraph() {
        treeViewUpdate();
        loadTables();
        updateUndoRedoButton();
        updateAutoLayoutButton();
        updateArrowMenuButton();
    }

    /**
     * Filter the logs accordingly to the given argument.
     *
     * @param entryName The desired log.
     */
    void filterLogs(LogEntryName entryName) {
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
                                loadFilterLogs(filterLog, rootItem);
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

    /**
     * Formats and loads the logs to the log overview.
     *
     * @param filterLog The list of logs.
     * @param rootItem  The treeitem that the logs should be loaded to.
     */
    @SuppressWarnings("unchecked")
    private void loadFilterLogs(List<Log> filterLog, TreeItem<Object> rootItem) {
        for (Log log : filterLog) {
            String time = logToStringConverter.convert(log);
            String index = time.substring(0, time.indexOf('\n'));
            time = time.replaceFirst(index, "");
            time = time.trim();
            String name = time.substring(0, time.indexOf('\n'));
            time = time.replaceFirst(name, "");
            time = time.trim();
            String parameter = time.substring(0, time.indexOf('\n'));
            time = time.replace(parameter, "");
            time = time.trim();
            TreeItem<Object> logIndexName = new TreeItem<>(index + ": " + name);
            TreeItem<Object> logTime = new TreeItem<>(time);

            TreeItem<Object> logInformation;
            if (parameter.contains(";")) {
                logInformation = new TreeItem<>(extractStart(parameter));
                List<String> entries = evaluateEntries(parameter);
                for (String s : entries) {
                    TreeItem<Object> entry = new TreeItem<>(s);
                    logInformation.getChildren().add(entry);
                }
            } else {
                logInformation = new TreeItem<>(parameter);
            }

            logIndexName.getChildren().addAll(logTime, logInformation);
            logIndexName.setExpanded(true);
            rootItem.getChildren().add(logIndexName);
        }
    }

    /**
     * Extracts the beginning of the parameters from the rest of the parameters in a log.
     *
     * @param parameters The parameters that need to be separated.
     * @return The beginning of the parameters from the logs.
     */
    private String extractStart(String parameters) {
        int indexOfDoublePoint = parameters.indexOf(':');
        return parameters.substring(0, indexOfDoublePoint);
    }

    /**
     * Sorts the parameters from a log from a singe line into a list to show them in the log overview.
     *
     * @param parameters The parameters that need to be sorted.
     * @return The list of parameters.
     */
    private List<String> evaluateEntries(String parameters) {
        String localParameters = parameters;
        int indexOfDoublePoint = parameters.indexOf(':');
        localParameters = localParameters.substring(indexOfDoublePoint);
        String[] strings = localParameters.split(";");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < strings.length - 1; i++) {
            if (i == 0) {
                list.add(strings[i].substring(1).trim());
            } else {
                list.add(strings[i].trim());
            }
        }
        return list;
    }

    /**
     * It checks if at least one vertex is picked.
     * If not, it will show an alert that at least one vertex has to be picked.
     *
     * @return true if at least one syndrom is picked
     */
    boolean isAtLeastOnePicked() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().isEmpty()) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText(loadLanguage.loadLanguagesKey("ANALYSIS_ALERT"), true, false);
            return false;
        }
        return true;
    }

    /**
     * Disables all other checkboxes, because only successor and predecessor can be selected at the same time in the
     * analysis-mode.
     */
    void disableAllCheckBoxes() {
        analysisOptions.setSelected(false);
        analysisPathCheckBox.setSelected(false);
        analysisSuccessor.setSelected(false);
        analysisPredecessor.setSelected(false);
        if (analysisPredecessor.isSelected() || analysisSuccessor.isSelected()) {
            amountSymptomTextField.setDisable(false);
        }
    }

    /**
     * Calculates the Endpoints in the graph. It checks, whether two vertices are selected.
     *
     * @return true if the just 2 syndrom are picked, false if more or less
     */
    boolean calculateEndpoints() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() != 2) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("ANALYSIS_OPTION_ALERT", true, true);
            return false;
        }
        return true;
    }
}
