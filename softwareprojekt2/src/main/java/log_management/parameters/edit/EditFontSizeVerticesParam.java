package log_management.parameters.edit;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object for the Action EditFontSizeVerticesLogAction.
 */
@Data
public class EditFontSizeVerticesParam extends Param implements Serializable {
    /**
     * The set of the vertices and their old font-size.
     */
    @Getter
    Map<Vertex,Integer> oldVertices;
    /**
     * The set of the vertices and their new font-size.
     */
    @Getter
    Map<Vertex,Integer> newVertices;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old font-size.
     * @param pNewVertices The vertices containing their new font-size.
     */
    public EditFontSizeVerticesParam(Map<Vertex,Integer> pOldVertices, Map<Vertex,Integer> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed:\n";
            for (Map.Entry<Vertex, Integer> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintEnglish((entry.getKey()))
                        + "New font size: " + newVertices.get(entry.getKey()) + "\n";
            }
        } else {
            information += "Veränderte Symptome:\n";
            for (Map.Entry<Vertex, Integer> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintGerman((entry.getKey()))
                        + "Neue Schriftgröße: " + newVertices.get(entry.getKey()) + "\n";
            }
        }
        return information;
    }
}
