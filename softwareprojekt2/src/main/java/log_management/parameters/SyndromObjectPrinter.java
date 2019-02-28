package log_management.parameters;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.properties.Language;

public class SyndromObjectPrinter {

    private SyndromObjectPrinter() {
        throw new IllegalStateException("utility class");
    }

    public static String vertexPrintEnglish(Vertex vertex) {
        return "\"" + vertex.getAnnotation().get(Language.ENGLISH.name()) + "\"";
    }

    public static String vertexPrintGerman(Vertex vertex) {
        return "\"" + vertex.getAnnotation().get(Language.GERMAN.name()) + "\"";
    }

    public static String edgePrintEnglish(javafx.util.Pair<Vertex, Vertex> vertices) {
        return "From Symptom: "
                + vertexPrintEnglish(vertices.getKey())
                + " to Symptom: " + vertexPrintEnglish(vertices.getValue());
    }

    public static String edgePrintGerman(javafx.util.Pair<Vertex, Vertex> vertices) {
        return "Von Symptom: "
                + vertexPrintGerman(vertices.getKey())
                + " zum Symptom: " + vertexPrintGerman(vertices.getValue());
    }

    public static String spherePrintEnglish(Sphere sphere) {
        return "\"" + sphere.getAnnotation().get(Language.ENGLISH.name()) + "\"";
    }

    public static String spherePrintGerman(Sphere sphere) {
        return "\"" + sphere.getAnnotation().get(Language.GERMAN.name()) + "\"";
    }
}