package log_management.parameters.edit;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditVErtexAnnotationLogAction.
 */
public class EditVertexAnnotationParam extends Param{
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private Pair<Vertex,String> vertexOldAnnotation;
    /**
     * The new annotation of the vertex.
     */
    @Getter
    private String newAnnotation;

    /**
     * Creates a parameterobject of its own class.
     * @param pVertexOldAnnotation The vertex and its annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditVertexAnnotationParam(Pair<Vertex,String> pVertexOldAnnotation, String pNewAnnotation) {
        this.vertexOldAnnotation = pVertexOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }

}
