package config;

import lombok.*;

import java.awt.*;

/**
 * Defines constants of the project
 */
@Value
public class Constants {
    @Getter
    private static final Color defaultColorSphere = null;
    @Getter
    private static final Color defaultFillColorSphere = null;
    @Getter
    private static final Color defaultFillColorVertex = null;
    @Getter
    private static final Color defaultColorEdge = null;
    @Getter
    private static final double widthSphere = -1;
    @Getter
    private static final double heightSphere = -1;
    @Getter
    private static final String defaultNameSphere = "";
    @Getter
    private static final String defaultNameVertex = "";
    @Getter
    private static final Shape defaultShapeVertex = null;
    @Getter
    private static final Stroke defaultStrokeEdge = null;
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


