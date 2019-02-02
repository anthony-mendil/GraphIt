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
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        StringBuilder builder = new StringBuilder();
        builder.append(language == Language.GERMAN ? "Relationen" : "Relations:");
        edges.forEach(e -> builder.append(language == Language.GERMAN
                ? SyndromObjectPrinter.edgePrintGerman(e)
                : SyndromObjectPrinter.edgePrintEnglish(e)));
        return builder.toString();
    }
}
