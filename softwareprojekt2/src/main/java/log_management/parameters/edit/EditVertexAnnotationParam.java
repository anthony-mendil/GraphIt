package log_management.parameters.edit;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditVertexAnnotationLogAction.
 */
@Data
public class EditVertexAnnotationParam extends Param implements Serializable {
    /**
     * The vertex containing its old annotation.
     */
    @Getter
    private Vertex vertex;
    /**
     * The new vertex and it's new annotation.
     */
    @Getter
    private String newAnnotation;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotation;


    /**
     * Creates a vertices object of its own class.
     * @param pVertex The vertex containing its old annotation.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditVertexAnnotationParam(Vertex pVertex, String pOldAnnotation, String pNewAnnotation) {
        this.vertex = pVertex;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }
    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptom:\n" + SyndromObjectPrinter.vertexPrintGerman(vertex)
                    + " New annotation: " + newAnnotation;
        } else {
            information += "Symptom:\n" + SyndromObjectPrinter.vertexPrintGerman(vertex)
                    + " Neue Beschriftung: " + newAnnotation;
        }
        return information;
    }
}
