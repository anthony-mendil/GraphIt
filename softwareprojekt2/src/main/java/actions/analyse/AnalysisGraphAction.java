package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.graph.util.EdgeType;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;
import graph.graph.Edge;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
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
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JUNG functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked.
     *
     * @param pAnalyseTypeSeveral A list of AnalyseTypeSeveral, several types can be analysed at once.
     * @param counterEdges The number of incoming/outgoing edges to analyse.
     * @param counterVertex The number of adjacent vertices to analyse.
     * @param edgeType The edge type to analyse.
     */
    public AnalysisGraphAction(List<AnalyseTypeSeveral> pAnalyseTypeSeveral, int counterVertex, int counterEdges,
                               EdgeType edgeType) {
        throw new UnsupportedOperationException();
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

    @Override
    public void action() {
        Set<Pair<Vertex, Vertex>> edges = new HashSet<>();
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
            for(Edge edge : graph.getEdges()){
                edu.uci.ics.jung.graph.util.Pair<Vertex> jungPair = graph.getEndpoints(edge);
                Pair<Vertex,Vertex> vertices = new Pair<>(jungPair.getFirst(), jungPair.getSecond());
                edges.add(vertices);
            }
        JGraphTHandler jGraphTHandler = new JGraphTHandler(new ArrayList<>(graph.getVertices()), edges);

        switch (analyseTypeSingle){
            case CYCLEN:
                List<List<Vertex>> listCylces = jGraphTHandler.detectCycles();
                List<Edge> edgesCycle = new ArrayList<>();
                for (List<Vertex> list : listCylces){
                    for(int i = 0; i < list.size(); i++){
                        if(i == list.size() - 1){
                            edgesCycle.add(graph.findEdge(list.get(i),list.get(0)));
                        }else{
                            edgesCycle.add(graph.findEdge(list.get(i),list.get(i+1)));
                        }
                    }
                }
                break;
            case DIVERGENT_BRANCHES:
                Set<Vertex> divergentBranches = jGraphTHandler.detectDivergentBranches();
                List<Edge> edgesDivergentBranches = new ArrayList<>();
                for(Vertex vertex : divergentBranches){
                    Collection<Edge> successors = graph.getOutEdges(vertex);
                    edgesDivergentBranches.addAll(successors);
                }
                break;
            case CONVERGENT_BRANCHES:
                Set<Vertex> convergentBranches = jGraphTHandler.detectConvergentBranches();
                List<Edge> edgesConvergentBranches = new ArrayList<>();
                for(Vertex vertex : convergentBranches){
                    Collection<Edge> predecessors = graph.getInEdges(vertex);
                    edgesConvergentBranches.addAll(predecessors);
                }
                break;
            case BRANCHES:
                Set<Vertex> branches = jGraphTHandler.detectDivergentBranches();
                branches.addAll(jGraphTHandler.detectConvergentBranches());
                List<Edge> edgesBranches = new ArrayList<>();
                for(Vertex vertex : branches){
                    Collection<Edge> predecessors = graph.getInEdges(vertex);
                    if(predecessors.size() > 1) {
                        edgesBranches.addAll(predecessors);
                    }
                    Collection<Edge> successors = graph.getOutEdges(vertex);
                    if(successors.size() > 1) {
                        edgesBranches.addAll(successors);
                    }
                }
                break;
            case EDGE_CHAINS:
                List<List<Vertex>> edgeChains = jGraphTHandler.detectRelationChains();
                break;
        }
    }

    /**
     * There is no undo for this action.
     */
    @Override
    public void undo() {
        return;
    }
}
