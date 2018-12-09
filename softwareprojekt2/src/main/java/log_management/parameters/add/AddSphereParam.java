package log_management.parameters.add;

import LogManagement.Parameters.Param;
import graph.graph.Sphere;

import java.io.Serializable;

public class AddSphereParam extends Param implements Serializable {

    private Sphere sphere;

    public AddSphereParam(Sphere sphere) {
        this.sphere = sphere;
    }
}
