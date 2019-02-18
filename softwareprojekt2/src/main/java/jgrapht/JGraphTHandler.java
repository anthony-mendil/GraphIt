package jgrapht;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import graph.visualization.picking.SyndromPickSupport;
import javafx.util.Pair;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.HashSet;
import java.util.Iterator;
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
     * Calculates the Endpoints in t
     */
    public void calculateEndpoints(){
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if(pickedState.getPicked().size() != 2){
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst zwei Knoten markieren.",true);
            return;
        }
        Iterator<Vertex> iterator = pickedState.getPicked().iterator();
        startVertex = iterator.next();
        endVertex = iterator.next();
    }
    /**
     * Detects all cycles in the directed graph and returns them in a set.
     *
     * @param pGraph The graph in JGraphT-form.
     * @return The set of vertices in a cycle.
     */
    public Set<Vertex> detectCycles(Graph pGraph) {
        CycleDetector<Vertex,Edge> cycleDetector = new CycleDetector<>(pGraph);
        Set<Vertex> cycle = cycleDetector.findCycles();
        for(Vertex vertex : cycle){
            System.out.println(vertex.getId());
        }
        return cycle;
    }
    /**
     * List all possible paths between two vertices.
     *
     * @return The list of paths between the vertices.
     */
    public List<GraphPath<Vertex,Edge>> getAllPaths() {
        calculateEndpoints();

        AllDirectedPaths pathFinder = new AllDirectedPaths<>(algorithmGraph);
        List<GraphPath<Vertex,Edge>> paths = pathFinder.getAllPaths(startVertex, endVertex, true, Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertices().size());

        return paths;
    }
    /**
     * Finds the shortest path between two vertices in the graph, if one exists.
     *
     * @return The shortest path between the vertices.
     */
    public List<Vertex> getShortestPath() {
        calculateEndpoints();

        DijkstraShortestPath shortestPathFinder = new DijkstraShortestPath(algorithmGraph);
        GraphPath<Vertex,Edge> shortestPath = shortestPathFinder.getPath(startVertex, endVertex);
        return shortestPath.getVertexList();
    }
    /**
     * Detects all convergent branches in the directed graph.
     *
     * @return The set of vertices in the convergent branch.
     */
    public Set<Vertex> detectConvergentBranches() {
        Set<Vertex> convergentBranches = new HashSet<>();
        for(Vertex vertex : (Set<Vertex>)algorithmGraph.vertexSet()){
            if(algorithmGraph.inDegreeOf(vertex) > 1){
                convergentBranches.add(vertex);
            }
        }
        return convergentBranches;
    }
    /**
     * Detects all divergent branches in the directed graph.
     *
     * @return The set of vertices in the divergent branch.
     */
    public Set<Vertex> detectDivergentBranches() {
        Set<Vertex> divergentBranches = new HashSet<>();
        for(Vertex vertex : (Set<Vertex>)algorithmGraph.vertexSet()){
            if(algorithmGraph.outDegreeOf(vertex) > 1){
                divergentBranches.add(vertex);
            }
        }
        return divergentBranches;
    }
}