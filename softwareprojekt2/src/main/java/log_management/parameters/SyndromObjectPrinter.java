package log_management.parameters;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.properties.Language;

/**
 * Used to print information about elements of the graph.
 *
 * @author Anthony Mendil
 */
public class SyndromObjectPrinter {

    /**
     * Throws exception if tried to create.
     */
    private SyndromObjectPrinter() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Prints the vertex in english.
     *
     * @param vertex The vertex.
     * @return The string of the vertex.
     */
    public static String vertexPrintEnglish(Vertex vertex) {
        return "\"" + vertex.getAnnotation().get(Language.ENGLISH.name()) + "\"";
    }

    /**
     * Prints the vertex in german.
     *
     * @param vertex The vertex.
     * @return The string of the vertex.
     */
    public static String vertexPrintGerman(Vertex vertex) {
        return "\"" + vertex.getAnnotation().get(Language.GERMAN.name()) + "\"";
    }

    /**
     * Prints the edge in english.
     *
     * @param vertices The vertices of the edge.
     * @return The string of the edge.
     */
    public static String edgePrintEnglish(javafx.util.Pair<Vertex, Vertex> vertices) {
        return "From Symptom: "
                + vertexPrintEnglish(vertices.getKey())
                + " to Symptom: " + vertexPrintEnglish(vertices.getValue());
    }

    /**
     * Prints the edge in german.
     *
     * @param vertices The vertices of the edge.
     * @return The string of the edge.
     */
    public static String edgePrintGerman(javafx.util.Pair<Vertex, Vertex> vertices) {
        return "Von Symptom: "
                + vertexPrintGerman(vertices.getKey())
                + " zum Symptom: " + vertexPrintGerman(vertices.getValue());
    }

    /**
     * Prints the sphere in english.
     *
     * @param sphere The sphere.
     * @return The string of the sphere.
     */
    public static String spherePrintEnglish(Sphere sphere) {
        return "\"" + sphere.getAnnotation().get(Language.ENGLISH.name()) + "\"";
    }

    /**
     * Prints the sphere in german.
     *
     * @param sphere The sphere.
     * @return The string of the sphere.
     */
    public static String spherePrintGerman(Sphere sphere) {
        return "\"" + sphere.getAnnotation().get(Language.GERMAN.name()) + "\"";
    }
}