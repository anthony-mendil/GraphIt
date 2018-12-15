package log_management.parameters.edit;

import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

/**
 * Parameter object of the action EditSphereLogAction.
 */
public class EditSphereAnnotationParam extends Param{
    /**
     * The old annotation of the sphere.
     */
    @Getter
    private Pair<Integer,String> oldAnnotation;
    /**
     * The new annotation of the sphere.
     */
    @Getter
    private String newAnnotation;

    /**
     * Creates a parameterobject of its own class.
     * @param pOldAnnotation The sphere and its old annotation.
     * @param pNewAnnotation The new annotation.
     */
    public EditSphereAnnotationParam(Pair<Integer,String> pOldAnnotation, String pNewAnnotation) {
        this.oldAnnotation = pOldAnnotation;
        this.newAnnotation = pNewAnnotation;
    }

}
