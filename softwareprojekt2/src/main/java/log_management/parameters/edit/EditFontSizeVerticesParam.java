package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;

public class EditFontSizeVerticesParam extends Param {

    private int oldFontSize;
    private int newFontSize;
    // does the font size change everywhere or is for example a list of vertex id's needed?

    public EditFontSizeVerticesParam(int oldFontSize, int newFontSize) {
        this.oldFontSize = oldFontSize;
        this.newFontSize = newFontSize;
    }

}
