package log_management.parameters.add_remove;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.List;


/**
 * Parameter object for the action AddSphereLogAction/RemoveSphereLogAction.
 */
@Data
public class AddRemoveSphereParam extends Param{
    /**
     * The Sphere, which will be added/deleted.
     */
    @Getter
    private List<Sphere> sphere;

    /**
     * Creates a parameter object of its own class.
     * @param pSphere The target sphere.
     */
    public AddRemoveSphereParam(List<Sphere>pSphere) {
        this.sphere = pSphere;
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
