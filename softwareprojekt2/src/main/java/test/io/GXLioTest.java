package test.io;

import java.awt.*;
import java.util.*;

import actions.add.AddSphereLogAction;
import actions.add.AddVerticesLogAction;
import actions.other.CreateGraphAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import gui.Controller;
import io.GXLio;
import edu.uci.ics.jung.graph.util.Pair;
import log_management.parameters.add_remove.AddRemoveVerticesParam;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.freehep.graphicsbase.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class GXLioTest {



    private GXLDocument doc = null;

    private static Logger logger = Logger.getLogger(GXLioTest.class);

    public void prepareTests() {
        //   controller.initialize();
        // controller.addSphere();

    }

    /*
    private List<Sphere> makeSpheres(){
        List<Sphere> spheres = new ArrayList<>();
        Point2D coordinates = new java.awt.geom.Point2D.Double(100, 20.0);
        java.awt.Color color = new Color(10, 30, 70, 255);
        Map<String, String> annotation = new HashMap<>();
        annotation.put("de Späre", "en sphere");
        LinkedList<Vertex> vertices = new LinkedList();
        for(Vertex v : makeVertices()){
            vertices.add(v);
        }
        Sphere sphere = new Sphere(0, color, coordinates, 40, 40, annotation, "font", 12);
        System.out.println("sphere: "+sphere);
        return spheres;
    }
    */

    private Sphere makeSphere(Vertex pVertex, Point2D.Double pCoordinates){
        Layout<Vertex, Edge> layout = Syndrom.getInstance().getVv().getGraphLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) layout.getGraph();
        GraphObjectsFactory factory = new GraphObjectsFactory();
        Sphere s1 = factory.createSphere(pCoordinates);
        s1.getVertices().add(pVertex);
        graph.getSpheres().add(s1);
        Syndrom.getInstance().getGraph().addSphere(s1.getCoordinates());
        System.out.println("sphere1: "+ s1);
        return s1;
    }

    /*
    private List<Vertex> makeVertices(){
        List<Vertex> vertices = new ArrayList<>();
        Point2D coordinates1 = new java.awt.geom.Point2D.Double(110, 25.0);
        Point2D coordinates2 = new java.awt.geom.Point2D.Double(120, 29.0);
        java.awt.Color fillColor = new Color(10, 30, 70, 255);
        Map<String, String> annotation = new HashMap<>();
        annotation.put("de Knoten", "en vertex");
        java.awt.Color drawColor = new Color(10, 30, 70, 255);
        Vertex vertex1 = new Vertex(1, fillColor, coordinates1, VertexShapeType.CIRCLE, annotation, drawColor, 5, "font", 12);
        Vertex vertex2 = new Vertex(2, fillColor, coordinates2, VertexShapeType.CIRCLE, annotation, drawColor, 5, "font", 12);
        vertices.add(vertex1);
        vertices.add(vertex2);
        return vertices;
    }
    */

    private Vertex makeVertex(Point2D.Double pCoordinates){
        Layout<Vertex, Edge> layout = Syndrom.getInstance().getVv().getGraphLayout();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) layout.getGraph();
        GraphObjectsFactory factory = new GraphObjectsFactory();
        Vertex v1 = factory.createVertex(pCoordinates);
        graph.addVertex(v1);
        Syndrom.getInstance().getGraph().addVertex(v1);
     //   Syndrom.getInstance().getVv().getGraphLayout().setLocation(v1, v1.getCoordinates());
     //   pSphere.getVertices().add(v1);
        return v1;
    }

    /*
    private List<Map<Edge, Pair<Vertex>>> makeEdges(){
        List<Map<Edge, Pair<Vertex>>> edges = new ArrayList<Map<Edge, Pair<Vertex>>>();
        java.awt.Color color = new Color(10, 30, 70, 255);
        Edge edge = new Edge(3, color, StrokeType.DOTTED, EdgeArrowType.EXTENUATING, true, false, false);
        Map<Edge, Pair<Vertex>> map = new HashMap<>();
        map.entrySet();
        Pair<Vertex> vertices = new Pair<>(makeVertex(new Point2D.Double(30, 30)), makeVertex(new Point2D.Double(40, 40)));
        map.put(edge, vertices);
        edges.add(map);
        return edges;
    }
    */

