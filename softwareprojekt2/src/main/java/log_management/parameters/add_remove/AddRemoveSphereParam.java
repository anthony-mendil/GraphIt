package log_management.parameters.remove;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject for the action AddSphereLogAction/RemoveSphereLogAction.
 */
public class RemoveSphereParam extends Param{
    /**
     * The Sphere, which will be added/deleted.
     */
    @Getter
    private Sphere sphere;

    /**
     * Creates a parameterobject of its own class.
     * @param pSphere The target sphere.
     */
    public RemoveSphereParam(Sphere pSphere) {
        this.sphere = pSphere;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
