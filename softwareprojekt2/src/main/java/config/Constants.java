package config;

import lombok.*;

import java.awt.*;

/**
 * Defines constants of the project
 */
@Value
public class Constants {
    @Getter
    private static final Paint defaultDrawColorSphere = null;
    @Getter
    private static final Paint defaultFillColorSphere = null;
    @Getter
    private static final double widthSphere = -1;
    @Getter
    private static final double heightSphere = -1;
    @Getter
    private static final String defaultNameSphere = "";
    @Getter
    private static final Font defaultFontSphere = null;
    @Getter
    private static final Shape defaultShapeSphere = null;


    @Getter
    private static final Paint defaultFillColorVertex = null;
    @Getter
    private static final Paint defaultDrawColorVertex = null;
    @Getter
    private static final String defaultNameVertex = "";
    @Getter
    private static final Shape defaultShapeVertex = null;
    @Getter
    private static final Font defaultFontVertex = null;
    @Getter
    private static final Font defaultLabelVertex = null;
    @Getter
    private static final int defaultSizeVertex = -1;
    @Getter
    private static final int defaultRatioVertex = -1;
    @Getter
    private static final Paint defaultHighlightPaintVertex = null;
    @Getter
    private static final Stroke defaultHighlightStrokeVertex = null;
    @Getter
    private static final Stroke defaultStrokeVertex = null;


    @Getter
    private static final Paint defaultEdgeDrawPaint = null;
    @Getter
    private static final Paint defaultEdgeFillPaint = null;
    @Getter
    private static final int defaultEdgeWeight = -1;
    @Getter
    private static final Stroke defaultStrokeEdge = null;
    @Getter
    private static final Paint defaultEdgeArrowDrawPaint = null;
    @Getter
    private static final Paint defaultEdgeArrowFillPaint = null;

    @Getter
    private static final Dimension defaultLayoutSize = null;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Constants instance;

    /**
     * @return singleton instance of constants
     */
    public static Constants getInstance(){
        if (instance == null){
            return new Constants();
        }
        return instance;
    }
}


