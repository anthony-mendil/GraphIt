package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.transformer.edge.EdgeHighlightTransformer;
import graph.visualization.transformer.edge.EdgePaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexPaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexPaintHighlightTransformer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;

import java.util.*;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * There are a few possibilities for the user to analyse the graph. We differentiate between two analyse types:
 * AnalyseTypeSeveral and AnalyseTypeSingle. The user can analyse the graph with multiple analysis types at once with
 * multiple AnalyseTypeSeveral selections. Unlike the AnalyseTypeSingle. Here is only one type selectable.
 * </p>
 *
 */
public class AnalysisGraphAction extends GraphAction{
    /**
     * The list of analyse types, several types can be passed and processed at once.
     */
    private List<AnalyseTypeSeveral> analyseTypeSeverals;

    /**
     * A single analyse type, one type can be analysed at once.
     */
    private AnalyseTypeSingle analyseTypeSingle;
    /**
     * The amount of successors/predecessors of a specific vertex.
     */
    private int amountSteps = 0;
    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JUNG functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked.
     *
     * @param pAnalyseTypeSeveral A list of AnalyseTypeSeveral, several types can be analysed at once.
     * @param counterVertex The number of adjacent vertices to analyse.
     */
    public AnalysisGraphAction(List<AnalyseTypeSeveral> pAnalyseTypeSeveral, int counterVertex) {
        this.analyseTypeSeverals = pAnalyseTypeSeveral;
        amountSteps = counterVertex;
    }

