package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object for the action ActivateAnchorPointsFadeoutLogAction/DeactivateAnchorPointsFadeoutLogAction.
 */
@Data
public class ActivateDeactivateAnchorPointsFadeoutParam extends Param{

    /**
     * Set of edges, which anchor-points will (cancel) fadeout.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates a parameter object of its own class.
     * @param pEdges The list of edges.
     */
    public ActivateDeactivateAnchorPointsFadeoutParam(List<Edge> pEdges) {
        this.edges = pEdges;
    }

    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            String list = "Relations:\n";
            for (int i = 0; i < edges.size(); i++) {
                list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i));
            }
            return list;
        } else {
            String list = "Relationen:\n";
            for (int i = 0; i < edges.size(); i++) {
                list += SyndromObjectPrinter.edgePrintGerman(edges.get(i));
            }
            return list;
        }
    }
}
