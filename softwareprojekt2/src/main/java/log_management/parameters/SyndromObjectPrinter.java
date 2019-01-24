package log_management.parameters;

import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.properties.Language;

public class SyndromObjectPrinter {

    public static String vertexPrintEnglish(Vertex vertex) {
        return "Id: " + vertex.getId() + ", Annotation: "
                + vertex.getAnnotation().get("en")
                + ", Shape: "
                + EnumNameCreator.vertexShapeTypeTranslator(vertex.getShape(), Language.ENGLISH)
                + ", Coordinates: x = "
                + vertex.getCoordinates().getX()
                + " y = "
                + vertex.getCoordinates().getY()
                + ", Fill color: "
                + ColorNameCreator.getInstance().
                getColorNameFromColor(vertex.getFillColor(), Language.ENGLISH)
                + ", Draw color: "
                + ColorNameCreator.getInstance().
                getColorNameFromColor(vertex.getDrawColor(), Language.ENGLISH)
                + "\n";
    }

    public static String vertexPrintGerman(Vertex vertex) {
        return "Id: " + vertex.getId() + ", Beschriftung: "
                + vertex.getAnnotation().get("de")
                + ", Form: "
                + EnumNameCreator.vertexShapeTypeTranslator(vertex.getShape(), Language.GERMAN)
                + ", Koordinaten: x = "
                + vertex.getCoordinates().getX()
                + " y = "
                + vertex.getCoordinates().getY()
                + ", Füllfarbe: "
                + ColorNameCreator.getInstance().
                getColorNameFromColor(vertex.getFillColor(), Language.GERMAN)
                + ", Umrandungsfarbe: "
                + ColorNameCreator.getInstance().
                getColorNameFromColor(vertex.getDrawColor(), Language.GERMAN)
                + "\n";
    }

    public static String edgePrintEnglish(Edge edge) {
        return "Id: " + edge.getId() + ", Stroke type: "
                + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.ENGLISH)
                + ", Arrow type: "
                + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.ENGLISH)
                + ", Color: "
                + ColorNameCreator.getInstance().getColorNameFromColor(edge.getColor(), Language.ENGLISH)
                + "\n";
    }

    public static String edgePrintGerman(Edge edge) {
        return "Id: " + edge.getId() + ", Lienienart: "
                + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.GERMAN)
                + ", Pfeilspitze: "
                + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.GERMAN)
                + ", Color: "
                + ColorNameCreator.getInstance().getColorNameFromColor(edge.getColor(), Language.GERMAN)
                + "\n";
    }

    public static String spherePrintEnglish(Sphere sphere) {
        throw new UnsupportedOperationException();
    }
    public static String spherePrintGerman(Sphere sphere) {
        throw new UnsupportedOperationException();

    }
}
