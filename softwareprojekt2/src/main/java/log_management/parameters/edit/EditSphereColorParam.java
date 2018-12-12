package log_management.parameters.edit;

import log_management.parameters.Param;

import java.awt.*;
import java.io.Serializable;

public class EditSphereColorParam extends Param implements Serializable {

    private int sphereId;
    private String sphereAnnotation;
    private Color oldColor;
    private Color newColor;

    public EditSphereColorParam(int sphereId, String sphereAnnotation, Color oldColor, Color newColor) {
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
