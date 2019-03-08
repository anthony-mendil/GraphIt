package io;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import gui.properties.Language;
import lombok.Getter;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

/**
 * This class provides methods to exports a GXL-Representation from a graph
 * and to import a gxl document into the system and make an graph out of the document
 * and visualize this graph.
 */
public class GXLio {
    private static final String FILL_PAINT = "fillPaint";
    private static final String ANNOTATION = "annotation";
    private static final String U00A6 = "\u00A6";
    private static final String FONT_SIZE = "fontSize";
    private static final String IS_LOCKED_POSITION = "isLockedPosition";
    private static final String IS_LOCKED_ANNOTATION = "isLockedAnnotation";
    private static final String IS_LOCKED_STYLE = "isLockedStyle";
    private static final String IS_VISIBLE = "isVisible";
    private static final String IS_HIGHLIGHTED = "isHighlighted";
    private static final String ANCHORANGLE_OF_SOURCE = "anchorAngle of source";
    private static final String ANCHORANGLE_OF_TARGET = "anchorAngle of target";
    private static final String NAME_OF_GRAPH = "name of the graph";
    private static Logger logger = Logger.getLogger(GXLio.class);
    /**
     * The syndrom representation.
     */
    private Syndrom syndrom = Syndrom.getInstance();
    private Template template;
    /**
     * The highest id of all GXLAttributetElements in the gxl document that
     * is imported in the {@link #gxlToInstance(String, boolean)}-method.
     */
    private int maxID = -1;
    @Getter
    private boolean templateFoundFlag;

    /**
     * Constructor of class GXLio.
     * Creates a new GXLio object.
     */
    public GXLio() {
        // can handle gxl import/export now
    }


    /**
     * Writes the gxl representation from the graph that was created or edited
     * and currently is shown to the user in the system into a document.
     * As location of the document the file-attribute is used.
     *
     * @param pGXL         The GXL representation that gets written into syndrom.
     * @param withTemplate true if the import includes the template, false if not
     */
    public void gxlToInstance(String pGXL, boolean withTemplate) {
        try {
            // At first a document needs to be imported into the system. Thereby it is important that the document selected by the user has a valid structure.
            GXLDocument doc = new GXLDocument(new ByteArrayInputStream(pGXL.getBytes(StandardCharsets.UTF_8)));
            GXLGraph gxlGraph = (GXLGraph) doc.getElement("syndrom");
            GXLGraph gxlTemplate = (GXLGraph) doc.getElement("template");
            if (gxlGraph == null) {
                logger.error("Error on empty Syndrom GXLGraph");
                return;
            }
            if (gxlTemplate != null) {
                if (!withTemplate && !templateFoundFlag) {
                    logger.info("Template found but not used");
                    templateFoundFlag = true;
                    return;
                }
                templateFoundFlag = true;
            } else {
                if (withTemplate) {
                    logger.error("Error on empty Template GXLGraph");
                    return;
                }
            }
            // The list of all vertices descripted in the document. Elements are added to this list when ever a gxl element that describes a vertex was found reading the gxl document and the java object from this gxl description was created.
            List<Vertex> vertices = new ArrayList<>();
            // This list saves for each sphere the vertices that belong to the sphere (as a list of vertices).
            List<Map<Sphere, List<Vertex>>> spheresWithVertices = new ArrayList<>();
            // This list saves for each edge the source and the target vertex.
            List<Map<Edge, Pair<Vertex>>> edgeAndVertices = new ArrayList<>();
            // This counter is needed as the documents elements not always will have successive ids. This fact is a result from the users possibility to delete elements after creating them before he/she exports the graph. This leads to gaps in the row of ids.
            int foundElements = 0;
            if (withTemplate) {
                initializeTemplateValues(gxlTemplate);
            }
            int elementCount = gxlGraph.getGraphElementCount();

            for (int idCounter = 0; foundElements < elementCount; idCounter++) {
                if (doc.containsID(idCounter + "")) {
                    foundElements++;
                    GXLAttributedElement el = doc.getElement(idCounter + "");
                    if (((GXLString) el.getAttr("TYPE").getValue()).getValue().equals("Sphaere")) {
                        makeSphere(spheresWithVertices, el, withTemplate);
                    }
                }
            }

            foundElements = 0;

            for (int idCounter = 0; foundElements < elementCount; idCounter++) {
                if (doc.containsID(idCounter + "")) {
                    foundElements++;
                    makeGraphElemt(vertices, spheresWithVertices, edgeAndVertices, doc.getElement(idCounter + ""), withTemplate);
                    maxID = Math.max(maxID, idCounter);
                }
            }
            String graphName = ((GXLString) gxlGraph.getAttr(NAME_OF_GRAPH).getValue()).getValue();
            updateSystemDataAndVisualisation(spheresWithVertices, edgeAndVertices, graphName, withTemplate);
        } catch (IOException | SAXException e) {
            logger.error(e.toString());
        }
    }

