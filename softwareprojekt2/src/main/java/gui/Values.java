package gui;

import com.google.inject.Singleton;
import graph.graph.EdgeArrowType;
import graph.graph.FunctionMode;
import gui.GraphButtonType;
import lombok.*;

import java.awt.*;

/**
 * Defines values for the syndrom. This are default values. The values represent the currently selected values from the
 * GUI.
 * Its a Singleton Instance.
 */
@Data
@Singleton
public class Values {

    /**
     * the fill paint color
     */
    @NonNull
    private Paint fillPaintSphere = null;

    /**
     * the default width sphere
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final double defaultWidthSphere = -1;

    /**
     * the default height of a sphere
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final double defaultHeightSphere = -1;

    /**
     * the default annotation of a sphere
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationSphere = "";

    /**
     * the font of a sphere
     */
    private Font fontSphere = null;

    /**
     * default shape of a sphere
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final Shape defaultShapeSphere = null;

    /**
     * the fill paint color of a vertex
     */
    private Paint fillPaintVertex = null;

    /**
     * the draw paint color pf a vertex
     */
    private Paint drawPaintVertex = null;

    /**
     * the default annotation of a vertex
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationVertex = "";

    /**
     * the shape of a vertex
     */
    private Shape shapeVertex = null;

    /**
     * the font of a vertex
     */
    private Font fontVertex = null;

    /**
     * the default size of a vertex
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final int defaultSizeVertex = -1;

    /**
     * the highlight color for vertices
     */
    private Paint highlightPaintVertex = null;

    /**
     * the highlight color one for highlighting selection/ picking
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final Paint defaultHighlightProPaintVertex = null;

    /**
     * the highlight color two for highlighting selection/ picking
     */
    private static final Paint defaultHighlightContraPaintVertex = null;

    /**
     * the highlight stroke
     */
    private static final Stroke defaultHighlightStrokeVertex = null;

    /**
     * the edge paint color of a edge
     */
    private Paint edgePaint = null;


    /**
     * the edge weight
     */
    private int edgeWeight = -1;

    /**
     * the edge stroke
     */
    private Stroke strokeEdge = null;


    /**
     * the edge arrow type
     */
    private EdgeArrowType edgeArrowType = null;


    /**
     * the default layout size
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultLayoutSize = null;

    /**
     * the default satellite layout size
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final Dimension defaultSatelliteLayoutSize = null;

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

    public Values(){
       throw new UnsupportedOperationException();
   }
}


