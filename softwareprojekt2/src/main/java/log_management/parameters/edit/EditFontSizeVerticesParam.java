package log_management.parameters.edit;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for the Action EditFontSizeVerticesLogAction.
 */
@Data
public class EditFontSizeVerticesParam implements Param {
    /**
     * The old vertices.
     */
    private List<Vertex> oldVertices;

    /**
     * The old font sizes of the vertices.
     */
    @Getter
    private List<Integer> oldFontSize;

    /**
     * The new vertices.
     */
    private List<Vertex> newVertices;

    /**
     * The new font sizes of the vertices.
     */
    @Getter
    private List<Integer> newFontSize;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The old vertices and their font sizes.
     * @param pNewVertices The new vertices and their font sizes.
     */
    public EditFontSizeVerticesParam(Map<Vertex, Integer> pOldVertices, Map<Vertex, Integer> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldFontSize = new ArrayList<>();
        newVertices = new ArrayList<>();
        newFontSize = new ArrayList<>();

        pOldVertices.forEach((v, i) -> {
            oldVertices.add(v);
            oldFontSize.add(i);
        });
        pNewVertices.forEach((v, i) -> {
            newVertices.add(v);
            newFontSize.add(i);
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
                        .append("Old font size: ").append(oldFontSize.get(i)).append(", new font size: ")
                        .append(newFontSize.get(i)).append("; ");
            }
        } else {
            information.append("Veränderte Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ")
                        .append("Alte Schriftgröße: ").append(oldFontSize.get(i)).append(", neue Schriftgröße: ")
                        .append(newFontSize.get(i)).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * Gets the old vertices and their font sizes.
     *
     * @return The old vertices and their font sizes.
     */
    public Map<Vertex, Integer> getOldVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), oldFontSize.get(i));
        }
        return map;
    }

    /**
     * Gets the new vertices and their font sizes.
     *
     * @return The new vertices and their font sizes.
     */
    public Map<Vertex, Integer> getNewVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newFontSize.get(i));
        }
        return map;
    }
}
