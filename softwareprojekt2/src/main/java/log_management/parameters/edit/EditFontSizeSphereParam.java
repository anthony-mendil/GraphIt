package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameter object of the action EditFontSizeSphereLogAction.
 */
public class EditFontSizeSphereParam extends Param{
    /**
     * The target sphere id.
     */
    @Getter
    private Integer id;
    /**
     * The old size of the font.
     */
    @Getter
    private int oldFontSize;
    /**
     * The new size of the font.
     */
    @Getter
    private int newFontSize;
    // Q:Does the font size change everywhere or is for example a list of vertex id's needed?
    // A:Like I said, no!! But I am not sure about it.

    /**
     * Creates a parameter object of its own class.
     * @param id the id of the sphere
     * @param pOldFontSize The old font size.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeSphereParam(Integer id, int pOldFontSize, int pNewFontSize) {
        this.id = id;
        this.oldFontSize = pOldFontSize;
        this.newFontSize = pNewFontSize;
    }


}
