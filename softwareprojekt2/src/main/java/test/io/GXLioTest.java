package test.io;

import java.awt.*;

import java.util.*;

import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import gui.Values;
import io.GXLio;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.freehep.graphicsbase.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class GXLioTest {



    /**
     * The values to use.
     */
    private Values values;

    private GXLDocument doc = null;

    GraphObjectsFactory factory = new GraphObjectsFactory();

    private static Logger logger = Logger.getLogger(GXLioTest.class);

    public GXLio prepareSyndrom() throws IOException, SAXException {
        Syndrom syndrom = graph.graph.Syndrom.getInstance();
        syndrom.generateNew();
        generateGraphElements();
        Syndrom.getInstance().getVv().getGraphLayout().setGraph(Syndrom.getInstance().getGraph());
        GXLio gxlio = new GXLio();
        gxlio.exportGXL(new File("testGraph.gxl"), false);
        Syndrom.getInstance().generateNew();
        doc = new GXLDocument(new File("testGraph.gxl"));
        return gxlio;
    }

    private void generateGraphElements(){
        Values.getInstance().setFontSizeSphere(10);
        Values.getInstance().setFillPaintSphere(new java.awt.Color(86, 151, 31, 183));
        Sphere s1 = factory.createSphere(new Point2D.Double(20, 20));
        Sphere s2 = factory.createSphere(new Point2D.Double(270, 50));
        s2.setHeight(250.0);
        s2.setWidth(300.0);
        Sphere s3 = factory.createSphere(new Point2D.Double(620, 20));
        Values.getInstance().setFillPaintSphere(new java.awt.Color(24, 54, 11, 178));
        Values. getInstance().setFontSizeSphere(24);
        Sphere s4 = factory.createSphere(new Point2D.Double(200, 310));
        Sphere s5 = factory.createSphere(new Point2D.Double(445, 310));

        Values.getInstance().setFontSizeVertex(12);
        Values.getInstance().setShapeVertex(VertexShapeType.CIRCLE);
        Values.getInstance().setFillPaintVertex(new java.awt.Color(88, 88, 219, 220));
        Values.getInstance().setFontVertex("Roboto");
        Vertex v1 = factory.createVertex(new Point2D.Double(80, 80));
        Vertex v2 = factory.createVertex(new Point2D.Double(340, 140));
        Vertex v3 = factory.createVertex(new Point2D.Double(415, 190));
        v3.setFillColor(new java.awt.Color(0xC80070));
        v3.setSize(120);
        Vertex v4 = factory.createVertex(new Point2D.Double(485, 140));
        Vertex v5 = factory.createVertex(new Point2D.Double(740, 80));
        Values.getInstance().setFillPaintVertex(new java.awt.Color(10, 20, 90, 255));
        Values.getInstance().setShapeVertex(VertexShapeType.RECTANGLE);
        Values.getInstance().setFillPaintVertex(new java.awt.Color(11, 19, 90, 220));
        Values.getInstance().setFontSizeVertex(7);
        Vertex v6 = factory.createVertex(new Point2D.Double(220, 360));
        Vertex v7 = factory.createVertex(new Point2D.Double(280, 420));
        v7.setFont("Mali");
        v7.setFontSize(14);
        v7.setSize(80);
        Vertex v8 = factory.createVertex(new Point2D.Double(555, 420));
        v8.setFont("Mali");
        v8.setFontSize(14);
        v8.setSize(80);
        Vertex v9 = factory.createVertex(new Point2D.Double(620, 360));

        Edge e1 = factory.createEdge();
        Edge e2 = factory.createEdge();
        Edge e3 = factory.createEdge();
        e3.setArrowType(EdgeArrowType.NEUTRAL);
        Edge e4 = factory.createEdge();
        e4.setArrowType(EdgeArrowType.NEUTRAL);
        Values.getInstance().setEdgeArrowType(EdgeArrowType.EXTENUATING);
        Edge e5 = factory.createEdge();
        Edge e6 = factory.createEdge();
        e6.setStroke(StrokeType.DASHED);
        e6.setColor(new java.awt.Color(28, 56, 249, 130));
        Edge e7 = factory.createEdge();
        e7.setStroke(StrokeType.DASHED);
        e7.setColor(new java.awt.Color(28, 56, 249, 130));
        Edge e8 = factory.createEdge();


        s1.getVertices().add(v1);
        s2.getVertices().add(v2);
        s2.getVertices().add(v3);
        s2.getVertices().add(v4);
        s3.getVertices().add(v5);
        s4.getVertices().add(v6);
        s4.getVertices().add(v7);
        s5.getVertices().add(v8);
        s5.getVertices().add(v9);

        Syndrom.getInstance().getGraph().getSpheres().add(s1);
        Syndrom.getInstance().getGraph().getSpheres().add(s2);
        Syndrom.getInstance().getGraph().getSpheres().add(s3);
        Syndrom.getInstance().getGraph().getSpheres().add(s4);
        Syndrom.getInstance().getGraph().getSpheres().add(s5);

        Syndrom.getInstance().getGraph().addEdge(e1, v1, v2);
        Syndrom.getInstance().getGraph().addEdge(e2, v5, v4);
        Syndrom.getInstance().getGraph().addEdge(e3, v7, v6);
        Syndrom.getInstance().getGraph().addEdge(e4, v8, v9);
        Syndrom.getInstance().getGraph().addEdge(e5, v6, v3);
        Syndrom.getInstance().getGraph().addEdge(e6, v7, v3);
        Syndrom.getInstance().getGraph().addEdge(e7, v8, v3);
        Syndrom.getInstance().getGraph().addEdge(e8, v9, v3);
    }

    @Test
    public void testElementNumberOfSyndromGraph() throws IOException, SAXException {
        prepareSyndrom().importGXL(new File("testGraph.gxl"), false);
        Assert.assertEquals(8, Syndrom.getInstance().getVv().getGraphLayout().getGraph().getEdgeCount());
        Assert.assertEquals(8, Syndrom.getInstance().getGraph().getEdgeCount());
        Assert.assertEquals(9, Syndrom.getInstance().getVv().getGraphLayout().getGraph().getVertexCount());
        Assert.assertEquals(9, Syndrom.getInstance().getGraph().getVertexCount());
        Assert.assertEquals(5, Syndrom.getInstance().getGraph().getSpheres().size());
    }

    @Test
    public void testElementNumberOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom();
        Assert.assertEquals(1, doc.getDocumentElement().getGraphCount());
        Assert.assertEquals(22, doc.getElement("syndrom").getChildCount());
    }

    @Test
    public void testSpheresWidthOfGraph() throws IOException, SAXException {
        prepareSyndrom().importGXL(new File("testGraph.gxl"), false);
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) Syndrom.getInstance().getGraph().getSpheres();
        Assert.assertEquals(200.0, spheres.get(0).getWidth());
        Assert.assertEquals(300.0, spheres.get(1).getWidth());
        Assert.assertEquals(200.0, spheres.get(2).getWidth());
        Assert.assertEquals(200.0, spheres.get(3).getWidth());
        Assert.assertEquals(200.0, spheres.get(4).getWidth());
    }

    @Test
    public void testSpheresHeightOfGraph() throws IOException, SAXException {
        prepareSyndrom().importGXL(new File("testGraph.gxl"), false);
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) Syndrom.getInstance().getGraph().getSpheres();
        Assert.assertEquals(200.0, spheres.get(0).getHeight());
        Assert.assertEquals(250.0, spheres.get(1).getHeight());
        Assert.assertEquals(200.0, spheres.get(2).getHeight());
        Assert.assertEquals(200.0, spheres.get(3).getHeight());
        Assert.assertEquals(200.0, spheres.get(4).getHeight());
    }

    @Test
    public void testVerticesSizeOfGraph() throws IOException, SAXException {
        prepareSyndrom().importGXL(new File("testGraph.gxl"), false);
        ArrayList<Vertex> vertices = new ArrayList();
        for(Vertex v : Syndrom.getInstance().getGraph().getVertices()){
            vertices.add(v);
        }
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        vertices.forEach(System.out::println);
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
            logger.error(e.toString());
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
