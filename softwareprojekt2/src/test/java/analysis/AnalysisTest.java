package analysis;

import actions.analyse.*;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.algorithmen.AnalyseType;
import graph.graph.*;
import gui.Values;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.*;

public class AnalysisTest {
    /**
     * The values to use when creating new elements of the graph.
     */
    private Values values = Values.getInstance();

    /**
     * The factory used to create elements of the graph. These are object of the type Sphere, Vertex and Edge.
     */
    private GraphObjectsFactory factory = new GraphObjectsFactory();

    /**
     * The list of vertices of the shortest path in the graph.
     */
    private ArrayList<Vertex> shortestPathVertices = new ArrayList<>();

    /**
     * The list of edges of the shortest path in the graph.
     */
    private ArrayList<Edge> shortestPathEdges = new ArrayList<>();

    /**
     * The list of vertices of the shortest path in the graph.
     */
    private Set<Vertex> allPathsVertices = new HashSet<>();

    /**
     * The list of edges of the shortest path in the graph.
     */
    private Set<Edge> allPathsEdges = new HashSet<>();

    /**
     * The start-vertex of the shortest path.
     */
    private Vertex vPathStart;

    /**
     * The sink-vertex of the shortest path.
     */
    private Vertex vPathSink;

    /**
     * The pivot-vertex for the neighbor-algorithms.
     */
    private Vertex neighborVertex;

    /**
     * The pivot-vertex2 for the neighbor-algorithms.
     */
    private Vertex neighborVertex2;

    /**
     * The predecessor-Set for the neighbor-algorithms.
     */
    private Set<Vertex> predecessorVerticesSet = new HashSet<>();

    /**
     * The predecessor-Set for the neighbor-algorithms.
     */
    private Set<Edge> predecessorEdgesSet = new HashSet<>();

    /**
     * The successor-Set for the neighbor-algorithms.
     */
    private Set<Vertex> successorVerticesSet = new HashSet<>();

    /**
     * The successor-Set for the neighbor-algorithms.
     */
    private Set<Edge> successorEdgesSet = new HashSet<>();

    /**
     * The predecessor/successor vertex set for multiple vertices picked.
     */
    private Set<Vertex> predecessorSuccessVertexSet = new HashSet<>();

    /**
     * The predecessor/successor edges set for multiple vertices picked.
     */
    private Set<Edge> predecessorSuccessEdgesSet = new HashSet<>();

    /**
     * The vertices-set for the cycle.
     */
    private Set<Vertex> cycleVerticesSet = new HashSet<>();

    /**
     * The edges-set for the cycle.
     */
    private Set<Edge> cycleEdgesSet = new HashSet<>();

    /**
     * The vertices-set for the convergent-branches algorithm.
     **/
    private Set<Vertex> convergentBranchesVertices = new HashSet<>();

    /**
     * The edges-set for the convergent-branches algorithm.
     */
    private Set<Edge> convergentBranchesEdges = new HashSet<>();

    /**
     * The vertices-set for the divergent-branches algorithm.
     **/
    private Set<Vertex> divergentBranchesVertices = new HashSet<>();

    /**
     * The edges-set for the divergent-branches algorithm.
     */
    private Set<Edge> divergentBranchesEdges = new HashSet<>();

    /**
     * The vertices-set for the relation-chain algorithm.
     */
    private Set<Vertex> relationChainVertices = new HashSet<>();

    /**
     * The edges-set for the relation-chain algorithm.
     */
    private Set<Edge> relationChainEdges = new HashSet<>();

    /**
     * The syndrom instance of the application containing the graph.
     */
    private Syndrom syndrom = Syndrom.getInstance();

    /**
     * The graph which is set.
     */
    private SyndromGraph<Vertex, Edge> graph = new SyndromGraph<>();


    /**
     * Sets the test graph, This graph has one of each construct, which can be filtered.
     *
     * @param withElements Indicator whether the graph has elements.
     */

    private void setupSyndrom(boolean withElements) {
        syndrom.generateNew();
        syndrom.setTemplate(new Template(25, 50, 75, true, false, true));
        if (withElements) {
            generateGraphElements();
        }
        syndrom.getVv().getGraphLayout().setGraph(graph);
    }

