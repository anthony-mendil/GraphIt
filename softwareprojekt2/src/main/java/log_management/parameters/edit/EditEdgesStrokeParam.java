package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import javafx.util.Pair;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
@Data
public class EditEdgesStrokeParam implements Param {
    @Getter
    private List<Edge> oldEdges;

    @Getter
    private List<StrokeType> oldStrokeTypes;

    @Getter
    private List<Edge> newEdges;

    @Getter
    private List<StrokeType> newStrokeTypes;

    @Getter
    private List<Vertex> startVertices;

    @Getter
    private List<Vertex> endVertices;


    public EditEdgesStrokeParam(Map<Edge, StrokeType> pEdgesOld, Map<Edge, StrokeType> pEdgesNew,
                                List<Vertex> pStartVertices, List<Vertex> pEndVertices) {
        this.startVertices = pStartVertices;
        this.endVertices = pEndVertices;

        oldEdges = new ArrayList<>();
        oldStrokeTypes = new ArrayList<>();
        newEdges = new ArrayList<>();
        newStrokeTypes = new ArrayList<>();

        pEdgesOld.forEach((e, s) -> {
            oldEdges.add(e);
            oldStrokeTypes.add(s);
        });
        pEdgesNew.forEach((e, s) -> {
            newEdges.add(e);
            newStrokeTypes.add(s);
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
                information.append("Old stroke type: ").append(EnumNameCreator.strokeTypeTranslator(oldStrokeTypes.get(i), Language.ENGLISH));
                information.append(", new stroke type: ").append(EnumNameCreator.strokeTypeTranslator(newStrokeTypes.get(i), Language.ENGLISH)).append(". ");
            }
        } else {
            information.append("Geänderte Relationen: ");
            for (int i = 0; i < oldEdges.size(); i++) {
                information.append(SyndromObjectPrinter.edgePrintGerman(
                        new Pair<>(startVertices.get(i), endVertices.get(i)))).append(". ");
                information.append("Alte Linienart: ").append(EnumNameCreator.strokeTypeTranslator(oldStrokeTypes.get(i), Language.GERMAN));
                information.append(", neue Linienart: ").append(EnumNameCreator.strokeTypeTranslator(newStrokeTypes.get(i), Language.GERMAN)).append(". ");
            }
        }
        return information.toString();
    }

    public Map<Edge, StrokeType> getEdgesOld() {
        Map<Edge, StrokeType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldStrokeTypes.get(i));
        }
        return map;
    }

    public Map<Edge, StrokeType> getEdgesNew() {
        Map<Edge, StrokeType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newStrokeTypes.get(i));
        }
        return map;
    }
}
