package jgrapht;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import javafx.util.Pair;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.cycle.TarjanSimpleCycles;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;

import java.util.*;

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
    private DefaultDirectedGraph algorithmGraph;
    /**
     * The list of selected vertices.
     */
    private Set<Vertex> pickedVertices;

    /**
     * Constructor in case the user changes to analyse-mode and analyses without using a vertex.
     */
    public JGraphTHandler(List<Vertex> pVertices, Set<Pair<Vertex, Vertex>> pEdges) {
        convertGraphToJGraphT(pVertices, pEdges);
    }

    /**
     * Converts the graph from our datatype to the JGraphT datatype.
     *
     * @param pVertices Given list of vertices.
     * @param pEdges    Given list of edges.
     * @return The Graph in JGraphT-Type.
     */
    public void convertGraphToJGraphT(List<Vertex> pVertices, Set<Pair<Vertex, Vertex>> pEdges) {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        DefaultDirectedGraph<Vertex, Edge> jGraphTGraph = new DefaultDirectedGraph<>(Edge.class);
        for (Vertex vertex : pVertices) {
            jGraphTGraph.addVertex(vertex);
        }
        for (Pair<Vertex, Vertex> edge : pEdges) {
            jGraphTGraph.addEdge(edge.getKey(), edge.getValue(), graph.findEdge(edge.getKey(), edge.getValue()));
        }
        algorithmGraph = jGraphTGraph;
    }

    /**
     * Calculates the Endpoints in the graph. It checks, whether two vertices are selected.
     */
    private boolean calculateEndpoints() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() != 2) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst zwei Knoten markieren.", true);
            return false;
        }
        Iterator<Vertex> iterator = pickedState.getPicked().iterator();
        startVertex = iterator.next();
        endVertex = iterator.next();
        return true;
    }

    /**
     * Sets the vertex for the neighbor algorithms. It checks if at least one vertex is picked.
     */
    public boolean isAtLeastOnePicked(){
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().size() < 1) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Bitte zuerst mindestens einen Knoten markieren.", true);
            return false;
        }
        pickedVertices = pickedState.getPicked();
        return true;
    }

    /**
     * Detects all cycles in the directed graph and returns them in a set. Tarjan's algorithm is the best one.
     *
     * @return The set of vertices in a cycle.
     */
    public List<List<Vertex>> detectCycles() {

        TarjanSimpleCycles tarjanSimpleCycles = new TarjanSimpleCycles(algorithmGraph);
        List<List<Vertex>> list = tarjanSimpleCycles.findSimpleCycles();


        return list;
    }

    /**
     * List all possible paths between two vertices.
     *
     * @return The list of paths between the vertices.
     */
    public List<GraphPath<Vertex, Edge>> getAllPaths() {
        if(calculateEndpoints()) {

            AllDirectedPaths pathFinder = new AllDirectedPaths<>(algorithmGraph);
            List<GraphPath<Vertex, Edge>> paths = pathFinder.getAllPaths(startVertex, endVertex, true, Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertices().size());
            return paths;
        }
        return null;
    }

    /**
     * Finds the shortest path between two vertices in the graph, if one exists.
     *
     * @return The shortest path between the vertices.
     */
    public GraphPath<Vertex,Edge> getShortestPath() {
        if(calculateEndpoints()) {
            DijkstraShortestPath shortestPathFinder = new DijkstraShortestPath(algorithmGraph);
            GraphPath<Vertex, Edge> shortestPath = shortestPathFinder.getPath(startVertex, endVertex);
            if(shortestPath == null){
                HelperFunctions helperFunctions = new HelperFunctions();
                helperFunctions.setActionText("Es exisitert kein Weg von " + startVertex.getAnnotation().get(Language.GERMAN.name()) + " nach " + endVertex.getAnnotation().get(Language.GERMAN.name()), true);
            }
            return shortestPath;
        }
        return null;
    }

    /**
     * Detects all convergent branches in the directed graph.
     *
     * @return The set of vertices in the convergent branch.
     */
    public Set<Vertex> detectConvergentBranches() {
        Set<Vertex> convergentBranches = new HashSet<>();
        for (Vertex vertex : (Set<Vertex>) algorithmGraph.vertexSet()) {
            if (algorithmGraph.inDegreeOf(vertex) > 1) {
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
        for (Vertex vertex : (Set<Vertex>) algorithmGraph.vertexSet()) {
            if (algorithmGraph.outDegreeOf(vertex) > 1) {
                divergentBranches.add(vertex);
            }
        }
        return divergentBranches;
    }

    /**
     * Returns all predecessors and their edges towards them in
     * the given iterations.
     */
    public Pair<List<Vertex>, List<Edge>> predecessorsIterations(int steps){
        if(isAtLeastOnePicked()) {
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for(Vertex startVertex : pickedVertices) {
                List<Vertex> tempVertex = new ArrayList<>();
                tempVertex.add(startVertex);
                vertices.add(startVertex);
                for (int i = steps; i > 0; i--) {
                    for (Vertex pivotVertex : tempVertex) {
                        List<Vertex> predecessors = Graphs.predecessorListOf(algorithmGraph, pivotVertex);
                        for (Vertex neighborVertex : predecessors) {
                            vertices.add(neighborVertex);
                            edges.add((Edge) algorithmGraph.getEdge(neighborVertex, pivotVertex));
                        }
                        tempVertex = predecessors;
                    }
                }
            }
            return new Pair<>(vertices, edges);
        }
        return null;
    }

    /**
     * Returns all predecessors and their edges towards them in
     * the given iterations.
     */
    public Pair<List<Vertex>, List<Edge>> successorIterations(int steps){
        if(isAtLeastOnePicked()) {
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for(Vertex startVertex : pickedVertices) {
                List<Vertex> tempVertex = new ArrayList<>();
                tempVertex.add(startVertex);
                vertices.add(startVertex);
                for (int i = steps; i > 0; i--) {
                    for (Vertex pivotVertex : tempVertex) {
                        List<Vertex> successors = Graphs.successorListOf(algorithmGraph, pivotVertex);
                        for (Vertex neighborVertex : successors) {
                            vertices.add(neighborVertex);
                            edges.add((Edge) algorithmGraph.getEdge(pivotVertex, neighborVertex));
                        }
                        tempVertex = successors;
                    }
                }
            }
            return new Pair<>(vertices, edges);
        }
        return null;
    }

    /**
     * Detects relation chains.
     */
    public Pair<List<List<Vertex>>,Set<Edge>> detectRelationChains() {
        List<List<Vertex>> relationChains = new LinkedList<>();
        List<Vertex> InnerVertices = new ArrayList<>();
        for(Vertex vert : (Set<Vertex>) algorithmGraph.vertexSet()){
            if(algorithmGraph.inDegreeOf(vert) == 1 && algorithmGraph.outDegreeOf(vert) == 1){
                InnerVertices.add(vert);
            }
        }
        while(!InnerVertices.isEmpty()) {
            LinkedList<Vertex> potentialChain = new LinkedList<>();
            Vertex pivotVertex = InnerVertices.get(0);
            Vertex predecessor = (Vertex) Graphs.predecessorListOf(algorithmGraph,pivotVertex).get(0);
            Vertex successor = (Vertex) Graphs.successorListOf(algorithmGraph,pivotVertex).get(0);
            potentialChain.add(predecessor);
            potentialChain.add(pivotVertex);
            potentialChain.add(successor);
            while(Graphs.predecessorListOf(algorithmGraph, predecessor).size() == 1 ){
                if(!potentialChain.contains(Graphs.predecessorListOf(algorithmGraph, predecessor).get(0))) {
                    potentialChain.addFirst((Vertex) Graphs.predecessorListOf(algorithmGraph, predecessor).get(0));
                    predecessor = (Vertex) Graphs.predecessorListOf(algorithmGraph, predecessor).get(0);
                }else{
                    break;
                }
            }
            while(Graphs.successorListOf(algorithmGraph, successor).size() == 1 ){
                if(!potentialChain.contains(Graphs.successorListOf(algorithmGraph, successor).get(0))) {
                    potentialChain.addLast((Vertex) Graphs.successorListOf(algorithmGraph, successor).get(0));
                    successor = (Vertex) Graphs.successorListOf(algorithmGraph, successor).get(0);
                }else{
                    break;
                }
            }
            InnerVertices.removeAll(potentialChain);
            if(potentialChain.size() > 3) {
                relationChains.add(potentialChain);
            }
        }
        Set<Edge> edgesRelationChain = new HashSet<>();
        for(List<Vertex> list : relationChains){
            for(int i = 0 ; i < list.size() - 1; i++){
                edgesRelationChain.add((Edge)algorithmGraph.getEdge(list.get(i),list.get(i + 1)));
            }
            if(algorithmGraph.getEdge(list.get(list.size()-1), list.get(0)) != null){
                edgesRelationChain.add((Edge)algorithmGraph.getEdge(list.get(list.size()-1), list.get(0)));
            }
        }
        return new Pair<>(relationChains,edgesRelationChain);
    }

}