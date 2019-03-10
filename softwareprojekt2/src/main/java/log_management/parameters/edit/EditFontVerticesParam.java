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
 * Parameter object of the action EditFontVerticesLogAction.
 *
 * @author Anthony Mendil
 */
@Data
public class EditFontVerticesParam implements Param {

    /**
     * The list of old vertices.
     */
    private List<Vertex> oldVertices;

    /**
     * The old font of the vertices.
     */
    @Getter
    private List<String> oldFonts;

    /**
     * The list of new vertices.
     */
    private List<Vertex> newVertices;

    /**
     * The list of new vertices.
     */
    @Getter
    private List<String> newFonts;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The vertices and their old font.
     * @param pNewVertices The vertices and their new font.
     */
    public EditFontVerticesParam(Map<Vertex, String> pOldVertices, Map<Vertex, String> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldFonts = new ArrayList<>();
        newVertices = new ArrayList<>();
        newFonts = new ArrayList<>();

        pOldVertices.forEach((v, s) -> {
            oldVertices.add(v);
            oldFonts.add(s);
        });
        pNewVertices.forEach((v, s) -> {
            newVertices.add(v);
            newFonts.add(s);
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
                        .append("Old font: ").append(oldFonts.get(i)).append(", new font: ")
                        .append(newFonts.get(i)).append("; ");
            }
        } else {
            information.append("Veränderte Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ")
                        .append("Alte Schriftart: ").append(oldFonts.get(i)).append(", neue Schriftart: ")
                        .append(newFonts.get(i)).append("; ");
            }
        }
        return information.toString();
    }

    /**
     * The map of vertices and their old font.
     * @return The map.
     */
    public Map<Vertex, String> getOldVertices() {
        Map<Vertex, String> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), oldFonts.get(i));
        }
        return map;
    }

    /**
     * The map of vertices and their new font.
     * @return The map.
     */
    public Map<Vertex, String> getNewVertices() {
        Map<Vertex, String> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newFonts.get(i));
        }
        return map;
    }
}
