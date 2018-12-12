package actions.analyse;

import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Filters the graph on given criteria. (in edit mode)
 * <p>
 * über predicates die gesetzt werden vv.getRenderContext().setVertexIncludePredicate(passendes predicate)
 * <p>
 * bei undo einfach predicate zurücksetzen
 */
public class FilterGraphAction {
    /**
     * Constructor in case the user filters the graph on the criteria.
     * @param edgetype edgetype
     */
    public FilterGraphAction(EdgeType edgetype) {
        throw new UnsupportedOperationException();
    }

    public FilterGraphAction(String regularExpression) {
        throw new UnsupportedOperationException();
    }

    /**
     * selection wird ausgeblendet, falls herausgefiltert
     */
    public FilterGraphAction() {
        throw new UnsupportedOperationException();
    }
}