    private void makeGraphElemt(List<Vertex> pVertices, List<Map<Sphere, List<Vertex>>> pSpheresWithVertices, List<Map<Edge, Pair<Vertex>>> pEdgeAndVertices, GXLAttributedElement pElem, boolean pWithTemplate) {
        switch (((GXLString) pElem.getAttr("TYPE").getValue()).getValue()) {
            case "Sphaere":
                break;
            case "Node":
                makeVertex(pVertices, pSpheresWithVertices, pElem, pWithTemplate);
                break;
            case "Edge":
                makeEdge(pVertices, pEdgeAndVertices, pElem, pWithTemplate);
                break;
            default:
                logger.error("Error on creating Graph Element");
                break;
        }
    }

    private void makeSphere(List<Map<Sphere, List<Vertex>>> pSpheresWithVertices, GXLAttributedElement pElem, boolean pWithTemplate) {
        Sphere newSphere = convertGXLElementToSphere(pElem, pWithTemplate);
        Map<Sphere, List<Vertex>> map = new HashMap<>();
        map.put(newSphere, new ArrayList<>());
        pSpheresWithVertices.add(map);
    }

    private void makeVertex(List<Vertex> pVertices, List<Map<Sphere, List<Vertex>>> pSpheresWithVertices, GXLAttributedElement pElem, boolean pWithTemplate) {
        Vertex newVertex = convertGXLElementToVertex(pElem, pWithTemplate);
        int sphereID = Integer.parseInt(((GXLString) pElem.getAttr("ID of the sphere containing this node:").getValue()).getValue());
        for (Map<Sphere, List<Vertex>> m : pSpheresWithVertices) {
            for (Map.Entry<Sphere, List<Vertex>> en : m.entrySet()) {
                if (en.getKey().getId() == sphereID) {
                    en.getValue().add(newVertex);
                }
            }
        }
        pVertices.add(newVertex);
    }

    private void makeEdge(List<Vertex> pVertices, List<Map<Edge, Pair<Vertex>>> pEdgeAndVertices, GXLAttributedElement pElem, boolean pWithTemplate) {
        Edge newEdge = convertGXLElemToEdge(pElem, pWithTemplate);
        // The edge generated from the description in the gxl document is add to the list of all edges together with its source and target vertex.
        GXLEdge currentEdge = (GXLEdge) pElem;
        int sourceID = Integer.parseInt(currentEdge.getSource().getID());
        int targetID = Integer.parseInt(currentEdge.getTarget().getID());
        Vertex source = null;
        Vertex target = null;
        for (Vertex v : pVertices) {
            if (v.getId() == sourceID) {
                source = v;
            } else if (v.getId() == targetID) {
                target = v;
            }
        }
        if (source == null || target == null) {
            logger.error("Error on creating Edge");
            return;
        }
        Pair<Vertex> pair = new Pair<>(source, target);
        Map<Edge, Pair<Vertex>> entry = new HashMap<>();
        entry.put(newEdge, pair);
        pEdgeAndVertices.add(entry);
    }

    private void updateSystemDataAndVisualisation(List<Map<Sphere, List<Vertex>>> spheresWithVertices, List<Map<Edge, Pair<Vertex>>> edgeAndVertices, String graphName, boolean withTemplate) {
        // Getting the objects that are needed to get the spheres, vertices and edges out of the lists into our system.
        syndrom.generateNew();
        if (withTemplate) {
            syndrom.setTemplate(template);
        } else {
            syndrom.setTemplate(new Template(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, true));
        }
        Layout<Vertex, Edge> layout = syndrom.getVv().getGraphLayout();
        SyndromGraph<Vertex, Edge> newGraph = (SyndromGraph<Vertex, Edge>) layout.getGraph();
        newGraph.getGraphObjectsFactory().setObjectCounter(++maxID);
        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        if (graphName != null) {
            syndrom.setGraphName(graphName);
        } else {
            syndrom.setGraphName("GraphIt");
        }
        updateSystemDataOfSpheresAndVertices(spheresWithVertices, newGraph, vv);
        updateSystemDataOfEdges(edgeAndVertices, newGraph);

        updateVisualisationAndLayout(newGraph, vv);
    }

