package actions.deactivate;

import actions.LogAction;
import actions.LogEntryName;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.EdgeIsVisiblePredicate;
import graph.algorithmen.predicates.VertexIsVisiblePredicate;
import graph.graph.Edge;
import graph.graph.Vertex;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.AllPredicate;
import org.apache.commons.collections15.functors.TruePredicate;


/**
 * Makes the vertices and attached edges, which used to be invisible, visible again.
 */
public class DeactivateFadeoutLogAction extends LogAction {
    /**
     * Constructor in case the user wants to make every vertex and edge visible again.
     */
    public DeactivateFadeoutLogAction() {
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

    /**
     * Makes the vertices and edges visible. Also used to implement the undo-method of
     * ActivateFadeoutLogAction.
     *
     * @param pParam The vertices object that contains every vertices that is needed.
     */
    public DeactivateFadeoutLogAction(ActivateDeactivateFadeoutParam pParam) {
        super(LogEntryName.DEACTIVATE_FADEOUT);
    }

    @Override
    public void action() {
        VisualizationViewer<Vertex, Edge> vv = syndrom.getVv();
        VisualizationViewer<Vertex, Edge> vv2 = syndrom.getVv2();

        vv.getRenderContext().setVertexIncludePredicate( TruePredicate.getInstance());
        vv2.getRenderContext().setVertexIncludePredicate(TruePredicate.getInstance());
        vv.getRenderContext().setEdgeIncludePredicate(TruePredicate.getInstance());
        vv2.getRenderContext().setEdgeIncludePredicate(TruePredicate.getInstance());
        vv.repaint();
        vv2.repaint();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }


    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
