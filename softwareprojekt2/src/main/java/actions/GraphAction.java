package actions;

import graph.graph.Syndrom;
import graph.visualization.control.HelperFunctions;
import gui.Values;

/**
 * Superclass of all actions that need access to the internal state of the graph, the visualization viewer and the
 * layout.
 */
public abstract class GraphAction extends Action {

    /**
     * The syndrom to work with.
     */
    protected Syndrom syndrom = Syndrom.getInstance();

    protected Values values = Values.getInstance();

    protected HelperFunctions helper = new HelperFunctions();

    /**
     * Sets layout, graph, pick support from the current syndrom presentation.
     */
    public GraphAction(){

    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
