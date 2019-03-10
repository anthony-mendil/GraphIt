package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a functor that transform a vertex into highlight color. The input vertex is left unchanged.
 * Its extracting the highlight color of a vertex.
 *
 * @author Nina Unterberg
 *
 * @param <V> The vertex type.
 */
public class VertexPaintAnalyseTransformer<V> implements Transformer<V, Paint> {
    /**
     * A list with all vertices that get analysed.
     */
    private ArrayList<Vertex> vertices;

    /**
     * Creates a new VertexPaintAnalyseTransformer and sets this vertex list.
     * @param pVertices A list of vertices that gets set as this vertices list.
     */
    public VertexPaintAnalyseTransformer(List<Vertex> pVertices) {
        vertices = new ArrayList<>(pVertices);
    }

    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        return vertices.contains(vertex) ? Color.green : Color.GRAY;
    }
}
