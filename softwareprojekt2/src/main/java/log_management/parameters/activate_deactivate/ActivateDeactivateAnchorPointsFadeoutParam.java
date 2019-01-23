package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
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
            String list = "";
            for (int i = 0; i < edges.size(); i++) {
                list += "Id: " + edges.get(i).getId() + " Stroke type: " + edges.get(i).getStroke().name()
                        + " Arrow type: " + edges.get(i).getArrowType().name();
            }
            return list;
        } else {
            String list = "";
            for (int i = 0; i < edges.size(); i++) {
                list += "Id: " + edges.get(i).getId() + " Linienart: " + edges.get(i).getStroke().name()
                        + " Relationsart: " + edges.get(i).getArrowType().name();
            }
            return list;
        }
    }
}
