package gui;

import com.google.inject.Singleton;
import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import lombok.*;

import java.awt.*;

/**
 * Defines values for the syndrom. This are default values. The values represent the currently selected values from the
 * GUI. Its a Singleton Instance.
 */
@Data
@Singleton
public class Values {

    /**
     * the default width sphere
     */
    @Setter(AccessLevel.NONE)
    private static final double defaultWidthSphere = -1;
    /**
     * the default height of a sphere
     */
    @Setter(AccessLevel.NONE)
    private static final double defaultHeightSphere = -1;
    /**
     * the default annotation of a sphere
     */
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationSphere = "";
    /**
     * default shape of a sphere
     */
    @Setter(AccessLevel.NONE)
    private static final Shape defaultShapeSphere = null;
    /**
     * the default annotation of a vertex
     */
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationVertex = "";
    /**
     * the default size of a vertex
     */
    @Setter(AccessLevel.NONE)
    private static final int defaultSizeVertex = -1;
    /**
     * the highlight color one for highlighting selection/ picking
     */
    @Setter(AccessLevel.NONE)
    private static final Paint defaultHighlightProPaintVertex = null;
    /**
     * the highlight color two for highlighting selection/ picking
     */
    @Setter(AccessLevel.NONE)
    private static final Paint defaultHighlightContraPaintVertex = null;
    /**
     * the highlight stroke
     */
    @Setter(AccessLevel.NONE)
    private static final Stroke defaultHighlightStrokeVertex = null;
    /**
     * the default layout size
     */
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultLayoutSize = null;
    /**
     * the default satellite layout size
     */
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultSatelliteLayoutSize = null;
    /**
     * the fill paint color
     */
    private Paint fillPaintSphere;
    /**
     * the font of a sphere
     */
    private Font fontSphere;
    /**
     * the fill paint color of a vertex
     */
    private Paint fillPaintVertex;
    /**
     * the draw paint color pf a vertex
     */
    private Paint drawPaintVertex;
    /**
     * the shape of a vertex
     */
    private Shape shapeVertex;
    /**
     * the font of a vertex
     */
    private Font fontVertex;
    /**
     * the highlight color for vertices
     */
    private Paint highlightPaintVertex;
    /**
     * the edge paint color of a edge
     */
    private Paint edgePaint;
    /**
     * the edge weight
     */
    private int edgeWeight;
    /**
     * the edge stroke
     */
    private Stroke strokeEdge;
    /**
     * the edge arrow type
     */
    private EdgeArrowType edgeArrowType;
    /**
     * the actual graph button type, pressed in the gui
     */
    private GraphButtonType graphButtonType;

    /**
     * the current application mode
     */
    private FunctionMode mode;

    /**
     * the current language of the objects
     */
    private String objectLanguage;

    public Values() {
        throw new UnsupportedOperationException();
    }
}


