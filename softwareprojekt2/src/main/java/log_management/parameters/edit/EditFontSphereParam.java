package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;


/**
 * Parameter object for the action EditFontSphereLogAction.
 */
@Data
public class EditFontSphereParam extends Param {
    /**
     * The sphere containing its old annotation-font.
     */
    @Getter
    private Sphere sphere;
    /**
     * The old font.
     */
    @Getter
    private String oldFont;
    /**
     * The new font.
     */
    @Getter
    private String newFont;

    /**
     * Creates a new parameter object of its own class.
     * @param sphere The sphere containing its old font.
     * @param pOldFont The old font.
     * @param pNewFont The new font.
     */
    public EditFontSphereParam(Sphere sphere, String pOldFont, String pNewFont) {
        this.sphere = sphere;
        this.oldFont = pOldFont;
        this.newFont = pNewFont;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
