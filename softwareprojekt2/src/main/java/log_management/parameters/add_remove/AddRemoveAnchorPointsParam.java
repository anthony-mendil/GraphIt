package log_management.parameters.add_remove;

import graph.graph.Edge;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Parameter object of the action AddAnchorPointsLogAction/RemoveAnchorPointsLogAction.
 */
@Data
public class AddRemoveAnchorPointsParam implements Param {
    /**
     * The selected edges.
     */
    @Getter
    private List<Edge> edges;

    /**
     * Creates a parameter object of its own class.
     *
     * @param pEdges The selected edges.
     */
    public AddRemoveAnchorPointsParam(List<Edge> pEdges) {
        this.edges = pEdges;
    }

    @Override
    public String prettyPrint() {
//        Language language = Values.getInstance().getGuiLanguage();
//        if (language == Language.ENGLISH) {
//            String list = "Relations:\n";
//            for (int i = 0; i < edges.size(); i++) {
//                list += SyndromObjectPrinter.edgePrintEnglish(edges.get(i));
//            }
//            return list;
//        } else {
//            String list = "Relationen:\n";
//            for (int i = 0; i < edges.size(); i++) {
//                list += SyndromObjectPrinter.edgePrintGerman(edges.get(i));
//            }
//            return list;
//        }
        return "";
    }
}
