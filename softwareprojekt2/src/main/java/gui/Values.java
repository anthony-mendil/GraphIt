package gui;

import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import gui.properties.Language;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines values for the syndrom. These are default values. The values represent the currently selected values from the
 * GUI. Its a singleton instance.
 *
 * @author Jacky Philipp Mach
 */
@Data
public class Values {

    /**
     * The relative path of the logo.
     */
    public static final String LOGO_MAIN = "/GraphItLogo.png";
    /**
     * The default width sphere.
     */
    @Setter(AccessLevel.NONE)
    public static final double DEFAULT_WIDTH_SPHERE = 200;
    /**
     * The default height of a sphere.
     */
    @Setter(AccessLevel.NONE)
    public static final double DEFAULT_HEIGHT_SPHERE = 200;
    /**
     * The default size of a vertex.
     */
    @Setter(AccessLevel.NONE)
    public static final int DEFAULT_SIZE_VERTEX = 50;
    /**
     * The values instance.
     */
    private static Values instance;
    /**
     * Standard-value of the anchor-highlight-color.
     */
    @Setter(AccessLevel.NONE)
    private final Color anchorHighlight = new Color(204, 0, 0);
    /**
     * The font used font the action alert messages.
     */
    @Setter(AccessLevel.NONE)
    private final javafx.scene.text.Font actionTextAlert = javafx.scene.text.Font.font("System Regular", FontWeight
            .EXTRA_BOLD, 14);
    /**
     * The stage of the application.
     */
    private Stage mainStage;
    /**
     * Standard-size of the visualization-viewer.
     */
    @Setter(AccessLevel.NONE)
    private Dimension defaultLayoutVVSize = new Dimension(2000, 1500);
    /**
     * The fill paint color.
     */
    private Color fillPaintSphere = new Color(187, 222, 250);
    /**
     * The font of a sphere.
     */
    private String fontSphere = "Roboto";
    /**
     * The font size of a sphere annotation.
     */
    private int fontSizeSphere = 12;
    /**
     * The font size of a vertex annotation.
     */
    private int fontSizeVertex = 12;
    /**
     * The fill paint color of a vertex.
     */
    private Color fillPaintVertex = new Color(255, 224, 130);
    /**
     * True if the anchor points are highlighted in the gui,
     * False if the anchor points are not highlighted in the gui.
     */
    private boolean showAnchor = false;
    /**
     * The draw paint color of a vertex.
     */
    private Color drawPaintVertex = Color.DARK_GRAY;
    /**
     * The shape of a vertex.
     */
    private VertexShapeType shapeVertex = VertexShapeType.CIRCLE;
    /**
     * The font of a vertex.
     */
    private String fontVertex = "Roboto";
    /**
     * The highlight color for vertices.
     */
    private Color highlightPaint = new Color(255, 48, 57);
    /**
     * The edge paint color of an edge.
     */
    private Color edgePaint = Color.BLACK;
    /**
     * The edge stroke.
     */
    private StrokeType strokeEdge = StrokeType.BASIC;
    /**
     * The edge arrow type.
     */
    private EdgeArrowType edgeArrowType = EdgeArrowType.REINFORCED;
    /**
     * The actual graph button type, pressed in the gui.
     */
    private GraphButtonType graphButtonType;
    /**
     * The current application mode.
     */
    private FunctionMode mode;
    /**
     * The current language of the gui.
     */
    private Language guiLanguage = Language.GERMAN;
    /**
     * The current language of the graph.
     */
    private Language graphLanguage = Language.GERMAN;
    /**
     * The current action text from the gui.
     */
    private Text currentActionText = new Text("");
    /**
     * The canvas of the gui, containing the visualisationViewer.
     */
    private Node canvas;
    /**
     * The hBox of the gui, containing the action alert.
     */
    private HBox hBox;
    /**
     * The toggleButton of the gui, used for the animation fade out.
     */
    private CheckMenuItem animationFadeout;
    /**
     * The mouse position text, coordinate x.
     */
    private Text positionMouseX = new Text("");
    /**
     * The mouse position text, coordinate y.
     */
    private Text positionMouseY = new Text("");
    /**
     * The roboto font.
     */
    private Font roboto;
    /**
     * The robotoSlab font.
     */
    private Font robotoSlab;
    /**
     * The averiaSansLibr font.
     */
    private Font averiaSansLibr;
    /**
     * The kalam font.
     */
    private Font kalam;
    /**
     * The mali font.
     */
    private Font mali;

    /**
     * @return The instance of the Value Class.
     */
    public static Values getInstance() {
        if (instance == null) {
            instance = new Values();
        }
        return instance;
    }

    /**
     * @return The default annotation map for the vertices.
     */
    public Map<String, String> getDefaultAnnotationVertex() {
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), "Symptom");
        annotation.put(Language.ENGLISH.name(), "Symptom");
        return annotation;
    }

    /**
     * @return The default annotation map for the spheres.
     */
    public Map<String, String> getDefaultAnnotationSphere() {
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), "Sphäre");
        annotation.put(Language.ENGLISH.name(), "Sphere");
        return annotation;
    }
}


