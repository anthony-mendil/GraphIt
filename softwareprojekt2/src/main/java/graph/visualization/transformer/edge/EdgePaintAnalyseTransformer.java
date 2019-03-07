package graph.visualization.transformer.edge;

import graph.graph.Edge;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a functor that transform a vertex into highlight color. The input vertex is left unchanged.
 * Its extracting the highlight color of a vertex.
 *
 * @param <E> The edge type
 */
public class EdgePaintAnalyseTransformer<E> implements Transformer<E, Paint> {
    /**
     * the list of edges
     */
    private ArrayList<Edge> edges;

    /**
     * the list of edges, need to get highlighted
     * @param pEdges list of edges
     */
    public EdgePaintAnalyseTransformer(List<Edge> pEdges) {
        edges = new ArrayList<>(pEdges);
    }

    @Override
    public Paint transform(E e) {
        Edge edge = (Edge) e;
        return edges.contains(edge) ? Color.green : Color.GRAY;
    }
}
