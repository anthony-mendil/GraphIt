package io;

import graph.graph.*;
import gui.Values;
import gui.properties.Language;
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

    /**
     * The instance of the class GXLio that is used to tests the functionality of the class by calling its methods.
     */
    private GXLio gxlio = new GXLio();

    /**
     * This method is called before each test method.
     * It generates a new syndrom, sets a template and some graaph elements to this syndrom / the graph of the syndrom,
     * exports the graph data, generates a new graph again to make sure the old graph data gets deleted from the system
     * and then initialises the GXLDocument attribute in this class. The last step also loads the graph data from the gxl into the system,
     *
     * @param pWithTemplate
     * @return the attribute of this test that is of type GXLio.
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    private GXLio prepareSyndrom(boolean pWithTemplate) throws IOException, SAXException {
        // generate new graph and set as actual graph
        syndrom.generateNew();
        syndrom.setTemplate(new Template(25, 50, 75, true, false, true));
        generateGraphElements();
        syndrom.getVv().getGraphLayout().setGraph(graph);
        // export graph

        gxlio.exportGXL(new File(nameTestGraph), pWithTemplate);
        // generate new clean syndrom
        syndrom.generateNew();
        doc = new GXLDocument(new File(nameTestGraph));
        return gxlio;
    }

    /**
     * This method creates some spheres, vertices and edges with different values for some of their attributes.
     */
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
        Map<String, String> annotation2 = s2.getAnnotation();
        annotation2.put(Language.GERMAN.name(), "Sphäre Nummer 1");
        s2.setAnnotation(annotation2);
        Sphere s3 = factory.createSphere(new Point2D.Double(620, 20));
        Map<String, String> annotation3 = s3.getAnnotation();
        annotation3.put(Language.ENGLISH.name(), "Sphere number 2");
        s3.setAnnotation(annotation3);
        values.setFillPaintSphere(new java.awt.Color(24, 54, 11, 178));
        values.setFontSizeSphere(24);
        Sphere s4 = factory.createSphere(new Point2D.Double(200, 310));
        s4.setLockedMaxAmountVertices("17");
        s4.setLockedPosition(true);
        Map<String, String> annotation4 = s4.getAnnotation();
        annotation4.put(Language.GERMAN.name(), "Sphäre Nummer 3");
        annotation4.put(Language.ENGLISH.name(), "Sphere number 3");
        s4.setAnnotation(annotation4);
        s4.setFont("Kalam");
        Sphere s5 = factory.createSphere(new Point2D.Double(445, 310));
        s5.setLockedMaxAmountVertices("5");
        s5.setLockedPosition(true);
        s5.setLockedStyle(true);
        s5.setFont("Kalam");

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
        v3.setDrawColor(new java.awt.Color(200, 10, 10, 203));
        v3.setLockedStyle(true);
        v3.setLockedAnnotation(true);
        v3.setLockedPosition(true);
        Map<String, String> annotationV3 = v3.getAnnotation();
        annotationV3.put(Language.GERMAN.name(), "Symptom Nummer 7");
        annotationV3.put(Language.ENGLISH.name(), "Symptom number 7");
        v3.setAnnotation(annotationV3);
        Vertex v4 = factory.createVertex(new Point2D.Double(485, 140));
        v4.setLockedAnnotation(true);
        Vertex v5 = factory.createVertex(new Point2D.Double(740, 80));
        v5.setLockedAnnotation(true);
        values.setShapeVertex(VertexShapeType.RECTANGLE);
        values.setFillPaintVertex(new java.awt.Color(11, 19, 90, 220));
        values.setFontSizeVertex(7);
        Vertex v6 = factory.createVertex(new Point2D.Double(220, 360));
        v6.setLockedStyle(true);
        Map<String, String> annotationV6 = v6.getAnnotation();
        annotationV6.put(Language.GERMAN.name(), "Symptom Nummer 10");
        v6.setAnnotation(annotationV6);
        Vertex v7 = factory.createVertex(new Point2D.Double(280, 420));
        v7.setFont("Mali");
        v7.setFontSize(14);
        v7.setSize(80);
        v7.setLockedPosition(true);
        Map<String, String> annotationV7 = v7.getAnnotation();
        annotationV7.put(Language.ENGLISH.name(), "Symptom number 11");
        v7.setAnnotation(annotationV7);
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

    // <---------   name of graph   ------------>

    /**
     * This method tests if the name of a graph is exported and imported correctly.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testGraphName() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        Assert.assertEquals("UntitledGraph", syndrom.getGraphName());
    }


    // <--------------       holistic comparision       ------------->

    /**
     * This method tests, if the list of spheres contained in the graph belonging to the syndrom after importing a gxl-File
     * is equal to the list of spheres of the graph that gets imported.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testSphereList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        Assert.assertEquals(spheresList, g.getSpheres());
    }

    /**
     * This method tests, if the list of vertices contained in the graph belonging to the syndrom after importing a gxl-File
     * is equal to the list of vertices of the graph that is imported.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVertexList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        List<Vertex> importedVertices = new ArrayList<>(g.getVertices());
        importedVertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(verticesList, importedVertices);
    }

    /**
     * This method tests, if the list of edges contained in the graph belonging to the syndrom after importing a gxl-File
     * is equal to the list of edges of the graph that is imported.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testEdgeList() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        List<Edge> importedEdges = new ArrayList<>(g.getEdges());
        importedEdges.sort(Comparator.comparingInt(Edge::getId));
        Assert.assertEquals(edgesList, importedEdges);
    }

    // <-------------   number of elements    ------------------>

    /**
     * This method tests, if the number of spheres, vertices and edges is correct after the import of a gxl File.
     * Correct in this sense means that it is identical to the number of spheres, vertices and edges of the graph that gets exported.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testElementNumberOfSyndromGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        Assert.assertEquals(8, g.getEdgeCount());
        Assert.assertEquals(9, g.getVertexCount());
        Assert.assertEquals(5, g.getSpheres().size());
    }

    /**
     * This method tests if the GXLGraph-Oject created from the File that is created by the emport of a graph
     * contains only one GXLGraph (the exported syndrom) and if this graph contains the right amount of childs.
     * Childs in this sense are spheres, vertices and edges.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testElementNumberOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(false);
        Assert.assertEquals(1, doc.getDocumentElement().getGraphCount());
        Assert.assertEquals(23, doc.getElement(syndromName).getChildCount());
    }

    /**
     * This method tests if the GXLGraph-Oject created from the File that is created by the emport of a graph
     * contains two GXLGraphs. One of them represents the syndromgraph and the other GXLGraph contains the rules of the template.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testElementNumberWithTemplateOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(true);
        Assert.assertEquals(2, doc.getDocumentElement().getGraphCount());
    }

    /**
     * This method tests if a GXLValidationException is thrown when a method call oocurs on a GXLGraph that would lead
     * to an invalid GXLDocument. The document would not be valid any longer after the method call, because two GXLElements would have the same id.
     * This is not allowed in GXL notation.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test(expected = GXLValidationException.class)
    public void testExceptionAddElementToGXLGraphThatAlreadyContainsThisID() throws IOException, SAXException {
        prepareSyndrom(true);
        GXLElement dublicateIDElement = new GXLNode("1");
        doc.getElement("syndrom").add(dublicateIDElement);
    }


    // <--------    template    --------->

    /**
     * This method tests if the template belonging to the syndrom that is created from the File that is created by the emport of a graph
     * contains the correct number vulues for the attributes that are numbers.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testNumberAttributesInTemplateWithTemplateOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        Assert.assertEquals(25, syndrom.getTemplate().getMaxSpheres());
        Assert.assertEquals(50, syndrom.getTemplate().getMaxVertices());
        Assert.assertEquals(75, syndrom.getTemplate().getMaxEdges());
    }

    /**
     * This method tests if the template graph contains the right number of attributes.
     * This attributes are the attributes that can be seen in the two tests below this test method.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testAttributeNumberOfGXLTemplate() throws IOException, SAXException {
        prepareSyndrom(true);
        GXLDocument document = new GXLDocument(new File(nameTestGraph));
        GXLGraph templateGraph = (GXLGraph) document.getElement("template");
        Assert.assertEquals(6, templateGraph.getAttrCount());
    }

    /**
     * This method tests if the template belonging to the syndrom that is created from the File that is created by the emport of a graph
     * contains the correct boolean vulues for the attributes that are booleans.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testBooleanAttributesInTemplateWithTemplateOfGXLGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        Assert.assertEquals(true, syndrom.getTemplate().isReinforcedEdgesAllowed());
        Assert.assertEquals(false, syndrom.getTemplate().isExtenuatingEdgesAllowed());
        Assert.assertEquals(true, syndrom.getTemplate().isNeutralEdgesAllowed());
    }

    /**
     * This method tests if a NullPointerException is thrown when a gxl File without any template data is tried to be imported as a gxl with template.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test(expected = NullPointerException.class)
    public void testExceptionImportWithTemplateAfterExportWithoutTemplateInTemplateWithTemplate() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), true);
    }

    /**
     * This method tests if a NullPointerException is thrown when a method call oocurs on a empty GXLGraph element.
     * The element below is empty because the export creates a gxl File without a graph with the name "template".
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test(expected = NullPointerException.class)
    public void testExceptionSearchForGXLTemplateAfterExportWithoutTemplateInTemplateWithTemplate() throws IOException, SAXException {
        prepareSyndrom(false);
        GXLDocument gxlDocument = new GXLDocument(new File(nameTestGraph));
        GXLGraph templateGraph = (GXLGraph) gxlDocument.getElement("template");
        templateGraph.getGraphElementCount();
    }

    // <--------------     relations of graph elements    ------------->

    /**
     * This method tests if the spheres that are created by importing the specified gxl-File contain the right vertices.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This is a helper method that returns the IDs of the vertices that are passed to this method as list.
     *
     * @param pVertices a list of vertices those IDs are of interest
     * @return the IDs of vertices contained in the passed list a list of Integers.
     */
    private ArrayList<Integer> getNodeIDs(LinkedList<Vertex> pVertices) {
        ArrayList<Integer> verticesIDs = new ArrayList<>();
        for (Vertex v : pVertices) {
            verticesIDs.add(v.getId());
        }
        return verticesIDs;
    }

    /**
     * This method tests if the edges that are created by importing the specified gxl-File connect the right vertices.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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
        Assert.assertEquals(12, g.getEndpoints(edges.get(3)).getFirst().getId());
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the coordinates-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the coordinates-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    // <---------     annotation    ---------->

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the annotation-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testSpheresAnnotaionOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals("Sphere 0", convertMapToList(spheres.get(0).getAnnotation()).get(1));
        Assert.assertEquals("Sphäre 0", convertMapToList(spheres.get(0).getAnnotation()).get(3));
        Assert.assertEquals("Sphere 1", convertMapToList(spheres.get(1).getAnnotation()).get(1));
        Assert.assertEquals("Sphäre Nummer 1", convertMapToList(spheres.get(1).getAnnotation()).get(3));
        Assert.assertEquals("Sphere number 2", convertMapToList(spheres.get(2).getAnnotation()).get(1));
        Assert.assertEquals("Sphäre 2", convertMapToList(spheres.get(2).getAnnotation()).get(3));
        Assert.assertEquals("Sphere number 3", convertMapToList(spheres.get(3).getAnnotation()).get(1));
        Assert.assertEquals("Sphäre Nummer 3", convertMapToList(spheres.get(3).getAnnotation()).get(3));
        Assert.assertEquals("Sphere 4", convertMapToList(spheres.get(4).getAnnotation()).get(1));
        Assert.assertEquals("Sphäre 4", convertMapToList(spheres.get(4).getAnnotation()).get(3));
    }

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the annotation-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesAnnotaionOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals("Symptom 5", convertMapToList(vertices.get(0).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 5", convertMapToList(vertices.get(0).getAnnotation()).get(3));
        Assert.assertEquals("Symptom 6", convertMapToList(vertices.get(1).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 6", convertMapToList(vertices.get(1).getAnnotation()).get(3));
        Assert.assertEquals("Symptom number 7", convertMapToList(vertices.get(2).getAnnotation()).get(1));
        Assert.assertEquals("Symptom Nummer 7", convertMapToList(vertices.get(2).getAnnotation()).get(3));
        Assert.assertEquals("Symptom Nummer 7", convertMapToList(vertices.get(2).getAnnotation()).get(3));
        Assert.assertEquals("Symptom 8", convertMapToList(vertices.get(3).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 8", convertMapToList(vertices.get(3).getAnnotation()).get(3));
        Assert.assertEquals("Symptom 9", convertMapToList(vertices.get(4).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 9", convertMapToList(vertices.get(4).getAnnotation()).get(3));
        Assert.assertEquals("Symptom 10", convertMapToList(vertices.get(5).getAnnotation()).get(1));
        Assert.assertEquals("Symptom Nummer 10", convertMapToList(vertices.get(5).getAnnotation()).get(3));
        Assert.assertEquals("Symptom number 11", convertMapToList(vertices.get(6).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 11", convertMapToList(vertices.get(6).getAnnotation()).get(3));
        Assert.assertEquals("Symptom 12", convertMapToList(vertices.get(7).getAnnotation()).get(1));
        Assert.assertEquals("Symptom 12", convertMapToList(vertices.get(7).getAnnotation()).get(3));
    }

    /**
     * This is a helper Method that rturns the values of the passed map as list.
     */
    private ArrayList<String> convertMapToList(Map<String, String> pHashMap) {
        ArrayList<String> list = new ArrayList<>();
        for (Map.Entry map : pHashMap.entrySet()) {
            list.add((String) map.getKey());
            list.add((String) map.getValue());
        }
        return list;
    }

    // <-------------   size    ------------------>

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the width-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the height-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the size-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesSizeOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the fillColor-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the fillColor-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the drawColor-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesDrawColorOfGraph() throws IOException, SAXException {
        prepareSyndrom(false).importGXL(new File(nameTestGraph), false);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(0).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(1).getDrawColor());
        Assert.assertEquals(new java.awt.Color(200, 10, 10, 203), vertices.get(2).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(3).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(4).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(5).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(6).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(7).getDrawColor());
        Assert.assertEquals(Values.getInstance().getDrawPaintVertex(), vertices.get(8).getDrawColor());
    }

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the color-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    // <---------     shape    ------------->

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the shape-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesShapeOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(VertexShapeType.CIRCLE, vertices.get(0).getShape());
        Assert.assertEquals(VertexShapeType.CIRCLE, vertices.get(1).getShape());
        Assert.assertEquals(VertexShapeType.CIRCLE, vertices.get(2).getShape());
        Assert.assertEquals(VertexShapeType.CIRCLE, vertices.get(3).getShape());
        Assert.assertEquals(VertexShapeType.CIRCLE, vertices.get(4).getShape());
        Assert.assertEquals(VertexShapeType.RECTANGLE, vertices.get(5).getShape());
        Assert.assertEquals(VertexShapeType.RECTANGLE, vertices.get(6).getShape());
        Assert.assertEquals(VertexShapeType.RECTANGLE, vertices.get(7).getShape());
        Assert.assertEquals(VertexShapeType.RECTANGLE, vertices.get(8).getShape());
    }


    // <------------    font style     -------------->

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the font-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testSpheresFontOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(values.getFontSphere(), spheres.get(0).getFont());
        Assert.assertEquals(values.getFontSphere(), spheres.get(1).getFont());
        Assert.assertEquals(values.getFontSphere(), spheres.get(2).getFont());
        Assert.assertEquals("Kalam", spheres.get(3).getFont());
        Assert.assertEquals("Kalam", spheres.get(4).getFont());
    }

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the fontSize-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testSpheresFontSizeOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();
        Assert.assertEquals(10, spheres.get(0).getFontSize());
        Assert.assertEquals(10, spheres.get(1).getFontSize());
        Assert.assertEquals(10, spheres.get(2).getFontSize());
        Assert.assertEquals(24, spheres.get(3).getFontSize());
        Assert.assertEquals(24, spheres.get(4).getFontSize());
    }

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the font-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesFontOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals("Roboto", vertices.get(0).getFont());
        Assert.assertEquals("Roboto", vertices.get(1).getFont());
        Assert.assertEquals("Roboto", vertices.get(2).getFont());
        Assert.assertEquals("Roboto", vertices.get(3).getFont());
        Assert.assertEquals("Roboto", vertices.get(4).getFont());
        Assert.assertEquals("Roboto", vertices.get(5).getFont());
        Assert.assertEquals("Mali", vertices.get(6).getFont());
        Assert.assertEquals("Mali", vertices.get(7).getFont());
        Assert.assertEquals("Roboto", vertices.get(8).getFont());
    }

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the fontSize-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testVerticesFontSizeOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Vertex> vertices = new ArrayList<>(g.getVertices());
        vertices.sort(Comparator.comparingInt(Vertex::getId));
        Assert.assertEquals(12, vertices.get(0).getFontSize());
        Assert.assertEquals(12, vertices.get(1).getFontSize());
        Assert.assertEquals(12, vertices.get(2).getFontSize());
        Assert.assertEquals(12, vertices.get(3).getFontSize());
        Assert.assertEquals(12, vertices.get(4).getFontSize());
        Assert.assertEquals(7, vertices.get(5).getFontSize());
        Assert.assertEquals(14, vertices.get(6).getFontSize());
        Assert.assertEquals(14, vertices.get(7).getFontSize());
        Assert.assertEquals(7, vertices.get(8).getFontSize());
    }

    // <----------     sphere locking    ------------>

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the isLockedAnnotation-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the isLockedVertices-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
    @Test
    public void testSpheresLockedVerticesOfGraph() throws IOException, SAXException {
        prepareSyndrom(true).importGXL(new File(nameTestGraph), true);
        SyndromGraph<Vertex, Edge> g = (SyndromGraph<Vertex, Edge>) syndrom.getVv().getGraphLayout().getGraph();
        ArrayList<Sphere> spheres = (ArrayList<Sphere>) g.getSpheres();

        Assert.assertFalse(spheres.get(0).isLockedVertices());
        Assert.assertTrue(spheres.get(1).isLockedVertices());
        Assert.assertFalse(spheres.get(2).isLockedVertices());
        Assert.assertFalse(spheres.get(3).isLockedVertices());
        Assert.assertFalse(spheres.get(4).isLockedVertices());
    }

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the isLockedPosition-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the isLockedStyle-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the spheres of the graph that is created by importing the specified gxl file have the right value for the maxAmountVertices-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the isLockedStyle-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the isLockedAnnotaion-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the vertices of the graph that is created by importing the specified gxl file have the right value for the isLockedPosition-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the isLockedStyle-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the isLockedPosition-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the isLockedEdgeType-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the arrowType-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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

    /**
     * This method tests if the edges of the graph that is created by importing the specified gxl file have the right value for the stroke-attribute.
     *
     * @throws IOException  if the File can*t be created or the file that is specified for the import can't be found.
     * @throws SAXException if their occurs any problem parsing the document
     */
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
