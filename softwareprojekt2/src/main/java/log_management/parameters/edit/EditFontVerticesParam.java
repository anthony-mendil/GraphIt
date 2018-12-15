package log_management.parameters.edit;

import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditFontLogAction.
 */
public class EditFontVerticesParam extends Param {
    /**
     * map of vertex ids to old fonts
     */
    @Getter
    private Map<Integer,String> vertices;
    /**
     * The new font-type.
     */
    @Getter
    private String newFont;
    // Q:Does the font change everywhere or is for example a list of vertex id's needed?
    // A:Unfortunately no :( . But I am not actually sure about it, just pretending.

    /**
     * Creates a parameter object of its own class.
     *
     * @param vertices The old font-type.
     * @param pNewFont The new font-type.
     */
    public EditFontVerticesParam(Map<Integer,String> vertices, String pNewFont) {
        this.vertices = vertices;
        this.newFont = pNewFont;
    }
}
