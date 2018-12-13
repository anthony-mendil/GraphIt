package log_management.parameters.add;

import graph.graph.Sphere;
import log_management.parameters.Param;

import java.io.Serializable;

public class AddSphereParam extends Param {

    private Sphere sphere;

    public AddSphereParam(Sphere sphere) {
        this.sphere = sphere;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
