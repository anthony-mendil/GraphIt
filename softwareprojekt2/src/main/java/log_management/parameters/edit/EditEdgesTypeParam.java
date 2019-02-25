package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.EdgeArrowType;
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
 * Parameter object of the action EditEdgesTypeLogAction.
 */
@Data
public class EditEdgesTypeParam implements Param {
    @Getter
    private List<Edge> oldEdges;

    @Getter
    private List<EdgeArrowType> oldArrowTypes;

    @Getter
    private List<Edge> newEdges;

    @Getter
    private List<EdgeArrowType> newArrowTypes;

    @Getter
    private List<Vertex> startVertices;

    @Getter
    private List<Vertex> endVertices;

    /**
     * Creates a new vertices object of its own class.
     *
     * @param pOldEdges The set of edges containing their old edge-types.
     * @param pNewEdges The set of edges containing their new edge-types.
     */
    public EditEdgesTypeParam(Map<Edge, EdgeArrowType> pOldEdges, Map<Edge, EdgeArrowType> pNewEdges,
                              List<Vertex> pStartVertices, List<Vertex> pEndVertices) {
        this.startVertices = pStartVertices;
        this.endVertices = pEndVertices;

        oldEdges = new ArrayList<>();
        oldArrowTypes = new ArrayList<>();
        newEdges = new ArrayList<>();
        newArrowTypes = new ArrayList<>();

        pOldEdges.forEach((e, ed) -> {
            oldEdges.add(e);
            oldArrowTypes.add(ed);
        });
        pNewEdges.forEach((e, ed) -> {
            newEdges.add(e);
            newArrowTypes.add(ed);
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
                information.append("Old arrow type: ").append(EnumNameCreator.edgeArrowTypeTranslator(oldArrowTypes.get(i), Language.ENGLISH));
                information.append(", new arrow type: ").append(EnumNameCreator.edgeArrowTypeTranslator(newArrowTypes.get(i), Language.ENGLISH)).append(". ");
            }
        } else {
            information.append("Geänderte Relationen: ");
            for (int i = 0; i < oldEdges.size(); i++) {
                information.append(SyndromObjectPrinter.edgePrintGerman(
                        new Pair<>(startVertices.get(i), endVertices.get(i)))).append(". ");
                information.append("Alte Pfeilspitze: ").append(EnumNameCreator.edgeArrowTypeTranslator(oldArrowTypes.get(i), Language.GERMAN));
                information.append(", neue Pfeilspitze: ").append(EnumNameCreator.edgeArrowTypeTranslator(newArrowTypes.get(i), Language.GERMAN)).append(". ");
            }
        }
        return information.toString();
    }

    public Map<Edge, EdgeArrowType> getEdgesOldEdgeType() {
        Map<Edge, EdgeArrowType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldArrowTypes.get(i));
        }
        return map;
    }

    public Map<Edge, EdgeArrowType> getEdgesNewEdgeType() {
        Map<Edge, EdgeArrowType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newArrowTypes.get(i));
        }
        return map;
    }

}
