package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Filters the graph (edges, vertices, spheres) for a regular expression or edge type or the isVisible attribute of
 * vertices.
 */
public class FilterGraphAction extends GraphAction {
    /**
     * Constructor in case the user filters the graph on the criteria.
     *
     * @param edgeType the edge type to filter for
     */
    public FilterGraphAction(EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * filters the vertices/ spheres annotation for a regular expression
     *
     * @param regularExpression the regular expression to filter for
     */
    public FilterGraphAction(String regularExpression) {
        throw new UnsupportedOperationException();
    }

    /**
     * filters the vertices for the attribute isVisible=false
     */
    public FilterGraphAction() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
