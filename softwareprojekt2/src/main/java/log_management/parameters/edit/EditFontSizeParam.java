package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class EditFontSizeParam extends Param implements Serializable {

    private int oldFontSize;
    private int newFontSize;
    // does the font size change everywhere or is for example a list of vertex id's needed?

    public EditFontSizeParam(int oldFontSize, int newFontSize) {
        this.oldFontSize = oldFontSize;
        this.newFontSize = newFontSize;
    }
}
