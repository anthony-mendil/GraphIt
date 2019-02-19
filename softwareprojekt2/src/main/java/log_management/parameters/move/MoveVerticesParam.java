package log_management.parameters.move;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for the action MoveVerticesLogAction.
 */
@Data
public class MoveVerticesParam extends Param implements Serializable {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Point2D> oldPositions;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<Point2D> newPositions;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The set of old vertices and their position.
     * @param pNewVertices The set of new vertices and their position.
     */
    public MoveVerticesParam(Map<Vertex,Point2D> pOldVertices, Map<Vertex,Point2D> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldPositions = new ArrayList<>();
        newVertices = new ArrayList<>();
        newPositions = new ArrayList<>();

        pOldVertices.forEach((v, p) -> {
            oldVertices.add(v);
            oldPositions.add(p);
        });
        pNewVertices.forEach((v, p) -> {
            newVertices.add(v);
            newPositions.add(p);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms moved: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptom : ";
                information += "Id: " + oldVertices.get(i).getId() + ", Annotation: "
                        + oldVertices.get(i).getAnnotation().get("en")
                        + ", Shape: "
                        + EnumNameCreator.vertexShapeTypeTranslator(oldVertices.get(i).getShape(), Language.ENGLISH)
                        + ", Coordinates: x = "
                        + oldPositions.get(i).getX()
                        + " y = "
                        + oldPositions.get(i).getY() + ", Size: " + oldVertices.get(i).getSize()
                        + ", Font: " + oldVertices.get(i).getFont()
                        + ", Font size: " + oldVertices.get(i).getFontSize()
                        + ", Fill color: "
                        + ColorNameCreator.getInstance().
                        getColorName(oldVertices.get(i).getFillColor(), Language.ENGLISH)
                        + ", Draw color: "
                        + ColorNameCreator.getInstance().
                        getColorName(oldVertices.get(i).getDrawColor(), Language.ENGLISH);
                information += ", New Coordinates: x = "
                        + newPositions.get(i).getX()
                        + " y = " + newPositions.get(i).getY() + "; ";
            }
        } else {
            information += "Bewegte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptom : ";
                information += "Id: " + oldVertices.get(i).getId() + ", Beschriftung: "
                        + oldVertices.get(i).getAnnotation().get(Language.GERMAN.name())
                        + ", Form: "
                        + EnumNameCreator.vertexShapeTypeTranslator(oldVertices.get(i).getShape(), Language.GERMAN)
                        + ", Koordinaten: x = "
                        + oldPositions.get(i).getX()
                        + " y = "
                        + oldPositions.get(i).getY() + ", Größe: " + oldVertices.get(i).getSize()
                        + ", Schriftart: " + oldVertices.get(i).getFont()
                        + ", Schriftgröße: " + oldVertices.get(i).getFontSize()
                        + ", Füllfarbe: "
                        + ColorNameCreator.getInstance().
                        getColorName(oldVertices.get(i).getFillColor(), Language.GERMAN)
                        + ", Umrandungsfarbe: "
                        + ColorNameCreator.getInstance().
                        getColorName(oldVertices.get(i).getDrawColor(), Language.GERMAN);
                information += ", Neue Koordinaten: x = "
                        + newPositions.get(i).getX()
                        + " y = " + newPositions.get(i).getY() + "; ";
            }
        }
        return information;
    }

    public Map<Vertex,Point2D> getOldVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldPositions.get(i));
        }
        return map;
    }

    public Map<Vertex,Point2D> getNewVertices() {
        Map<Vertex, Point2D> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newPositions.get(i));
        }
        return map;
    }
}
