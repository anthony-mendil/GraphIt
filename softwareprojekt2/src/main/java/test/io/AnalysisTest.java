package test.io;

import actions.analyse.AnalysisGraphAllPathsAction;
import actions.analyse.AnalysisGraphShortestPathAction;
import actions.analyse.GraphDimensionAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import gui.Values;
import net.sourceforge.gxl.GXLDocument;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
     * The start-vertex of the shortest path.
     */
    private Vertex vPathStart;

    /**
     * The sink-vertex of the shortest path.
     */
    private Vertex vPathSink;

    /**
     * The GXLDocument that is created from the file with the name below.
     */
    private GXLDocument doc = null;

    /**
     * The name of the gxl file.
     */
    private String nameTestGraph = "testGraph.gxl";

    /**
     * The syndrom instance of the application containing the graph that gets exported.
     */
    private Syndrom syndrom = Syndrom.getInstance();

    /**
     * The graph that gets exported.
     */
    private SyndromGraph<Vertex, Edge> graph = new SyndromGraph<>();

    /**
     * The list of spheres that belong to the graph those data gets exported.
     */
    private List<Sphere> spheresList = new ArrayList<>();

    /**
     * The list of vertices that belong to the graph those data gets exported.
     */
    private List<Vertex> verticesList = new ArrayList<>();

    /**
     * The list of edges that belong to the graph those data gets exported.
     */
    private List<Edge> edgesList = new ArrayList<>();

    /**
     * The name of the GXLGraph element that descripes the data of the exported syndromgraph.
     */
    private String syndromName = "syndrom";

    private void setupSyndrom() {
        syndrom.generateNew();
        syndrom.setTemplate(new Template(25, 50, 75, true, false, true));
        generateGraphElements();
        syndrom.getVv().getGraphLayout().setGraph(graph);
    }

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
        Edge e9 = factory.createEdge();
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
        graph.addEdgeExisting(e9, v1, v9);
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

        //allPathVertices.add(v1);


    }

    /**
     * Tests the setup, if it differs from the implemented graph.
     */
    @Test
    public void testSetup() {
        setupSyndrom();
        Assert.assertEquals(4, graph.getSpheres().size());
        Assert.assertEquals(10, graph.getVertices().size());
        Assert.assertEquals(12, graph.getEdges().size());
    }

    @Test
    public void testScopeIndex() {
        setupSyndrom();
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("22", graphDimensionAction.getScope());
    }

    @Test
    public void testNetworkIndex() {
        setupSyndrom();
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("2,4", graphDimensionAction.getNetworkIndex());
    }

    @Test
    public void testStructureIndex() {
        setupSyndrom();
        GraphDimensionAction graphDimensionAction = new GraphDimensionAction();
        graphDimensionAction.action();
        Assert.assertEquals("0,7", graphDimensionAction.getStructureIndex());
    }

    @Test
    public void testShortestPath() {
        setupSyndrom();
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
        Assert.assertEquals(shortestPathVertices,verticesList);
        Assert.assertEquals(shortestPathEdges, edgesList);
    }

    @Test
    public void testAllPaths(){
        setupSyndrom();
        PickedState<Vertex> pickedState = syndrom.getVv().getPickedVertexState();
        //Picking the vertices.
        pickedState.pick(vPathStart, true);
        pickedState.pick(vPathSink, true);
        AnalysisGraphAllPathsAction analysisGraphAllPathsAction = new AnalysisGraphAllPathsAction();
        analysisGraphAllPathsAction.action();
        List<Vertex> verticesList = analysisGraphAllPathsAction.getVerticesAnalyse();
    }


}
