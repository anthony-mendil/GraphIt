package log_management.parameters.move;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

public class LayoutSpheresParam extends Param implements Serializable {

    /**
     * Creates a vertices object of its own class.
     */
    public LayoutSpheresParam() {

    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The Spheres were automatically positioned";
        } else {
            return "Die Sphären wurden automatisch angeordnet";
        }
    }
}
