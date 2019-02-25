package log_management.parameters.edit;

import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

/**
 * Parameter object of the action EditVertexAnnotationLogAction.
 */
@Data
public class EditVertexAnnotationParam implements Param {
    /**
     * The vertex containing its old annotation.
     */
    @Getter
    private Vertex vertex;
    /**
     * The new vertex and it's new annotation.
     */
    @Getter
    private String newAnnotationEnglish;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotationEnglish;
    /**
     * The new vertex and it's new annotation.
     */
    @Getter
    private String newAnnotationGerman;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotationGerman;
    /**
     * The current language of the Vertex.
     */
    @Getter
    private String language;


    /**
     * Creates a vertices object of its own class.
     *
     * @param pVertex        The vertex containing its old annotation.
     * @param pOldAnnotationEnglish The old annotation in english.
     * @param pNewAnnotationEnglish The new annotation in english.
     * @param pOldAnnotationGerman  The old annotation in german.
     * @param pOldAnnotationGerman  The new annotation in german.
     */
    public EditVertexAnnotationParam(Vertex pVertex, String pOldAnnotationEnglish, String pNewAnnotationEnglish, String pOldAnnotationGerman, String pNewAnnotationGerman) {
        this.vertex = pVertex;
        this.oldAnnotationEnglish = pOldAnnotationEnglish;
        this.newAnnotationEnglish = pNewAnnotationEnglish;
        this.oldAnnotationGerman = pOldAnnotationGerman;
        this.newAnnotationGerman = pNewAnnotationGerman;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptom: " + SyndromObjectPrinter.vertexPrintGerman(vertex) + ". "
                    + "Old annotation(English): " + oldAnnotationEnglish
                    + ", new annotation(English): " + newAnnotationEnglish
                     + "Old annotation(German): " + oldAnnotationGerman
                    + ", new annotation(German): " + newAnnotationGerman + ". ";
        } else {
            information += "Symptom: " + SyndromObjectPrinter.vertexPrintGerman(vertex) + ". "
                    + "Alte Beschriftung(Englisch): " + oldAnnotationEnglish
                    + ", neue Beschriftung(Englisch): " + newAnnotationEnglish
                    + "Alte Beschriftung(Deutsch): " + oldAnnotationGerman
                    + ", neue Beschriftung(Deutsch): " + newAnnotationGerman + ". ";
        }
        return information;
    }
}
