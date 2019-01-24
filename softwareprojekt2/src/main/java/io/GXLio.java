package io;

import com.google.inject.Inject;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import log_management.dao.GraphDao;
import net.sourceforge.gxl.*;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The GXL importer/exporter.
 */
public class GXLio {
    /**
     * The syndrom representation.
     */
    @Inject
    private Syndrom syndrom;

    /**
     * The graph dao object, for accessing the graph data.
     */
    @Inject
    private GraphDao graphDao;


    /**
     * Der File an den die erstellte GXL_Datei geschrieben werden soll.
     */
    private File file;


    /**
     * Creates a new GXLio object.
     */
    public GXLio(){;
    }

    /**
     * Writes the GXL representation into the syndrom.
     *
     * @param pGXL The GXL representation that gets written into syndrom.
     */
    protected void gxlToInstance(String pGXL){

        try { GXLDocument doc = new GXLDocument(file);
            GXLGraph gxlGraph = (GXLGraph) doc.getElement("syndrom");
            if (gxlGraph == null) {
                System.out.println("Das Dokument ist leer!");
            }
            List<Sphere> spheres = new ArrayList<>();
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            // each array contains at index 0 the id of a node and at index 1 the id of the spere that  contains the node with the id (at index 0)
            List<int[]> nodeIDAndSphereID = new ArrayList<>();
            // each array contains at index 0 the id of an edge, at index 1 the id of the source node and at index 2 the id of the target node
            List<int[]> edgeIDsAndNodeIDs = new ArrayList<>();
            //for (int i = 0; i < readDok.getDocumentElement().getGraphCount(); i++) {
            for (int i = 0; i < gxlGraph.getGraphElementCount(); i++) {
                // System.out.println("Graph gefunden");
                GXLAttributedElement elem = doc.getElement(i + "");

                //System.out.println(((GXLString) elem.getAttr("TYPE").getValue()).getValue());

                if (((GXLString) elem.getAttr("TYPE").getValue()).getValue().equals("Sphäre")) {
                    //   System.out.println("Sphäre gefunden");
                    int id = Integer.parseInt(elem.getID());
                    //  System.out.println("id: " + id);
                    //   System.out.println("before fillPaint: " + getNumberArrayFromString(((GXLString) elem.getAttr("fillPaint").getValue()).getValue()).get(0));
                    //
                    String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr("fillPaint").getValue()).getValue());
                    Color paint = new Color(
                            Integer.parseInt(paintArray[0]),
                            Integer.parseInt(paintArray[1]),
                            Integer.parseInt(paintArray[2]),
                            Integer.parseInt(paintArray[3]));
                    System.out.println("" + paint.toString() + "ist da");
                    String[] coordinatesArray = getNumberArrayFromString(((GXLString) elem.getAttr("coordinates").getValue()).getValue());
                    java.awt.geom.Point2D coordinates = new java.awt.geom.Point2D.Float(
                            java.lang.Float.parseFloat(coordinatesArray[0].substring(0, coordinatesArray[0].length()-2)),
                            java.lang.Float.parseFloat(coordinatesArray[1].substring(0, coordinatesArray[1].length()-2)));
                    System.out.println("coordinates: " + coordinates.toString());
                    //    System.out.println(getNumberArrayFromString(elem.getAttr("coordinates") + "").get(0).substring(0, getNumberArrayFromString(elem.getAttr("coordinates") + "").get(0).length()-2));
                    //    System.out.println(getNumberArrayFromString(elem.getAttr("coordinates") + "").get(1).substring(0, getNumberArrayFromString(elem.getAttr("coordinates") + "").get(1).length()-2));
                    double width = Double.parseDouble(((GXLString) elem.getAttr("width").getValue()).getValue());
                    System.out.println("width: " + width);
                    double height = Double.parseDouble(((GXLString) elem.getAttr("height").getValue()).getValue());
                    System.out.println("height: " + height);
                    Map<String, String> annotation = new HashMap<String, String>();
                    annotation.put(((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[0],
                            ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[1]);
                    System.out.println("annotation: " + annotation.toString());
                    String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
                    System.out.println("font: " + font);
                    int fontSize = Integer.parseInt(((GXLString) elem.getAttr("fontSize").getValue()).getValue());
                    System.out.println("fontSize: " + fontSize);
                    Sphere newSphere = new Sphere(id, paint, coordinates, width, height, annotation, font, fontSize);

                    spheres.add(newSphere);
                }
                if (((GXLString) elem.getAttr("TYPE").getValue()).getValue().equals("Node")) {
                    System.out.println("Knoten gefunden");
                    int id = Integer.parseInt(elem.getID());
                    String[] paintArray = getNumberArrayFromString(((GXLString) elem.getAttr("fillPaint").getValue()).getValue());
                    Color paint = new Color(
                            Integer.parseInt(paintArray[0]),
                            Integer.parseInt(paintArray[1]),
                            Integer.parseInt(paintArray[2]),
                            Integer.parseInt(paintArray[3]));
                    System.out.println("Knoten: " + paint);
                    String[] coordinatesArray = getNumberArrayFromString(((GXLString) elem.getAttr("coordinate").getValue()).getValue());
                    java.awt.geom.Point2D coordinates = new java.awt.geom.Point2D.Float(
                            java.lang.Float.parseFloat(coordinatesArray[0].substring(0, coordinatesArray[0].length()-2)),
                            java.lang.Float.parseFloat(coordinatesArray[1].substring(0, coordinatesArray[1].length()-2)));
                    System.out.println("Knoten: " + coordinates);
                    VertexShapeType shape = VertexShapeType.valueOf(((GXLString) elem.getAttr("shape").getValue()).getValue());
                    System.out.println("Knoten: " + shape);
                    //Map<String, String> annotation = new HashMap<String, String>();
                    // String[] annotationArray = ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6");
                    //  annotation.put(annotationArray[0], annotationArray[1]);
                    Map<String, String> annotation = new HashMap<String, String>();
                    annotation.put(((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[0],
                            ((GXLString) elem.getAttr("annotation").getValue()).getValue().split("\u00A6")[1]);
                    System.out.println("Knoten: " + annotation);
                    String[] drawPaintArray = getNumberArrayFromString(((GXLString) elem.getAttr("drawPaint").getValue()).getValue());
                    Color drawPaint = new Color(
                            Integer.parseInt(drawPaintArray[0]),
                            Integer.parseInt(drawPaintArray[1]),
                            Integer.parseInt(drawPaintArray[2]),
                            Integer.parseInt(drawPaintArray[3]));
                    System.out.println("Knoten: " + drawPaint);
                    String vertexArrowReinforced = ((GXLString) elem.getAttr("vertexArrowReinforced").getValue()).getValue();
                    String vertexArrowNeutral = ((GXLString) elem.getAttr("vertexArrowNeutral").getValue()).getValue();
                    String vertexArrowExtenuating = ((GXLString) elem.getAttr("vertexArrowExtenuating").getValue()).getValue();
                    int size = Integer.parseInt(((GXLString) elem.getAttr("size").getValue()).getValue());
                    System.out.println("Knoten: " + size);
                    boolean isVisible = Boolean.parseBoolean(((GXLString) elem.getAttr("isVisible").getValue()).getValue());
                    String font = ((GXLString) elem.getAttr("font").getValue()).getValue();
                    System.out.println("Knoten: " + font);
                    int fontSize = Integer.parseInt(((GXLString) elem.getAttr("fontSize").getValue()).getValue());
                    System.out.println("Knoten: " + fontSize);
                    Vertex newVertex = new Vertex(id, paint, coordinates, shape, annotation, drawPaint, size, font, fontSize);

                    vertices.add(newVertex);
                    System.out.println("Knoten ins Array gelegt.");

                    int nodeID = Integer.parseInt(elem.getID());
                    System.out.println("NodeID: " + nodeID);
                    int idOfTheSphereContainingThisNode = Integer.parseInt(((GXLString) elem.getAttr("ID of the sphere containing this node:").getValue()).getValue());
                    System.out.println("ID of sphere containing this node: " + idOfTheSphereContainingThisNode);
                    int[] entry = {nodeID, idOfTheSphereContainingThisNode};
                    nodeIDAndSphereID.add(entry);
                    System.out.println("Die Eintrag von Knoten- und Sphären-ID wurde ins Array geschrieben.");
                }
                if (((GXLString) elem.getAttr("TYPE").getValue()).getValue().equals("Edge")) {
                    System.out.println("Kante gefunden");
                    int id = Integer.parseInt(elem.getID());
                    System.out.println("Kante: " + id);
                    String[] drawPaintArray = getNumberArrayFromString(((GXLString) elem.getAttr("paint").getValue()).getValue());
                    Color paint = new Color(
                            Integer.parseInt(drawPaintArray[0]),
                            Integer.parseInt(drawPaintArray[1]),
                            Integer.parseInt(drawPaintArray[2]),
                            Integer.parseInt(drawPaintArray[3]));
                    System.out.println("Kante: " + paint);
                    StrokeType stroke = StrokeType.valueOf(((GXLString) elem.getAttr("stroke").getValue()).getValue());
                    System.out.println("Kante: " + stroke);
                    EdgeArrowType arrowType = EdgeArrowType.valueOf(((GXLString) elem.getAttr("arrowType").getValue()).getValue());
                    System.out.println("Kante: " + arrowType);
                    Boolean hasAnchor = Boolean.getBoolean(((GXLString) elem.getAttr("hasAnchor").getValue()).getValue());
                    // edge.getAttr("anchorAngle");
                    System.out.println("Kante: " + hasAnchor);
                    Boolean isVisible = Boolean.getBoolean(((GXLString) elem.getAttr("isVisible").getValue()).getValue());
                    System.out.println("Kante: "+ isVisible);
                    Edge newEdge = new Edge(id, paint, stroke, arrowType, hasAnchor, isVisible);

                    edges.add(newEdge);

                    GXLEdge currentEdge = (GXLEdge) elem;
                    int edgeID = Integer.parseInt(currentEdge.getID());
                    int sourceID = Integer.parseInt(currentEdge.getSource().getID());
                    int targetID = Integer.parseInt(currentEdge.getTarget().getID());
                    int[] entry = {edgeID, sourceID, targetID};
                    edgeIDsAndNodeIDs.add(entry);
                }

            }
            System.out.println("number of spheres: " + spheres.size());
            System.out.println("number of nodes: " + vertices.size());
            System.out.println("number of edges; " + edges.size());


            //    VisualizationViewer<Vertex, Edge> newVV = syndrom.getVv();
            //    SyndromGraph<Vertex, Edge> newGraph = new SyndromGraph();
            //    newVV.getGraphLayout().setGraph(newGraph);
            for(Sphere s : spheres){
                //      newGraph.getSpheres().add(s);
                System.out.println("Die Sphäre wurde dem Graphen hinzugefügt");
            }
            for(Vertex v : vertices){
                Sphere sphereWithThisNode = null;
                int idOfSphere = -1;
                for(int[] i : nodeIDAndSphereID){
                    if(v.getId() == i[0]){
                        idOfSphere = i[1];
                    }
                }
                //newGraph.addVertex(v);
                System.out.println("Der Knoten wurde dem Graphen hinzugefügt");
                for(Sphere s : spheres){
                    if(s.getId() == idOfSphere){
                        sphereWithThisNode = s;
                        System.out.println("Die Sphäre zu dem dieser Knoten gehört wurde gefunden");
                    }
                }
            }
            for(Edge e : edges){
                Vertex start = null;
                Vertex end = null;
                int idOfStart = -1;
                int idOfEnd = -1;
                for(int[] i : edgeIDsAndNodeIDs){
                    if(e.getId() == i[0]){
                        idOfStart = i[1];
                        idOfEnd = i[2];
                    }
                }
                for(Vertex v : vertices){
                    if(v.getId() == idOfStart){
                        start = v;
                    }else if(v.getId() == idOfEnd){
                        end = v;
                    }
                }
                // Pair<Vertex> endPoints = new Pair<Vertex>(start, end);
                // newGraph.addEdge(e, endPoints);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }



    /**
     * Extracts the GXL representation from our syndrom and gives it back as string.
     *
     *
     */
    public void gxlFromInstanceBasti(){
        VisualizationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> theGraph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();

        GXLDocument doc = new GXLDocument();
        GXLIDGenerator idGenerator = new GXLIDGenerator(doc);
        List<GXLNode> myNodes = new ArrayList<>();

   /*     GXLNode aa = new GXLNode(idGenerator.generateID());
   //     aa.setAttr("GXLType");
     //   aa.setAttribute("attr", "attri");
        aa.setAttr("TYPE", new GXLString("lname"));
        GXLAtomicValue gx = (GXLString) aa.getAttr("TYPE").getValue();
        gx.getValue();
        GXLString sss = new GXLString("fr");
        GXLNode bb = new GXLNode(idGenerator.generateID());
        GXLEdge ee = new GXLEdge(aa, bb);
        GXLGraph gg = new GXLGraph(idGenerator.generateID());
        gg.add(aa);
        gg.add(bb);
        gg.add(ee);
        doc.getDocumentElement().add(gg);
        /*
        GXLRel rr = new GXLRel();
        GXLRelend re = new GXLRelend(aa);
        rr.add(re);
        GXLEdge ed = new GXLEdge(rr, rr);
        doc.getDocumentElement().add(gg);
        */

        GXLGraph gxlSyndrom = new GXLGraph("syndrom");
        for (Sphere s : currentSpheres) {
            GXLNode sphere = new GXLNode(s.getId() + "");
            //gxlSyndrom.add(sphere);
            // sphere.setID("" + s.getId());
            sphere.setAttr("TYPE", new GXLString("Sphäre"));
            Color color = s.getColor();
            System.out.println("color sphere: "+color);
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
            for(Vertex v : s.getVertices()){
                nodeIDs = nodeIDs + v.getId();
                numberOfCurrentNode++;
                if(numberOfCurrentNode < s.getVertices().size()){
                    nodeIDs = nodeIDs + ", ";
                }
            }
            sphere.setAttr("IDs of nodes containt in this shpere: ", new GXLString(nodeIDs));
             gxlSyndrom.add(sphere);
        }
        for(Sphere s :currentSpheres) {
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
            // GXLEdge edge = new GXLEdge((GXLNode) doc.getElement(s.getId() + ""), rel);

        }
        for(Edge e : theGraph.getEdges()) {
            Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
            GXLEdge edge = new GXLEdge(verticesOfEdge.getFirst().getId() + "", verticesOfEdge.getSecond().getId() + "");
            edge.setID(e.getId() + "");

            edge.setAttr("TYPE", new GXLString("Edge"));
            Color color = (Color) e.getColor();
            edge.setAttr("paint", new GXLString(getPaintDescription(color)));
            // edge.setAttr("paint", new GXLString(e.getPaint() + ""));
            edge.setAttr("stroke", new GXLString("" + e.getStroke()));
            edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));
            edge.setAttr("hasAnchor", new GXLString("" + e.isHasAnchor()));
            edge.setAttr("anchorAngle", new GXLString("" + e.getAnchorPoint()));
            edge.setAttr("isVisible", new GXLString("" + e.isVisible()));
            gxlSyndrom.add(edge);
        }

        doc.getDocumentElement().add(gxlSyndrom);

        try {
            doc.write(file);
        }catch(Exception e){}
        //throw new UnsupportedOperationException();
        //return doc.toString();
    }


    /**
     * Extracts the GXL representation from our syndrom and gives it back as string.
     *
     * @return The extracted GXL represenation.
     */
    public String gxlFromInstance(){
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> theGraph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();

        GXLDocument doc = new GXLDocument();
        GXLIDGenerator idGenerator = new GXLIDGenerator(doc);
        List<GXLNode> myNodes = new ArrayList<>();

   /*     GXLNode aa = new GXLNode(idGenerator.generateID());
   //     aa.setAttr("GXLType");
     //   aa.setAttribute("attr", "attri");
        aa.setAttr("TYPE", new GXLString("lname"));
        GXLAtomicValue gx = (GXLString) aa.getAttr("TYPE").getValue();
        gx.getValue();
        GXLString sss = new GXLString("fr");
        GXLNode bb = new GXLNode(idGenerator.generateID());
        GXLEdge ee = new GXLEdge(aa, bb);
        GXLGraph gg = new GXLGraph(idGenerator.generateID());
        gg.add(aa);
        gg.add(bb);
        gg.add(ee);
        doc.getDocumentElement().add(gg);
        /*
        GXLRel rr = new GXLRel();
        GXLRelend re = new GXLRelend(aa);
        rr.add(re);
        GXLEdge ed = new GXLEdge(rr, rr);
        doc.getDocumentElement().add(gg);
        */
   GXLGraph gxlSyndrom = new GXLGraph("syndrom");
        for (Sphere s : theGraph.getSpheres()) {
            GXLNode sphere = new GXLNode(s.getId() + "");
            //gxlSyndrom.add(sphere);
            // sphere.setID("" + s.getId());
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
            for(Vertex v : s.getVertices()){
                nodeIDs = nodeIDs + v.getId();
                numberOfCurrentNode++;
                if(numberOfCurrentNode < s.getVertices().size()){
                    nodeIDs = nodeIDs + ", ";
                }
            }
            sphere.setAttr("IDs of nodes containt in this shpere: ", new GXLString(nodeIDs));
            gxlSyndrom.add(sphere);
        }
        for(Sphere s : currentSpheres) {
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
            // GXLEdge edge = new GXLEdge((GXLNode) doc.getElement(s.getId() + ""), rel);

        }
        for(Edge e : theGraph.getEdges()) {
            Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
            GXLEdge edge = new GXLEdge(verticesOfEdge.getFirst().getId() + "", verticesOfEdge.getSecond().getId() + "");
            edge.setID(e.getId() + "");

            edge.setAttr("TYPE", new GXLString("Edge"));
            Color color = (Color) e.getColor();
            edge.setAttr("paint", new GXLString(getPaintDescription(color)));
            // edge.setAttr("paint", new GXLString(e.getPaint() + ""));
            edge.setAttr("stroke", new GXLString("" + e.getStroke()));
            edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));
            edge.setAttr("hasAnchor", new GXLString("" + e.isHasAnchor()));
            edge.setAttr("anchorAngle", new GXLString("" + e.getAnchorPoint()));
            edge.setAttr("isVisible", new GXLString("" + e.isVisible()));
            gxlSyndrom.add(edge);
        }

        doc.getDocumentElement().add(gxlSyndrom);
        try {
            doc.write(file);
        }catch(Exception e){}
        //throw new UnsupportedOperationException();
        return doc.toString();
    }




    /**
     * Extracts the GXL representation from our syndrom and gives it back as string.
     *
     * @return The extracted GXL represenation.
     */
    public String gxlTemplateFromInstance(){throw new UnsupportedOperationException();}






    private String getPaintDescription(Color color){
        return ("java.awt.Color[r=" + color.getRed() + ",g=" + color.getGreen()
                + ",b=" + color.getBlue() + ",a=" + color.getAlpha() + "]");
    }









    public String[] getNumberArrayFromString(String pWord) {
        String word = pWord.trim();
        String[] alphabet = {"2D", "a","b", "c","d", "e","f", "g","h", "i", "j", "k","l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for(String s : alphabet){
            word = word.replaceAll(s, "");
            word = word.replaceAll(s.toUpperCase(), "");
        }
        int numberOFDots = 0;
        while(word.charAt(numberOFDots) == '.' ){
            numberOFDots++;
        }
        word = word.substring(numberOFDots);
        word = word.substring(2, word.length()-1);
        if(word.contains("=")){
            word = word.replaceAll(",", "");
            System.out.println("aktuelles word: " + word);
            //System.out.println(word.split("=")[0] + word.split("=")[1] + word.split("=")[2] + word.split("=")[4]);
            return word.split("=");
            //  return Arrays.asList(word.split("="));
        }

        return word.split(",");
        //return Arrays.asList(word.split(", "));
        /*
        if(word.contains(".")){
            List<Double> doubles = new ArrayList<>();
            for(String s : numbersAsString){
                doubles.add(Double.parseDouble(s));
            }
            return doubles;
        }else{
            List<Integer> integers = new ArrayList<>();
            for(String s : numbersAsString){
                integers.add(Integer.parseInt(s));
            }
            return integers;
        }
        */

    }




    /**
     * Save the GXL representation to a specific location.
     *
     * @param pFile The destination File
     */
    public void exportGXL(File pFile){
        file = pFile;
        gxlFromInstanceBasti();
        // den Rest dieser Methode braucht man meiner Meinung nicht.
        /*
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile));
            bufferedWriter.write(gxlFromInstance());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /**
     * Save the GXL as template representation to a specific location.
     *
     * @param pFile The destination File
     */
    public void exportGXLTemplate(File pFile){
        gxlTemplateFromInstance();
        // ich denke den rest der Methode brauchen wir nicht, da das GXL-Tool nicht schrittweise
        // ins dokument schreiben knn, wie es der Buffered Writer macht
        /*
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile));
            bufferedWriter.write(gxlTemplateFromInstance());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    /**
     * Import a GXL file from a specific location to the syndrom
     *
     * @param pFile The File to import
     */

    public void importGXL(File pFile){
        String gxl = "";
        try {
            gxl = new Scanner(pFile).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gxlToInstance(gxl);
    }
}
