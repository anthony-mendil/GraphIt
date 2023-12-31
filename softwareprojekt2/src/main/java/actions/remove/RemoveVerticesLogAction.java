package actions.remove;

import actions.LogAction;
import actions.LogEntryName;
import actions.add.AddVerticesLogAction;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import javafx.util.Pair;
import log_management.DatabaseManager;
import log_management.parameters.add_remove.AddRemoveVerticesParam;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * Removes vertices from the syndrom graph.
 * @author Clement Phung, Nina Unterberg
 */
public class RemoveVerticesLogAction extends LogAction {

    /**
     * Removes all passed vertices from the graph.
     * Gets the picked vertices through pick support.
     */
    public RemoveVerticesLogAction() {
        super(LogEntryName.REMOVE_VERTICES);
    }

    /**
     * Removes all vertices which are defined in pParam. Also used to implement the undo-method of
     * AddVerticesLogAction.
     *
     * @param pParam The RemoveVerticesParam object, containing all vertices to remove.
     */
    public RemoveVerticesLogAction(AddRemoveVerticesParam pParam) {
        super(LogEntryName.REMOVE_VERTICES);
        parameters = pParam;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void action() {
        SyndromVisualisationViewer<Vertex, Edge> vv = syndrom.getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        if (parameters == null) {
            List<Vertex> lockedVertices = new LinkedList<>();
            PickedState<Vertex> pickedState = vv.getPickedVertexState();
            Map<Vertex, Sphere> params = new HashMap<>();
            Map<Edge, Pair<Vertex, Vertex>> edg = new HashMap<>();
            removeVertices(params, lockedVertices, edg, pickedState, vv, pickSupport);
            if (checkTemplateRules(lockedVertices, pickedState)) {
                return;
            }
            pickedState.clear();
            createParameter(params, edg);
        } else {
            Map<Vertex, Sphere> vertices = ((AddRemoveVerticesParam) parameters).getVertices();
            for (Map.Entry<Vertex, Sphere> entry : vertices.entrySet()) {
                graph.removeVertex(entry.getKey());
                Point2D posVertex = entry.getKey().getCoordinates();
                posVertex = vv.getRenderContext().getMultiLayerTransformer().transform(posVertex);
                Sphere sp = pickSupport.getSphere(posVertex.getX(), posVertex.getY());
                sp.getVertices().remove(entry.getKey());
            }
        }
        vv.repaint();
        syndrom.getVv2().repaint();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.addEntryDatabase(createLog());
        notifyObserverGraph();
    }

    /**
     * Remove the vertices, if it is allowed.
     * @param params        The map later used to create the parameter.
     * @param lockedVertices The set of locked vertices.
     * @param edg           The map of vertices involved in that.
     * @param pickedState   The pickedState of vertices.
     * @param vv            The visualization viewer to wirk on.
     * @param pickSupport   The pickSupport to pick the vertices and edges.
     */
    private void removeVertices(Map<Vertex, Sphere> params, List<Vertex> lockedVertices, Map<Edge, Pair<Vertex, Vertex>> edg,
                                PickedState<Vertex> pickedState, SyndromVisualisationViewer<Vertex, Edge> vv,
                                SyndromPickSupport<Vertex, Edge> pickSupport) {
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();
        for (Vertex vertex : pickedState.getPicked()) {
            handleVertex(vertex, vv, pickSupport, lockedVertices, graph, edg, params);
        }
    }

    /**
     * Remove handler for a picked vertex.
     * @param vertex The selected vertex.
     * @param vv The SyndromVisualisationViewer which contains the vertex.
     * @param pickSupport The PickSupport needed to get the necessary information about the graph.
     * @param lockedVertices A list with all locked vertices.
     * @param graph The SyndromGraph containing the vertex.
     * @param edg The Edge pair.
     * @param params The param Map.
     */
    private void handleVertex(Vertex vertex, SyndromVisualisationViewer<Vertex, Edge> vv, SyndromPickSupport<Vertex, Edge> pickSupport,
                              List<Vertex> lockedVertices, SyndromGraph<Vertex, Edge> graph, Map<Edge, Pair<Vertex, Vertex>> edg,
                              Map<Vertex, Sphere> params){
        if (vertexIsNotLocked(vertex)) {
            Point2D posVertex = vertex.getCoordinates();
            posVertex = vv.getRenderContext().getMultiLayerTransformer().transform(posVertex);
            Sphere sp = pickSupport.getSphere(posVertex.getX(), posVertex.getY());
            if (sp.isLockedVertices() && values.getMode() != FunctionMode.TEMPLATE) {
                lockedVertices.add(vertex);
            } else {
                Collection<Edge> inList = graph.getInEdges(vertex);
                Collection<Edge> outList = graph.getOutEdges(vertex);
                ArrayList<Edge> bothList = new ArrayList<>();
                bothList.addAll(outList);
                bothList.addAll(inList);
                if (allowedRemoveVertex(bothList)) {
                    para(inList, outList, graph, edg);
                    graph.removeVertex(vertex);
                    sp.getVertices().remove(vertex);
                    params.put(vertex, sp);

                } else {
                    helper.setActionText("REMOVE_EDGES_ALERT", true, true);
                }
            }
        } else {
            lockedVertices.add(vertex);
        }
    }

    /**
     * Fills the edges into the parameter.
     *
     * @param inList  The list of edges going into the vertex.
     * @param outList The list of edges going out to the vertex.
     * @param graph   The graph of the syndrom.
     * @param edg     The map of edges to save the edges.
     */
    private void para(Collection<Edge> inList, Collection<Edge> outList, SyndromGraph<Vertex, Edge> graph,
                      Map<Edge, Pair<Vertex, Vertex>> edg) {
        for (Edge e : inList) {
            edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);
            Pair<Vertex, Vertex> vertexPair = new Pair<>(vertices.getFirst(), vertices.getSecond());
            edg.put(e, vertexPair);
        }
        for (Edge e : outList) {
            edu.uci.ics.jung.graph.util.Pair<Vertex> vertices = graph.getEndpoints(e);
            Pair<Vertex, Vertex> vertexPair = new Pair<>(vertices.getFirst(), vertices.getSecond());
            edg.put(e, vertexPair);
        }
    }

