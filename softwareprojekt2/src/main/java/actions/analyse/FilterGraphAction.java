package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.EdgeArrowPredicate;
import graph.algorithmen.predicates.EdgeIsVisiblePredicate;
import graph.algorithmen.predicates.VertexAnnotationPredicate;
import graph.algorithmen.predicates.VertexIsVisiblePredicate;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.Vertex;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.TruePredicate;

/**
 * Filters the graph (edges, vertices, spheres) for a regular expression or edge type or the isVisible attribute of
 * vertices.
 */
public class FilterGraphAction extends GraphAction {
    private Predicate<Context<Graph<Vertex, Edge>, Edge>> predicateEdge;
    private Predicate<Context<Graph<Vertex, Edge>, Vertex>> predicateVertex;

    /**
     * Constructor in case the user filters the graph on the criteria.
     *
     * @param edgeType The edge type to filter for.
     */
    public FilterGraphAction(EdgeArrowType edgeType, boolean toDo) {
        if (toDo) {
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
     */
    public FilterGraphAction(String regularExpression, boolean toDo) {
        if (toDo && regularExpression != null) {
            predicateVertex = new VertexAnnotationPredicate<>(regularExpression);
            predicateEdge = TruePredicate.getInstance();
        } else {
            predicateVertex = TruePredicate.getInstance();
            predicateEdge = TruePredicate.getInstance();
        }
    }

    /**
     * Filters the vertices for the attribute isVisible=false.
     */
    public FilterGraphAction(boolean toDo) {
        if (toDo) {
            predicateVertex = new VertexIsVisiblePredicate<>();
            predicateEdge = new EdgeIsVisiblePredicate<>();
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
        // nothing to do
    }
}
