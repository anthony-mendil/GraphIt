package log_management.parameters;

import graph.graph.*;
import gui.properties.Language;

public class SyndromObjectPrinter {

    public static String vertexPrintEnglish(Vertex vertex) {
        return vertex.getAnnotation().get(Language.ENGLISH.name());
    }

    public static String vertexPrintGerman(Vertex vertex) {
        return vertex.getAnnotation().get(Language.GERMAN.name());
    }

    public static String edgePrintEnglish(Edge edge, javafx.util.Pair<Vertex, Vertex> vertices) {
        return "From Symptom: "
                + vertexPrintEnglish(vertices.getKey())
                + "to Symptom: " + vertexPrintEnglish(vertices.getValue());
    }

    public static String edgePrintGerman(Edge edge, javafx.util.Pair<Vertex, Vertex> vertices) {
        return "Von Symptom: "
                + vertexPrintGerman(vertices.getKey())
                + "zum Symptom: " + vertexPrintGerman(vertices.getValue());
    }

    public static String spherePrintEnglish(Sphere sphere) {
        return sphere.getAnnotation().get(Language.ENGLISH.name());
    }

    public static String spherePrintGerman(Sphere sphere) {
        return sphere.getAnnotation().get(Language.GERMAN.name());
    }
}