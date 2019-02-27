package test.io;

import graph.graph.*;
import gui.Values;
import net.sourceforge.gxl.GXLDocument;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class AnalysisTest {
    /**
     * The values to use when creating new elements of the graph.
     */
    private Values values = Values.getInstance();

    /**
     * The logger sed to document the behaviour of this testclass.
     */
    private static Logger logger = Logger.getLogger(GXLioTest.class);

    /**
     * The factory used to create elements of the graph. These are object of the type Sphere, Vertex and Edge.
     */
    private GraphObjectsFactory factory = new GraphObjectsFactory();

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

    private void generateGraphElements(){
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

        graph.addVertexExisting(v1);
        graph.addVertexExisting(v2);
        graph.addVertexExisting(v3);
        graph.addVertexExisting(v4);
        graph.addVertexExisting(v5);
        graph.addVertexExisting(v6);
        graph.addVertexExisting(v7);
        graph.addVertexExisting(v8);
        graph.addVertexExisting(v9);
        graph.addVertexExisting(v10);

        //relationChain
        graph.addEdgeExisting(e1,v1,v2);
        graph.addEdgeExisting(e2,v2,v3);
        graph.addEdgeExisting(e3,v3,v4);
        graph.addEdgeExisting(e4,v4,v5);
        //cycle
        graph.addEdgeExisting(e5,v5,v6);
        graph.addEdgeExisting(e6,v6,v7);
        graph.addEdgeExisting(e7,v7,v5);

        graph.addEdgeExisting(e8,v1,v8);
        graph.addEdgeExisting(e9,v1,v9);
        graph.addEdgeExisting(e10,v8,v9);
        graph.addEdgeExisting(e11,v8,v10);
        graph.addEdgeExisting(e12,v9,v10);


    }
    @Test
    public void testSetup(){
        
    }
}
