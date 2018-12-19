package actions.analyse;

import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Filters the graph (edges, vertices, spheres) for a regular expression or edge type or the isVisible attribute of
 * vertices.
 */
public class FilterGraphAction {
    /**
     * Constructor in case the user filters the graph on the criteria.
     * @param edgeType The edge type to filter for.
     */
    public FilterGraphAction(EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * Filters the vertices/spheres annotation for a regular expression.
     * @param regularExpression The regular expression to filter for.
     */
    public FilterGraphAction(String regularExpression) {
        throw new UnsupportedOperationException();
    }

    /**
     * Filters the vertices for the attribute isVisible=false.
     */
    public FilterGraphAction() {
        throw new UnsupportedOperationException();
    }
}