    /**
     * Constructor in case the user chooses a AnalyseTypeSingle - analyse option.
     * These analyse functions are implemented by JGraphT algorithms and will be processed through the JGraphT Handler.
     * After processing and finding out the values the action is looking for, the information is displayed
     * or the found vertices/ edges get highlighted.
     * The action is applied to all picked vertices/ edges or to all objects if nothing is picked.
     * @param pAnalyseTypeSingle The analyse type, one type can be analysed at once.
     */
    public AnalysisGraphAction(AnalyseTypeSingle pAnalyseTypeSingle) {
        analyseTypeSingle = pAnalyseTypeSingle;

    }

    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {
        Set<Pair<Vertex, Vertex>> edges = new HashSet<>();
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();

        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            for(Edge edge : graph.getEdges()){
                edu.uci.ics.jung.graph.util.Pair<Vertex> jungPair = graph.getEndpoints(edge);
                Pair<Vertex,Vertex> vertices = new Pair<>(jungPair.getFirst(), jungPair.getSecond());
                edges.add(vertices);
            }
        JGraphTHandler jGraphTHandler = new JGraphTHandler(new ArrayList<>(graph.getVertices()), edges);
        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();
            if(analyseTypeSingle != null) {
            switch (analyseTypeSingle) {
                case CYCLEN:
                    List<List<Vertex>> listCylces = jGraphTHandler.detectCycles();
                    for (List<Vertex> list : listCylces) {
                        for (int i = 0; i < list.size(); i++) {
                            verticesAnalyse.add(list.get(i));
                            if (i == list.size() - 1) {
                                edgesAnalyse.add(graph.findEdge(list.get(i), list.get(0)));
                            } else {
                                edgesAnalyse.add(graph.findEdge(list.get(i), list.get(i + 1)));
                            }
                        }
                    }
                    break;
                case DIVERGENT_BRANCHES:
                    Set<Vertex> divergentBranches = jGraphTHandler.detectDivergentBranches();
                    for (Vertex vertex : divergentBranches) {
                        Collection<Edge> successors = graph.getOutEdges(vertex);
                        edgesAnalyse.addAll(successors);
                    }
                    verticesAnalyse.addAll(divergentBranches);

                    break;
                case CONVERGENT_BRANCHES:
                    Set<Vertex> convergentBranches = jGraphTHandler.detectConvergentBranches();
                    for (Vertex vertex : convergentBranches) {
                        Collection<Edge> predecessors = graph.getInEdges(vertex);
                        edgesAnalyse.addAll(predecessors);
                    }
                    verticesAnalyse.addAll(convergentBranches);
                    break;
                case BRANCHES:
                    Set<Vertex> branches = jGraphTHandler.detectDivergentBranches();
                    branches.addAll(jGraphTHandler.detectConvergentBranches());
                    for (Vertex vertex : branches) {
                        Collection<Edge> predecessors = graph.getInEdges(vertex);
                        if (predecessors.size() > 1) {
                            edgesAnalyse.addAll(predecessors);
                        }
                        Collection<Edge> successors = graph.getOutEdges(vertex);
                        if (successors.size() > 1) {
                            edgesAnalyse.addAll(successors);
                        }
                    }
                    verticesAnalyse.addAll(branches);
                    break;
                case EDGE_CHAINS:
                    Pair<List<List<Vertex>>,Set<Edge>> edgeChains = jGraphTHandler.detectRelationChains();
                    for(List<Vertex> list : edgeChains.getKey()){
                        for(int i = 0 ; i < list.size(); i++){
                            verticesAnalyse.add(list.get(i));
                        }
                    }
                    edgesAnalyse.addAll(edgeChains.getValue());
                    break;
                case REINFORCED:
                    for(Edge edge : graph.getEdges()){
                        if(edge.getArrowType() == EdgeArrowType.REINFORCED){
                            edgesAnalyse.add(edge);
                            edu.uci.ics.jung.graph.util.Pair<Vertex> endPoints = graph.getEndpoints(edge);
                            verticesAnalyse.add(endPoints.getFirst());
                        }
                    }
                    break;
                case EXTENUATING:
                    for(Edge edge : graph.getEdges()){
                        if(edge.getArrowType() == EdgeArrowType.EXTENUATING){
                            edgesAnalyse.add(edge);
                            edu.uci.ics.jung.graph.util.Pair<Vertex> endPoints = graph.getEndpoints(edge);
                            verticesAnalyse.add(endPoints.getFirst());
                        }
                    }
                    break;
                case NEUTRAL:
                    for(Edge edge : graph.getEdges()){
                        if(edge.getArrowType() == EdgeArrowType.NEUTRAL){
                            edgesAnalyse.add(edge);
                            edu.uci.ics.jung.graph.util.Pair<Vertex> endPoints = graph.getEndpoints(edge);
                            verticesAnalyse.add(endPoints.getFirst());
                        }
                    }
                    break;
                case ALLPATHS:
                    List<GraphPath<Vertex,Edge>> allPaths = jGraphTHandler.getAllPaths();
                    if(allPaths == null){
                        break;
                    }
                    for(GraphPath<Vertex,Edge> path : allPaths){
                        verticesAnalyse.addAll(path.getVertexList());
                        edgesAnalyse.addAll(path.getEdgeList());
                    }
                    break;
                case SHORTESTPATH:
                    GraphPath<Vertex,Edge> shortestPath = jGraphTHandler.getShortestPath();
                    if(shortestPath == null){
                        break;
                    }
                    verticesAnalyse.addAll(shortestPath.getVertexList());
                    edgesAnalyse.addAll(shortestPath.getEdgeList());
                    break;
            }



        }else{
            for(AnalyseTypeSeveral analyseTypeSeveral : analyseTypeSeverals){
                if(analyseTypeSeveral == AnalyseTypeSeveral.NEIGHBOUR_PREDECESSOR){
                    Pair<List<Vertex>,List<Edge>> predecessors = jGraphTHandler.predecessorsIterations(amountSteps);
                    verticesAnalyse.addAll(predecessors.getKey());
                    edgesAnalyse.addAll(predecessors.getValue());
                }else{
                    Pair<List<Vertex>,List<Edge>> successors = jGraphTHandler.predecessorsIterations(amountSteps);
                    verticesAnalyse.addAll(successors.getKey());
                    edgesAnalyse.addAll(successors.getValue());
                }
            }
        }
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(verticesAnalyse));
        vv2.getRenderContext().setVertexFillPaintTransformer(new VertexPaintAnalyseTransformer<>(verticesAnalyse));
        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv2.getRenderContext().setEdgeDrawPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv2.getRenderContext().setArrowFillPaintTransformer(new EdgePaintAnalyseTransformer<>(edgesAnalyse));
        vv.repaint();
        vv2.repaint();
    }

    /**
     * There is no undo for this action.
     */
    @Override
    public void undo() {
        return;
    }
}
