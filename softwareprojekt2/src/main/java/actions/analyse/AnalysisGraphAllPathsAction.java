package actions.analyse;

import actions.GraphAction;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.AnalyseTypeSeveral;
import graph.algorithmen.AnalyseTypeSingle;
import graph.graph.Edge;
import graph.graph.EdgeArrowType;
import graph.graph.SyndromGraph;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.HelperFunctions;
import graph.visualization.transformer.edge.EdgePaintAnalyseTransformer;
import graph.visualization.transformer.vertex.VertexPaintAnalyseTransformer;
import gui.properties.Language;
import javafx.util.Pair;
import jgrapht.JGraphTHandler;
import org.jgrapht.GraphPath;

import java.util.*;

/**
 * Analyses the graph in matter of heavily connected vertices or highly important vertices.
 * <p>
 * This Action find all different paths between two selected vertices.
 *
 */
public class AnalysisGraphAllPathsAction extends GraphAction{
    /**
     * Constructor in case the user chooses a AnalyseTypeSeveral - analyse option.
     * These analyse functions are implemented by JGraphT functions.
     * After analysing the graph and finding out the values the action is looking for,
     * the information is displayed or the found vertices/edges get highlighted.
     * The action is applied to all picked vertices/edges or to all objects if nothing is picked.
     *
     */
    public AnalysisGraphAllPathsAction() {
    }


    /**
     * Analyses the graph on the given criteria. All the
     * calculated edges and vertices will be highlighted.
     */
    @Override
    public void action() {

        JGraphTHandler jGraphTHandler = new JGraphTHandler();
        ArrayList<Edge> edgesAnalyse = new ArrayList<>();
        ArrayList<Vertex> verticesAnalyse = new ArrayList<>();
        List<GraphPath<Vertex,Edge>> allPaths = jGraphTHandler.getAllPaths();
        if(allPaths == null){
            HelperFunctions helperFunctions = new HelperFunctions();
            helperFunctions.setActionText("Es sxistiert kein Weg von " + jGraphTHandler.getStartVertex().getAnnotation().get(Language.GERMAN.name()) + " nach " + jGraphTHandler.getEndVertex().getAnnotation().get(Language.GERMAN.name()), true);

        }
        for(GraphPath<Vertex,Edge> path : allPaths){
            verticesAnalyse.addAll(path.getVertexList());
            edgesAnalyse.addAll(path.getEdgeList());
        }

        ShowAnalysisResultAction showAnalysisResultAction = new ShowAnalysisResultAction(verticesAnalyse, edgesAnalyse);
        showAnalysisResultAction.action();
    }

    /**
     * There is no undo for this action.
     */
    @Override
    public void undo() {
        return;
    }
}
