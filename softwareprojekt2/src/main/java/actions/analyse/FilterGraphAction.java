package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.EdgeArrowPredicate;
import graph.algorithmen.predicates.VertexAnnotationPredicate;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.TruePredicate;

/**
 * Filters the graph (edges, vertices, spheres) for a regular expression or edge type or
 * the isVisible attribute of vertices.
 * @author Clement Phung
 */
public class FilterGraphAction extends GraphAction {
    /**
     * Predicate for the edges.
     */
    private Predicate<Context<Graph<Vertex, Edge>, Edge>> predicateEdge;
    /**
     * Predicate for the vertices.
     */
    private Predicate<Context<Graph<Vertex, Edge>, Vertex>> predicateVertex;


    /**
     * Constructor in case the user filters the graph on the criteria.
     *
     * @param edgeType The edge type to filter for.
     * @param doFilter Indicator, if it should be filtered or not.
     */
    public FilterGraphAction(EdgeArrowType edgeType, boolean doFilter) {
        if (doFilter) {
            predicateEdge = new EdgeArrowPredicate<>(edgeType);
            predicateVertex = TruePredicate.getInstance();
        } else {
            predicateVertex = TruePredicate.getInstance();
            predicateEdge = TruePredicate.getInstance();
        }
    }

    /**
     * Filters the vertices/spheres regex for a regular expression.
     *
     * @param regularExpression The regular expression to filter for.
     * @param doFilter          Indicator, if it should be filtered or not.
     */
    public FilterGraphAction(String regularExpression, boolean doFilter) {
        if (doFilter && regularExpression != null) {
            predicateVertex = new VertexAnnotationPredicate<>(regularExpression);
            predicateEdge = TruePredicate.getInstance();
        } else {
            predicateVertex = TruePredicate.getInstance();
            predicateEdge = TruePredicate.getInstance();
        }
    }

    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();
        vv.getRenderContext().setVertexIncludePredicate(predicateVertex);
        vv2.getRenderContext().setVertexIncludePredicate(predicateVertex);
        vv.getRenderContext().setEdgeIncludePredicate(predicateEdge);
        vv2.getRenderContext().setEdgeIncludePredicate(predicateEdge);
        vv.repaint();
        vv2.repaint();
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        // no undo for this action.
    }
}
