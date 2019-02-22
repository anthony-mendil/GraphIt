package graph.visualization.transformer.vertex;

import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;
import java.util.ArrayList;

/**
 * Defines a functor that transform a vertex into highlight color. The input vertex is left unchanged.
 * Its extracting the highlight color of a vertex.
 *
 * @param <V> The vertex type
 */
public class VertexPaintAnalyseTransformer<V> implements Transformer<V, Paint> {
    private ArrayList<Vertex> vertices;
    public VertexPaintAnalyseTransformer(ArrayList<Vertex> vertices){
        this.vertices = vertices;
    }
    @Override
    public Paint transform(V v) {
        Vertex vertex = (Vertex) v;
        return vertices.contains(vertex) ? Color.green : Color.GRAY;
    }
}