//List<Sphere> pSpheres, List<Vertex> pVertices, List<Map<Edge, Pair<Vertex>>> pEdges
    public void prepareSyndrom() {
        Syndrom syndrom = graph.graph.Syndrom.getInstance();
        syndrom.generateNew();
        Vertex v = makeVertex(new Point2D.Double(30, 30));
        Sphere s = makeSphere(v, new Point2D.Double(20, 20));
      //  Syndrom.getInstance().getVv().getGraphLayout().setGraph(Syndrom.getInstance().getGraph());
    }

    @Test
    public void testGraph() throws IOException, SAXException {
        prepareSyndrom();
        GXLio gxlio = new GXLio();
        System.out.println(Syndrom.getInstance().getVv().getGraphLayout().getGraph());
        System.out.println(Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertices());
        System.out.println(Syndrom.getInstance().getGraph().getVertices().size());
        System.out.println(Syndrom.getInstance().getGraph().getSpheres().get(0));
      //  gxlio.setSyndrom(Syndrom.getInstance());
        gxlio.exportGXL(new File("testGraph"), false);
        GXLDocument doc = new GXLDocument(new File("testGraph"));
        Assert.assertEquals(1, doc.getDocumentElement().getGraphCount());
        System.out.println("Anzahl an Elementen: " + doc.getElement("syndrom").getChildCount() + Syndrom.getInstance().getGraph().getSpheres().get(0).getVertices());
        Assert.assertEquals(1, doc.getElement("syndrom").getChildCount());
    }

    @Before
    public void prepare(){
        doc = new GXLDocument();
        GXLGraph gxlGraph = new GXLGraph("syndrom");
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
            e.printStackTrace();
        }
    }

    @Test
    public void testElementNumber(){
        logger.info("Ich bin das GXLDokument: " + doc);
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        int numberOfElementsInTheGraph = doc.getElement("syndrom").getChildCount();
        Assert.assertEquals(15, numberOfElementsInTheGraph);
    }

    @Test
    public void testColor(){
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        GXLNode sphere0 = (GXLNode) doc.getElement("0");
        java.awt.Color expectedColor = new Color(255, 0, 0, 255);
        String sphereColorDescription = ((GXLString) sphere0.getAttr("color").getValue()).getValue();
        String[] colorArray = getNumberArrayFromString(sphereColorDescription);
        java.awt.Color sphereColor = new Color(Integer.parseInt(colorArray[0]), Integer.parseInt(colorArray[1]),
                Integer.parseInt(colorArray[2]), Integer.parseInt(colorArray[3]));
        logger.info("Beschreibung der erwarteten Farbe: " + getPaintDescription(expectedColor));
        logger.info("Beschreibung der Farbe der Sphäre: " + getPaintDescription(sphereColor));
        Assert.assertEquals(expectedColor, sphereColor);
    }



    @Test
    public void testCoordinates(){
        GXLNode vertex0 = (GXLNode) doc.getElement("3");
        String coordinatesDescription = ((GXLString) vertex0.getAttr("coordinates").getValue()).getValue();
        String[] coordinatesArray = getNumberArrayFromString(coordinatesDescription);
        Assert.assertEquals(250.0, Double.parseDouble(coordinatesArray[0]), 0.0);
        Assert.assertEquals(500.0, Double.parseDouble(coordinatesArray[1]), 0.0);
    }

    @Test
    public void testSize(){
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
    public String getPaintDescription(Color color) {
        return ("java.awt.Color[r=" + color.getRed() + ",g=" + color.getGreen()
                + ",b=" + color.getBlue() + ",a=" + color.getAlpha() + "]");
    }


    /**
     * Converts a String that contains an unknown amount of numbers into a String array.
     * Each entry of the array contains a number as String.
     * It is not generally clear of wich concrete type this number is.
     *
     * @param pWord a word containing an unknown amount of numbers.
     * @return the numbers as String contained in the String parameter as entries in the array.
     */
    public String[] getNumberArrayFromString(String pWord) {
        String word = pWord;
        String[] alphabet = {"2D", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String s : alphabet) {
            word = word.replaceAll(s, "");
            word = word.replaceAll(s.toUpperCase(), "");
        }
        int numberOFDots = 0;
        while (word.charAt(numberOFDots) == '.') {
            numberOFDots++;
        }
        word = word.substring(numberOFDots);
        word = word.substring(1, word.length() - 1);
        if (word.contains("=")) {
            word = word.substring(1);
            word = word.replaceAll(",", "");
            return word.split("=");
        }
        word = word.trim();
        return word.split(",");
    }

/*
    @Test
    public void testPrepareTestsWithoutTemplate(){
        prepareTests();
        System.out.println("Die Anzahl der Sphären im Graphen beträgt: " + syndrom.getGraph().getSpheres().size());
        controller.exportGXL();

        System.out.println("Die Anzahl der Sphären im Graphen beträgt: " + syndrom.getGraph().getSpheres().size());
        controller.importGXL();
        System.out.println("Die Anzahl der Sphären im Graphen beträgt: " + syndrom.getGraph().getSpheres().size());
        Assert.assertEquals(1, syndrom.getGraph().getSpheres().size());
    }

    @Test
    public void testPrepareTestsWithTemplate(){
        prepareTests();
        controller.exportGXLWithTemplate();
        controller.importGXLWithTemplate();
    }

    */
}