    @Override
    public void undo() {
        AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction((AddRemoveVerticesParam) parameters);
        addVerticesLogAction.action();
    }

    /**
     * Creates a parameter-object for this action.
     *
     * @param vertices The vertices and their sphere, in which they were.
     * @param edges    The edges, that got deleted too.
     */
    public void createParameter(Map<Vertex, Sphere> vertices, Map<Edge, Pair<Vertex, Vertex>> edges) {
        parameters = new AddRemoveVerticesParam(vertices, edges);
    }

    /**
     * Checks whether the template rules allow this action.
     *
     * @param lockedEdges The list of locked vertices.
     * @param pickedState The set of the picked elements
     * @return The indicator, if the action is allowed.
     */
    private boolean checkTemplateRules(List<Vertex> lockedEdges, PickedState<Vertex> pickedState) {
        if (!lockedEdges.isEmpty()) {
            helper.setActionText("REMOVE_EDGES_ALERT", true, true);
        }
        if (lockedEdges.size() == pickedState.getPicked().size()) {
            actionHistory.removeLastEntry();
            return true;
        }
        return false;
    }

    /**
     * Checks whether vertex can be removed regarding the edges.
     * @param edges The list of edges.
     * @return  true: The vertex can be removed | false: the vertex can't be removed.
     */
    private boolean allowedRemoveVertex(List<Edge> edges) {
        for (Edge e : edges) {
            if ((e.isLockedEdgeType() || e.isLockedStyle())&& values.getMode() != FunctionMode.TEMPLATE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a vertex has a locked property.
     * @param vertex The vertex that gets checked.
     * @return True if its not locked, false otherwise.
     */
    private boolean vertexIsNotLocked(Vertex vertex){
        return(!vertex.isLockedStyle() && !vertex.isLockedAnnotation() && !vertex.isLockedPosition()) ||
                values.getMode() == FunctionMode.TEMPLATE;
    }
}
