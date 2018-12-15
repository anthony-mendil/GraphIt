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
     * The sphere containing the annotation.
     */
    @Getter
    private Sphere sphereText;
    /**
     * The new size of the font.
     */
    @Getter
    private int newFontSize;
    // Q:Does the font size change everywhere or is for example a list of vertex id's needed?
    // A:Like I said, no!! But I am not sure about it.

    /**
     * Creates a parameter object of its own class.
     * @param pSphere The sphere containing the old annotation.
     * @param pNewFontSize The new font size.
     */
    public EditFontSizeSphereParam(Sphere pSphere, int pNewFontSize) {
        this.sphereText = pSphere;
        this.newFontSize = pNewFontSize;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
