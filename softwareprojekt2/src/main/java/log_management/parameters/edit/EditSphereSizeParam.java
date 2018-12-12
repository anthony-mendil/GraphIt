package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;

public class EditSphereSizeParam extends Param implements Serializable {

    private int sphereId;
    private String sphereAnnotation;
    private double oldWidth;
    private double newWidth;
    private double oldHeight;
    private double newHeight;

    public EditSphereSizeParam(int sphereId, String sphereAnnotation, double oldWidth, double newWidth, double oldHeight, double newHeight) {
        this.sphereId = sphereId;
        this.sphereAnnotation = sphereAnnotation;
        this.oldWidth = oldWidth;
        this.newWidth = newWidth;
        this.oldHeight = oldHeight;
        this.newHeight = newHeight;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
