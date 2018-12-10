package actions;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.SyndromGraph;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * superclass of all actions that need access to the internal state of the graph, the visualisation viewer and the
 * layout
 */
public abstract class GraphAction extends Action {
    /**
     * the internal state of the layout, at the time the action was initialized
     */
    @NonNull
    private Layout layout;

    /**
     * sets layout to the current layout of the visualisation viewer
     */
    public GraphAction() {
        throw new NotImplementedException();
    }

    @Override
    public void redo() {
        throw new NotImplementedException();
    }
}
