package log_management.parameters.remove;

import graph.graph.Sphere;
import log_management.parameters.Param;

import java.io.Serializable;

public class RemoveSphereParam extends Param {

    private Sphere sphere;

    public RemoveSphereParam(Sphere sphere) {
        this.sphere = sphere;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
