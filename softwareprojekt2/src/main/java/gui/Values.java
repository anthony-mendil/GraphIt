package gui;

import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import gui.properties.Language;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines values for the syndrom. These are default values. The values represent the currently selected values from the
 * GUI. Its a singleton instance.
 */
@Data
public class Values {

    /**
     * The first highlight color for highlighting selection/ picking.
     */
    @Setter(AccessLevel.NONE)
    private static final Paint defaultHighlightProPaintVertex = null;
    /**
     * The second highlight color for highlighting selection/ picking.
     */
    @Setter(AccessLevel.NONE)
    private static final Paint defaultHighlightContraPaintVertex = null;
    /**
     * The highlight stroke.
     */
    @Setter(AccessLevel.NONE)
    private static final StrokeType defaultHighlightStrokeVertex = null;
    /**
     * The default satellite layout size.
     */
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultSatelliteLayoutSize = null;
    private static Values instance;
    /**
     * The default width sphere.
     */
    @Setter(AccessLevel.NONE)
    private final double defaultWidthSphere = 200;
    /**
     * The default height of a sphere.
     */
    @Setter(AccessLevel.NONE)
    private final double defaultHeightSphere = 200;
    /**
     * The default size of a vertex.
     */
    @Setter(AccessLevel.NONE)
    private final int defaultSizeVertex = 50;
    @Setter(AccessLevel.NONE)
    private final Color anchorHighlight = new Color(204, 0, 0);
    @Setter(AccessLevel.NONE)
    private final javafx.scene.text.Font actionTextInfo = javafx.scene.text.Font.font("System Regular", FontWeight
            .NORMAL, 12);
    @Setter(AccessLevel.NONE)
    private final javafx.scene.text.Font actionTextAlert = javafx.scene.text.Font.font("System Regular", FontWeight
            .EXTRA_BOLD, 14);
    @Setter(AccessLevel.NONE)
    private final javafx.scene.paint.Color actionTextColorAlert = javafx.scene.paint.Color.rgb(160, 12, 12, 1);
    @Setter(AccessLevel.NONE)
    private final javafx.scene.paint.Color actionTextColorInfo = javafx.scene.paint.Color.BLACK;
    @Setter(AccessLevel.NONE)
    private final int minScale = 10;
    @Setter(AccessLevel.NONE)
    private final int maxScale = 200;
    /**
     * The default layout size.
     */
    private Dimension defaultLayoutSize = new Dimension(985, 540);
    @Setter(AccessLevel.NONE)
    private Dimension defaultLayoutVVSize = new Dimension(2000, 1500);
    /**
     * The fill paint color.
     */
    private Color fillPaintSphere = new Color(174, 208, 197);
    /**
     * The font of a sphere.
     */
    private String fontSphere = "Roboto";
    /**
     * The font size of a sphere annotation.
     */
    private int fontSizeSphere = 12;
    /**
     * The fonz size of a vertex annotation.
     */
    private int fontSizeVertex = 12;
    /**
     * The fill paint color of a vertex.
     */
    private Color fillPaintVertex = new Color(123, 255, 51);
    /**
     * true if the anchor points are highlighted in the gui
     * false if the anchor points are not highlighted in the gui
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
     * The edge weight.
     */
    private int edgeWeight = 1;
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
     * The current language of the objects.
     */
    private Language objectLanguage;
    /**
     * The current language of the gui.
     */
    private Language guiLanguage = Language.GERMAN;
    /**
     * the current language of the graph.
     */
    private Language graphLanguage = Language.GERMAN;
    private Text currentActionText = new Text("");
    private Node canvas;
    private HBox hBox;
    private int scale;
    private Font roboto;
    private Font robotoSlab;
    private Font averiaSansLibr;
    private Font kalam;
    private Font mali;


    private Values() {

    }

    public static Values getInstance() {
        if (instance == null) {
            instance = new Values();
        }
        return instance;
    }

    public Map<String, String> getDefaultAnnotationVertex() {
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), "Symptom");
        annotation.put(Language.ENGLISH.name(), "Symptom");
        return annotation;
    }

    public Map<String, String> getDefaultAnnotationSphere() {
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), "Sphäre");
        annotation.put(Language.ENGLISH.name(), "Sphere");
        return annotation;
    }
}


