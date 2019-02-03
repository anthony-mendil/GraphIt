package actions.activate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.EdgeIsVisiblePredicate;
import graph.algorithmen.predicates.VertexIsVisiblePredicate;
import graph.graph.Edge;
import graph.graph.Vertex;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;

/**
 * The chosen vertices and all edges attached to them fadeout and will no longer be visible.
 */

public class ActivateFadeoutLogAction extends LogAction {
    /**
     * Constructor
     *
     */
    public ActivateFadeoutLogAction() {
        super(LogEntryName.ACTIVATE_FADEOUT);
    }

    /**
     * Fadeout all vertices/edges defined in ActivateFadeoutParam. Also used to implement the undo-method of
     * DeactivateFadeoutLogAction.
     *
     * @param pActivateDeactivateFadeoutParam The parameter object containing all vertices/edges to fadeout
     */
    public ActivateFadeoutLogAction(ActivateDeactivateFadeoutParam pActivateDeactivateFadeoutParam) {
        super(LogEntryName.ACTIVATE_FADEOUT);
        throw new UnsupportedOperationException();
    }



    /**
     * Chosen vertices and edges fadeout and adds the log to the database.
     */
    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();

        vv.getRenderContext().setVertexIncludePredicate(new VertexIsVisiblePredicate<>());
        vv2.getRenderContext().setVertexIncludePredicate(new VertexIsVisiblePredicate<>());
        vv.getRenderContext().setEdgeIncludePredicate(new EdgeIsVisiblePredicate<>());
        vv2.getRenderContext().setEdgeIncludePredicate(new EdgeIsVisiblePredicate<>());
        vv.repaint();
        vv2.repaint();
    }

    /**
     * Undoes the fadeout of the chosen vertices and edges.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}