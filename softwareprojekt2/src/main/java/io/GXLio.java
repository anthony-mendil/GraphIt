package io;

import com.google.inject.Inject;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import log_management.dao.GraphDao;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class provides methods to exports a GXL-Representation from a graph
 * and to import a gxl document into the system and make an graph out of the document
 * and visualize this graph.
 */
public class GXLio {
    /**
     * The syndrom representation.
     */
    private Syndrom syndrom = Syndrom.getInstance();

    /**
     * The graph dao object, for accessing the graph data.
     */
    @Inject
    private GraphDao graphDao;

    /**
     * The highest id of all GXLAttributetElements in the gxl document that is imported in the {@gxlToInstance()}-method.
     */
    int maxID = -1;

    /**
     * Specifies rather an export is just the export of a graph or a graph with editing rules.
     */
    boolean exportWithRules = false;

    /**
     * Specifies rather an import is just the import of a graph or a graph with editing rules.
     */
    boolean importWithRules = false;

    private static Logger logger = Logger.getLogger(GXLio.class);

    /**
     * Constructor of class GXLio.
     * Creates a new GXLio object.
     */
    public GXLio() {
        ;
    }

    /**
     * Writes the gxl representation from the graph that was created or edited
     * and currently is shown to the user in the system into a document.
     * As location of the document the file-attribut is used.
     * Whose value is set when the {@importGXL}-method is called.
     *
     * @param pGXL The GXL representation that gets written into syndrom.
     */
    public void gxlToInstance(String pGXL) {
        File tmpGXL = new File("gxl");
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmpGXL))){
            bufferedWriter.write(pGXL);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        try {
            // At first a document needs to be imported into the system.
            // Thereby it is important that the document selected by the user has a valid structure.
            GXLDocument doc = new GXLDocument(tmpGXL);
            // The only gxl graph in documents that are created with or system is called "syndrom". See the {@gxlFromInstance()}-method in this class.
            GXLGraph gxlGraph = (GXLGraph) doc.getElement("syndrom");
            if (gxlGraph == null) {
                System.out.println("Das Dokument ist leer!");
            }
            // The list of all vertices descripted in the document.
            // Elements are added to this list when ever a gxl element that descripes a vertex was found
            // and the java object from this gxl description was created.
            List<Vertex> vertices = new ArrayList<>();
            // This list saves for each sphere the vertices that belong to the sphere (as a list).
            List<Map<Sphere, List<Vertex>>> list = new ArrayList<>();
            // This list saves for each edge the source and the target vertex.
            List<Map<Edge, Pair<Vertex>>> edgeAndVertices = new ArrayList<>();
            // This counter is needed as the documents elements not always will have successive ids.
            // This fact is a result from the users possibility to delete elements after creating them
            // befor he/she exports the graph. This leads to gaps in the row of ids.
            int idCounter = 0;

            for (int i = 0; i < gxlGraph.getGraphElementCount(); i++) {
                if (doc.containsID(idCounter + "")) {
                    GXLAttributedElement elem = doc.getElement(idCounter + "");
                    if (maxID < Integer.parseInt(elem.getID())) {
                        maxID = Integer.parseInt(elem.getID());
                    }
                    // Checks if the current element is a sphere.
                    if (((GXLString) elem.getAttr("TYPE").getValue()).getValue().equals("Sphäre")) {
                        Sphere newSphere = convertGXLElementToSphere(elem);
                        // The sphere generated from the description in the gxl document is add to the list
                        // of all spheres at the moment with an empty list of vertices that belong to the sphere
                        Map<Sphere, List<Vertex>> map = new HashMap<>();
                        map.put(newSphere, new ArrayList<>());
                        list.add(map);
                        // Checks if the current element is a vertex.
                    } else if (((GXLString) elem.getAttr("TYPE").getValue()).getValue().equals("Node")) {
                        Vertex newVertex = convertGXLElementToVertex(elem);
                        // The vertex generated from the description in the gxl document is add to the list
                        // of vertices of the sphere that this vertex belongs to.
                        Integer sphereID = Integer.parseInt(((GXLString) elem.getAttr("ID of the sphere containing this node:").getValue()).getValue());
                        for (Map<Sphere, List<Vertex>> m : list) {
                            for (Map.Entry<Sphere, List<Vertex>> en : m.entrySet()) {
                                if (en.getKey().getId() == sphereID) {
                                    en.getValue().add(newVertex);
                                }
                            }
                        }
                        // The vertex is added to the list of all vertices.
                        // his is neccessa. (see else.paragraph)ry to be able to find the source and target vertex of an edge
                        vertices.add(newVertex);
                        // If the element is weither a sphere nor a vertex it has to be an edge.
                    } else {
                        Edge newEdge = convertGXLElemToEdge(elem);
                        // The edge generated from the description in the gxl document is add to the list
                        // of all edges together with its source and target vertex.
                        GXLEdge currentEdge = (GXLEdge) elem;
                        int sourceID = Integer.parseInt(currentEdge.getSource().getID());
                        int targetID = Integer.parseInt(currentEdge.getTarget().getID());
                        Vertex source = null;
                        Vertex target = null;
                        for (Vertex v : vertices) {
                            if (v.getId() == sourceID) {
                                source = v;
                            } else if (v.getId() == targetID) {
                                target = v;
                            }
                        }
                        // The source vertex is the first element of the pair and the target vertex the second element.
                        Pair<Vertex> pair = new Pair<>(source, target);
                        Map<Edge, Pair<Vertex>> entry = new HashMap<>();
                        entry.put(newEdge, pair);
                        edgeAndVertices.add(entry);
                    }
                }
                idCounter++;
            }

            if(importWithRules) {
                GXLNode gxlTemplate = (GXLNode) doc.getElement("template");
                initializeTemplateValues(gxlTemplate);
            }

            // Getting the objects that are needed to get the spheres, vertices and edges
            // out of the lists into or system.
            syndrom.generateNew();
            Layout<Vertex, Edge> layout = syndrom.getVv().getGraphLayout();
            SyndromGraph<Vertex, Edge> newGraph = (SyndromGraph<Vertex, Edge>) layout.getGraph();
            newGraph.getGraphObjectsFactory().setObjectCounter(++maxID);
            SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();

            for (Map<Sphere, List<Vertex>> m : list) {
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
            for (Map<Edge, Pair<Vertex>> m : edgeAndVertices) {
                for (Map.Entry<Edge, Pair<Vertex>> e : m.entrySet()) {
                    Edge edge = e.getKey();
                    Pair<Vertex> endPoints = e.getValue();
                    // Adds the edge to the graph.
                    newGraph.addEdge(edge, endPoints.getFirst(), endPoints.getSecond());
                }
            }
            // Paints the graph with the elements imported from the gxl document.

            Syndrom.getInstance().getLayout().setGraph(newGraph);
            Syndrom.getInstance().setGraph(newGraph);
            vv.getGraphLayout().setGraph(newGraph);
            vv.repaint();
            Syndrom.getInstance().getVv2().repaint();

        } catch (IOException e) {
            logger.error(e.toString());
        } catch (SAXException e) {
            logger.error(e.toString());
        }
        tmpGXL.deleteOnExit();
    }



    /**
     * This method is called from the {@gxlToInstance()}-method, when the import is an import of a graph with its rules.
     *
     * @param gxlTemplate the GXLNode from the imported GXLGraph describing a template object
     */
    private void initializeTemplateValues(GXLNode gxlTemplate){
        Template template = Syndrom.getInstance().getTemplate();
        int maxSpheres = ((GXLInt) gxlTemplate.getAttr("maxSpheres").getValue()).getIntValue();
        template.setMaxSpheres(maxSpheres);
        int maxVertices = ((GXLInt) gxlTemplate.getAttr("maxVertices").getValue()).getIntValue();
        template.setMaxVertices(maxVertices);
        int maxEdges = ((GXLInt) gxlTemplate.getAttr("maxEdges").getValue()).getIntValue();
        template.setMaxEdges(maxEdges);
        int maxVerticesInSphere = ((GXLInt) gxlTemplate.getAttr("maxVerticesInSphere").getValue()).getIntValue();
        template.setMaxVerticesInSphere(maxVerticesInSphere);
        boolean reinforcedEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("reinforcedEdgesAllowed").getValue()).getBooleanValue();
        template.setReinforcedEdgesAllowed(reinforcedEdgesAllowed);
        boolean unknownEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("unknownEdgesAllowed").getValue()).getBooleanValue();
        template.setUnknownEdgesAllowed(unknownEdgesAllowed);
        boolean extenuatingEdgesAllowed = ((GXLBool) gxlTemplate.getAttr("extenuatingEdgesAllowed").getValue()).getBooleanValue();
        template.setExtenuatingEdgesAllowed(extenuatingEdgesAllowed);
    }


    /**
     * This method is called from the {@gxlToInstance()}-method each time, when the gxl document is read and
     * an element representing a sphere object is found.
     *.
     * the current element that is read
     * by the gxl document
     * This method creates a new sphere object with the values from the passed GXLAttributedElement and returns this sphere.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Sphere]-class.
     *
     * @param elem is a GXLAttributetElement that describes a sphere having the same atributes as an sphere.
     * @return a new sphere object with the values from the passed GXLAttributetElement object
     */
    private Sphere convertGXLElementToSphere(GXLAttributedElement elem) {
        int id = Integer.parseInt(elem.getID());
        System.out.println("id: " + id);
        String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr("fillPaint").getValue()).getValue());
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
        annotation.put("de", ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[0]);
        annotation.put("en+-", ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[1]);
        String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
        int fontSize = ((GXLInt) elem.getAttr("fontSize").getValue()).getIntValue();
        Sphere newSphere = new Sphere(id, paint, coordinates, width, height, annotation, font, fontSize);
        if (importWithRules == true) {
            boolean isLockedPosition = ((GXLBool) elem.getAttr("isLockedPosition").getValue()).getBooleanValue();
            newSphere.setLockedPosition(isLockedPosition);
            boolean isLockedAnnotation = ((GXLBool) elem.getAttr("isLockedAnnotation").getValue()).getBooleanValue();
            newSphere.setLockedAnnotation(isLockedAnnotation);
            boolean isLockedStyle = ((GXLBool) elem.getAttr("isLockedStyle").getValue()).getBooleanValue();
            newSphere.setLockedStyle(isLockedStyle);
            boolean isLockedVertices = ((GXLBool) elem.getAttr("isLockedVertices").getValue()).getBooleanValue();
            newSphere.setLockedVertices(isLockedVertices);
        }
        return newSphere;
    }

    /**
     * This method is called from the {@gxlToInstance()}-method each time, when the gxl document is read and
     * an element representing a vertex object is found.
     * This method creates a new vertex object with the values from the passed GXLAttributedElement and returns this vertex.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Vertex]-class.
     *
     * @param elem is a GXLAttributetElement that describes a vertex having the same atributes as an vertex.
     * @return a new vertex object with the values from the passed GXLAttributetElement object
     */
    private Vertex convertGXLElementToVertex(GXLAttributedElement elem) {
        int id = Integer.parseInt(elem.getID());
        String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr("fillPaint").getValue()).getValue());
        Color paint = new Color(
                Integer.parseInt(paintArray[0]),
                Integer.parseInt(paintArray[1]),
                Integer.parseInt(paintArray[2]),
                Integer.parseInt(paintArray[3]));
        String[] coordinatesArray = getNumberArrayFromString(((GXLString) elem.getAttr("coordinate").getValue()).getValue());
        java.awt.Point coordinates = null;
        if (coordinatesArray[0].contains(".")) {
            System.out.println(coordinatesArray[0]);
            System.out.println(coordinatesArray[1]);
            coordinates = new java.awt.Point(
                    (int) java.lang.Double.parseDouble(coordinatesArray[0].substring(0, coordinatesArray[0].length() - 2)),
                    (int) java.lang.Double.parseDouble(coordinatesArray[1].trim().substring(0, coordinatesArray[1].length() - 3)));
        } else {
            coordinates = new java.awt.Point(
                    java.lang.Integer.parseInt(coordinatesArray[0]),
                    java.lang.Integer.parseInt(coordinatesArray[1]));
        }
        VertexShapeType shape = VertexShapeType.valueOf(((GXLString) elem.getAttr("shape").getValue()).getValue());
        Map<String, String> annotation = new HashMap<String, String>();
        annotation.put("de", ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[0]);
        annotation.put("en", ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[1]);
        String[] drawPaintArray = getNumberArrayFromString(((GXLString) elem.getAttr("drawPaint").getValue()).getValue());
        Color drawPaint = new Color(
                Integer.parseInt(drawPaintArray[0]),
                Integer.parseInt(drawPaintArray[1]),
                Integer.parseInt(drawPaintArray[2]),
                Integer.parseInt(drawPaintArray[3]));
        String vertexArrowReinforced = ((GXLString) elem.getAttr("vertexArrowReinforced").getValue()).getValue();
        String vertexArrowNeutral = ((GXLString) elem.getAttr("vertexArrowNeutral").getValue()).getValue();
        String vertexArrowExtenuating = ((GXLString) elem.getAttr("vertexArrowExtenuating").getValue()).getValue();
        int size = ((GXLInt) elem.getAttr("size").getValue()).getIntValue();
        boolean isVisible = ((GXLBool) elem.getAttr("isVisible").getValue()).getBooleanValue();
        String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
        int fontSize = ((GXLInt) elem.getAttr("fontSize").getValue()).getIntValue();
        Vertex newVertex = new Vertex(id, paint, coordinates, shape, annotation, drawPaint, size, font, fontSize);
        newVertex.setVisible(isVisible);
        if (importWithRules == true) {
            boolean isLockedPosition = ((GXLBool) elem.getAttr("isLockedPosition").getValue()).getBooleanValue();
            newVertex.setLockedPosition(isLockedPosition);
            boolean isLockedAnnotation = ((GXLBool) elem.getAttr("isLockedAnnotation").getValue()).getBooleanValue();
            newVertex.setLockedAnnotation(isLockedAnnotation);
            boolean isLockedStyle = ((GXLBool) elem.getAttr("isLockedStyle").getValue()).getBooleanValue();
            newVertex.setLockedStyle(isLockedStyle);
        }
        return newVertex;

    }

    /**
     * This method is called from the {@gxlToInstance()}-method each time, when the gxl document is read and
     * an element representing an edge object is found.
     *
     * This method creates a new edge object with the values from the passed GXLAttributedElement and returns this edge.
     * Therefore it declares and initialises local variables and passes them to the constructor of the [@graph.Edge]-class.
     *
     * @param elem is a GXLAttributetElement that describes an edge having the same atributes as an edge.
     * @return a new edge object with the values from the passed GXLAttributetElement object
     */
    private Edge convertGXLElemToEdge(GXLAttributedElement elem) {
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
        String[] coordinatesArraySource = null;
        java.awt.geom.Point2D coordinatesSource = null;
        if (!((GXLString) elem.getAttr("anchorAngle of source").getValue()).getValue().equals("no anchorpoint at the source set")) {
            coordinatesArraySource = getNumberArrayFromString(((GXLString) elem.getAttr("anchorAngle of source").getValue()).getValue());
            coordinatesSource = new java.awt.geom.Point2D.Double(
                    java.lang.Double.parseDouble(coordinatesArraySource[0]),
                    java.lang.Double.parseDouble(coordinatesArraySource[1]));
        }
        boolean hasAnchorOut = ((GXLBool) elem.getAttr("hasAnchorOut").getValue()).getBooleanValue();
        String[] coordinatesArrayTarget = null;
        java.awt.geom.Point2D coordinatesTarget = null;
        if (!((GXLString) elem.getAttr("anchorAngle of source").getValue()).getValue().equals("no anchorpoint at the target set")) {
            coordinatesArrayTarget = getNumberArrayFromString(((GXLString) elem.getAttr("anchorAngle of target").getValue()).getValue());
            coordinatesTarget = new java.awt.geom.Point2D.Double(
                    java.lang.Double.parseDouble(coordinatesArrayTarget[0]),
                    java.lang.Double.parseDouble(coordinatesArrayTarget[1]));
        }

        boolean isVisible = ((GXLBool) elem.getAttr("isVisible").getValue()).getBooleanValue();
        Edge newEdge = new Edge(id, paint, stroke, arrowType, isVisible, hasAnchorIn, hasAnchorOut);
        newEdge.setHasAnchorIn(hasAnchorIn);
        javafx.util.Pair<java.awt.geom.Point2D, java.awt.geom.Point2D> endPoints = new  javafx.util.Pair<>(coordinatesSource, coordinatesTarget);
        newEdge.setAnchorPoints(endPoints);
        if (importWithRules == true) {
            boolean isLockedStyle = ((GXLBool) elem.getAttr("isLockedStyle").getValue()).getBooleanValue();
            newEdge.setLockedStyle(isLockedStyle);
            boolean isLockedEdgeType = ((GXLBool) elem.getAttr("isLockedEdgeType").getValue()).getBooleanValue();
            newEdge.setLockedStyle(isLockedEdgeType);

        }
        return newEdge;


    }


    /**
     * Extracts the graph from our syndrom and creates a gxl document for this graph.
     * The document is saved at the location specified by the file. This file is set when the
     * {@exportGXL}-method is called.
     * To create the document the method extracts the elements from the graph (spheres, vertices and edges)
     * and saves them as GXLAttributedElements. In case of spheres and vertices this are GXLNodes object.
     * In case of edges this are GXLEdges. GXLNode and GXLEdge are subclassses from the GXLAttributetElement class.
     * The GXLAttributetElements have GXLAttr objects as childs. These childs describe the GXLAttributedElements.
     * This method gives back the contetn of the new created document as string.
     *
     * @return the content of the created document
     */
     public String gxlFromInstance() {
        VisualizationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> theGraph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();

        File tmpGXL = new File("tmp.gxl");

        GXLDocument doc = new GXLDocument();
        GXLIDGenerator idGenerator = new GXLIDGenerator(doc);
        List<GXLNode> myNodes = new ArrayList<>();
        GXLGraph gxlSyndrom = new GXLGraph("syndrom");
        for (Sphere s : currentSpheres) {
            GXLNode sphere = new GXLNode(s.getId() + "");
            sphere.setAttr("TYPE", new GXLString("Sphäre"));
            Color color = s.getColor();
            sphere.setAttr("fillPaint", new GXLString(getPaintDescription(color)));
            sphere.setAttr("coordinates", new GXLString("" + s.getCoordinates().toString()));
            sphere.setAttr("width", new GXLString("" + s.getWidth()));
            sphere.setAttr("height", new GXLString("" + s.getHeight()));
            String annotationContent = "";
            if (s.getAnnotation() != null) {
                for (String partOfAnnotation : s.getAnnotation().keySet()) {
                    annotationContent = annotationContent + s.getAnnotation().get(partOfAnnotation) + "\u00A6";
                }
            }
            sphere.setAttr("annotation", new GXLString(annotationContent));
            sphere.setAttr("font", new GXLString(s.getFont()));
            sphere.setAttr("fontSize", new GXLString("" + s.getFontSize()));
            String nodeIDs = "";
            int numberOfCurrentNode = 0;
            for (Vertex v : s.getVertices()) {
                nodeIDs = nodeIDs + v.getId();
                numberOfCurrentNode++;
                if (numberOfCurrentNode < s.getVertices().size()) {
                    nodeIDs = nodeIDs + ", ";
                }
            }
            sphere.setAttr("IDs of nodes containt in this shpere: ", new GXLString(nodeIDs));
            gxlSyndrom.add(sphere);
        }
        for (Sphere s : currentSpheres) {
            // GXLRel rel = new GXLRel();
            for (Vertex v : s.getVertices()) {
                GXLNode singleNodeInSphere = new GXLNode(v.getId() + "");
                //  sphere.add(singleNodeInSphere);
                gxlSyndrom.add(singleNodeInSphere);

                // rel.add(new GXLRelend(singleNodeInSphere));
                singleNodeInSphere.setAttr("TYPE", new GXLString("Node"));
                Color color = v.getFillColor();
                singleNodeInSphere.setAttr("fillPaint", new GXLString(getPaintDescription(color)));
                singleNodeInSphere.setAttr("coordinate", new GXLString("" + v.getCoordinates().toString()));
                singleNodeInSphere.setAttr("shape", new GXLString("" + v.getShape()));
                String nodeAnnotationContent = "";
                if (v.getAnnotation() != null) {
                    for (String partOfNodeAnnotation : v.getAnnotation().keySet()) {
                        nodeAnnotationContent = nodeAnnotationContent + v.getAnnotation().get(partOfNodeAnnotation) + "\u00A6";
                    }
                }
                singleNodeInSphere.setAttr("annotation", new GXLString(nodeAnnotationContent));
                Color drawPaint = (Color) v.getDrawColor();
                singleNodeInSphere.setAttr("drawPaint", new GXLString(getPaintDescription(drawPaint)));
                if (v.getVertexArrowReinforced() != null) {
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString("" + v.getVertexArrowReinforced()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString(""));
                }
                if (v.getVertexArrowNeutral() != null) {
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString("" + v.getVertexArrowNeutral()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString(""));
                }
                if (v.getVertexArrowExtenuating() != null) {
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString("" + v.getVertexArrowExtenuating()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString(""));
                }
                singleNodeInSphere.setAttr("size", new GXLString("" + v.getSize()));
                singleNodeInSphere.setAttr("isVisible", new GXLString("" + v.isVisible()));
                singleNodeInSphere.setAttr("font", new GXLString("" + v.getFont()));
                singleNodeInSphere.setAttr("fontSize", new GXLString("" + v.getFontSize()));
                singleNodeInSphere.setAttr("ID of the sphere containing this node:", new GXLString("" + s.getId()));
                myNodes.add(singleNodeInSphere);
            }
        }
        for (Edge e : theGraph.getEdges()) {
            Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
            GXLEdge edge = new GXLEdge(verticesOfEdge.getFirst().getId() + "", verticesOfEdge.getSecond().getId() + "");
            edge.setID(e.getId() + "");
            edge.setAttr("TYPE", new GXLString("Edge"));
            Color color = (Color) e.getColor();
            edge.setAttr("paint", new GXLString(getPaintDescription(color)));
            edge.setAttr("stroke", new GXLString("" + e.getStroke()));
            edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));

            edge.setAttr("hasAnchor", new GXLString("" + e.isHasAnchorOut()));
            if(e.isHasAnchorOut()) {
                edge.setAttr("anchorAngle", new GXLString("" + e.getAnchorPoints().getValue()));
            }
            edge.setAttr("isVisible", new GXLString("" + e.isVisible()));
            gxlSyndrom.add(edge);
        }

        doc.getDocumentElement().add(gxlSyndrom);

        String content = "";
        try {
            doc.write(tmpGXL);

            // reading the content of the created gxl document and write its content into a String
            try (BufferedReader reader = new BufferedReader(new FileReader(tmpGXL));) {
                Stream<String> lines = reader.lines();
                Object[] linesArray = lines.toArray();
                for (Object s : linesArray) {
                    String objectContetnt = (String) s;
                    content = content + "\n" + objectContetnt;
                }
            } catch (FileNotFoundException e) {
                logger.error(e.toString());
            } catch (IOException e) {
                logger.error(e.toString());
            }
        } catch (Exception e) {
            System.out.println("error");
            logger.error(e.toString());
        }
       // System.out.println(content);
        tmpGXL.deleteOnExit();
        return content;
    }


    /**
     * Extracts the graph from our syndrom and creates a gxl document for this graph.
     * The document is saved at the location specified by the file. This file is set when the
     * {@exportGXL}-method is called.
     * To create the document the method extracts the elements from the graph (spheres, vertices and edges)
     * and saves them as GXLAttributedElements. In case of spheres and vertices this are GXLNodes object.
     * In case of edges this are GXLEdges. GXLNode and GXLEdge are subclassses from the GXLAttributetElement class.
     * The GXLAttributetElements have GXLAttr objects as childs. These childs describe the GXLAttributedElements.
     * This method gives back the contetn of the new created document as string.
     *
     * @return the content of the created document
     */
    public String gxlFromInstanceWithTemplate() {
        VisualizationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> theGraph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();

        File tmpGXL = new File("tmp.gxl");

        GXLDocument doc = new GXLDocument();
        GXLIDGenerator idGenerator = new GXLIDGenerator(doc);
        List<GXLNode> myNodes = new ArrayList<>();
        GXLGraph gxlSyndrom = new GXLGraph("syndrom");
        for (Sphere s : currentSpheres) {
            GXLNode sphere = new GXLNode(s.getId() + "");
            sphere.setAttr("TYPE", new GXLString("Sphäre"));
            Color color = s.getColor();
            sphere.setAttr("fillPaint", new GXLString(getPaintDescription(color)));
            sphere.setAttr("coordinates", new GXLString("" + s.getCoordinates().toString()));
            sphere.setAttr("width", new GXLString("" + s.getWidth()));
            sphere.setAttr("height", new GXLString("" + s.getHeight()));
            String annotationContent = "";
            if (s.getAnnotation() != null) {
                for (String partOfAnnotation : s.getAnnotation().keySet()) {
                    annotationContent = annotationContent + s.getAnnotation().get(partOfAnnotation) + "\u00A6";
                }
            }
            sphere.setAttr("annotation", new GXLString(annotationContent));
            sphere.setAttr("font", new GXLString(s.getFont()));
            sphere.setAttr("fontSize", new GXLInt(s.getFontSize()));
            String nodeIDs = "";
            int numberOfCurrentNode = 0;
            for (Vertex v : s.getVertices()) {
                nodeIDs = nodeIDs + v.getId();
                numberOfCurrentNode++;
                if (numberOfCurrentNode < s.getVertices().size()) {
                    nodeIDs = nodeIDs + ", ";
                }
            }
            if (exportWithRules) {
                sphere = addRulesToSphere(s, sphere);
            }
            sphere.setAttr("IDs of nodes containt in this shpere: ", new GXLString(nodeIDs));
            gxlSyndrom.add(sphere);
        }
        for (Sphere s : currentSpheres) {
            // GXLRel rel = new GXLRel();
            for (Vertex v : s.getVertices()) {
                GXLNode singleNodeInSphere = new GXLNode(v.getId() + "");
                //  sphere.add(singleNodeInSphere);
                gxlSyndrom.add(singleNodeInSphere);

                // rel.add(new GXLRelend(singleNodeInSphere));
                singleNodeInSphere.setAttr("TYPE", new GXLString("Node"));
                Color color = v.getFillColor();
                singleNodeInSphere.setAttr("fillPaint", new GXLString(getPaintDescription(color)));
                singleNodeInSphere.setAttr("coordinate", new GXLString("" + v.getCoordinates().toString()));
                singleNodeInSphere.setAttr("shape", new GXLString("" + v.getShape()));
                String nodeAnnotationContent = "";
                if (v.getAnnotation() != null) {
                    for (String partOfNodeAnnotation : v.getAnnotation().keySet()) {
                        nodeAnnotationContent = nodeAnnotationContent + v.getAnnotation().get(partOfNodeAnnotation) + "\u00A6";
                    }
                }
                singleNodeInSphere.setAttr("annotation", new GXLString(nodeAnnotationContent));
                Color drawPaint = (Color) v.getDrawColor();
                singleNodeInSphere.setAttr("drawPaint", new GXLString(getPaintDescription(drawPaint)));
                if (v.getVertexArrowReinforced() != null) {
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString("" + v.getVertexArrowReinforced()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString(""));
                }
                if (v.getVertexArrowNeutral() != null) {
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString("" + v.getVertexArrowNeutral()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString(""));
                }
                if (v.getVertexArrowExtenuating() != null) {
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString("" + v.getVertexArrowExtenuating()));
                } else {
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString(""));
                }
                singleNodeInSphere.setAttr("size", new GXLInt(v.getSize()));
                singleNodeInSphere.setAttr("isVisible", new GXLBool(v.isVisible()));
                singleNodeInSphere.setAttr("font", new GXLString("" + v.getFont()));
                singleNodeInSphere.setAttr("fontSize", new GXLInt(v.getFontSize()));
                singleNodeInSphere.setAttr("ID of the sphere containing this node:", new GXLString("" + s.getId()));
                if (exportWithRules == true) {
                    singleNodeInSphere = addRulesToNode(v, singleNodeInSphere);
                }
                myNodes.add(singleNodeInSphere);
            }
        }
        for (Edge e : theGraph.getEdges()) {
            Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
            GXLEdge edge = new GXLEdge(verticesOfEdge.getFirst().getId() + "", verticesOfEdge.getSecond().getId() + "");
            edge.setID(e.getId() + "");
            edge.setAttr("TYPE", new GXLString("Edge"));
            Color color = (Color) e.getColor();
            edge.setAttr("paint", new GXLString(getPaintDescription(color)));
            edge.setAttr("stroke", new GXLString("" + e.getStroke()));
            edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));

            edge.setAttr("hasAnchorIn", new GXLBool(e.isHasAnchorIn()));
            if (e.getAnchorPoints().getKey() == null) {
                edge.setAttr("anchorAngle of source", new GXLString("no anchorpoint at the source set"));
            } else {
                edge.setAttr("anchorAngle of source", new GXLString("" + e.getAnchorPoints().getKey()));
            }

            edge.setAttr("hasAnchorOut", new GXLBool(e.isHasAnchorOut()));
            if (e.getAnchorPoints().getValue() == null) {
                edge.setAttr("anchorAngle of source", new GXLString("no anchorpoint at the target set"));
            } else {
                edge.setAttr("anchorAngle of target", new GXLString("" + e.getAnchorPoints().getValue()));
            }

            edge.setAttr("isVisible", new GXLBool(e.isVisible()));
            if (exportWithRules) {
                edge = addRulesToEdge(e, edge);
            }
            gxlSyndrom.add(edge);
        }

        doc.getDocumentElement().add(gxlSyndrom);


        if(exportWithRules){
            GXLNode templateNode = createTemplateNode();
            doc.getDocumentElement().add(templateNode);
        }



        String content = "";
        try {
            doc.write(tmpGXL);

            // reading the content of the created gxl document and write its content into a String
            try (BufferedReader reader = new BufferedReader(new FileReader(tmpGXL));) {
                Stream<String> lines = reader.lines();
                Object[] linesArray = lines.toArray();
                boolean firstLine = true;
                for (Object s : linesArray) {
                    if(firstLine){
                        String objectContetnt = (String) s;
                        content = objectContetnt;
                        firstLine = false;
                    }else {
                        String objectContetnt = (String) s;
                        content = content + "\n" + objectContetnt;
                    }
                }
            } catch (FileNotFoundException e) {
                logger.error(e.toString());
            } catch (IOException e) {
                logger.error(e.toString());
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        tmpGXL.deleteOnExit();
        return content;
    }

    /**
     * This method is called from the {@gxlFromInstanceWithTemplate()}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     *
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param sphere the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlSphere the GXLAttributedElement still not having the neccessary attributes.
     * @return the GXLAttributedElement passed to this method now with the neccessary attributes.
     */
    private GXLNode addRulesToSphere(Sphere sphere, GXLNode gxlSphere) {
        gxlSphere.setAttr("isLockedPosition", new GXLBool(sphere.isLockedPosition()));
        gxlSphere.setAttr("isLockedAnnotation", new GXLBool(sphere.isLockedAnnotation()));
        gxlSphere.setAttr("isLockedStyle", new GXLBool(sphere.isLockedStyle()));
        gxlSphere.setAttr("isLockedVertices", new GXLBool(sphere.isLockedVertices()));
        return gxlSphere;
    }

    /**
     * This method is called from the {@gxlFromInstanceWithTemplate()}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     *
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param vertex the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlNode the GXLAttributedElement still not having the neccessary attributes.
     * @return the GXLAttributedElement passed to this method now with the neccessary attributes.
     */
    private GXLNode addRulesToNode(Vertex vertex, GXLNode gxlNode) {
        gxlNode.setAttr("isLockedPosition", new GXLBool(vertex.isLockedPosition()));
        gxlNode.setAttr("isLockedAnnotation", new GXLBool(vertex.isLockedAnnotation()));
        gxlNode.setAttr("isLockedStyle", new GXLBool(vertex.isLockedStyle()));
        return gxlNode;
    }

    /**
     * This method is called from the {@gxlFromInstanceWithTemplate()}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     *
     * Adds attributes to the GXLAttributedElement that describe the editing options of the graph and returns it.
     *
     * @param edge the object those values describe the editing options that the GXLAttributedElement needs.
     * @param gxlEdge the GXLAttributedElement still not having the neccessary attributes.
     * @return the GXLAttributedElement passed to this method now with the neccessary attributes.
     */
    private GXLEdge addRulesToEdge(Edge edge, GXLEdge gxlEdge) {
        gxlEdge.setAttr("isLockedStyle", new GXLBool(edge.isLockedStyle()));
        gxlEdge.setAttr("isLockedEdgeType", new GXLBool(edge.isLockedEdgeType()));
        return gxlEdge;
    }

    /**
     * This method is called from the {@gxlFromInstanceWithTemplate()}-method when the export
     * describes an export of a graph and the rules describing the editing options belonging to the graph.
     *
     * Creates a new GXLNode that descriped a template object
     * having some of the attributes a template object has and returns it.
     *
     * @return the GXLNode representing the template object.
     */
    private GXLNode createTemplateNode(){
        GXLNode templateNode = new GXLNode("template");
        Template template = Syndrom.getInstance().getTemplate();
        templateNode.setAttr("maxSpheres", new GXLInt(template.getMaxSpheres()));
        templateNode.setAttr("maxVertices", new GXLInt(template.getMaxVertices()));
        templateNode.setAttr("maxEdges", new GXLInt(template.getMaxEdges()));
        templateNode.setAttr("maxVerticesInSphere", new GXLInt(template.getMaxVerticesInSphere()));
        templateNode.setAttr("reinforcedEdgesAllowed", new GXLBool(template.isReinforcedEdgesAllowed()));
        templateNode.setAttr("unknownEdgesAllowed", new GXLBool(template.isUnknownEdgesAllowed()));
        templateNode.setAttr("extenuatingEdgesAllowed", new GXLBool(template.isExtenuatingEdgesAllowed()));
        return templateNode;
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
            System.out.println("aktuelles word: " + word);
            return word.split("=");
        }
        word = word.trim();
        System.out.println(word);
        return word.split(",");
    }


    /**
     * Save the GXL representation to a specific location.
     *
     * @param pFile The destination File
     */
    public void exportGXL(File pFile, boolean pExportWithRules) {
        exportWithRules = pExportWithRules;
        String gxl = gxlFromInstanceWithTemplate();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile))) {
            bufferedWriter.write(gxl);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Import a GXL file from a specific location to the syndrom
     *
     * @param pFile The File to import
     */

    public void importGXL(File pFile, boolean pImportWithRules) {
        importWithRules = pImportWithRules;
        String gxl = "";
        try (Scanner scanner = new Scanner(pFile)) {
            gxl = scanner.useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            logger.error(e.toString());
        }
        gxlToInstance(gxl);
    }


}
