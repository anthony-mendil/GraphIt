package log_management.parameters.edit;

import log_management.parameters.Param;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;

public class EditEdgeTypeParam extends Param implements Serializable {

    private Integer edgeId;
    private Integer sourceVertexId;
    private String sourceVertexAnnotation;
    private Integer targetVertexId;
    private String targetVertexAnnotation;
    // later add edge type attribute


    public EditEdgeTypeParam(Integer edgeId, Integer sourceVertexId, String sourceVertexAnnotation, Integer targetVertexId, String targetVertexAnnotation) {
        this.edgeId = edgeId;
        this.sourceVertexId = sourceVertexId;
        this.sourceVertexAnnotation = sourceVertexAnnotation;
        this.targetVertexId = targetVertexId;
        this.targetVertexAnnotation = targetVertexAnnotation;
    }

    @Override
    public String convertToJson() {
        throw new NotImplementedException();
    }
}
