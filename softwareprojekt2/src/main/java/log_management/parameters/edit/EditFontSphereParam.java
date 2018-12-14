package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;

public class EditFontSphereParam extends Param {

    private String oldFont;
    private String newFont;
    // does the font change everywhere or is for example a list of vertex id's needed?

    public EditFontSphereParam(String oldFont, String newFont) {
        this.oldFont = oldFont;
        this.newFont = newFont;
    }

}
