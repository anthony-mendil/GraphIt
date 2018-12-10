package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class EditVerticesColorParam extends Param implements Serializable {

    private List<Integer> vertexId;
    private List<Color> oldColors;
    private Color newColor;

    // maybe new parameter class for fill color
    private List<Color> oldFillColors;
    private Color newFillColor;

    public EditVerticesColorParam() {
    }

}
