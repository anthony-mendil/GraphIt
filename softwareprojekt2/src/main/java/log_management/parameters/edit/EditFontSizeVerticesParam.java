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
 * Parameter object for the Action EditFontSizeVerticesLogAction.
 */
@Data
public class EditFontSizeVerticesParam extends Param implements Serializable {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Integer> oldFontSize;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<Integer> newFontSize;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old font-size.
     * @param pNewVertices The vertices containing their new font-size.
     */
    public EditFontSizeVerticesParam(Map<Vertex,Integer> pOldVertices, Map<Vertex,Integer> pNewVertices) {
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
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i)) + ". "
                        + "Old font size: " + oldFontSize.get(i)
                        + ", new font size: " + newFontSize.get(i) + ". ";
            }
        } else {
            information += "Veränderte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i)) + ". "
                        + "Alte Schriftgröße: " + oldFontSize.get(i)
                        + ", neue Schriftgröße: " + newFontSize.get(i) + ". ";
            }
        }
        return information;
    }

    public Map<Vertex,Integer> getOldVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), oldFontSize.get(i));
        }
        return map;
    }

    public Map<Vertex,Integer> getNewVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newFontSize.get(i));
        }
        return map;
    }
}
