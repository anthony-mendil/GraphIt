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
    /**
     * The old edges.
     */
    @Getter
    private List<Edge> oldEdges;

    /**
     * The old arrow types of the edges.
     */
    @Getter
    private List<EdgeArrowType> oldArrowTypes;

    /**
     * The new edges.
     */
    @Getter
    private List<Edge> newEdges;

    /**
     * The new arrow types of the edges.
     */
    @Getter
    private List<EdgeArrowType> newArrowTypes;

    /**
     * The start vertices of the edges.
     */
    @Getter
    private List<Vertex> startVertices;

    /**
     * The end vertices of the edges.
     */
    @Getter
    private List<Vertex> endVertices;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pOldEdges      The old edges and their arrow types.
     * @param pNewEdges      The new edges and their arrow types.
     * @param pStartVertices The start vertices of the edges.
     * @param pEndVertices   The end vertices of the edges.
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
                information.append(", new arrow type: ").append(EnumNameCreator.edgeArrowTypeTranslator(newArrowTypes.get(i), Language.ENGLISH)).append("; ");
            }
        } else {
            information.append("Geänderte Relationen: ");
            for (int i = 0; i < oldEdges.size(); i++) {
                information.append(SyndromObjectPrinter.edgePrintGerman(
                        new Pair<>(startVertices.get(i), endVertices.get(i)))).append(". ");
                information.append("Alte Pfeilspitze: ").append(EnumNameCreator.edgeArrowTypeTranslator(oldArrowTypes.get(i), Language.GERMAN));
                information.append(", neue Pfeilspitze: ").append(EnumNameCreator.edgeArrowTypeTranslator(newArrowTypes.get(i), Language.GERMAN)).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * Gets the old edges and their arrow types.
     *
     * @return The old edges and their arrow types.
     */
    public Map<Edge, EdgeArrowType> getEdgesOldEdgeType() {
        Map<Edge, EdgeArrowType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), oldArrowTypes.get(i));
        }
        return map;
    }

    /**
     * Gets the new edges and their arrow types.
     *
     * @return The new edges and their arrow types.
     */
    public Map<Edge, EdgeArrowType> getEdgesNewEdgeType() {
        Map<Edge, EdgeArrowType> map = new HashMap<>();
        for (int i = 0; i < newEdges.size(); i++) {
            map.put(newEdges.get(i), newArrowTypes.get(i));
        }
        return map;
    }

}
