package log_management.parameters;
import graph.graph.EdgeArrowType;
import graph.graph.ScopePoint;
import graph.graph.SizeChange;
import graph.graph.StrokeType;
import graph.graph.VertexShapeType;
import gui.properties.Language;

public class EnumNameCreator {

    public static String edgeArrowTypeTranslator(EdgeArrowType edgeArrowType, Language language) {
        if (language == Language.ENGLISH) {
            switch (edgeArrowType) {
                case REINFORCED: return "Reinforced";
                case EXTENUATING: return "Extenuating";
                default: return "Neutral";
            }
        } else {
            switch (edgeArrowType) {
                case REINFORCED: return "Verstärkend";
                case EXTENUATING: return "Abschwächend";
                default: return "Neutral";
            }
        }
    }

    public static String vertexShapeTypeTranslator(VertexShapeType vertexShapeType, Language language) {
        if (language == Language.ENGLISH) {
            switch (vertexShapeType) {
                case CIRCLE: return "Circle";
                case ELLIPSE: return "Ellipse";
                default: return "Rectangle";
            }
        } else {
            switch (vertexShapeType) {
                case CIRCLE: return "Kreis";
                case ELLIPSE: return "Ellipse";
                default: return "Rechteck";
            }
        }
    }

    //public static String
}
