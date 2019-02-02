package log_management.parameters.edit;

import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFormLogAction.
 */
@Data
public class EditVerticesFormParam extends Param implements Serializable {
    /**
     * The set of vertices containing their old shapes/annotation.
     */
    @Getter
    Map<Vertex, VertexShapeType> oldVertices;
    /**
     * The set of vertices containing their old shapes/annotation.
     */
    @Getter
    Map<Vertex,VertexShapeType> newVertices;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old shape/annotation.
     * @param pNewVertices The vertices containing their new shape/annotation.
     */
    public EditVerticesFormParam(Map<Vertex,VertexShapeType> pOldVertices, Map<Vertex,VertexShapeType> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed:\n";
            for (Map.Entry<Vertex, VertexShapeType> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintEnglish(entry.getKey())
                        + "New shape: "
                        + EnumNameCreator.vertexShapeTypeTranslator(newVertices.get(entry.getKey()), language) + "\n";
            }
        } else {
            information += "Veränderte Symptome:\n";
            for (Map.Entry<Vertex, VertexShapeType> entry : oldVertices.entrySet()) {
                information += "Symptom : " + SyndromObjectPrinter.vertexPrintGerman(entry.getKey())
                        + "Neue Form: "
                        + EnumNameCreator.vertexShapeTypeTranslator(newVertices.get(entry.getKey()), language) + "\n";
            }
        }
        return information;
    }
}
