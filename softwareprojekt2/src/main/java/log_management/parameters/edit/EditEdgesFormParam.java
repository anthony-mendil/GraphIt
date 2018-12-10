package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditEdgesFormParam extends Param implements Serializable {

    private List<Integer> edgesId;
    private List<Integer> sourceVerticesId;
    private List<String> sourceVerticesAnnotation;
    private List<Integer> targetVerticesId;
    private List<String> targetVerticesAnnotation;
    private List<Stroke> oldStrokes;
    private Stroke newStroke;

    public EditEdgesFormParam(List<Integer> edgesId, List<Integer> sourceVerticesId, List<String> sourceVerticesAnnotation, List<Integer> targetVerticesId, List<String> targetVerticesAnnotation, List<Stroke> oldStrokes, Stroke newStroke) {
        this.edgesId = edgesId;
        this.sourceVerticesId = sourceVerticesId;
        this.sourceVerticesAnnotation = sourceVerticesAnnotation;
        this.targetVerticesId = targetVerticesId;
        this.targetVerticesAnnotation = targetVerticesAnnotation;
        this.oldStrokes = oldStrokes;
        this.newStroke = newStroke;
    }
}