    /**
     * Generates the graph with the elements. The graph contains: 4 spheres, 10 vertices and 11 edges.
     * All attributes for the test are gonna be set in this method.
     */
    private void generateGraphElements() {
        values.setFontSizeSphere(10);
        values.setFillPaintSphere(new java.awt.Color(86, 151, 31, 183));

        Sphere s1 = factory.createSphere(new Point2D.Double(196, 116));
        Sphere s2 = factory.createSphere(new Point2D.Double(660, 116));
        Sphere s3 = factory.createSphere(new Point2D.Double(1101, 116));
        Sphere s4 = factory.createSphere(new Point2D.Double(660, 470));

        Vertex v1 = factory.createVertex(new Point2D.Double(260, 241));
        Vertex v2 = factory.createVertex(new Point2D.Double(334, 159));
        Vertex v3 = factory.createVertex(new Point2D.Double(721, 157));
        Vertex v4 = factory.createVertex(new Point2D.Double(810, 243));
        Vertex v5 = factory.createVertex(new Point2D.Double(1141, 248));
        Vertex v6 = factory.createVertex(new Point2D.Double(1267, 247));
        Vertex v7 = factory.createVertex(new Point2D.Double(1187, 154));
        Vertex v8 = factory.createVertex(new Point2D.Double(774, 522));
        Vertex v9 = factory.createVertex(new Point2D.Double(692, 630));
        Vertex v10 = factory.createVertex(new Point2D.Double(827, 625));

        Edge e1 = factory.createEdge();
        Edge e2 = factory.createEdge();
        Edge e3 = factory.createEdge();
        Edge e4 = factory.createEdge();
        Edge e5 = factory.createEdge();
        Edge e6 = factory.createEdge();
        Edge e7 = factory.createEdge();
        Edge e8 = factory.createEdge();
        //e9 was removed.
        Edge e10 = factory.createEdge();
        Edge e11 = factory.createEdge();
        Edge e12 = factory.createEdge();

        graph.getSpheres().add(s1);
        graph.getSpheres().add(s2);
        graph.getSpheres().add(s3);
        graph.getSpheres().add(s4);

        s1.getVertices().add(v1);
        s1.getVertices().add(v2);
        s2.getVertices().add(v3);
        s2.getVertices().add(v4);
        s3.getVertices().add(v5);
        s3.getVertices().add(v6);
        s3.getVertices().add(v7);
        s4.getVertices().add(v8);
        s4.getVertices().add(v9);
        s4.getVertices().add(v10);

        //relationChain
        graph.addEdgeExisting(e1, v1, v2);
        graph.addEdgeExisting(e2, v2, v3);
        graph.addEdgeExisting(e3, v3, v4);
        graph.addEdgeExisting(e4, v4, v5);
        //cycle
        graph.addEdgeExisting(e5, v5, v6);
        graph.addEdgeExisting(e6, v6, v7);
        graph.addEdgeExisting(e7, v7, v5);

        graph.addEdgeExisting(e8, v1, v8);
        graph.addEdgeExisting(e10, v8, v9);
        graph.addEdgeExisting(e11, v8, v10);
        graph.addEdgeExisting(e12, v9, v10);

        //initializing shortest path
        shortestPathVertices.add(v1);
        shortestPathVertices.add(v8);
        shortestPathVertices.add(v10);

        shortestPathEdges.add(e8);
        shortestPathEdges.add(e11);

        vPathStart = v1;
        vPathSink = v10;

        //initializing all paths
        allPathsVertices.add(v1);
        allPathsVertices.add(v8);
        allPathsVertices.add(v9);
        allPathsVertices.add(v10);

        allPathsEdges.add(e8);
        allPathsEdges.add(e10);
        allPathsEdges.add(e11);
        allPathsEdges.add(e12);

        //initializing for the neighbors-algorithm

        neighborVertex = v3;
        neighborVertex2 = v4;

        //(predecessor)

        predecessorVerticesSet.add(v3);
        predecessorVerticesSet.add(v2);
        predecessorVerticesSet.add(v1);

        predecessorEdgesSet.add(e1);
        predecessorEdgesSet.add(e2);

        predecessorSuccessEdgesSet.add(e2);
        predecessorSuccessEdgesSet.add(e3);
        predecessorSuccessEdgesSet.add(e4);

        predecessorSuccessVertexSet.add(v2);
        predecessorSuccessVertexSet.add(v3);
        predecessorSuccessVertexSet.add(v4);
        predecessorSuccessVertexSet.add(v5);


        //(successor)

        successorVerticesSet.add(v3);
        successorVerticesSet.add(v4);
        successorVerticesSet.add(v5);

        successorEdgesSet.add(e3);
        successorEdgesSet.add(e4);

        //initializing cycles
        cycleVerticesSet.add(v5);
        cycleVerticesSet.add(v6);
        cycleVerticesSet.add(v7);

        cycleEdgesSet.add(e5);
        cycleEdgesSet.add(e6);
        cycleEdgesSet.add(e7);

        //initializing convergent branches
        convergentBranchesVertices.add(v5);
        convergentBranchesVertices.add(v10);

        convergentBranchesEdges.add(e4);
        convergentBranchesEdges.add(e7);
        convergentBranchesEdges.add(e11);
        convergentBranchesEdges.add(e12);

        //initializing divergent branches
        divergentBranchesVertices.add(v1);
        divergentBranchesVertices.add(v8);

        divergentBranchesEdges.add(e1);
        divergentBranchesEdges.add(e8);
        divergentBranchesEdges.add(e10);
        divergentBranchesEdges.add(e11);

        //initializing relationChains
        relationChainVertices.add(v1);
        relationChainVertices.add(v2);
        relationChainVertices.add(v3);
        relationChainVertices.add(v4);
        relationChainVertices.add(v5);

        relationChainEdges.add(e1);
        relationChainEdges.add(e2);
        relationChainEdges.add(e3);
        relationChainEdges.add(e4);


    }

