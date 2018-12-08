package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.io.Serializable;
import java.util.List;

public class EditEdgesTypeParam extends Param implements Serializable {

    private List<Integer> edgesId;
    private List<Integer> sourceVerticesId;
    private List<String> sourceVerticesAnnotation;
    private List<Integer> targetVerticesId;
    private List<String> targetVerticesAnnotation;
    // later add edge type attribute


    public EditEdgesTypeParam(List<Integer> edgesId, List<Integer> sourceVerticesId, List<String> sourceVerticesAnnotation, List<Integer> targetVerticesId, List<String> targetVerticesAnnotation) {
        this.edgesId = edgesId;
        this.sourceVerticesId = sourceVerticesId;
        this.sourceVerticesAnnotation = sourceVerticesAnnotation;
        this.targetVerticesId = targetVerticesId;
        this.targetVerticesAnnotation = targetVerticesAnnotation;
    }
}
