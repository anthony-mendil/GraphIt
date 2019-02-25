package test.io;

import actions.other.SwitchModeAction;
import graph.graph.*;
import gui.Values;
import io.GXLio;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.freehep.graphicsbase.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class GXLioTest {

    /**
     * The values to use.
     */
    private Values values = Values.getInstance();
    private SwitchModeAction sm = new SwitchModeAction(FunctionMode.TEMPLATE);
    private static Logger logger = Logger.getLogger(GXLioTest.class);
    private GraphObjectsFactory factory = new GraphObjectsFactory();
    private GXLDocument doc = null;
    private List<Sphere> spheresList = new ArrayList<>();
    private List<Vertex> verticesList = new ArrayList<>();
    private List<Edge> edgesList = new ArrayList<>();
    private SyndromGraph<Vertex, Edge> graph = new SyndromGraph<>();
    private Syndrom syndrom = Syndrom.getInstance();
    private String nameTestGraph = "testGraph.gxl";
    private String syndromName = "syndrom";
    private GXLio gxlio = new GXLio();

    private GXLio prepareSyndrom(boolean pWithTemplate) throws IOException, SAXException {
        // generate new graph and set as actual graph
        syndrom.generateNew();
        syndrom.setTemplate(new Template(25, 50, 75, true, true, true));
        generateGraphElements();
        syndrom.getVv().getGraphLayout().setGraph(graph);
        // export graph

        gxlio.exportGXL(new File(nameTestGraph), pWithTemplate);
        // generate new clean syndrom
        syndrom.generateNew();
        doc = new GXLDocument(new File(nameTestGraph));
        return gxlio;
    }

    private void generateGraphElements() {
        values.setFontSizeSphere(10);
        values.setFillPaintSphere(new java.awt.Color(86, 151, 31, 183));
        Sphere s1 = factory.createSphere(new Point2D.Double(20, 20));
        s1.setLockedAnnotation(true);
        Sphere s2 = factory.createSphere(new Point2D.Double(270, 50));
        s2.setHeight(250.0);
        s2.setWidth(300.0);
        s2.setLockedAnnotation(true);
        s2.setLockedVertices(true);
        Sphere s3 = factory.createSphere(new Point2D.Double(620, 20));
        values.setFillPaintSphere(new java.awt.Color(24, 54, 11, 178));
        values.setFontSizeSphere(24);
        Sphere s4 = factory.createSphere(new Point2D.Double(200, 310));
        s4.setLockedMaxAmountVertices("17");
        s4.setLockedPosition(true);
        Sphere s5 = factory.createSphere(new Point2D.Double(445, 310));
        s5.setLockedMaxAmountVertices("5");
        s5.setLockedPosition(true);
        s5.setLockedStyle(true);

        values.setFontSizeVertex(12);
        values.setShapeVertex(VertexShapeType.CIRCLE);
        values.setFillPaintVertex(new java.awt.Color(88, 88, 219, 220));
        values.setFontVertex("Roboto");
        Vertex v1 = factory.createVertex(new Point2D.Double(80, 80));
        v1.setLockedStyle(true);
        Vertex v2 = factory.createVertex(new Point2D.Double(340, 140));
        Vertex v3 = factory.createVertex(new Point2D.Double(415, 190));
        v3.setFillColor(new java.awt.Color(0xC80070));
        v3.setSize(120);
        v3.setDrawColor(new java.awt.Color(200,10,10, 203));
        v3.setLockedStyle(true);
        v3.setLockedAnnotation(true);
        v3.setLockedPosition(true);
        Vertex v4 = factory.createVertex(new Point2D.Double(485, 140));
        v4.setLockedAnnotation(true);
        Vertex v5 = factory.createVertex(new Point2D.Double(740, 80));
        v5.setLockedAnnotation(true);
        values.setShapeVertex(VertexShapeType.RECTANGLE);
        values.setFillPaintVertex(new java.awt.Color(11, 19, 90, 220));
        values.setFontSizeVertex(7);
        Vertex v6 = factory.createVertex(new Point2D.Double(220, 360));
        v6.setLockedStyle(true);
        Vertex v7 = factory.createVertex(new Point2D.Double(280, 420));
        v7.setFont("Mali");
        v7.setFontSize(14);
        v7.setSize(80);
        v7.setLockedPosition(true);
        Vertex v8 = factory.createVertex(new Point2D.Double(555, 420));
        v8.setFont("Mali");
        v8.setFontSize(14);
        v8.setSize(80);
        v8.setLockedPosition(true);
        Vertex v9 = factory.createVertex(new Point2D.Double(620, 360));

        values.setEdgeArrowType(EdgeArrowType.EXTENUATING);
        values.setStrokeEdge(StrokeType.BASIC);
        Edge e1 = factory.createEdge();
        e1.setLockedEdgeType(true);
        Edge e2 = factory.createEdge();
        e2.setStroke(StrokeType.BASIC_WEIGHT);
        e2.setLockedEdgeType(true);
        values.setEdgeArrowType(EdgeArrowType.NEUTRAL);
        Edge e3 = factory.createEdge();
        e3.setStroke(StrokeType.DOTTED_WEIGHT);
        e3.setLockedStyle(true);
        Edge e4 = factory.createEdge();
        e4.setLockedPosition(true);
        e4.setStroke(StrokeType.DOTTED);
        values.setEdgeArrowType(EdgeArrowType.REINFORCED);
        Edge e5 = factory.createEdge();
        Edge e6 = factory.createEdge();
        e6.setStroke(StrokeType.DASHED);
        e6.setColor(new java.awt.Color(28, 56, 249, 130));
        e6.setLockedStyle(true);
        Edge e7 = factory.createEdge();
        e7.setStroke(StrokeType.DASHED);
        e7.setColor(new java.awt.Color(28, 56, 249, 130));
        e7.setLockedStyle(true);
        Edge e8 = factory.createEdge();
        e8.setStroke(StrokeType.DASHED_WEIGHT);
        e8.setLockedEdgeType(true);
        e8.setLockedPosition(true);

        s1.getVertices().add(v1);
        s2.getVertices().add(v2);
        s2.getVertices().add(v3);
        s2.getVertices().add(v4);
        s3.getVertices().add(v5);
        s4.getVertices().add(v6);
        s4.getVertices().add(v7);
        s5.getVertices().add(v8);
        s5.getVertices().add(v9);

        graph.getSpheres().add(s1);
        graph.getSpheres().add(s2);
        graph.getSpheres().add(s3);
        graph.getSpheres().add(s4);
        graph.getSpheres().add(s5);

        graph.addEdge(e1, v1, v2);
        graph.addEdge(e2, v5, v4);
        graph.addEdge(e3, v7, v6);
        graph.addEdge(e4, v8, v9);
        graph.addEdge(e5, v6, v3);
        graph.addEdge(e6, v7, v3);
        graph.addEdge(e7, v8, v3);
        graph.addEdge(e8, v9, v3);
        spheresList = graph.getSpheres();
        verticesList.addAll(graph.getVertices());
        verticesList.sort(Comparator.comparingInt(Vertex::getId));
        edgesList.addAll(graph.getEdges());
        edgesList.sort(Comparator.comparingInt(Edge::getId));
    }

    // <--------------       holistic comparision       ------------->

    @Test
    public void testSphereList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        Assert.assertEquals(spheresList, g.getSpheres());
    }

    @Test
    public void testVertexList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        List<Vertex> importedVertices = new ArrayList<>(g.getVertices());
        importedVertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(verticesList, importedVertices);
    }

    @Test
    public void testEdgeList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        List<Edge> importedEdges = new ArrayList<>(g.getEdges());
        importedEdges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(edgesList, importedEdges);
    }

    // <-------------   number of elements    ------------------>

    @Test
    public void testElementNumberOfSyndromGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        Assert.assertEquals(8, g.getEdgeCount());
        Assert.assertEquals(9, g.getVertexCount());
        Assert.assertEquals(5, g.getSpheres().size());
    }

    @Test
    public void testElementNumberWithTemplateOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(true);
        Assert.assertEquals(2, doc.getDocumentElement().getGraphCount());
    }


    @Test
    public void testElementNumberOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(false);
        Assert.assertEquals(1, doc.getDocumentElement().getGraphCount());
        Assert.assertEquals(22, doc.getElement(syndromName).getChildCount());
    }

    // <--------------     relations of graph elements    ------------->

    @Test
    public void testVerticesInSpheresOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(new ArrayList<>(Collections.singletonList(5)), getNodeIDs(spheres.get(0).getVertices()));
        Assert.assertEquals(new ArrayList<>(Arrays.asList(6, 7, 8)), getNodeIDs(spheres.get(1).getVertices()));
        Assert.assertEquals(new ArrayList<>(Collections.singletonList(9)), getNodeIDs(spheres.get(2).getVertices()));
        Assert.assertEquals(new ArrayList<>(Arrays.asList(10, 11)), getNodeIDs(spheres.get(3).getVertices()));
        Assert.assertEquals(new ArrayList<>(Arrays.asList(12, 13)), getNodeIDs(spheres.get(4).getVertices()));
    }

    private ArrayList<Integer> getNodeIDs(LinkedList<Vertex> pVertices){
        ArrayList<Integer> verticesIDs = new ArrayList<>();
        for(Vertex v : pVertices){
            verticesIDs.add(v.getId());
        }
        return verticesIDs;
    }

    @Test
    public void testEdgesConnectingRightVerticesOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(5, g.getEndpoints(edges.get(0)).getFirst().getId());
        Assert.assertEquals(6, g.getEndpoints(edges.get(0)).getSecond().getId());
        Assert.assertEquals(9, g.getEndpoints(edges.get(1)).getFirst().getId());
        Assert.assertEquals(8, g.getEndpoints(edges.get(1)).getSecond().getId());
        Assert.assertEquals(11, g.getEndpoints(edges.get(2)).getFirst().getId());
        Assert.assertEquals(10, g.getEndpoints(edges.get(2)).getSecond().getId());
        Assert.assertEquals(12,g.getEndpoints(edges.get(3)).getFirst().getId());
        Assert.assertEquals(13, g.getEndpoints(edges.get(3)).getSecond().getId());
        Assert.assertEquals(10, g.getEndpoints(edges.get(4)).getFirst().getId());
        Assert.assertEquals(7, g.getEndpoints(edges.get(4)).getSecond().getId());
        Assert.assertEquals(11, g.getEndpoints(edges.get(5)).getFirst().getId());
        Assert.assertEquals(7, g.getEndpoints(edges.get(5)).getSecond().getId());
        Assert.assertEquals(5, g.getEndpoints(edges.get(0)).getFirst().getId());
        Assert.assertEquals(12, g.getEndpoints(edges.get(6)).getFirst().getId());
        Assert.assertEquals(5, g.getEndpoints(edges.get(0)).getFirst().getId());
        Assert.assertEquals(7, g.getEndpoints(edges.get(6)).getSecond().getId());
        Assert.assertEquals(13, g.getEndpoints(edges.get(7)).getFirst().getId());
        Assert.assertEquals(7, g.getEndpoints(edges.get(7)).getSecond().getId());
    }

    // <-----------    coordinates    --------->

    @Test
    public void testSpheresCoordinatesOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(new Point2D.Double(20, 20), spheres.get(0).getCoordinates());
        Assert.assertEquals(new Point2D.Double(270, 50), spheres.get(1).getCoordinates());
        Assert.assertEquals(new Point2D.Double(620, 20), spheres.get(2).getCoordinates());
        Assert.assertEquals(new Point2D.Double(200, 310), spheres.get(3).getCoordinates());
        Assert.assertEquals(new Point2D.Double(445, 310), spheres.get(4).getCoordinates());
    }

    @Test
    public void testVerticesCoordinatesOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(new Point2D.Double(80, 80), vertices.get(0).getCoordinates());
        Assert.assertEquals(new Point2D.Double(340, 140), vertices.get(1).getCoordinates());
        Assert.assertEquals(new Point2D.Double(415, 190), vertices.get(2).getCoordinates());
        Assert.assertEquals(new Point2D.Double(485, 140), vertices.get(3).getCoordinates());
        Assert.assertEquals(new Point2D.Double(740, 80), vertices.get(4).getCoordinates());
        Assert.assertEquals(new Point2D.Double(220, 360), vertices.get(5).getCoordinates());
        Assert.assertEquals(new Point2D.Double(280, 420), vertices.get(6).getCoordinates());
        Assert.assertEquals(new Point2D.Double(555, 420), vertices.get(7).getCoordinates());
        Assert.assertEquals(new Point2D.Double(620, 360), vertices.get(8).getCoordinates());
    }

    // <-------------   size    ------------------>

    @Test
    public void testSpheresWidthOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(200.0, spheres.get(0).getWidth());
        Assert.assertEquals(300.0, spheres.get(1).getWidth());
        Assert.assertEquals(200.0, spheres.get(2).getWidth());
        Assert.assertEquals(200.0, spheres.get(3).getWidth());
        Assert.assertEquals(200.0, spheres.get(4).getWidth());
    }

    @Test
    public void testSpheresHeightOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(200.0, spheres.get(0).getHeight());
        Assert.assertEquals(250.0, spheres.get(1).getHeight());
        Assert.assertEquals(200.0, spheres.get(2).getHeight());
        Assert.assertEquals(200.0, spheres.get(3).getHeight());
        Assert.assertEquals(200.0, spheres.get(4).getHeight());
    }

    @Test
    public void testVerticesSizeOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
       // vertices.forEach(System.out::println);
        Assert.assertEquals(50, vertices.get(0).getSize());
        Assert.assertEquals(50, vertices.get(1).getSize());
        Assert.assertEquals(120, vertices.get(2).getSize());
        Assert.assertEquals(50, vertices.get(3).getSize());
        Assert.assertEquals(50, vertices.get(4).getSize());
        Assert.assertEquals(50, vertices.get(5).getSize());
        Assert.assertEquals(80, vertices.get(6).getSize());
        Assert.assertEquals(80, vertices.get(7).getSize());
        Assert.assertEquals(50, vertices.get(8).getSize());
    }

    // <-----------    Color    ------------>

    @Test
    public void testSpheresFillColorOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(new java.awt.Color(86, 151, 31, 183), spheres.get(0).getColor());
        Assert.assertEquals(new java.awt.Color(86, 151, 31, 183), spheres.get(1).getColor());
        Assert.assertEquals(new java.awt.Color(86, 151, 31, 183), spheres.get(2).getColor());
        Assert.assertEquals(new java.awt.Color(24, 54, 11, 178), spheres.get(3).getColor());
        Assert.assertEquals(new java.awt.Color(24, 54, 11, 178), spheres.get(4).getColor());
    }


    @Test
    public void testVerticesFillColorOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(new java.awt.Color(88, 88, 219, 220), vertices.get(0).getFillColor());
        Assert.assertEquals(new java.awt.Color(88, 88, 219, 220), vertices.get(1).getFillColor());
        Assert.assertEquals(new java.awt.Color(0xC80070), vertices.get(2).getFillColor());
        Assert.assertEquals(new java.awt.Color(88, 88, 219, 220), vertices.get(3).getFillColor());
        Assert.assertEquals(new java.awt.Color(88, 88, 219, 220), vertices.get(4).getFillColor());
        Assert.assertEquals(new java.awt.Color(11, 19, 90, 220), vertices.get(5).getFillColor());
        Assert.assertEquals(new java.awt.Color(11, 19, 90, 220), vertices.get(6).getFillColor());
        Assert.assertEquals(new java.awt.Color(11, 19, 90, 220), vertices.get(7).getFillColor());
        Assert.assertEquals(new java.awt.Color(11, 19, 90, 220), vertices.get(8).getFillColor());

    }

    @Test
    public void testVerticesDrawColorOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(0).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(1).getDrawColor());
        Assert.assertEquals(new java.awt.Color(200,10,10, 203), vertices.get(2).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(3).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(4).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(5).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(6).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(7).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(8).getDrawColor());
    }

    @Test
    public void testEdgesColorOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(0).getColor());
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(1).getColor());
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(2).getColor());
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(3).getColor());
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(4).getColor());
        Assert.assertEquals(new java.awt.Color(28, 56, 249, 130), edges.get(5).getColor());
        Assert.assertEquals(new java.awt.Color(28, 56, 249, 130), edges.get(6).getColor());
        Assert.assertEquals(Values.getInstance().getEdgePaint(), edges.get(7).getColor());
    }

    // <----------     sphere locking    ------------>

    @Test
    public void testSpheresLockedAnnotationOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertTrue(spheres.get(0).isLockedAnnotation());
        Assert.assertTrue(spheres.get(1).isLockedAnnotation());
        Assert.assertFalse(spheres.get(2).isLockedAnnotation());
        Assert.assertFalse(spheres.get(3).isLockedAnnotation());
        Assert.assertFalse(spheres.get(4).isLockedAnnotation());
    }

    @Test
    public void testSpheresLockedVerticesOfGraph() throws IOException, SAXException {
        sm.action();
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();

        Assert.assertFalse(spheres.get(0).isLockedVertices());
        Assert.assertTrue(spheres.get(1).isLockedVertices());
        Assert.assertFalse(spheres.get(2).isLockedVertices());
        Assert.assertFalse(spheres.get(3).isLockedVertices());
        Assert.assertFalse(spheres.get(4).isLockedVertices());
    }

    @Test
    public void testSpheresLockedPositionOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertFalse(spheres.get(0).isLockedPosition());
        Assert.assertFalse(spheres.get(1).isLockedPosition());
        Assert.assertFalse(spheres.get(2).isLockedPosition());
        Assert.assertTrue(spheres.get(3).isLockedPosition());
        Assert.assertTrue(spheres.get(4).isLockedPosition());
    }

    @Test
    public void testSpheresLockedStyleOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertFalse(spheres.get(0).isLockedStyle());
        Assert.assertFalse(spheres.get(1).isLockedStyle());
        Assert.assertFalse(spheres.get(2).isLockedStyle());
        Assert.assertFalse(spheres.get(3).isLockedStyle());
        Assert.assertTrue(spheres.get(4).isLockedStyle());
    }

    @Test
    public void testSpheresLockedMaxAmountVerticesOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals("", spheres.get(0).getLockedMaxAmountVertices());
        Assert.assertEquals("", spheres.get(1).getLockedMaxAmountVertices());
        Assert.assertEquals("", spheres.get(2).getLockedMaxAmountVertices());
        Assert.assertEquals("17", spheres.get(3).getLockedMaxAmountVertices());
        Assert.assertEquals("5", spheres.get(4).getLockedMaxAmountVertices());
    }


    // <-------------   vertex locking    ----------->

    @Test
    public void testVerticesLockedStyleOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertTrue(vertices.get(0).isLockedStyle());
        Assert.assertFalse(vertices.get(1).isLockedStyle());
        Assert.assertTrue(vertices.get(2).isLockedStyle());
        Assert.assertFalse(vertices.get(3).isLockedStyle());
        Assert.assertFalse(vertices.get(4).isLockedStyle());
        Assert.assertTrue(vertices.get(5).isLockedStyle());
        Assert.assertFalse(vertices.get(6).isLockedStyle());
        Assert.assertFalse(vertices.get(7).isLockedStyle());
        Assert.assertFalse(vertices.get(8).isLockedStyle());
    }

    @Test
    public void testVerticesLockedAnnotationOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertFalse(vertices.get(0).isLockedAnnotation());
        Assert.assertFalse(vertices.get(1).isLockedAnnotation());
        Assert.assertTrue(vertices.get(2).isLockedAnnotation());
        Assert.assertTrue(vertices.get(3).isLockedAnnotation());
        Assert.assertTrue(vertices.get(4).isLockedAnnotation());
        Assert.assertFalse(vertices.get(5).isLockedAnnotation());
        Assert.assertFalse(vertices.get(6).isLockedAnnotation());
        Assert.assertFalse(vertices.get(7).isLockedAnnotation());
        Assert.assertFalse(vertices.get(8).isLockedAnnotation());
    }

    @Test
    public void testVerticesLockedPositionOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertFalse(vertices.get(0).isLockedPosition());
        Assert.assertFalse(vertices.get(1).isLockedPosition());
        Assert.assertTrue(vertices.get(2).isLockedPosition());
        Assert.assertFalse(vertices.get(3).isLockedPosition());
        Assert.assertFalse(vertices.get(4).isLockedPosition());
        Assert.assertFalse(vertices.get(5).isLockedPosition());
        Assert.assertTrue(vertices.get(6).isLockedPosition());
        Assert.assertTrue(vertices.get(7).isLockedPosition());
        Assert.assertFalse(vertices.get(8).isLockedPosition());
    }

    // <--------    edge locking    ---------->

    @Test
    public void testEdgesLockedStyleOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertFalse(edges.get(0).isLockedStyle());
        Assert.assertFalse(edges.get(1).isLockedStyle());
        Assert.assertTrue(edges.get(2).isLockedStyle());
        Assert.assertFalse(edges.get(3).isLockedStyle());
        Assert.assertFalse(edges.get(4).isLockedStyle());
        Assert.assertTrue(edges.get(5).isLockedStyle());
        Assert.assertTrue(edges.get(6).isLockedStyle());
        Assert.assertFalse(edges.get(7).isLockedStyle());
    }

    @Test
    public void testEdgesLockedPositionOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertFalse(edges.get(0).isLockedPosition());
        Assert.assertFalse(edges.get(1).isLockedPosition());
        Assert.assertFalse(edges.get(2).isLockedPosition());
        Assert.assertTrue(edges.get(3).isLockedPosition());
        Assert.assertFalse(edges.get(4).isLockedPosition());
        Assert.assertFalse(edges.get(5).isLockedPosition());
        Assert.assertFalse(edges.get(6).isLockedPosition());
        Assert.assertTrue(edges.get(7).isLockedPosition());
    }

    @Test
    public void testEdgesLockedEdgeTypeOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertTrue(edges.get(0).isLockedEdgeType());
        Assert.assertTrue(edges.get(1).isLockedEdgeType());
        Assert.assertFalse(edges.get(2).isLockedEdgeType());
        Assert.assertFalse(edges.get(3).isLockedEdgeType());
        Assert.assertFalse(edges.get(4).isLockedEdgeType());
        Assert.assertFalse(edges.get(5).isLockedEdgeType());
        Assert.assertFalse(edges.get(6).isLockedEdgeType());
        Assert.assertTrue(edges.get(7).isLockedEdgeType());
    }


    // <-----------     other edge style   ------------------->

    @Test
    public void testEdgesArrowTypeOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(EdgeArrowType.EXTENUATING, edges.get(0).getArrowType());
        Assert.assertEquals(EdgeArrowType.EXTENUATING, edges.get(1).getArrowType());
        Assert.assertEquals(EdgeArrowType.NEUTRAL, edges.get(2).getArrowType());
        Assert.assertEquals(EdgeArrowType.NEUTRAL, edges.get(3).getArrowType());
        Assert.assertEquals(EdgeArrowType.REINFORCED, edges.get(4).getArrowType());
        Assert.assertEquals(EdgeArrowType.REINFORCED, edges.get(5).getArrowType());
        Assert.assertEquals(EdgeArrowType.REINFORCED, edges.get(6).getArrowType());
        Assert.assertEquals(EdgeArrowType.REINFORCED, edges.get(7).getArrowType());
    }

    @Test
    public void testEdgesStrokeTypeOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(StrokeType.BASIC, edges.get(0).getStroke());
        Assert.assertEquals(StrokeType.BASIC_WEIGHT, edges.get(1).getStroke());
        Assert.assertEquals(StrokeType.DOTTED_WEIGHT, edges.get(2).getStroke());
        Assert.assertEquals(StrokeType.DOTTED, edges.get(3).getStroke());
        Assert.assertEquals(StrokeType.BASIC, edges.get(4).getStroke());
        Assert.assertEquals(StrokeType.DASHED, edges.get(5).getStroke());
        Assert.assertEquals(StrokeType.DASHED, edges.get(6).getStroke());
        Assert.assertEquals(StrokeType.DASHED_WEIGHT, edges.get(7).getStroke());
    }





    @Before
    public void prepare() {
        doc = new GXLDocument();
        GXLGraph gxlGraph = new GXLGraph(syndromName);
        GXLNode sphere0 = new GXLNode("0");
        java.awt.Color fillPaint = new java.awt.Color(255, 0, 0, 255);
        String paintDescription = getPaintDescription(fillPaint);
        sphere0.setAttr("color", new GXLString(paintDescription));
        GXLNode sphere1 = new GXLNode("1");
        sphere1.setAttr("width", new GXLString("25.0"));
        sphere1.setAttr("height", new GXLString("30.0"));
        GXLNode sphere2 = new GXLNode("2");

        Point2D coordinates = new java.awt.geom.Point2D.Double(250.0, 500.0);
        GXLNode vertex0 = new GXLNode("3");
        vertex0.setAttr("coordinates", new GXLString(coordinates.toString()));
        GXLNode vertex1 = new GXLNode("4");
        vertex1.setAttr("size", new GXLInt(15));
        GXLNode vertex2 = new GXLNode("5");
        GXLNode vertex3 = new GXLNode("6");
        GXLNode vertex4 = new GXLNode("7");
        GXLNode vertex5 = new GXLNode("8");
        GXLEdge edge0 = new GXLEdge("2", "3");
        GXLEdge edge1 = new GXLEdge("3", "5");
        GXLEdge edge2 = new GXLEdge("6", "8");
        GXLEdge edge3 = new GXLEdge("8", "6");
        GXLEdge edge4 = new GXLEdge("2", "7");
        GXLEdge edge5 = new GXLEdge("5", "4");
        gxlGraph.add(sphere0);
        gxlGraph.add(sphere1);
        gxlGraph.add(sphere2);
        gxlGraph.add(vertex0);
        gxlGraph.add(vertex1);
        gxlGraph.add(vertex2);
        gxlGraph.add(vertex3);
        gxlGraph.add(vertex4);
        gxlGraph.add(vertex5);
        gxlGraph.add(edge0);
        gxlGraph.add(edge1);
        gxlGraph.add(edge2);
        gxlGraph.add(edge3);
        gxlGraph.add(edge4);
        gxlGraph.add(edge5);
        doc.getDocumentElement().add(gxlGraph);
        try {
            doc.write(new File("GXLTest"));
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @Test
    public void testElementNumber() {
        logger.info("Ich bin das GXLDokument: " + doc);
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        int numberOfElementsInTheGraph = doc.getElement(syndromName).getChildCount();
        Assert.assertEquals(15, numberOfElementsInTheGraph);
    }

    @Test
    public void testColor() {
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        GXLNode sphere0 = (GXLNode) doc.getElement("0");
        java.awt.Color expectedColor = new Color(255, 0, 0, 255);
        String sphereColorDescription = ((GXLString) sphere0.getAttr("color").getValue()).getValue();
        String[] colorArray = gxlio.getNumberArrayFromString(sphereColorDescription);
        java.awt.Color sphereColor = new Color(Integer.parseInt(colorArray[0]), Integer.parseInt(colorArray[1]),
                Integer.parseInt(colorArray[2]), Integer.parseInt(colorArray[3]));
        logger.info("Beschreibung der erwarteten Farbe: " + getPaintDescription(expectedColor));
        logger.info("Beschreibung der Farbe der Sphäre: " + getPaintDescription(sphereColor));
        Assert.assertEquals(expectedColor, sphereColor);
    }


    @Test
    public void testCoordinates() {
        GXLNode vertex0 = (GXLNode) doc.getElement("3");
        String coordinatesDescription = ((GXLString) vertex0.getAttr("coordinates").getValue()).getValue();
        String[] coordinatesArray = gxlio.getNumberArrayFromString(coordinatesDescription);
        Assert.assertEquals(250.0, Double.parseDouble(coordinatesArray[0]), 0.0);
        Assert.assertEquals(500.0, Double.parseDouble(coordinatesArray[1]), 0.0);
    }

    @Test
    public void testSize() {
        GXLNode sphere1 = (GXLNode) doc.getElement("1");
        GXLNode vertex1 = (GXLNode) doc.getElement("4");
        double sphereWidth = Double.parseDouble((((GXLString) sphere1.getAttr("width").getValue()).getValue()));
        double sphereheight = Double.parseDouble((((GXLString) sphere1.getAttr("height").getValue()).getValue()));
        Assert.assertEquals(25.0, sphereWidth, 0.0);
        Assert.assertEquals(30.0, sphereheight, 0.0);
        int vertexSize = (((GXLInt) vertex1.getAttr("size").getValue()).getIntValue());
        Assert.assertEquals(15, vertexSize);
    }


    /**
     * Forms a description of a color.
     *
     * @param color the color that need to be describted
     * @return the description of the color as a String
     */
    private String getPaintDescription(Color color) {
        return ("java.awt.Color[r=" + color.getRed() + ",g=" + color.getGreen()
                + ",b=" + color.getBlue() + ",a=" + color.getAlpha() + "]");
    }
}
