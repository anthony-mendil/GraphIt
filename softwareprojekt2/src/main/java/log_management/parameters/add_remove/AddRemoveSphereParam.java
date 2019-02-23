package log_management.parameters.add_remove;

import graph.graph.Sphere;
import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.List;


/**
 * Parameter object for the action AddSphereLogAction/RemoveSphereLogAction.
 */
@Data
public class AddRemoveSphereParam implements Param {
    /**
     * The Sphere, which will be added/deleted.
     */
    @Getter
    private Sphere sphere;
    /**
     * The list of vertices, that got removed too.
     */
    @Getter
    private List<Vertex> vertices;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pSphere The target sphere.
     */
    public AddRemoveSphereParam(Sphere pSphere, List<Vertex> vertices) {
        this.sphere = pSphere;
        this.vertices = vertices;
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "Sphere: " + SyndromObjectPrinter.spherePrintEnglish(sphere) + ".";
        } else {
            return "Sphäre: " + SyndromObjectPrinter.spherePrintGerman(sphere) + ".";
        }
    }
}