    /**
     * Tests the setup, if it differs from the implemented graph.
     */
    @Test
    public void testSetup() {
        setupSyndrom(true);
        Assert.assertEquals(4, graph.getSpheres().size());
        Assert.assertEquals(10, graph.getVertices().size());
        Assert.assertEquals(11, graph.getEdges().size());
    }

    /**
     * Tests the calculation of the scop index. According to the site
     * http://www.informatik.uni-bremen.de/st/Lehre/swp/anforderungen/
     * the scope index is the sum of vertices and the relations.
     */
    @Test
    public void testScopeIndex() {
        setupSyndrom(true);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("21", graphDimensionAction.getScope());
    }

    /**
     * Tests the calculation of the scope index, if the graph doesn't contain any
     * elements.
     */
    @Test
    public void testScopeIndexNoElements() {
        setupSyndrom(false);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("0", graphDimensionAction.getScope());
    }

    /**
     * Tests the calculation of the network index. According to the site
     * http://www.informatik.uni-bremen.de/st/Lehre/swp/anforderungen/
     * the network index is the number of relations multiplied by 2 divided by
     * the number of vertices.
     */
    @Test
    public void testNetworkIndex() {
        setupSyndrom(true);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("2,2", graphDimensionAction.getNetworkIndex());
    }

    /**
     * Tests the calculation of the network index, if the graph doesn't contain any
     * elements. Therefore the amount of vertices is zero and the division by zero is
     * undefined.
     */
    @Test
    public void testNetworkIndexNoVertices() {
        setupSyndrom(false);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("NaN", graphDimensionAction.getNetworkIndex());
    }

    /**
     * Tests the calculation of the structure index. According to the site
     * http://www.informatik.uni-bremen.de/st/Lehre/swp/anforderungen/
     * the structure index is the sum of relation-chains, branches and cycles
     * divided by symptoms.
     */
    @Test
    public void testStructureIndex() {
        setupSyndrom(true);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("0,6", graphDimensionAction.getStructureIndex());
    }

    /**
     * Tests the calculation of the structure index, if the graph doesn't contain any
     * elements. Therefore the amount of vertices is zero and the division by zero is
     * undefined.
     */
    @Test
    public void testStructureIndexNoVertices() {
        setupSyndrom(false);
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("NaN", graphDimensionAction.getStructureIndex());
    }