    private void updateSystemDataOfSpheresAndVertices(List<Map<Sphere, List<Vertex>>> spheresWithVertices, SyndromGraph<Vertex, Edge> newGraph, SyndromVisualisationViewer<Vertex, Edge> vv) {
        for (Map<Sphere, List<Vertex>> m : spheresWithVertices) {
            for (Map.Entry<Sphere, List<Vertex>> e : m.entrySet()) {
                for (Vertex currentVertex : e.getValue()) {
                    e.getKey().getVertices().add(currentVertex);
                }
                // Adds the sphere to the graph.
                newGraph.getSpheres().add(e.getKey());
                for (Vertex v : e.getValue()) {
                    // Adds the vertex to the graph.
                    newGraph.addVertex(v);
                    vv.getGraphLayout().setLocation(v, v.getCoordinates());
                }
            }
        }
    }

    private void updateSystemDataOfEdges(List<Map<Edge, Pair<Vertex>>> edgeAndVertices, SyndromGraph<Vertex, Edge> newGraph) {
        for (Map<Edge, Pair<Vertex>> m : edgeAndVertices) {
            for (Map.Entry<Edge, Pair<Vertex>> e : m.entrySet()) {
                Edge edge = e.getKey();
                Pair<Vertex> endPoints = e.getValue();
                newGraph.addEdge(edge, endPoints.getFirst(), endPoints.getSecond());
            }
        }
    }

    private void updateVisualisationAndLayout(SyndromGraph<Vertex, Edge> newGraph, SyndromVisualisationViewer<Vertex, Edge> vv) {
        vv.getGraphLayout().setGraph(newGraph);
        vv.repaint();
        Syndrom.getInstance().getVv2().repaint();
    }

