package graph.graph;

import lombok.*;

import java.awt.*;

/**
 * Defines values for the syndrom. This are default values.
 * The values represent the currently selected values from the GUI.
 */
@Data
public class Values {
    /**
     * the draw paint color of a sphere
     */
    @NonNull
    private static Paint drawPaintSphere = null;

    /**
     * the fill paint color
     */
    @NonNull
    private static Paint fillPaintSphere = null;

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
    private static Font fontSphere = null;

    /**
     * default shape of a sphere
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final Shape defaultShapeSphere = null;

    /**
     * the fill paint color of a vertex
     */
    private static Paint fillPaintVertex = null;

    /**
     * the draw paint color pf a vertex
     */
    private static Paint drawPaintVertex = null;

    /**
     * the default annotation of a vertex
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final String defaultAnnotationVertex = "";

    /**
     * the shape of a vertex
     */
    private static Shape shapeVertex = null;

    /**
     * the font of a vertex
     */
    private static Font fontVertex = null;

    /**
     * the default size of a vertex
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final int defaultSizeVertex = -1;

    /**
     * the highlight color for vertices
     */
    private static Paint highlightPaintVertex = null;

    /**
     * the highlight color one for highlighting selection/ picking
     */
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
    private static Paint edgePaint = null;


    /**
     * the edge weight
     */
    private static int edgeWeight = -1;

    /**
     * the edge stroke
     */
    private static Stroke strokeEdge = null;


    /**
     * the edge arrow type
     */
    private static EdgeArrowType edgeArrowType = null;


    /**
     * the default layout size
     */
    private static final Dimension defaultLayoutSize = null;

    /**
     * the values instance
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Values instance;

    /**
     * @return singleton instance of constants
     */
    public static Values getInstance(){
        if (instance == null){
            return new Values();
        }
        return instance;
    }
}


