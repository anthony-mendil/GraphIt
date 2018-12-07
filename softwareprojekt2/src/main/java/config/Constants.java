package config;

import lombok.*;

import java.awt.*;

/**
 * Defines constants of the project
 */
@Value
public class Constants {
    @Getter
    private static final Color defaultColorSphere = Color.GREEN;
    @Getter
    private static final Color defaultFillColorSphere = Color.BLUE;
    @Getter
    private static final double widthSphere = 200;
    @Getter
    private static final double heightSphere = 200;
    @Getter
    private static final String defaultNameSphere = "Sphere Überschrift";
    @Getter
    private static final Shape defaultShapeVertex = null;
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


