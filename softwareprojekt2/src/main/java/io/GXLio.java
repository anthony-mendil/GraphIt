package io;

import com.google.inject.Inject;
import edu.uci.ics.jung.graph.util.Pair;
import graph.graph.*;
import log_management.dao.GraphDao;
import net.sourceforge.gxl.*;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
            try {
                GXLDocument readDok = new GXLDocument(new File(pGXL));
                for(int i = 0; i<readDok.getDocumentElement().getGraphCount(); i++){
                    GXLGraph graphOfDoc = readDok.getDocumentElement().getGraphAt(i);
                    for(int j = 0; j<graphOfDoc.getGraphElementCount(); j++){
                        GXLGraphElement graphElement = graphOfDoc.getGraphElementAt(j);
                        if(graphElement.getClass().isInstance(GXLNode.class)) {
                            graphElement.getAttr("");
                        }
                        if(graphElement.getClass().isInstance(GXLEdge.class)) {
                            graphElement.getAttr("");
                        }
                        GXLNode sphere = new GXLNode("");
                        sphere.getAttr("id");
                        String fillPaintString = sphere.getAttr("fillPaint").getName();
                        Paint fillPaint = getPaintFromString(fillPaintString.substring(15, fillPaintString.length()-1));
                        sphere.getAttr("coordinates");
                        sphere.getAttr("width");
                        sphere.getAttr("height");
                        sphere.getAttr("annotation");


                        GXLNode singleNodeInSphere = new GXLNode("");
                        singleNodeInSphere.getAttr("id");
                        singleNodeInSphere.getAttr("fillPaint");
                        singleNodeInSphere.getAttr("coordinate");
                        singleNodeInSphere.getAttr("shape");
                        singleNodeInSphere.getAttr("annotation");
                        singleNodeInSphere.getAttr("drawPaint");
                        singleNodeInSphere.getAttr("vertexArrowReinforced");
                        singleNodeInSphere.getAttr("vertexArrowNeutral");
                        singleNodeInSphere.getAttr("vertexArrowExtenuating");
                        singleNodeInSphere.getAttr("size");
                        singleNodeInSphere.getAttr("isVisible");
                        singleNodeInSphere.getAttr("font");
                        singleNodeInSphere.getAttr("fontSize");

                        GXLNode localNode = new GXLNode("");
                        GXLNode localNode2 = new GXLNode("");

                        GXLEdge edge = new GXLEdge(localNode, localNode2);
                        edge.getAttr("id");
                        edge.getAttr("paint");
                        edge.getAttr("stroke");
                        edge.getAttr("arrowType");
                        edge.getAttr("hasAnchor");
                        edge.getAttr("anchorAngle");
                        edge.getAttr("isVisible");


                        sphere.getAttr("font");
                        sphere.getAttr("fontSize");
                    }
                    throw new UnsupportedOperationException();
                }
            } catch (IOException | SAXException e) {
                e.printStackTrace();
            }

    }

    /**
     * Extracts the GXL representation from our syndrom and gives it back as string.
     *
     * @return The extracted GXL represenation.
     */
    public String gxlFromInstance(){
        Syndrom theSyndrom = syndrom.getInstance();
        SyndromGraph<Vertex, Edge> theGraph = theSyndrom.getGraph();
        List<Sphere> currentSpheres = theGraph.getSpheres();
        //SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        //SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        GXLDocument doc = new GXLDocument();
        GXLIDGenerator idGenerator = new GXLIDGenerator(doc);
        GXLGraph gxlGraph = new GXLGraph(idGenerator.generateID());
        for(Sphere s : currentSpheres){
            GXLNode sphere = new GXLNode(idGenerator.generateID());
            sphere.setID("" + s.getId());
            Color color = (Color) s.getFillPaint();

            sphere.setAttr("id", new GXLString("" + s.getId()));
            sphere.setAttr("fillPaint", new GXLString(getPaintDescription(color)));
            sphere.setAttr("coordinates", new GXLString("" + s.getCoordinates()));
            sphere.setAttr("width", new GXLString("" + s.getWidth()));
            sphere.setAttr("height", new GXLString("" + s.getHeight()));
            String annotationContent = "";
            if(s.getAnnotation() != null){
                for(String partOfAnnotation : s.getAnnotation().keySet()){
                    annotationContent = annotationContent + s.getAnnotation().get(partOfAnnotation);
                }
            }
            sphere.setAttr("annotation", new GXLString(annotationContent));

            //     if(s.getVertices() != null) {

            GXLGraph allNodesInTheSpere = new GXLGraph(idGenerator.generateID());
            sphere.add(allNodesInTheSpere);

            // ------------------ diese Abschnitt kann gelöscht und die auskommentierte Zeile darunter wieder einkommentiert werden
            //Vertex aV = new Vertex(222);
            //Vertex bV = new Vertex(333);
            List<Vertex> inventedVertexList = new ArrayList<>();
            //inventedVertexList.add(aV);
            //inventedVertexList.add(bV);
            for(Vertex v : inventedVertexList){
                //      for (Vertex v : s.getVertices()){
                GXLNode singleNodeInSphere = new GXLNode(idGenerator.generateID());
                allNodesInTheSpere.add(singleNodeInSphere);
                singleNodeInSphere.setAttr("id", new GXLString(idGenerator.generateID()));
                singleNodeInSphere.setAttr("fillPaint", new GXLString("" + v.getFillPaint()));
                singleNodeInSphere.setAttr("coordinate", new GXLString("" + v.getCoordinates()));
                singleNodeInSphere.setAttr("shape", new GXLString("" + v.getShape()));
                String nodeAnnotationContent = "";
                if(v.getAnnotation() != null){
                    for (String partOfNodeAnnotation : v.getAnnotation().keySet()) {
                        nodeAnnotationContent = nodeAnnotationContent + v.getAnnotation().get(partOfNodeAnnotation);
                    }
                }
                singleNodeInSphere.setAttr("annotation", new GXLString(nodeAnnotationContent));
                singleNodeInSphere.setAttr("drawPaint", new GXLString("" + v.getDrawPaint()));
                if(v.getVertexArrowReinforced() != null){
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString("" + v.getVertexArrowReinforced()));
                }else{
                    singleNodeInSphere.setAttr("vertexArrowReinforced", new GXLString("" ));
                }
                if(v.getVertexArrowNeutral() != null){
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString("" + v.getVertexArrowNeutral()));
                }else{
                    singleNodeInSphere.setAttr("vertexArrowNeutral", new GXLString("" ));
                }
                if(v.getVertexArrowExtenuating() != null){
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString("" + v.getVertexArrowExtenuating()));
                }else{
                    singleNodeInSphere.setAttr("vertexArrowExtenuating", new GXLString("" ));
                }

                singleNodeInSphere.setAttr("size", new GXLString("" + v.getSize()));
                singleNodeInSphere.setAttr("isVisible", new GXLString("" + v.isVisible()));
                singleNodeInSphere.setAttr("font", new GXLString("" + v.getFont()));
                singleNodeInSphere.setAttr("fontSize", new GXLString("" + v.getFontSize()));

                GXLGraph edgesToNode = new GXLGraph(idGenerator.generateID());
                GXLNode localNode = new GXLNode(idGenerator.generateID());
                GXLNode localNode2 = new GXLNode(idGenerator.generateID());
                singleNodeInSphere.add(edgesToNode);
                allNodesInTheSpere.add(localNode);
                allNodesInTheSpere.add(localNode2);
                Collection<Edge> edges = theGraph.getEdges();
                for(Edge e : edges) {
                    Pair<Vertex> verticesOfEdge = theGraph.getEndpoints(e);
                }
                /*Edge e = new Edge(12345);
                GXLEdge edge = new GXLEdge(localNode, localNode2);
                edgesToNode.add(edge);
                edge.setAttr("id", new GXLString(idGenerator.generateID()));
                edge.setAttr("paint", new GXLString("" + e.getPaint()));
                edge.setAttr("stroke", new GXLString("" + e.getStroke()));
                edge.setAttr("arrowType", new GXLString("" + e.getArrowType()));
                edge.setAttr("hasAnchor", new GXLString("" + e.isHasAnchor()));
                edge.setAttr("anchorAngle", new GXLString("" + e.getAnchorAngle()));
                edge.setAttr("isVisible", new GXLString("" + e.isVisible()));
                }*/
            }

            //    }
            /*
            GXLGraph specialGraph = new GXLGraph(idGenerator.generateID());
            sphere.add(specialGraph);
            GXLNode specialNode = new GXLNode(idGenerator.generateID());
            GXLNode specialNode2 = new GXLNode(idGenerator.generateID());
            specialGraph.add(specialNode);
            specialGraph.add(specialNode2);
            specialNode.setAttr("childCount", new GXLString("Anzahl an Childs in der Sphäre: " + specialGraph.getChildCount()));
            */
            sphere.setAttr("font", new GXLString(s.getFont()));
            sphere.setAttr("fontSize", new GXLString("" + s.getFontSize()));

            gxlGraph.add(sphere);

        }
        doc.getDocumentElement().add(gxlGraph);
        return doc.toString();
    }

    private String getPaintDescription(Color color){
        return ("java.awt.Color[r=" + color.getRed() + ",g=" + color.getGreen()
                + ",b=" + color.getBlue() + ",a=" + color.getAlpha() + "]");
    }

    private Paint getPaintFromString(String word) {
        String[] alphabet = {"a","b", "c","d", "e","f", "g","h", "i", "j", "k","l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "[", "]"};
        for(String s : alphabet){
            word.replace(s, "");
            word.replace(s.toUpperCase(), "");
        }
        int numberOFDots= 0;
        while(word.charAt(0) == '.'){
            numberOFDots++;
        }
        word.substring(numberOFDots);
        String[] numbersAsString = word.split("=");
        if(word.contains(".")){
            List<Double> doubles = new ArrayList<>();
            for(String s : numbersAsString){
                doubles.add(Double.parseDouble(s));
            }
        }else{
            List<Integer> integers = new ArrayList<>();
            for(String s : numbersAsString){
                integers.add(Integer.parseInt(s));
            }
        }
        return null;
    }

    /**
     * Save the GXL representation to a specific location.
     *
     * @param pFile The destination File
     */
    public void exportGXL(File pFile){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pFile));
            bufferedWriter.write(gxlFromInstance());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import a GXL file from a specific location to the syndrom
     *
     * @param pFile The File to import
     */

    public void importGXL(File pFile){
       /* try {
            BufferedReader bufferedReader = new BufferedReader(new FileWriter(pFile));
            bufferedWriter.write(gxlFromInstance());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
