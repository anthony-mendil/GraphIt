package log_management.parameters.edit;

import log_management.parameters.Param;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object of the action EditFontSizeSphereLogAction.
 */
public class EditFontSizeVerticesParam extends Param {

    /**
     * the new font size
     */
    private int newFontSize;
    /**
     * a map of vertex ids to the old font size
     */
    private Map<Integer, Integer> vertices;

    /**
     * creates a new parameter object
     * @param vertices map of vertex ids to old font size
     * @param newFontSize the new font size
     */
    public EditFontSizeVerticesParam(Map<Integer, Integer> vertices, int newFontSize) {
        this.vertices = vertices;
        this.newFontSize = newFontSize;
    }
}
