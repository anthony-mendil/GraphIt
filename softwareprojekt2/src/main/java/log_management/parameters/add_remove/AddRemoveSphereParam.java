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
     * The vertices, that got deleted too.
     */
    @Getter
    private AddRemoveVerticesParam addRemoveVerticesParam;
    /**
     * Creates a parameter object of its own class.
     *
     * @param pSphere The target sphere.
     */
    public AddRemoveSphereParam(Sphere pSphere, AddRemoveVerticesParam addRemoveVerticesParam) {
        this.sphere = pSphere;
        this.addRemoveVerticesParam = addRemoveVerticesParam;
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
