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
import java.util.Map;

/**
 * Parameter object of the action EditVerticesDrawColorLogAction.
 */
@Data
public class EditVerticesDrawColorParam extends Param implements Serializable {
    /**
     * The set of vertices containing their old colors.
     */
    @Getter
    private Map<Vertex,Color> oldVertices;
    /**
     * The set of vertices containing their new colors.
     */
    @Getter
    private Map<Vertex,Color> newVertices;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The selected vertices containing their old color.
     * @param pNewVertices The selected vertices containing their new color.
     */
    public EditVerticesDrawColorParam(Map<Vertex,Color> pOldVertices, Map<Vertex,Color> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed:\n";
            for (Map.Entry<Vertex, Color> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintEnglish(entry.getKey())
                        + "New draw color: "
                        + ColorNameCreator.getInstance().getColorName(newVertices.get(entry.getKey()),
                            Language.ENGLISH) + "\n";
            }
        } else {
            information += "Veränderte Symptome:\n";
            for (Map.Entry<Vertex, Color> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintGerman(entry.getKey())
                        + "Neue Umrandungsfarbe: "
                        + ColorNameCreator.getInstance().getColorName(newVertices.get(entry.getKey()),
                            Language.GERMAN) + "\n";
            }
        }
        return information;
    }
}
