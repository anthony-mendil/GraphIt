package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;


/**
 * Paramterobject for the action EditFontSphereParam.
 */
public class EditFontSphereParam extends Param {
    /**
     * The sphere and its old annotation-font.
     */
    @Getter
    private Pair<Sphere,Pair<String,String>> sphereOldFont;
    /**
     * The new font.
     */
    @Getter
    private String newFont;

    /**
     * Creates a new parameterobject of its own class.
     * @param pSphereOldFont The sphere and its old font.
     * @param pNewFont The new font.
     */
    public EditFontSphereParam(Pair<Sphere,Pair<String,String>> pSphereOldFont, String pNewFont) {
        this.sphereOldFont = pSphereOldFont;
        this.newFont = pNewFont;
    }


}
