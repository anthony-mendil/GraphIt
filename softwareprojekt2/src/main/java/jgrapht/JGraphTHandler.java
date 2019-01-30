package jgrapht;

import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.List;
import java.util.Set;

/**
 * Represents a handler converting the JUNG-graph to a graph working with the
 * JGraphT algorithms and analyses by the given criteria.
 */
public class JGraphTHandler {
    /**
     * The start-vertex of the path.
     */
    private Vertex startVertex;
    /**
     * The end-vertex of the path.
     */
    private Vertex endVertex;
    /**
     * The graph in JGraphT-form.
     */
    private Graph algorithmGraph;
    /**
     * Constructor in case the user changes to analyse-mode and analyses without using a vertex.
     */
    public JGraphTHandler(List<Vertex> pVertices, Set<Pair<Vertex,Vertex>> pEdges) {
        convertGraphToJGraphT(pVertices,pEdges);
    }
    /**
     * Constructor in case the user wants to analyse the graph in terms of paths.
     * @param pStart The start-vertex.
     * @param pEnd The end-vertex.
     */
    public JGraphTHandler(Vertex pStart, Vertex pEnd){

    }
    /**
     * Converts the graph from our datatype to the JGraphT datatype.
     *
     * @param pVertices Given list of vertices.
     * @param pEdges    Given list of edges.
     * @return The Graph in JGraphT-Type.
     */
    public void convertGraphToJGraphT(List<Vertex> pVertices, Set<Pair<Vertex,Vertex>> pEdges) {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        Graph<Vertex,Edge> jGraphTGraph = new DefaultDirectedGraph<>(Edge.class);
        for(Vertex vertex : pVertices){
            graph.addVertex(vertex);
        }
        for(Pair<Vertex,Vertex> edge : pEdges){
            jGraphTGraph.addEdge(edge.getKey(),edge.getValue(),graph.findEdge(edge.getKey(), edge.getValue()));
        }
        algorithmGraph = jGraphTGraph;
    }
    /**
     * Detects all cycles in the directed graph and returns them in a set.
     *
     * @param pGraph The graph in JGraphT-form.
     * @return The set of vertices in a cycle.
     */
    public Set<Vertex> detectCycles(Graph pGraph) {
        throw new UnsupportedOperationException();
    }
    /**
     * List all possible paths between two vertices.
     *
     * @param pGraph  The graph in JGraphT-form.
     * @param pSource The start-vertex of the path.
     * @param pSink   The end-vertex of the path.
     * @return The list of paths between the vertices.
     */
    public List<List<Vertex>> getAllPaths(Graph pGraph, Vertex pSource, Vertex pSink) {
        throw new UnsupportedOperationException();
    }
    /**
     * Finds the shortest path between two vertices in the graph, if one exists.
     *
     * @param pGraph  The graph in JGraphT-form.
     * @param pSource The start-vertex of the path.
     * @param pSink   The end-vertex of the path.
     * @return The shortest path between the vertices.
     */
    public List<Vertex> getShortestPath(Graph pGraph, Vertex pSource, Vertex pSink) {
        throw new UnsupportedOperationException();
    }
    /**
     * Detects all convergent branches in the directed graph.
     *
     * @param pGraph The graph in JGraphT-form.
     * @return The set of vertices in the convergent branch.
     */
    public Set<Vertex> detectConvergentBranches(Graph pGraph) {
        throw new UnsupportedOperationException();
    }
    /**
     * Detects all divergent branches in the directed graph.
     *
     * @param pGraph The graph in JGraphT-form.
     * @return The set of vertices in the divergent branch.
     */
    public Set<Vertex> detectDivergentBranches(Graph pGraph) {
        throw new UnsupportedOperationException();
    }
}