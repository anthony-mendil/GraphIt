package log_management.parameters.edit;

import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditVertexAnnotationLogAction.
 */
@Data
public class EditVertexAnnotationParam extends Param{
    /**
     * The vertex containing its old annotation.
     */
    @Getter
    private Vertex vertex;
    /**
     * The new annotation of the vertex.
     */
    @Getter
    private String newAnnotation;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotation;


    /**
     * Creates a parameter object of its own class.
     * @param pVertex The vertex containing its old annotation.
     * @param pOldAnnotation The old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditVertexAnnotationParam(Vertex pVertex,String pOldAnnotation, String pNewAnnotation) {
        this.vertex = pVertex;
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
