package log_management.parameters;

import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import gui.properties.Language;

import java.util.List;

public class SyndromObjectPrinter {

    public static String vertexPrintEnglish(Vertex vertex) {
        return "Id: " + vertex.getId() + ", Annotation: "
                + vertex.getAnnotation().get("en")
                + ", Shape: "
                + EnumNameCreator.vertexShapeTypeTranslator(vertex.getShape(), Language.ENGLISH)
                + ", Coordinates: x = "
                + vertex.getCoordinates().getX()
                + " y = "
                + vertex.getCoordinates().getY() + ", Size: " + vertex.getSize()
                + ", Font: " + vertex.getFont()
                + ", Font size: " + vertex.getFontSize()
                + ", Fill color: "
                + ColorNameCreator.getInstance().
                getColorName(vertex.getFillColor(), Language.ENGLISH)
                + ", Draw color: "
                + ColorNameCreator.getInstance().
                getColorName(vertex.getDrawColor(), Language.ENGLISH);
    }

    public static String vertexPrintGerman(Vertex vertex) {
        return "Id: " + vertex.getId() + ", Beschriftung: "
                + vertex.getAnnotation().get(Language.GERMAN.name())
                + ", Form: "
                + EnumNameCreator.vertexShapeTypeTranslator(vertex.getShape(), Language.GERMAN)
                + ", Koordinaten: x = "
                + vertex.getCoordinates().getX()
                + " y = "
                + vertex.getCoordinates().getY() + ", Größe: " + vertex.getSize()
                + ", Schriftart: " + vertex.getFont()
                + ", Schriftgröße: " + vertex.getFontSize()
                + ", Füllfarbe: "
                + ColorNameCreator.getInstance().
                getColorName(vertex.getFillColor(), Language.GERMAN)
                + ", Umrandungsfarbe: "
                + ColorNameCreator.getInstance().
                getColorName(vertex.getDrawColor(), Language.GERMAN);
    }

    public static String edgePrintEnglish(Edge edge) {
        Pair<Vertex> vertices = Syndrom.getInstance().getGraph().getEndpoints(edge);

        return "Id: " + edge.getId() + ", Stroke type: "
                + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.ENGLISH)
                + ", Arrow type: "
                + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.ENGLISH)
                + ", Color: "
                + ColorNameCreator.getInstance().getColorName(edge.getColor(), Language.ENGLISH)
                + ", Symptoms of the relation: First Symptom: "
                + vertexPrintEnglish(vertices.getFirst())
                + ", Second Symptom: " + vertexPrintEnglish(vertices.getSecond());
    }

    public static String edgePrintGerman(Edge edge) {
        Pair<Vertex> vertices = Syndrom.getInstance().getGraph().getEndpoints(edge);

        return "Id: " + edge.getId() + ", Lienienart: "
                + EnumNameCreator.strokeTypeTranslaotr(edge.getStroke(), Language.GERMAN)
                + ", Pfeilspitze: "
                + EnumNameCreator.edgeArrowTypeTranslator(edge.getArrowType(), Language.GERMAN)
                + ", Color: "
                + ColorNameCreator.getInstance().getColorName(edge.getColor(), Language.GERMAN)
                + ", Symptome der Relation: Erstes Symptom: "
                + vertexPrintGerman(vertices.getFirst())
                + ", Zweites Symptom: " + vertexPrintGerman(vertices.getSecond());
    }

    public static String spherePrintEnglish(Sphere sphere) {
        String sphereText = "Id: " + sphere.getId() + ", Annotation: "
                + sphere.getAnnotation().get("en") + ", Coordinates: x = "
                + sphere.getCoordinates().getX() + " y = "
                + sphere.getCoordinates().getY() + ", Height: "
                + sphere.getHeight() + ", Width: "
                + sphere.getWidth() + ", Font: "
                + sphere.getFont() + ", Font size: "
                + sphere.getFontSize() + ", Color: "
                + ColorNameCreator.getInstance().getColorName(sphere.getColor(), Language.ENGLISH)
                + ", Symptoms of the sphere: ";
        List<Vertex> vertexList = sphere.getVertices();
        for (int i = 0; i < vertexList.size(); i++) {
            sphereText += vertexPrintEnglish(vertexList.get(i));
        }
        return sphereText;
    }

    public static String spherePrintGerman(Sphere sphere) {
        String sphereText = "Id: " + sphere.getId() + ", Beschriftung: "
                + sphere.getAnnotation().get(Language.GERMAN.name()) + ", Koordinaten: x = "
                + sphere.getCoordinates().getX() + " y = "
                + sphere.getCoordinates().getY() + ", Höhe: "
                + sphere.getHeight() + ", Breite: "
                + sphere.getWidth() + ", Schriftart: "
                + sphere.getFont() + ", Schriftgröße: "
                + sphere.getFontSize() + ", Farbe: "
                + ColorNameCreator.getInstance().getColorName(sphere.getColor(), Language.GERMAN)
                + ", Symptome der Sphäre: ";
        List<Vertex> vertexList = sphere.getVertices();

        if(vertexList.isEmpty()) {
            System.out.println( "\nempty\n");
        }

        for (int i = 0; i < vertexList.size(); i++) {
            sphereText += vertexPrintGerman(vertexList.get(i));
        }
        return sphereText;
    }
}