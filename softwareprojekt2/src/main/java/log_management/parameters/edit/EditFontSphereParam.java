package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;

/**
 * Parameter object of the action EditFontSphereLogAction.
 */
public class EditFontSphereParam extends Param {

    /**
     * the old font
     */
    private String oldFont;
    /**
     * the new font
     */
    private String newFont;
    /**
     * the sphere id
     */
    private int sphereId;
    // does the font change everywhere or is for example a list of vertex id's needed?

    /**
     * creates a new parameter object
     * @param sphereId the sphere id
     * @param oldFont the old font
     * @param newFont the new font
     */
    public EditFontSphereParam(int sphereId, String oldFont, String newFont) {
        this.oldFont = oldFont;
        this.newFont = newFont;
    }

}
