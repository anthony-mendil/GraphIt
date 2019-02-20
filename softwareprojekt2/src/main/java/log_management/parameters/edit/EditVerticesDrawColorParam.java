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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesDrawColorLogAction.
 */
@Data
public class EditVerticesDrawColorParam extends Param implements Serializable {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<Color> oldColors;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<Color> newColors;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The selected vertices containing their old color.
     * @param pNewVertices The selected vertices containing their new color.
     */
    public EditVerticesDrawColorParam(Map<Vertex,Color> pOldVertices, Map<Vertex,Color> pNewVertices) {
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
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i))
                        + ", New draw color: "
                        + ColorNameCreator.getInstance().getColorName(newColors.get(i),
                            Language.ENGLISH) + "; ";
            }
        } else {
            information += "Veränderte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i))
                        + ", Neue Umrandungsfarbe: "
                        + ColorNameCreator.getInstance().getColorName(newColors.get(i),
                            Language.GERMAN) + "; ";
            }
        }
        return information;
    }

    public Map<Vertex,Color> getOldVertices() {
        Map<Vertex, Color> map = new HashMap<>();
        for (int i = 0; i < newVertices.size(); i++) {
            map.put(newVertices.get(i), oldColors.get(i));
        }
        return map;
    }

    public Map<Vertex,Color> getNewVertices() {
        Map<Vertex, Color> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newColors.get(i));
        }
        return map;
    }
}
