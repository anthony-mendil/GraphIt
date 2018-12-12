package graph.visualization.renderers;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeRenderer;

/**
 * The Edge Renderer renderers all edges from the syndrom graph. If edges converge at the vertex with the same arrowhead
 * in a specific area at the vertex, the arrowheads are grouped together. If the edge has an anchor point at the vertex,
 * the edge is drawn there.
 *
 * @param <V> the vertex type
 * @param <E> the edge type
 */
public class EdgeRenderer<V, E> extends BasicEdgeRenderer {
    @Override
    protected void drawSimpleEdge(RenderContext rc, Layout layout, Object o) {
        throw new UnsupportedOperationException();
    }
}
