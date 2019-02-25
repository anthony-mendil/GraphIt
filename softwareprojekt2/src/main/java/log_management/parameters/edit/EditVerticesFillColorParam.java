package log_management.parameters.edit;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.ColorNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFillColorLogAction.
 */
@Data
public class EditVerticesFillColorParam implements Param {

    private List<Vertex> oldVertices;

    @Getter
    private List<Color> oldColors;

    private List<Vertex> newVertices;

    @Getter
    private List<Color> newColors;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldVertices The selected vertices containing their old color.
     * @param pNewVertices The selected vertices containing their new color.
     */
    public EditVerticesFillColorParam(Map<Vertex, Color> pOldVertices, Map<Vertex, Color> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldColors = new ArrayList<>();
        newVertices = new ArrayList<>();
        newColors = new ArrayList<>();

        pOldVertices.forEach((v, c) -> {
            oldVertices.add(v);
            oldColors.add(c);
        });
        pNewVertices.forEach((v, c) -> {
            newVertices.add(v);
            newColors.add(c);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        StringBuilder information = new StringBuilder();
        if (language == Language.ENGLISH) {
            information.append("Symptoms changed: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))).append(". ").append("Old fill color: ").append(ColorNameCreator.getInstance().getColorName(oldColors.get(i),
                        Language.ENGLISH)).append(", new fill color: ").append(ColorNameCreator.getInstance().getColorName(newColors.get(i),
                        Language.ENGLISH)).append(". ");
            }
        } else {
            information.append("Veränderte Symptome: ");
            for (int i = 0; i < oldVertices.size(); i++) {
                information.append(SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))).append(". ").append("Alte Füllfarbe: ").append(ColorNameCreator.getInstance().getColorName(oldColors.get(i),
                        Language.GERMAN)).append(", neue Füllfarbe: ").append(ColorNameCreator.getInstance().getColorName(newColors.get(i),
                        Language.GERMAN)).append(". ");
            }
        }
        return information.toString();
    }

    public Map<Vertex, Color> getOldVertices() {
        Map<Vertex, Color> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(newVertices.get(i), oldColors.get(i));
        }
        return map;
    }

    public Map<Vertex, Color> getNewVertices() {
        Map<Vertex, Color> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), newColors.get(i));
        }
        return map;
    }
}
