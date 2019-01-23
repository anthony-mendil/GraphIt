package log_management.parameters.activate_deactivate;

import graph.graph.Edge;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.EnumNameCreator;
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
                list += "\nId: " + edges.get(i).getId() + ", Stroke type: "
                        + EnumNameCreator.strokeTypeTranslaotr(edges.get(i).getStroke(), language)
                        + ", Arrow type: "
                        + EnumNameCreator.edgeArrowTypeTranslator(edges.get(i).getArrowType(), language);
            }
            return list;
        } else {
            String list = "";
            for (int i = 0; i < edges.size(); i++) {
                list += "\nId: " + edges.get(i).getId() + ", Linienart: "
                        + EnumNameCreator.strokeTypeTranslaotr(edges.get(i).getStroke(), language)
                        + ", Relationsart: "
                        + EnumNameCreator.edgeArrowTypeTranslator(edges.get(i).getArrowType(), language);
            }
            return list;
        }
    }
}
