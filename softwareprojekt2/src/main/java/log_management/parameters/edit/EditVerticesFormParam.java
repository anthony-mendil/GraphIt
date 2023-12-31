package log_management.parameters.edit;

import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import gui.Values;
import gui.properties.Language;
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
 * Parameter object of the action EditVerticesFormLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditVerticesFormParam implements Param {
    /**
     * The old vertices.
     */
    private List<Vertex> oldVertices;
    /**
     * The old shape types of the vertices.
     */
    @Getter
    private List<VertexShapeType> oldShapeTypes;
    /**
     * The new vertices.
     */
    private List<Vertex> newVertices;
    /**
     * The new shape types of the vertices.
     */
    @Getter
    private List<VertexShapeType> newShapeTypes;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The vertices and their old shapes.
     * @param pNewVertices The vertices and their new shapes.
     */
    public EditVerticesFormParam(Map<Vertex, VertexShapeType> pOldVertices, Map<Vertex, VertexShapeType> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldShapeTypes = new ArrayList<>();
        newVertices = new ArrayList<>();
        newShapeTypes = new ArrayList<>();

        pOldVertices.forEach((v, s) -> {
            oldVertices.add(v);
            oldShapeTypes.add(s);
        });
        pNewVertices.forEach((v, s) -> {
            newVertices.add(v);
            newShapeTypes.add(s);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            information.append("Symptoms changed: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))).append(". ")
                        .append("Old shape: ").append(EnumNameCreator
                        .vertexShapeTypeTranslator(oldShapeTypes.get(i), language)).
                        append(", new shape: ").append(EnumNameCreator.vertexShapeTypeTranslator(newShapeTypes.get(i), language)).append("; ");
            }
        } else {
            information.append("Veränderte Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ")
                        .append("Alte Form: ").append(EnumNameCreator.vertexShapeTypeTranslator(oldShapeTypes.get(i), language)).append(", neue Form: ").
                        append(EnumNameCreator.vertexShapeTypeTranslator(newShapeTypes.get(i), language)).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * Gets the old vertices and their old shape types.
     *
     * @return The old vertices and their old shape types.
     */
    public Map<Vertex, VertexShapeType> getOldVertices() {
        Map<Vertex, VertexShapeType> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldShapeTypes.get(i));
        }
        return map;
    }

    /**
     * Gets the new vertices and their new shape types.
     *
     * @return The new vertices and their new shape types.
     */
    public Map<Vertex, VertexShapeType> getNewVertices() {
        Map<Vertex, VertexShapeType> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newShapeTypes.get(i));
        }
        return map;
    }
}
