package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditFontSizeLogAction.
 */
public class EditFontSizeParam extends Param{
    /**
     * The target text.
     */
    @Getter
    private String text;
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
     * Creates a parameterobject of its own class.
     * @param pText The target text.
     * @param pOldFontSize The old font size.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeParam(String pText, int pOldFontSize, int pNewFontSize) {
        this.text = pText;
        this.oldFontSize = pOldFontSize;
        this.newFontSize = pNewFontSize;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }

}
