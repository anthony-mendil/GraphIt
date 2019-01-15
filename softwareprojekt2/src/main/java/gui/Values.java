package gui;

import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import javafx.collections.ObservableMap;
import javafx.scene.text.FontWeight;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.xml.stream.events.Namespace;
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
     * The default annotation of a sphere.
     */
    @Setter(AccessLevel.NONE)
    private final Map<String, String> defaultAnnotationSphere;

    /**
     * The default annotation of a vertex.
     */
    @Setter(AccessLevel.NONE)
    private final Map<String, String> defaultAnnotationVertex;
    /**
     * The default size of a vertex.
     */
    @Setter(AccessLevel.NONE)
    private final int defaultSizeVertex = 50;
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
     * The default layout size.
     */
    @Setter(AccessLevel.NONE)
    private Dimension defaultLayoutSize = new Dimension(985,540);

    @Setter(AccessLevel.NONE)
    private Dimension defaultLayoutVVSize = new Dimension(2000,2000);
    /**
     * The default satellite layout size.
     */
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultSatelliteLayoutSize = null;
    /**
     * The fill paint color.
     */
    private Color fillPaintSphere = new Color(174, 208, 197);
    /**
     * The font of a sphere.
     */
    private String fontSphere = "Arial";
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
    private Color fillPaintVertex = new Color(123,255,51);
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
    private String fontVertex = "Arial";
    /**
     * The highlight color for vertices.
     */
    private Paint highlightPaintVertex;
    /**
     * The edge paint color of an edge.
     */
    private Paint edgePaint;
    /**
     * The edge weight.
     */
    private int edgeWeight;
    /**
     * The edge stroke.
     */
    private StrokeType strokeEdge;
    /**
     * The edge arrow type.
     */
    private EdgeArrowType edgeArrowType;
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
    private String objectLanguage;
    private static Values instance;

    private ObservableMap<String, Object> namespace;

    @Setter(AccessLevel.NONE)
    private final javafx.scene.text.Font actionTextInfo =  javafx.scene.text.Font.font("System Regular", FontWeight
            .NORMAL, 12);
    @Setter(AccessLevel.NONE)
    private final javafx.scene.text.Font actionTextAlert =  javafx.scene.text.Font.font("System Regular", FontWeight
            .EXTRA_BOLD, 14);

    @Setter(AccessLevel.NONE)
    private final javafx.scene.paint.Color actionTextColorAlert =  javafx.scene.paint.Color.rgb(160, 12, 12, 1);

    @Setter(AccessLevel.NONE)
    private final javafx.scene.paint.Color actionTextColorInfo =  javafx.scene.paint.Color.BLACK;



    private Values() {
        defaultAnnotationSphere = new HashMap<>();
        defaultAnnotationSphere.put("de","Sphäre Überschrift");
        defaultAnnotationSphere.put("en","Sphere Headline");

        defaultAnnotationVertex = new HashMap<>();
        defaultAnnotationVertex.put("de","Vertex");
        defaultAnnotationVertex.put("en","vertex");
    }

    public static Values getInstance(){
        if (instance == null){
            instance = new Values();
        }
        return instance;
    }
}


