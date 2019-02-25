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
public class EditVerticesSizeParam implements Param {
    private List<Vertex> oldVertices;

    @Getter
    private List<Integer> oldSizes;

    private List<Vertex> newVertices;

    @Getter
    private List<Integer> newSizes;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The vertices containing their old size.
     * @param pNewVertices The vertices containing their new size.
     */
    public EditVerticesSizeParam(Map<Vertex, Integer> pOldVertices, Map<Vertex, Integer> pNewVertices) {
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
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            information.append("Symptoms changed: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))).append(". ").append("Old size: ").append(oldSizes.get(i)).append(", new size: ").append(newSizes.get(i)).append(". ");
            }
        } else {
            information.append("Veränderte Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ").append("Alte Größe: ").append(oldSizes.get(i)).append(", neue Größe: ").append(newSizes.get(i)).append(". ");
            }
        }
        return information.toString();
    }

    public Map<Vertex, Integer> getOldVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldSizes.get(i));
        }
        return map;
    }

    public Map<Vertex, Integer> getNewVertices() {
        Map<Vertex, Integer> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newSizes.get(i));
        }
        return map;
    }
}
