package log_management.parameters;

import graph.graph.EdgeArrowType;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import gui.properties.Language;

/**
 * Creates names of the enums for pretty printing.
 *
 * @author Anthony Mendil
 */
public class EnumNameCreator {

    /**
     * Throws an exception if tried to create.
     */
    private EnumNameCreator() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Determines the pretty string for the arrow type depending on the language.
     *
     * @param edgeArrowType The arrow type.
     * @param language      The language.
     * @return The pretty string for the arrow type.
     */
    public static String edgeArrowTypeTranslator(EdgeArrowType edgeArrowType, Language language) {
        if (language == Language.ENGLISH) {
            switch (edgeArrowType) {
                case REINFORCED:
                    return "Reinforced";
                case EXTENUATING:
                    return "Extenuating";
                default:
                    return "Indefinite";
            }
        } else {
            switch (edgeArrowType) {
                case REINFORCED:
                    return "Verstärkend";
                case EXTENUATING:
                    return "Abschwächend";
                default:
                    return "Unbestimmt";
            }
        }
    }

    /**
     * Determines the pretty string for the shape type depending on the language.
     *
     * @param vertexShapeType The shape type.
     * @param language        The language.
     * @return The pretty string for the shape type.
     */
    public static String vertexShapeTypeTranslator(VertexShapeType vertexShapeType, Language language) {
        if (language == Language.ENGLISH) {
            if (vertexShapeType == VertexShapeType.CIRCLE) {
                return "Circle";
            } else {
                return "Rectangle";
            }
        } else {
            if (vertexShapeType == VertexShapeType.CIRCLE) {
                return "Kreis";
            } else {
                return "Rechteck";
            }
        }
    }

    /**
     * Determines the pretty string for the stroke type depending on the language.
     *
     * @param strokeType The stroke type.
     * @param language   The language.
     * @return The pretty string for the stroke type.
     */
    public static String strokeTypeTranslator(StrokeType strokeType, Language language) {
        if (language == Language.ENGLISH) {
            switch (strokeType) {
                case BASIC:
                    return "Basic";
                case DOTTED_WEIGHT:
                    return "Dotted (weight)";
                case DASHED_WEIGHT:
                    return "Dashed (weight)";
                case DOTTED:
                    return "Dotted";
                case BASIC_WEIGHT:
                    return "Basic (weight)";
                default:
                    return "Dashed";
            }
        } else {
            switch (strokeType) {
                case BASIC:
                    return "Durchgezogen";
                case DOTTED_WEIGHT:
                    return "Gepunkted (dick)";
                case DASHED_WEIGHT:
                    return "Gestrichelt (dick)";
                case DOTTED:
                    return "Gepunktet";
                case BASIC_WEIGHT:
                    return "Durchgezogen (dick)";
                default:
                    return "Gestrichelt";
            }
        }
    }
}
