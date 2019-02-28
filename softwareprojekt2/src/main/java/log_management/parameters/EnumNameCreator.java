package log_management.parameters;

import graph.graph.EdgeArrowType;
import graph.graph.SizeChange;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import gui.properties.Language;

public class EnumNameCreator {

    private EnumNameCreator() {
        throw new IllegalStateException("utility class");
    }

    public static String edgeArrowTypeTranslator(EdgeArrowType edgeArrowType, Language language) {
        if (language == Language.ENGLISH) {
            switch (edgeArrowType) {
                case REINFORCED:
                    return "Reinforced";
                case EXTENUATING:
                    return "Extenuating";
                default:
                    return "Neutral";
            }
        } else {
            switch (edgeArrowType) {
                case REINFORCED:
                    return "Verstärkend";
                case EXTENUATING:
                    return "Abschwächend";
                default:
                    return "Neutral";
            }
        }
    }

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

    public static String sizeChangeTransaltor(SizeChange sizeChange, Language language) {
        if (language == Language.ENGLISH) {
            if (sizeChange == SizeChange.ENLARGE) {
                return "Enlarged";
            } else {
                return "Shrunk";
            }
        } else {
            if (sizeChange == SizeChange.ENLARGE) {
                return "Vergrößert";
            } else {
                return "Verkleinert";
            }
        }
    }
}
