package log_management.parameters.remove;

import LogManagement.Parameters.Param;
import graph.graph.Sphere;

import java.io.Serializable;

public class RemoveSphereParam extends Param implements Serializable {

    private Sphere sphere;

    public RemoveSphereParam(Sphere sphere) {
        this.sphere = sphere;
    }
}
