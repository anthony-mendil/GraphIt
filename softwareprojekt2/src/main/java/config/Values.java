package config;

import graph.graph.EdgeArrowType;
import lombok.*;

import java.awt.*;

/**
 * Defines values for the syndrom. This are default values.
 * The values are represent the currently selected values from the GUI.
 */
@Data
public class Values {
    /**
     *
     */
    private static final Paint defaultDrawColorSphere = null;

    /**
     *
     */
    private static final Paint defaultFillColorSphere = null;

    /**
     *
     */
    private static final double widthSphere = -1;

    /**
     *
     */
    private static final double heightSphere = -1;

    /**
     *
     */
    private static final String defaultNameSphere = "";

    /**
     *
     */
    private static final Font defaultFontSphere = null;

    /**
     *
     */
    private static final Shape defaultShapeSphere = null;

    /**
     *
     */
    private static final Paint defaultFillColorVertex = null;

    /**
     *
     */
    private static final Paint defaultDrawColorVertex = null;

    /**
     *
     */
    private static final String defaultNameVertex = "";

    /**
     *
     */
    private static final Shape defaultShapeVertex = null;

    /**
     *
     */
    private static final Font defaultFontVertex = null;

    /**
     *
     */
    private static final Font defaultLabelVertex = null;

    /**
     *
     */
    private static final int defaultSizeVertex = -1;

    /**
     *
     */
    private static final int defaultRatioVertex = -1;

    /**
     *
     */
    private static final Paint defaultHighlightPaintVertex = null;

    /**
     *
     */
    private static final Stroke defaultHighlightStrokeVertex = null;

    /**
     *
     */
    private static final Stroke defaultStrokeVertex = null;


    /**
     *
     */
    private static final Paint defaultEdgeDrawPaint = null;

    /**
     *
     */
    private static final Paint defaultEdgeFillPaint = null;

    /**
     *
     */
    private static final int defaultEdgeWeight = -1;

    /**
     *
     */
    private static final Stroke defaultStrokeEdge = null;

    /**
     *
     */
    private static final Paint defaultEdgeArrowDrawPaint = null;

    /**
     *
     */
    private static final EdgeArrowType edgeArrowType = null;

    /**
     *
     */
    private static final Paint defaultEdgeArrowFillPaint = null;

    /**
     *
     */
    private static final Dimension defaultLayoutSize = null;

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


