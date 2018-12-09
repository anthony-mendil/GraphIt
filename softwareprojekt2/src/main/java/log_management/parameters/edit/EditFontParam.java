package log_management.parameters.edit;

import LogManagement.Parameters.Param;

import java.io.Serializable;

public class EditFontParam extends Param implements Serializable {

    private String oldFont;
    private String newFont;
    // does the font change everywhere or is for example a list of vertex id's needed?

    public EditFontParam(String oldFont, String newFont) {
        this.oldFont = oldFont;
        this.newFont = newFont;
    }
}
