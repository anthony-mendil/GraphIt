package log_management.parameters.edit;

import LogManagement.Parameters.Param;
import log_management.parameters.Param;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditEdgesColorParam extends Param implements Serializable {

    private List<Integer> edgesId;
    private List<Integer> sourceVerticesId;
    private List<String> sourceVerticesAnnotation;
    private List<Integer> targetVerticesId;
    private List<String> targetVerticesAnnotation;
    private List<Color> oldColors;
    private Color newColor;

    public EditEdgesColorParam(List<Integer> edgesId, List<Integer> sourceVerticesId, List<String> sourceVerticesAnnotation, List<Integer> targetVerticesId, List<String> targetVerticesAnnotation, List<Color> oldColors, Color newColor) {
        this.edgesId = edgesId;
        this.sourceVerticesId = sourceVerticesId;
        this.sourceVerticesAnnotation = sourceVerticesAnnotation;
        this.targetVerticesId = targetVerticesId;
        this.targetVerticesAnnotation = targetVerticesAnnotation;
        this.oldColors = oldColors;
        this.newColor = newColor;
    }

    @Override
    public String convertToJson() {
        return null;
    }
}
