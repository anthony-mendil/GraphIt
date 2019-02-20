package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesColorLogAction.
 */
@Data
public class EditEdgesColorParam extends Param implements Serializable {
    @Getter
    private List<Edge> oldEdges;

    @Getter
    private List<Color> oldColors;

    @Getter
    private List<Edge> newEdges;

    @Getter
    private List<Color> newColors;

    @Getter
    private List<Vertex> startVertices;

    @Getter
    private List<Vertex> endVertices;

    /**
     * Creates an vertices object of its own class.
     * @param pEdgesOld The list of edges and their old color.
     * @param pEdgesNew The list of edges and their new color.
     */
    public EditEdgesColorParam(Map<Edge,Color> pEdgesOld, Map<Edge,Color> pEdgesNew,
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
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed: ";
            for (int i = 0; i < oldEdges.size(); i++) {
                information += SyndromObjectPrinter.edgePrintEnglish(oldEdges.get(i),
                        new Pair<>(startVertices.get(i), endVertices.get(i))) + ". ";
                information += "Old color: "
                        + ColorNameCreator.getInstance().getColorName(oldColors.get(i), Language.ENGLISH);
                information += ", new color: "
                        + ColorNameCreator.getInstance().getColorName(newColors.get(i), Language.ENGLISH) + ". ";
            }
        } else {
            information += "Geänderte Relationen: ";
            for (int i = 0; i < oldEdges.size(); i++) {
                information += SyndromObjectPrinter.edgePrintGerman(oldEdges.get(i),
                        new Pair<>(startVertices.get(i), endVertices.get(i))) + ". ";
                information += "Alte Farbe: "
                        + ColorNameCreator.getInstance().getColorName(oldColors.get(i), Language.GERMAN);
                information += ", neue Farbe: "
                        + ColorNameCreator.getInstance().getColorName(newColors.get(i), Language.GERMAN) +  ". ";
            }
        }
        return information;

    }

    public Map<Edge,Color> getEdgesOld() {
        Map<Edge, Color> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldColors.get(i));
        }
        return map;
    }

    public Map<Edge,Color> getEdgesNew() {
        Map<Edge, Color> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newColors.get(i));
        }
        return map;
    }
}
