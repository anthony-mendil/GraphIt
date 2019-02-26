package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesColorLogAction.
 */
@Data
public class EditEdgesColorParam implements Param {

    /**
     * The old edges.
     */
    @Getter
    private List<Edge> oldEdges;

    /**
     * The old colors.
     */
    @Getter
    private List<Color> oldColors;

    /**
     * The new edges.
     */
    @Getter
    private List<Edge> newEdges;

    /**
     * The new colors.
     */
    @Getter
    private List<Color> newColors;

    /**
     * The start vertices.
     */
    @Getter
    private List<Vertex> startVertices;

    /**
     * The end vertices.
     */
    @Getter
    private List<Vertex> endVertices;

    /**
     * Creates a parameter object of its own class.
     * @param pEdgesOld The old edges and their old colors.
     * @param pEdgesNew The new edges and their new colors.
     * @param pStartVertices The start vertices of the edges.
     * @param pEndVertices The end vertices of the edges.
     */
    public EditEdgesColorParam(Map<Edge, Color> pEdgesOld, Map<Edge, Color> pEdgesNew,
                               List<Vertex> pStartVertices, List<Vertex> pEndVertices) {
        this.startVertices = pStartVertices;
        this.endVertices = pEndVertices;

        oldEdges = new ArrayList<>();
        oldColors = new ArrayList<>();
        newEdges = new ArrayList<>();
        newColors = new ArrayList<>();

        pEdgesOld.forEach((e, c) -> {
            oldEdges.add(e);
            oldColors.add(c);
        });
        pEdgesNew.forEach((e, c) -> {
            newEdges.add(e);
            newColors.add(c);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            information.append("Relations changed: ");
            for (int i = 0; i < oldEdges.size(); i++) {
                information.append(SyndromObjectPrinter.edgePrintEnglish(
                        new Pair<>(startVertices.get(i), endVertices.get(i)))).append(". ");
                information.append("Old color: ").append(ColorNameCreator.getInstance().getColorName(oldColors.get(i), Language.ENGLISH));
                information.append(", new color: ").append(ColorNameCreator.getInstance().getColorName(newColors.get(i), Language.ENGLISH)).append("; ");
            }
        } else {
            information.append("Geänderte Relationen: ");
            for (int i = 0; i < oldEdges.size(); i++) {
                information.append(SyndromObjectPrinter.edgePrintGerman(
                        new Pair<>(startVertices.get(i), endVertices.get(i)))).append(". ");
                information.append("Alte Farbe: ").append(ColorNameCreator.getInstance().getColorName(oldColors.get(i), Language.GERMAN));
                information.append(", neue Farbe: ").append(ColorNameCreator.getInstance().getColorName(newColors.get(i), Language.GERMAN)).append("; ");
            }
        }
        return information.toString();

    }

    /**
     * Gets the old edges and their colors.
     * @return The old edges and their colors.
     */
    public Map<Edge, Color> getEdgesOld() {
        Map<Edge, Color> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldColors.get(i));
        }
        return map;
    }

    /**
     * Gets the new edges and their colors.
     * @return The new edges and their colors.
     */
    public Map<Edge, Color> getEdgesNew() {
        Map<Edge, Color> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newColors.get(i));
        }
        return map;
    }
}