    /**
     * Tests the shortest path algorithm. It returns graph path between both vertices.
     */
    @Test
    public void testShortestPath() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertices.
        pickedState.pick(vPathStart, true);
        pickedState.pick(vPathSink, true);
        AnalysisGraphShortestPathAction analysisGraphShortestPathAction = new AnalysisGraphShortestPathAction();
        analysisGraphShortestPathAction.action();
        List<Vertex> verticesList = analysisGraphShortestPathAction.getVerticesAnalyse();
        verticesList.sort(Comparator.comparingInt(Vertex::getId));
        List<Edge> edgesList = analysisGraphShortestPathAction.getEdgesAnalyse();
        edgesList.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(shortestPathVertices, verticesList);
        Assert.assertEquals(shortestPathEdges, edgesList);
    }

    /**
     * Tests the shortest path algorithm, if no path between two vertices exists.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testShortestPathNoPath() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertices.
        pickedState.pick(vPathSink, true);
        pickedState.pick(vPathStart, true);
        AnalysisGraphShortestPathAction analysisGraphShortestPathAction = new AnalysisGraphShortestPathAction();
        analysisGraphShortestPathAction.action();
        List<Vertex> verticesList = analysisGraphShortestPathAction.getVerticesAnalyse();
        List<Edge> edgesList = analysisGraphShortestPathAction.getEdgesAnalyse();
        Assert.assertEquals(new ArrayList<>(), verticesList);
        Assert.assertEquals(new ArrayList<>(), edgesList);
    }

    /**
     * Tests the shortest path algorithm, if the set of picked vertices is empty.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testShortestPathNoVertexSelected() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //No vertex picked.
        AnalysisGraphShortestPathAction analysisGraphShortestPathAction = new AnalysisGraphShortestPathAction();
        analysisGraphShortestPathAction.action();
    }

    /**
     * Tests the shortest path algorithm, if the set of picked vertices has three elements.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testShortestPathThreeVertexSelected() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Three vertices picked.
        pickedState.pick(vPathStart, true);
        pickedState.pick(vPathSink, true);
        pickedState.pick(neighborVertex, true);
        AnalysisGraphShortestPathAction analysisGraphShortestPathAction = new AnalysisGraphShortestPathAction();
        analysisGraphShortestPathAction.action();
    }

    /**
     * Tests the all paths algorithm. It returns the set of all vertices and edges used
     * in the graph path.
     */
    @Test
    public void testAllPaths() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertices.
        pickedState.pick(vPathStart, true);
        pickedState.pick(vPathSink, true);
        AnalysisGraphAllPathsAction analysisGraphAllPathsAction = new AnalysisGraphAllPathsAction();
        analysisGraphAllPathsAction.action();
        List<Vertex> verticesList = analysisGraphAllPathsAction.getVerticesAnalyse();
        Set<Vertex> verticesSet = new HashSet<>();
        verticesSet.addAll(verticesList);
        List<Edge> edgesList = analysisGraphAllPathsAction.getEdgesAnalyse();
        Set<Edge> edgesSet = new HashSet<>();
        edgesSet.addAll(edgesList);
        edgesList.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(allPathsVertices, verticesSet);
        Assert.assertEquals(allPathsEdges, edgesSet);
    }

    /**
     * Tests the all paths algorithm, if no path between two vertices exists.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testAllPathsNoPath() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertices.
        pickedState.pick(vPathSink, true);
        pickedState.pick(vPathStart, true);
        AnalysisGraphAllPathsAction analysisGraphAllPathsAction = new AnalysisGraphAllPathsAction();
        analysisGraphAllPathsAction.action();
        List<Vertex> verticesList = analysisGraphAllPathsAction.getVerticesAnalyse();
        Set<Vertex> verticesSet = new HashSet<>();
        verticesSet.addAll(verticesList);
        List<Edge> edgesList = analysisGraphAllPathsAction.getEdgesAnalyse();
        Set<Edge> edgesSet = new HashSet<>();
        edgesSet.addAll(edgesList);
        edgesList.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(allPathsVertices, verticesSet);
        Assert.assertEquals(allPathsEdges, edgesSet);
    }

    /**
     * Tests the all paths algorithm, if the set of picked vertices is empty.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testAllPathsNoVertexSelected() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //No vertices picked.
        AnalysisGraphAllPathsAction analysisGraphAllPathsAction = new AnalysisGraphAllPathsAction();
        analysisGraphAllPathsAction.action();
    }

    /**
     * Tests the all paths algorithm, if the set of picked vertices has three elements.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testAllPathsThreeVertexSelected() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //No vertices picked.
        pickedState.pick(vPathStart, true);
        pickedState.pick(vPathSink, true);
        pickedState.pick(neighborVertex, true);
        AnalysisGraphAllPathsAction analysisGraphAllPathsAction = new AnalysisGraphAllPathsAction();
        analysisGraphAllPathsAction.action();
    }

    /**
     * Tests the predecessor algorithm. It returns all vertices and edges gotten within
     * zero iterations. Basically only the selected graph should be returned.
     */
    @Test
    public void testPredecessorZeroIterations() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertex
        pickedState.pick(neighborVertex, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, 0);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(neighborVertex);
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Assert.assertEquals(vertices, vertexSet);
        Assert.assertEquals(new ArrayList<Edge>(), analysisGraphNeighborsAction.getEdgesAnalyse());

    }

    /**
     * Tests the successor algorithm. It returns all vertices and edges gotten within
     * zero iterations. Basically only the selected graph should be returned.
     */
    @Test
    public void testSuccessorZeroIterations() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertex
        pickedState.pick(neighborVertex, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, 0);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertices = new HashSet<>();
        vertices.add(neighborVertex);
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Assert.assertEquals(vertices, vertexSet);
        Assert.assertEquals(new ArrayList<Edge>(), analysisGraphNeighborsAction.getEdgesAnalyse());

    }

    /**
     * Tests the predecessor algorithm. It returns all vertices and edges gotten within
     * two iterations.
     */
    @Test
    public void testPredecessorTwoIterations() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertex
        pickedState.pick(neighborVertex, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR, 2);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Set<Edge> edgesSet = new HashSet<>(analysisGraphNeighborsAction.getEdgesAnalyse());
        Assert.assertEquals(predecessorVerticesSet, vertexSet);
        Assert.assertEquals(predecessorEdgesSet, edgesSet);

    }

    /**
     * Tests the successor algorithm. It returns all vertices and edges gotten within
     * two iterations.
     */
    @Test
    public void testSuccessorTwoIterations() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertex
        pickedState.pick(neighborVertex, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_SUCCESSOR, 2);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Set<Edge> edgesSet = new HashSet<>(analysisGraphNeighborsAction.getEdgesAnalyse());
        Assert.assertEquals(successorVerticesSet, vertexSet);
        Assert.assertEquals(successorEdgesSet, edgesSet);

    }

    /**
     * Tests the predecessor_successor algorithm. It returns all vertices and edges gotten within
     * two iterations.
     */
    @Test
    public void testPredecessorSuccessorTwoIterations() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertex
        pickedState.pick(neighborVertex, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, 2);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Set<Edge> edgesSet = new HashSet<>(analysisGraphNeighborsAction.getEdgesAnalyse());
        Set<Vertex> bothVertices = successorVerticesSet;
        bothVertices.addAll(predecessorVerticesSet);
        Set<Edge> bothEdges = successorEdgesSet;
        bothEdges.addAll(predecessorEdgesSet);
        Assert.assertEquals(bothVertices, vertexSet);
        Assert.assertEquals(bothEdges, edgesSet);

    }

    /**
     * Tests the predecessor_successor algorithm, if no vertex is picked.
     * It should throw an IllegalStateException, because the context of the alert-text
     * is not set.
     */
    @Test(expected = IllegalStateException.class)
    public void testPredecessorSuccessorZeroIterationsNoVertexPicked() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //No vertex picked
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, 2);
        analysisGraphNeighborsAction.action();
    }

    /**
     * Tests the predecessor_successor algorithm, if two vertices are picked.
     */
    @Test
    public void testPredecessorSuccessorZeroIterationsTwoVertexPicked() {
        setupSyndrom(true);
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //two vertices picked.
        pickedState.pick(neighborVertex, true);
        pickedState.pick(neighborVertex2, true);
        AnalysisGraphNeighborsAction analysisGraphNeighborsAction = new AnalysisGraphNeighborsAction(AnalyseType.NEIGHBOUR_PREDECESSOR_SUCCESSOR, 1);
        analysisGraphNeighborsAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphNeighborsAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphNeighborsAction.getEdgesAnalyse());
        Assert.assertEquals(predecessorSuccessVertexSet, vertexSet);
        Assert.assertEquals(predecessorSuccessEdgesSet, edgeSet);
    }

    /**
     * Tests the cycle detection algorithm. It returns all vertices and edges inside that cycle.
     */
    @Test
    public void testCycle() {
        setupSyndrom(true);
        AnalysisGraphCyclesAction analysisGraphCyclesAction = new AnalysisGraphCyclesAction();
        analysisGraphCyclesAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphCyclesAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphCyclesAction.getEdgesAnalyse());
        Assert.assertEquals(cycleVerticesSet, vertexSet);
        Assert.assertEquals(cycleEdgesSet, edgeSet);
    }

    /**
     * Test the cycle detection algorithm, if no cycle is inside the graph.
     * Therefore it should return an empty set for both, the vertices and the edges.
     */
    @Test
    public void testCycleNoCycles() {
        setupSyndrom(false);
        AnalysisGraphCyclesAction analysisGraphCyclesAction = new AnalysisGraphCyclesAction();
        analysisGraphCyclesAction.action();
        Assert.assertEquals(new ArrayList<>(), analysisGraphCyclesAction.getVerticesAnalyse());
        Assert.assertEquals(new ArrayList<>(), analysisGraphCyclesAction.getEdgesAnalyse());
    }

    /**
     * Tests the detection of all convergent branches. The set of vertices should be
     * returned. An convergent branch has an incoming degree of at least 2.
     */
    @Test
    public void testConvergentBranch() {
        setupSyndrom(true);
        AnalysisGraphConvergentBranchesAction analysisGraphConvergentBranchesAction = new AnalysisGraphConvergentBranchesAction();
        analysisGraphConvergentBranchesAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphConvergentBranchesAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphConvergentBranchesAction.getEdgesAnalyse());
        Assert.assertEquals(convergentBranchesVertices, vertexSet);
        Assert.assertEquals(convergentBranchesEdges, edgeSet);
    }

    /**
     * Tests the detection of all divergent branches. The set of vertices should be
     * returned. An convergent branch has an outgoing degree of at least 2.
     */
    @Test
    public void testDivergentBranch() {
        setupSyndrom(true);
        AnalysisGraphDivergentBranchesAction analysisGraphDivergentBranchesAction = new AnalysisGraphDivergentBranchesAction();
        analysisGraphDivergentBranchesAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphDivergentBranchesAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphDivergentBranchesAction.getEdgesAnalyse());
        Assert.assertEquals(divergentBranchesVertices, vertexSet);
        Assert.assertEquals(divergentBranchesEdges, edgeSet);
    }

    /**
     * Tests the detection of all branches. The set of vertices should be
     * returned. An branch has an incoming or outgoing degree of at least 2.
     */
    @Test
    public void testDivergentConvergentBranch() {
        setupSyndrom(true);
        AnalysisGraphBranchesAction analysisGraphBranchesAction = new AnalysisGraphBranchesAction();
        analysisGraphBranchesAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphBranchesAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphBranchesAction.getEdgesAnalyse());
        convergentBranchesVertices.addAll(divergentBranchesVertices);
        convergentBranchesEdges.addAll(divergentBranchesEdges);
        Assert.assertEquals(convergentBranchesVertices, vertexSet);
        Assert.assertEquals(convergentBranchesEdges, edgeSet);
    }

    /**
     * Tests the detection of relation chains. The set of vertices and relations
     * should be returned.
     */
    @Test
    public void testRelationChain() {
        setupSyndrom(true);
        AnalysisGraphEdgeChainsAction analysisGraphEdgeChainsAction = new AnalysisGraphEdgeChainsAction();
        analysisGraphEdgeChainsAction.action();
        Set<Vertex> vertexSet = new HashSet<>(analysisGraphEdgeChainsAction.getVerticesAnalyse());
        Set<Edge> edgeSet = new HashSet<>(analysisGraphEdgeChainsAction.getEdgesAnalyse());
        Assert.assertEquals(relationChainVertices, vertexSet);
        Assert.assertEquals(relationChainEdges, edgeSet);
    }
}
