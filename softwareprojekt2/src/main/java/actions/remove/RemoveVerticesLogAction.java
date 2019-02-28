package actions.remove;

import actions.ActionHistory;
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
            for (Vertex vertex : pickedState.getPicked()) {
                if (!vertex.isLockedStyle() && !vertex.isLockedAnnotation() && !vertex.isLockedPosition() || values.getMode() == FunctionMode.TEMPLATE) {
                    Point2D posVertex = vertex.getCoordinates();
                    posVertex = vv.getRenderContext().getMultiLayerTransformer().transform(posVertex);
                    Sphere sp = pickSupport.getSphere(posVertex.getX(), posVertex.getY());
                    if (sp.isLockedVertices() && values.getMode() != FunctionMode.TEMPLATE) {
                        lockedVertices.add(vertex);
                    } else {
                        Collection<Edge> inList = graph.getInEdges(vertex);
                        Collection<Edge> outList = graph.getOutEdges(vertex);
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
                        graph.removeVertex(vertex);
                        sp.getVertices().remove(vertex);
                        params.put(vertex, sp);
                    }
                } else {
                    lockedVertices.add(vertex);
                }
            }
            pickedState.clear();
            if (!lockedVertices.isEmpty()) {
                helper.setActionText("REMOVE_VERTICES_ALERT", true, true);
                ActionHistory.getInstance().removeLastEntry();
                return;
            }
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

    @Override
    public void undo() {
        AddVerticesLogAction addVerticesLogAction = new AddVerticesLogAction((AddRemoveVerticesParam) parameters);
        addVerticesLogAction.action();
    }


    public void createParameter(Map<Vertex, Sphere> vertices, Map<Edge, Pair<Vertex, Vertex>> edges) {
        parameters = new AddRemoveVerticesParam(vertices, edges);
    }
}
