package log_management.parameters.add;

import graph.graph.Sphere;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject of the action addSphereLogAction.
 */
public class AddSphereParam extends Param{
    /**
     * Sphere which will be added to the graph.
     */
    @Getter
    private Sphere sphere;

    /**
     * Creates an object of its own class.
     * @param sphere The new sphere.
     */
    public AddSphereParam(Sphere sphere) {
        this.sphere = sphere;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