    /**
     * This method is called from the {@link #gxlToInstance(String, boolean)}-method, when the import is an import of a graph with its rules.
     *
     * @param gxlTemplate the GXLNode from the imported GXLGraph describing a template object
     */
    private void initializeTemplateValues(GXLGraph gxlTemplate) {
        int maxSpheres = ((GXLInt) gxlTemplate.getAttr("maxSpheres").getValue()).getIntValue();
        int maxVertices = ((GXLInt) gxlTemplate.getAttr("maxVertices").getValue()).getIntValue();
        int maxEdges = ((GXLInt) gxlTemplate.getAttr("maxEdges").getValue()).getIntValue();
        boolean reinforcedEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("reinforcedEdgesAllowed").getValue()).getBooleanValue();
        boolean unknownEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("unknownEdgesAllowed").getValue()).getBooleanValue();
        boolean extenuatingEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("extenuatingEdgesAllowed").getValue()).getBooleanValue();
        template = new Template(maxSpheres, maxVertices, maxEdges, reinforcedEdgesAllowed, extenuatingEdgesAllowed, unknownEdgesAllowed);
    }


    /**
     * This method is called from the {@link #gxlToInstance(String, boolean)}-method each time, when the gxl document is read and
     * an element representing a sphere object is found.
     * .
     * the current element that is read
     * by the gxl document
     * This method creates a new sphere object with the values from the passed GXLAttributedElement and returns this sphere.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Sphere]-class.
     *
     * @param elem         is a GXLAttributetElement that describes a sphere having the same atributes as an sphere.
     * @param withTemplate true it the import includes the template
     * @return a new sphere object with the values from the passed GXLAttributetElement object
     */
    private Sphere convertGXLElementToSphere(GXLAttributedElement elem, boolean withTemplate) {
        int id = Integer.parseInt(elem.getID());
        String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr(FILL_PAINT).getValue()).getValue());
        Color paint = new Color(
                Integer.parseInt(paintArray[0]),
                Integer.parseInt(paintArray[1]),
                Integer.parseInt(paintArray[2]),
                Integer.parseInt(paintArray[3]));
        String[] coordinatesArray = getNumberArrayFromString(((GXLString) elem.getAttr("coordinates").getValue()).getValue());
        java.awt.geom.Point2D coordinates = new java.awt.geom.Point2D.Float(
                java.lang.Float.parseFloat(coordinatesArray[0].substring(0, coordinatesArray[0].length() - 2)),
                java.lang.Float.parseFloat(coordinatesArray[1].substring(0, coordinatesArray[1].length() - 2)));
        double width = Double.parseDouble(((GXLString) elem.getAttr("width").getValue()).getValue());
        double height = Double.parseDouble(((GXLString) elem.getAttr("height").getValue()).getValue());
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), ((GXLString) elem.getAttr(ANNOTATION).getValue()).getValue().split(U00A6)[1]);
        annotation.put(Language.ENGLISH.name(), ((GXLString) elem.getAttr(ANNOTATION).getValue()).getValue().split(U00A6)[0]);
        String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
        int fontSize = ((GXLInt) elem.getAttr(FONT_SIZE).getValue()).getIntValue();
        Pair<Double> size = new Pair<>(width, height);
        Sphere newSphere = new Sphere(id, paint, coordinates, size, annotation, font, fontSize);
        if (withTemplate) {
            boolean isLockedPosition = ((GXLBool) elem.getAttr(IS_LOCKED_POSITION).getValue()).getBooleanValue();
            newSphere.setLockedPosition(isLockedPosition);
            boolean isLockedAnnotation = ((GXLBool) elem.getAttr(IS_LOCKED_ANNOTATION).getValue()).getBooleanValue();
            newSphere.setLockedAnnotation(isLockedAnnotation);
            boolean isLockedStyle = ((GXLBool) elem.getAttr(IS_LOCKED_STYLE).getValue()).getBooleanValue();
            newSphere.setLockedStyle(isLockedStyle);
            boolean isLockedVertices = ((GXLBool) elem.getAttr("isLockedVertices").getValue()).getBooleanValue();
            newSphere.setLockedVertices(isLockedVertices);
            String lockedMaxAmountVertices = ((GXLString) elem.getAttr("lockedMaxAmountVertices").getValue()).getValue();
            newSphere.setLockedMaxAmountVertices(lockedMaxAmountVertices);
        }
        return newSphere;
    }

    /**
     * This method is called from the {@link #gxlToInstance(String, boolean)}-method each time, when the gxl document is read and
     * an element representing a vertex object is found.
     * This method creates a new vertex object with the values from the passed GXLAttributedElement and returns this vertex.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Vertex]-class.
     *
     * @param elem         is a GXLAttributetElement that describes a vertex having the same atributes as an vertex.
     * @param withTemplate true if the import contains the template as well
     * @return a new vertex object with the values from the passed GXLAttributetElement object
     */
    private Vertex convertGXLElementToVertex(GXLAttributedElement elem, boolean withTemplate) {
        int id = Integer.parseInt(elem.getID());
        String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr(FILL_PAINT).getValue()).getValue());
        Color paint = new Color(
                Integer.parseInt(paintArray[0]),
                Integer.parseInt(paintArray[1]),
                Integer.parseInt(paintArray[2]),
                Integer.parseInt(paintArray[3]));
        String[] coordinatesArray = getNumberArrayFromString(((GXLString) elem.getAttr("coordinate").getValue()).getValue());
        java.awt.geom.Point2D.Float coordinates;
        coordinates = new java.awt.geom.Point2D.Float(
                java.lang.Float.parseFloat(coordinatesArray[0].substring(0, coordinatesArray[0].length() - 2)),
                java.lang.Float.parseFloat(coordinatesArray[1].trim().substring(0, coordinatesArray[1].length() - 3)));
        VertexShapeType shape = VertexShapeType.valueOf(((GXLString) elem.getAttr("shape").getValue()).getValue());
        Map<String, String> annotation = new HashMap<>();
        annotation.put(Language.GERMAN.name(), ((GXLString) elem.getAttr(ANNOTATION).getValue()).getValue().split(U00A6)[1]);
        annotation.put(Language.ENGLISH.name(), ((GXLString) elem.getAttr(ANNOTATION).getValue()).getValue().split(U00A6)[0]);
        String[] drawPaintArray = getNumberArrayFromString(((GXLString) elem.getAttr("drawPaint").getValue()).getValue());
        Color drawPaint = new Color(
                Integer.parseInt(drawPaintArray[0]),
                Integer.parseInt(drawPaintArray[1]),
                Integer.parseInt(drawPaintArray[2]),
                Integer.parseInt(drawPaintArray[3]));
        int size = ((GXLInt) elem.getAttr("size").getValue()).getIntValue();
        boolean isVisible = ((GXLBool) elem.getAttr(IS_VISIBLE).getValue()).getBooleanValue();
        String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
        int fontSize = ((GXLInt) elem.getAttr(FONT_SIZE).getValue()).getIntValue();
        boolean isHighlighted = ((GXLBool) elem.getAttr(IS_HIGHLIGHTED).getValue()).getBooleanValue();
        Pair<Color> colors = new Pair<>(paint, drawPaint);
        Pair<Integer> sizes = new Pair<>(size, fontSize);
        Vertex newVertex = new Vertex(id, colors, coordinates, shape, annotation, sizes, font);
        newVertex.setVisible(isVisible);
        newVertex.setHighlighted(isHighlighted);
        if (withTemplate) {
            boolean isLockedPosition = ((GXLBool) elem.getAttr(IS_LOCKED_POSITION).getValue()).getBooleanValue();
            newVertex.setLockedPosition(isLockedPosition);
            boolean isLockedAnnotation = ((GXLBool) elem.getAttr(IS_LOCKED_ANNOTATION).getValue()).getBooleanValue();
            newVertex.setLockedAnnotation(isLockedAnnotation);
            boolean isLockedStyle = ((GXLBool) elem.getAttr(IS_LOCKED_STYLE).getValue()).getBooleanValue();
            newVertex.setLockedStyle(isLockedStyle);
        }
        return newVertex;

    }

    /**
     * This method is called from the {@link #gxlToInstance(String, boolean)}-method each time, when the gxl document is read and
     * an element representing an edge object is found.
     * <p>
     * This method creates a new edge object with the values from the passed GXLAttributedElement and returns this edge.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Edge]-class.
     *
     * @param elem         is a GXLAttributetElement that describes an edge having the same atributes as an edge.
     * @param withTemplate true if the import contains the template rules
     * @return a new edge object with the values from the passed GXLAttributetElement object
     */
    private Edge convertGXLElemToEdge(GXLAttributedElement elem, boolean withTemplate) {
        int id = Integer.parseInt(elem.getID());
        String[] drawPaintArray = getNumberArrayFromString(((GXLString) elem.getAttr("paint").getValue()).getValue());
        Color paint = new Color(
                Integer.parseInt(drawPaintArray[0]),
                Integer.parseInt(drawPaintArray[1]),
                Integer.parseInt(drawPaintArray[2]),
                Integer.parseInt(drawPaintArray[3]));
        StrokeType stroke = StrokeType.valueOf(((GXLString) elem.getAttr("stroke").getValue()).getValue());
        EdgeArrowType arrowType = EdgeArrowType.valueOf(((GXLString) elem.getAttr("arrowType").getValue()).getValue());
        boolean hasAnchorIn = ((GXLBool) elem.getAttr("hasAnchorIn").getValue()).getBooleanValue();
        String[] coordinatesArraySource;
        java.awt.geom.Point2D coordinatesSource = null;
        if (!((GXLString) elem.getAttr(ANCHORANGLE_OF_SOURCE).getValue()).getValue().equals("no anchorpoint at the source set")) {
            coordinatesArraySource = getNumberArrayFromString(((GXLString) elem.getAttr(ANCHORANGLE_OF_SOURCE).getValue()).getValue());
            coordinatesSource = new java.awt.geom.Point2D.Double(
                    java.lang.Double.parseDouble(coordinatesArraySource[0]),
                    java.lang.Double.parseDouble(coordinatesArraySource[1]));
        }
        boolean hasAnchorOut = ((GXLBool) elem.getAttr("hasAnchorOut").getValue()).getBooleanValue();
        String[] coordinatesArrayTarget;
        java.awt.geom.Point2D coordinatesTarget = null;
        if (!((GXLString) elem.getAttr(ANCHORANGLE_OF_TARGET).getValue()).getValue().equals("no anchorpoint at the target set")) {
            coordinatesArrayTarget = getNumberArrayFromString(((GXLString) elem.getAttr(ANCHORANGLE_OF_TARGET).getValue()).getValue());
            coordinatesTarget = new java.awt.geom.Point2D.Double(
                    java.lang.Double.parseDouble(coordinatesArrayTarget[0]),
                    java.lang.Double.parseDouble(coordinatesArrayTarget[1]));
        }

        boolean isVisible = ((GXLBool) elem.getAttr(IS_VISIBLE).getValue()).getBooleanValue();
        Edge newEdge = new Edge(id, paint, stroke, arrowType, isVisible, hasAnchorIn, hasAnchorOut);
        javafx.util.Pair<java.awt.geom.Point2D, java.awt.geom.Point2D> endPoints = new javafx.util.Pair<>(coordinatesSource, coordinatesTarget);
        newEdge.setAnchorPoints(endPoints);
        boolean isHighlighted = ((GXLBool) elem.getAttr(IS_HIGHLIGHTED).getValue()).getBooleanValue();
        newEdge.setHighlighted(isHighlighted);
        if (withTemplate) {
            boolean isLockedStyle = ((GXLBool) elem.getAttr(IS_LOCKED_STYLE).getValue()).getBooleanValue();
            newEdge.setLockedStyle(isLockedStyle);
            boolean isLockedEdgeType = ((GXLBool) elem.getAttr("isLockedEdgeType").getValue()).getBooleanValue();
            newEdge.setLockedEdgeType(isLockedEdgeType);
        }
        return newEdge;


    }


    private static final Comparator<Sphere> sphereCompare = Comparator.comparingInt(Sphere::getId);
    private static final Comparator<Edge> edgeCompare = Comparator.comparingInt(Edge::getId);

    private static final Comparator<Vertex> vertexCompare = Comparator.comparingInt(Vertex::getId);


    /**
     * Extracts the graph from our syndrom and creates a gxl document for this graph.
     * The document is saved at the location specified by the file. This file is set when the
     * {@link #exportGXL(File, boolean)}-method is called.
     * To create the document the method extracts the elements from the graph (spheres, vertices and edges)
     * and saves them as GXLAttributedElements. In case of spheres and vertices this are GXLNodes object.
     * In case of edges this are GXLEdges. GXLNode and GXLEdge are subclassses from the GXLAttributetElement class.
     * The GXLAttributetElements have GXLAttr objects as childs. These childs describe the GXLAttributedElements.
     * This method gives back the contetn of the new created document as string.
     *
     * @param withTemplate true if the import contains the template rules
     * @return the content of the created document
     */
    public String gxlFromInstance(boolean withTemplate) {
        VisualizationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> theGraph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();
        currentSpheres.sort(sphereCompare);

        ByteArrayOutputStream gxlStream = new ByteArrayOutputStream();

        GXLDocument doc = new GXLDocument();
        GXLGraph gxlSyndrom = new GXLGraph("syndrom");
        if (syndrom.getGraphName() == null) {
            gxlSyndrom.setAttr(NAME_OF_GRAPH, new GXLString("UntitledGraph"));
        } else {
            gxlSyndrom.setAttr(NAME_OF_GRAPH, new GXLString(syndrom.getGraphName()));
        }
        for (Sphere s : currentSpheres) {

            gxlSyndrom.add(createSphereNode(s, withTemplate));
            doc.getDocumentElement().remove(gxlSyndrom);
            doc.getDocumentElement().add(gxlSyndrom);
        }
        for (Sphere s : currentSpheres) {
            List<Vertex> vertices = s.getVertices();
            vertices.sort(vertexCompare);
            for (Vertex v : vertices) {
                GXLNode singleNodeInSphere = createVertexNode(s, v, withTemplate);
                gxlSyndrom.add(singleNodeInSphere);
                doc.getDocumentElement().remove(gxlSyndrom);
                doc.getDocumentElement().add(gxlSyndrom);
            }
        }
        Collection<Edge> edges = theGraph.getEdges();
        ArrayList<Edge> edgesList = new ArrayList<>(edges);
        edgesList.sort(edgeCompare);
        for (Edge e : edgesList) {
            gxlSyndrom.add(createEdge(theGraph, e, withTemplate));
            doc.getDocumentElement().remove(gxlSyndrom);
            doc.getDocumentElement().add(gxlSyndrom);
        }
        doc.getDocumentElement().remove(gxlSyndrom);
        doc.getDocumentElement().add(gxlSyndrom);
        if (withTemplate) {
            GXLGraph templateNode = createTemplateNode();
            doc.getDocumentElement().add(templateNode);
        }


        String content = "";
        try {
            doc.getDanglingTentacleCount();
            doc.write(gxlStream);
            content = (gxlStream).toString("UTF-8");
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return content;
    }

    private GXLNode createSphereNode(Sphere s, boolean withTemplate) {
        GXLNode sphere = new GXLNode(s.getId() + "");
        sphere.setAttr("TYPE", new GXLString("Sphaere"));
        Color color = s.getColor();
        sphere.setAttr(FILL_PAINT, new GXLString(getPaintDescription(color)));
        sphere.setAttr("coordinates", new GXLString("" + s.getCoordinates().toString()));
        sphere.setAttr("width", new GXLString("" + s.getWidth()));
        sphere.setAttr("height", new GXLString("" + s.getHeight()));
        StringBuilder annotationContent = new StringBuilder();
        for (String partOfAnnotation : s.getAnnotation().keySet()) {
            annotationContent.append(s.getAnnotation().get(partOfAnnotation));
            annotationContent.append(U00A6);
        }

        sphere.setAttr(ANNOTATION, new GXLString(annotationContent.toString()));
        sphere.setAttr("font", new GXLString(s.getFont()));
        sphere.setAttr(FONT_SIZE, new GXLInt(s.getFontSize()));
        StringBuilder nodeIDs = new StringBuilder();
        int numberOfCurrentNode = 0;
        for (Vertex v : s.getVertices()) {
            nodeIDs.append(v.getId());
            numberOfCurrentNode++;
            if (numberOfCurrentNode < s.getVertices().size()) {
                nodeIDs.append(", ");
            }
        }
        if (withTemplate) {
            addRulesToSphere(s, sphere);
        }
        sphere.setAttr("IDs of nodes containt in this sphere: ", new GXLString(nodeIDs.toString()));
        return sphere;
    }

    /**
     * This method is called from the {@link #gxlFromInstance(boolean)}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     * <p>
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param sphere    the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlSphere the GXLAttributedElement still not having the neccessary attributes.
     */
    private void addRulesToSphere(Sphere sphere, GXLNode gxlSphere) {
        gxlSphere.setAttr(IS_LOCKED_POSITION, new GXLBool(sphere.isLockedPosition()));
        gxlSphere.setAttr(IS_LOCKED_ANNOTATION, new GXLBool(sphere.isLockedAnnotation()));
        gxlSphere.setAttr(IS_LOCKED_STYLE, new GXLBool(sphere.isLockedStyle()));
        gxlSphere.setAttr("isLockedVertices", new GXLBool(sphere.isLockedVertices()));
        gxlSphere.setAttr("lockedMaxAmountVertices", new GXLString(sphere.getLockedMaxAmountVertices()));
    }

    private GXLNode createVertexNode(Sphere s, Vertex v, boolean withTemplate) {
        GXLNode singleNodeInSphere = new GXLNode(v.getId() + "");
        singleNodeInSphere.setAttr("TYPE", new GXLString("Node"));
        Color color = v.getFillColor();
        singleNodeInSphere.setAttr(FILL_PAINT, new GXLString(getPaintDescription(color)));
        singleNodeInSphere.setAttr("coordinate", new GXLString("" + v.getCoordinates().toString()));
        singleNodeInSphere.setAttr("shape", new GXLString("" + v.getShape()));
        StringBuilder nodeAnnotationContent = new StringBuilder();
        for (String partOfNodeAnnotation : v.getAnnotation().keySet()) {
            nodeAnnotationContent.append(v.getAnnotation().get(partOfNodeAnnotation));
            nodeAnnotationContent.append(U00A6);
        }

        singleNodeInSphere.setAttr(ANNOTATION, new GXLString(nodeAnnotationContent.toString()));
        Color drawPaint = v.getDrawColor();
        singleNodeInSphere.setAttr("drawPaint", new GXLString(getPaintDescription(drawPaint)));
        singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString("" + v.getVertexArrowReinforced()));
        singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString("" + v.getVertexArrowNeutral()));
        singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString("" + v.getVertexArrowExtenuating()));

        singleNodeInSphere.setAttr("size", new GXLInt(v.getSize()));
        singleNodeInSphere.setAttr(IS_VISIBLE, new GXLBool(v.isVisible()));
        singleNodeInSphere.setAttr("font", new GXLString("" + v.getFont()));
        singleNodeInSphere.setAttr(FONT_SIZE, new GXLInt(v.getFontSize()));
        singleNodeInSphere.setAttr(IS_HIGHLIGHTED, new GXLBool(v.isHighlighted()));
        singleNodeInSphere.setAttr("ID of the sphere containing this node:", new GXLString("" + s.getId()));
        if (withTemplate) {
            addRulesToNode(v, singleNodeInSphere);
        }
        return singleNodeInSphere;
    }

    /**
     * This method is called from the {@link #gxlFromInstance(boolean)}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     * <p>
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param vertex  the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlNode the GXLAttributedElement still not having the neccessary attributes.
     */
    private void addRulesToNode(Vertex vertex, GXLNode gxlNode) {
        gxlNode.setAttr(IS_LOCKED_POSITION, new GXLBool(vertex.isLockedPosition()));
        gxlNode.setAttr(IS_LOCKED_ANNOTATION, new GXLBool(vertex.isLockedAnnotation()));
        gxlNode.setAttr(IS_LOCKED_STYLE, new GXLBool(vertex.isLockedStyle()));
    }

    private GXLEdge createEdge(SyndromGraph<Vertex, Edge> theGraph, Edge e, boolean withTemplate) {
        Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
        GXLEdge edge = new GXLEdge(verticesOfEdge.getFirst().getId() + "", verticesOfEdge.getSecond().getId() + "");
        edge.setID(e.getId() + "");
        edge.setAttr("TYPE", new GXLString("Edge"));
        Color color = e.getColor();
        edge.setAttr("paint", new GXLString(getPaintDescription(color)));
        edge.setAttr("stroke", new GXLString("" + e.getStroke()));
        edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));

        edge.setAttr("hasAnchorIn", new GXLBool(e.isHasAnchorIn()));
        if (e.getAnchorPoints().getKey() == null) {
            edge.setAttr(ANCHORANGLE_OF_SOURCE, new GXLString("no anchorpoint at the source set"));
        } else {
            edge.setAttr(ANCHORANGLE_OF_SOURCE, new GXLString("" + e.getAnchorPoints().getKey()));
        }

        edge.setAttr("hasAnchorOut", new GXLBool(e.isHasAnchorOut()));
        if (e.getAnchorPoints().getValue() == null) {
            edge.setAttr(ANCHORANGLE_OF_TARGET, new GXLString("no anchorpoint at the target set"));
        } else {
            edge.setAttr(ANCHORANGLE_OF_TARGET, new GXLString("" + e.getAnchorPoints().getValue()));
        }

        edge.setAttr(IS_VISIBLE, new GXLBool(e.isVisible()));
        edge.setAttr(IS_HIGHLIGHTED, new GXLBool(e.isHighlighted()));
        if (withTemplate) {
            addRulesToEdge(e, edge);
        }
        return edge;
    }

    /**
     * This method is called from the {@link #gxlFromInstance(boolean)}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     * <p>
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param edge    the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlEdge the GXLAttributedElement still not having the neccessary attributes.
     */
    private void addRulesToEdge(Edge edge, GXLEdge gxlEdge) {
        gxlEdge.setAttr(IS_LOCKED_STYLE, new GXLBool(edge.isLockedStyle()));
        gxlEdge.setAttr("isLockedEdgeType", new GXLBool(edge.isLockedEdgeType()));
    }

    /**
     * This method is called from the {@link #gxlFromInstance(boolean)}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     * <p>
     * Creates a new GXLNode that descriped a template object
     * having some of the attributes a template object has and returns it.
     *
     * @return the GXLNode representing the template object.
     */
    private GXLGraph createTemplateNode() {
        GXLGraph templateNode = new GXLGraph("template");
        Template templ = Syndrom.getInstance().getTemplate();
        templateNode.setAttr("maxSpheres", new GXLInt(templ.getMaxSpheres()));
        templateNode.setAttr("maxVertices", new GXLInt(templ.getMaxVertices()));
        templateNode.setAttr("maxEdges", new GXLInt(templ.getMaxEdges()));
        templateNode.setAttr("reinforcedEdgesAllowed", new GXLBool(templ.isReinforcedEdgesAllowed()));
        templateNode.setAttr("unknownEdgesAllowed", new GXLBool(templ.isNeutralEdgesAllowed()));
        templateNode.setAttr("extenuatingEdgesAllowed", new GXLBool(templ.isExtenuatingEdgesAllowed()));
        return templateNode;
    }

    /**
     * Forms a description of a color.
     *
     * @param color the color that need to be describted
     * @return the description of the color as a String
     */
    String getPaintDescription(Color color) {
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
    private String[] getNumberArrayFromString(String pWord) {
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


    /**
     * Save the GXL representation to a specific location.
     *
     * @param pFile            The destination File
     * @param pExportWithRules true if the export contains the template rules as well
     */
    public void exportGXL(File pFile, boolean pExportWithRules) {
        String gxl = gxlFromInstance(pExportWithRules);
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pFile), StandardCharsets.UTF_8)) {
            writer.write(gxl);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Import a GXL file from a specific location to the syndrom
     *
     * @param pFile            The File to import
     * @param pImportWithRules true if the import contains the template rules
     */
    public void importGXL(File pFile, boolean pImportWithRules) {
        String gxl = FileHandler.fileToString(pFile);
        gxlToInstance(gxl, pImportWithRules);
    }
}
