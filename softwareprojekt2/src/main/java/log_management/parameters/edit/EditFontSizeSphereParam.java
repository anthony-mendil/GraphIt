package log_management.parameters.edit;

import graph.graph.Sphere;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;


/**
 * Parameter object of the action EditFontSizeSphereLogAction.
 */
public class EditFontSizeSphereParam extends Param{
    /**
     * The sphere and the annotation.
     */
    @Getter
    private Pair<Sphere,String> sphereText;
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
     * @param pSphereText The sphere and the annotation.
     * @param pOldFontSize The old font size.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeSphereParam(Pair<Sphere,String> pSphereText, int pOldFontSize, int pNewFontSize) {
        this.sphereText = pSphereText;
        this.oldFontSize = pOldFontSize;
        this.newFontSize = pNewFontSize;
    }


}
