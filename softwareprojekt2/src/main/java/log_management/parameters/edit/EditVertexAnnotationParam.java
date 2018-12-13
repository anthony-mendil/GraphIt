package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditVErtexAnnotationLogAction.
 */
public class EditVertexAnnotationParam extends Param{
    /**
     * The selected vertex-Id.
     */
    @Getter
    private int vertexId;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotation;
    /**
     * The new annotation of the vertex.
     */
    @Getter
    private String newAnnotation;

    /**
     * Creates a parameterobject of its own class.
     * @param pVertexId The vertex-Id.
     * @param pOldAnnotation The old annotation of it.
     * @param pNewAnnotation The new annotation.
     */
    public EditVertexAnnotationParam(int pVertexId, String pOldAnnotation, String pNewAnnotation) {
        this.vertexId = pVertexId;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
