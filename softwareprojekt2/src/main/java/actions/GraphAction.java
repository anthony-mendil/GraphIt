package actions;

import graph.graph.Syndrom;
import graph.graph.Template;
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
    /**
     * The values to get the data from.
     */
    protected Values values = Values.getInstance();
    /**
     * Helper-functions helping with the alert-text.
     */
    protected HelperFunctions helper = new HelperFunctions();
    /**
     * The template returning information about the existing rules.
     */
    protected Template template = Template.getInstance();
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
