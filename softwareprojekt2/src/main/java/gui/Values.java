package gui;

import com.google.inject.Singleton;
import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import lombok.*;

import java.awt.*;

/**
 * Defines values for the syndrom. These are default values. The values represent the currently selected values from the
 * GUI. Its a singleton instance.
 */
@Data
@Singleton
public class Values {

    /**
     * The default width sphere.
     */
    @Setter(AccessLevel.NONE)
    private static final double defaultWidthSphere = -1;
    /**
     * The default height of a sphere.
     */
    @Setter(AccessLevel.NONE)
    private static final double defaultHeightSphere = -1;
    /**
     * The default annotation of a sphere.
     */
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationSphere = "";
    /**
     * The default shape of a sphere.
     */
    @Setter(AccessLevel.NONE)
    private static final Shape defaultShapeSphere = null;
    /**
     * The default annotation of a vertex.
     */
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationVertex = "";
    /**
     * The default size of a vertex.
     */
    @Setter(AccessLevel.NONE)
    private static final int defaultSizeVertex = -1;
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
    private static final Dimension defaultLayoutSize = null;
    /**
     * The default satellite layout size.
     */
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultSatelliteLayoutSize = null;
    /**
     * The fill paint color.
     */
    private Paint fillPaintSphere;
    /**
     * The font of a sphere.
     */
    private String fontSphere;
    /**
     * The font size of a sphere annotation.
     */
    private int fontSizeSphere;
    /**
     * The fonz size of a vertex annotation.
     */
    private int fontSizeVertex;
    /**
     * The fill paint color of a vertex.
     */
    private Paint fillPaintVertex;
    /**
     * The draw paint color of a vertex.
     */
    private Paint drawPaintVertex;
    /**
     * The shape of a vertex.
     */
    private VertexShapeType shapeVertex;
    /**
     * The font of a vertex.
     */
    private String fontVertex;
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


    public Values() {
        throw new UnsupportedOperationException();
    }
}


