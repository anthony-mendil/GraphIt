package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.awt.*;
import java.io.Serializable;

public class EditVertexColorParam extends Param implements Serializable {

    private int vertexId;
    private Color oldColor;
    private Color newColor;

    // maybe new parameter class for fill color
    private Color oldFillColor;
    private Color newFillColor;

    public EditVertexColorParam(int vertexId, Color oldColor, Color newColor, Color oldFillColor, Color newFillColor) {
        this.vertexId = vertexId;
        this.oldColor = oldColor;
        this.newColor = newColor;
        this.oldFillColor = oldFillColor;
        this.newFillColor = newFillColor;
    }
}
