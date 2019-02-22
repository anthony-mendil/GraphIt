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
 * Parameter object of the action EditVerticesSizeLogAction.
 */
@Data
public class EditVerticesSizeParam extends Param {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Integer> oldSizes;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<Integer> newSizes;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old size.
     * @param pNewVertices The vertices containing their new size.
     */
    public EditVerticesSizeParam(Map<Vertex,Integer> pOldVertices, Map<Vertex,Integer> pNewVertices ) {
        oldVertices = new ArrayList<>();
        oldSizes = new ArrayList<>();
        newVertices = new ArrayList<>();
        newSizes = new ArrayList<>();

        pOldVertices.forEach((v, s) -> {
            oldVertices.add(v);
            oldSizes.add(s);
        });
        pNewVertices.forEach((v, s) -> {
            newVertices.add(v);
            newSizes.add(s);
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
                        + "Old size: " + oldSizes.get(i)
                        + ", new size: " + newSizes.get(i) + ". ";
            }
        } else {
            information += "Veränderte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i)) + ". "
                        + "Alte Größe: " + oldSizes.get(i)
                        + ", neue Größe: " + newSizes.get(i) + ". ";
            }
        }
        return information;
    }

    public Map<Vertex,Integer> getOldVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldSizes.get(i));
        }
        return map;
    }

    public Map<Vertex,Integer> getNewVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newSizes.get(i));
        }
        return map;
    }
}
