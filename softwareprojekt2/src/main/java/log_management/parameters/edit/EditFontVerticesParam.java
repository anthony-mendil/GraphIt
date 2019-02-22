package log_management.parameters.edit;


import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
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
 * Parameter object of the action EditFontVerticesLogAction.
 */
@Data
public class EditFontVerticesParam extends Param {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<String> oldFonts;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<String> newFonts;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old font.
     * @param pNewVertices The vertices containing their new font.
     */
    public EditFontVerticesParam(Map<Vertex,String> pOldVertices, Map<Vertex,String> pNewVertices) {
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
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i)) + ". "
                        + "Old font: " + oldFonts.get(i)
                        + ", new font: " + newFonts.get(i) + ". ";
            }
        } else {
            information += "Veränderte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i)) + ". "
                        + "Alte Schriftart: " + oldFonts.get(i)
                        + ", neue Schriftart: " + newFonts.get(i) + ". ";
            }
        }
        return information;
    }

    public Map<Vertex,String> getOldVertices() {
        Map<Vertex, String> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), oldFonts.get(i));
        }
        return map;
    }

    public Map<Vertex,String> getNewVertices() {
        Map<Vertex, String> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newFonts.get(i));
        }
        return map;
    }
}
