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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesStrokeLogAction.
 */
@Data
public class EditEdgesStrokeParam extends Param implements Serializable {
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


    public EditEdgesStrokeParam(Map<Edge,StrokeType> pEdgesOld, Map<Edge,StrokeType> pEdgesNew,
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
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Relations changed: ";
            for (int i = 0; i < oldEdges.size(); i++) {
                information += SyndromObjectPrinter.edgePrintEnglish(oldEdges.get(i),
                        new Pair<>(startVertices.get(i), endVertices.get(i))) + ". ";
                information += "Old stroke type: "
                        + EnumNameCreator.strokeTypeTranslator(oldStrokeTypes.get(i), Language.ENGLISH);
                information += ", new stroke type: "
                        + EnumNameCreator.strokeTypeTranslator(newStrokeTypes.get(i), Language.ENGLISH) + ". ";
            }
        } else {
            information += "Geänderte Relationen: ";
            for (int i = 0; i < oldEdges.size(); i++) {
                information += SyndromObjectPrinter.edgePrintGerman(oldEdges.get(i),
                        new Pair<>(startVertices.get(i), endVertices.get(i))) + ". ";
                information += "Alte Linienart: "
                        + EnumNameCreator.strokeTypeTranslator(oldStrokeTypes.get(i), Language.GERMAN);
                information += ", neue Linienart: "
                        + EnumNameCreator.strokeTypeTranslator(newStrokeTypes.get(i), Language.GERMAN) + ". ";
            }
        }
        return information;
    }

    public Map<Edge,StrokeType> getEdgesOld() {
        Map<Edge, StrokeType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldStrokeTypes.get(i));
        }
        return map;
    }

    public Map<Edge,StrokeType> getEdgesNew() {
        Map<Edge, StrokeType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newStrokeTypes.get(i));
        }
        return map;
    }
}
