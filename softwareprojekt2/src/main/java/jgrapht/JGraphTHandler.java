package jgrapht;

import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.util.Pair;
import lombok.Getter;
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
    @Getter
    private Vertex startVertex;
    /**
     * The end-vertex of the path.
     */
    @Getter
    private Vertex endVertex;
    /**
     * The graph in JGraphT-form.
     */
    private DefaultDirectedGraph algorithmGraph;
    /**
     * The list of selected vertices.
     */
    private Set<Vertex> pickedVertices;

    private LoadLanguage loadLanguage = LoadLanguage.getInstance();

    /**
     * Constructor in case the user changes to analyse-mode and analyses without using a vertex.
     */
    public JGraphTHandler() {
        Set<Pair<Vertex, Vertex>> edges = new HashSet<>();
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Edge edge : graph.getEdges()) {
            edu.uci.ics.jung.graph.util.Pair<Vertex> jungPair = graph.getEndpoints(edge);
            Pair<Vertex, Vertex> vertices = new Pair<>(jungPair.getFirst(), jungPair.getSecond());
            edges.add(vertices);
        }
        convertGraphToJGraphT(new ArrayList<>(graph.getVertices()), edges);
    }

    /**
     * Converts the graph from our datatype to the JGraphT datatype.
     *
     * @param pVertices Given list of vertices.
     * @param pEdges    Given list of edges.
     */
    private void convertGraphToJGraphT(List<Vertex> pVertices, Set<Pair<Vertex, Vertex>> pEdges) {
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
            helperFunctions.setActionText("J_GRAPH_T_ALERT", true, true);
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
    private boolean isAtLeastOnePicked() {
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        PickedState<Vertex> pickedState = vv.getPickedVertexState();
        if (pickedState.getPicked().isEmpty()) {
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("J_GRAPH_T_MARK_ALERT", true, true);
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
    @SuppressWarnings("unchecked")
    public List<List<Vertex>> detectCycles() {

        TarjanSimpleCycles tarjanSimpleCycles = new TarjanSimpleCycles(algorithmGraph);
        return tarjanSimpleCycles.findSimpleCycles();

    }

    /**
     * List all possible paths between two vertices.
     *
     * @return The list of paths between the vertices.
     */
    @SuppressWarnings("unchecked")
    public List<GraphPath<Vertex, Edge>> getAllPaths() {
        if (calculateEndpoints()) {
            AllDirectedPaths pathFinder = new AllDirectedPaths<>(algorithmGraph);
            return pathFinder.getAllPaths(startVertex, endVertex, true, Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertices().size());

        }
        return new ArrayList<>();
    }

    /**
     * Finds the shortest path between two vertices in the graph, if one exists.
     *
     * @return The shortest path between the vertices.
     */
    @SuppressWarnings("unchecked")
    public GraphPath<Vertex, Edge> getShortestPath() {
        if (calculateEndpoints()) {
            DijkstraShortestPath shortestPathFinder = new DijkstraShortestPath(algorithmGraph);
            GraphPath<Vertex, Edge> shortestPath = shortestPathFinder.getPath(startVertex, endVertex);
            if (shortestPath == null) {
                HelperFunctions helperFunctions = new HelperFunctions();
                Object[] obj = {startVertex.getAnnotation().get(Language.GERMAN.name()), endVertex.getAnnotation().get(Language.GERMAN.name())};
                helperFunctions.setActionText(loadLanguage.loadLanguagesKey("J_GRAPH_T_COUNT", obj), true, false);
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
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
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
    public Pair<List<Vertex>, List<Edge>> predecessorsIterations(int steps) {
        if (isAtLeastOnePicked()) {
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for (Vertex startV : pickedVertices) {
                List<Vertex> tempVertex = new ArrayList<>();
                tempVertex.add(startV);
                vertices.add(startV);
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
    @SuppressWarnings("unchecked")
    public Pair<List<Vertex>, List<Edge>> successorIterations(int steps) {
        if (isAtLeastOnePicked()) {
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for (Vertex sVertex : pickedVertices) {
                List<Vertex> tempVertex = new ArrayList<>();
                tempVertex.add(sVertex);
                vertices.add(sVertex);
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
     * Detects relation chains. This algorithm is created by Clement Phung and Jonah Jaeger. No unnamed help
     * has been used.
     */
    @SuppressWarnings("unchecked")
    public Pair<List<List<Vertex>>, Set<Edge>> detectRelationChains() {
        List<List<Vertex>> relationChains = new LinkedList<>();
        List<Vertex> innerVertices = new ArrayList<>();
        for (Vertex vert : (Set<Vertex>) algorithmGraph.vertexSet()) {
            if (algorithmGraph.inDegreeOf(vert) == 1 && algorithmGraph.outDegreeOf(vert) == 1) {
                innerVertices.add(vert);
            }
        }
        growPotentailChains(innerVertices, relationChains);
        Set<Edge> edgesRelationChain = new HashSet<>();
        for (List<Vertex> list : relationChains) {
            for (int i = 0; i < list.size() - 1; i++) {
                edgesRelationChain.add((Edge) algorithmGraph.getEdge(list.get(i), list.get(i + 1)));
            }
            if (algorithmGraph.getEdge(list.get(list.size() - 1), list.get(0)) != null && algorithmGraph.inDegreeOf(list.get(0)) == 1 && algorithmGraph.outDegreeOf(list.get(0)) == 1) {
                edgesRelationChain.add((Edge) algorithmGraph.getEdge(list.get(list.size() - 1), list.get(0)));
            }
        }
        return new Pair<>(relationChains, edgesRelationChain);
    }

    /**
     * Inner algorithm for the relation chain algorithm. Potential relation chains will be build
     * and eventually added to the list of relation chains.
     */
    public void growPotentailChains(List<Vertex> innerVertices, List<List<Vertex>> relationChains) {
        while (!innerVertices.isEmpty()) {
            LinkedList<Vertex> potentialChain = new LinkedList<>();
            Vertex pivotVertex = innerVertices.get(0);
            Vertex predecessor = pivotVertex;
            Vertex successor = pivotVertex;
            potentialChain.add(pivotVertex);
            while (Graphs.predecessorListOf(algorithmGraph, predecessor).size() == 1 && Graphs.successorListOf(algorithmGraph, predecessor).size() == 1) {
                if (!potentialChain.contains((Vertex) Graphs.predecessorListOf(algorithmGraph, predecessor).get(0))) {
                    potentialChain.addFirst((Vertex) Graphs.predecessorListOf(algorithmGraph, predecessor).get(0));
                    predecessor = (Vertex) Graphs.predecessorListOf(algorithmGraph, predecessor).get(0);
                } else {
                    break;
                }
            }
            while (Graphs.successorListOf(algorithmGraph, successor).size() == 1 && Graphs.predecessorListOf(algorithmGraph, successor).size() == 1) {
                if (!potentialChain.contains((Vertex) Graphs.successorListOf(algorithmGraph, successor).get(0))) {
                    potentialChain.addLast((Vertex) Graphs.successorListOf(algorithmGraph, successor).get(0));
                    successor = (Vertex) Graphs.successorListOf(algorithmGraph, successor).get(0);
                } else {
                    break;
                }
            }
            innerVertices.removeAll(potentialChain);
            if (potentialChain.size() > 3) {
                relationChains.add(potentialChain);
            }
        }
    }


}