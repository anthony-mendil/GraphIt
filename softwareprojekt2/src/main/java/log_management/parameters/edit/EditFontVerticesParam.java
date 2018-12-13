package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action EditFontLogAction.
 */
public class EditFontVerticesParam extends Param{
    /**
     * The text, which have to been changed.
     */
    @Getter
    private String text;
    /**
     * The old font-type.
     */
    @Getter
    private String oldFont;
    /**
     * The new font-type.
     */
    @Getter
    private String newFont;
    // Q:Does the font change everywhere or is for example a list of vertex id's needed?
    // A:Unfortunately no :( . But I am not actually sure about it, just pretending.

    /**
     * Creates a parameterobject of its own class.
     * @param pOldFont The old font-type.
     * @param pNewFont The new font-type.
     * @param pText TODO
     */
    public EditFontVerticesParam(String pText, String pOldFont, String pNewFont) {
        this.text = pText;
        this.oldFont = pOldFont;
        this.newFont = pNewFont;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
