package graph.graph;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


/**
 * The syndrom graph. Its extending the directed sparse graph with spheres and anchor points.
 *
 * @author Nina Unterberg
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    /**
     * The object factory for syndrom.
     */
    private final transient GraphObjectsFactory graphObjectsFactory;
    /**
     * List of spheres.
     */
    private transient List<Sphere> spheres = new ArrayList<>();

    /**
     * Creates a new syndrom graph.
     */
    public SyndromGraph() {
        graphObjectsFactory = new GraphObjectsFactory();
    }

    /**
     * Assigns a vertex to a sphere.
     *
     * @param pSphere The sphere to assign to.
     * @param pVertex The vertex.
     */
    private void addVertexToSphere(Sphere pSphere, Vertex pVertex) {
        pSphere.getVertices().add(pVertex);
    }

    @SuppressWarnings("unchecked")
    public void addEdge(V v1, V v2) {
        Edge edge = graphObjectsFactory.createEdge();
        addEdge((E) edge, v1, v2);
    }

    /**
     * Adds an edge to the graph, if it already existed once in the past.
     *
     * @param edge The existing edge.
     * @param v1   The source vertex.
     * @param v2   The sink vertex.
     */
    @SuppressWarnings("unchecked")
    public void addEdgeExisting(Edge edge, V v1, V v2) {
        addEdge((E) edge, v1, v2);
    }

    /**
     * Adds a vertex to the syndrom graphs and assigns it to a sphere if it wasn't existing in the past.
     *
     * @param pSphere The sphere to assign to.
     * @param pos     the position of the new sphere
     * @return True if the vertex was added to the graph, false if not.
     */
    @SuppressWarnings("unchecked")
    public Vertex addVertex(Point2D pos, Sphere pSphere) {
        Vertex vertex = graphObjectsFactory.createVertex(pos);
        addVertex((V) vertex);
        addVertexToSphere(pSphere, vertex);
        return vertex;
    }

    /**
     * Adds a vertex to the SyndromGraph and assigns it to the sphere if it was existing in the past.
     *
     * @param vertex the new vertex
     */
    @SuppressWarnings("unchecked")
    public void addVertexExisting(Vertex vertex) {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport<Vertex, Edge>) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(vertex.getCoordinates().getX(), vertex.getCoordinates().getY());
        addVertex((V) vertex);
        addVertexToSphere(sp, vertex);
    }

    /**
     * Adds a new sphere to the graph.
     *
     * @param pos The point where the sphere gets placed
     */
    public void addSphere(Point2D pos) {
        Sphere sphere = graphObjectsFactory.createSphere(pos);
        spheres.add(sphere);
    }

    /**
     * Removes a sphere from the graph.
     *
     * @param pSphere The sphere to remove.
     */
    public void removeSphere(Sphere pSphere) {
        spheres.remove(pSphere);
    }
}
