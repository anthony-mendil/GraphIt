package actions;

import graph.graph.Syndrom;
import graph.graph.Template;
import graph.visualization.control.HelperFunctions;
import gui.Values;
import gui.properties.LoadLanguage;

/**
 * Superclass of all actions that need access to the internal state of the graph, the visualization viewer and the
 * layout.
 * @author Clement Phung, Nina Unterberg
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
    protected Template template = Syndrom.getInstance().getTemplate();
    /**
     * The actionHistory in case the action needs to delete the latest entry.
     */
    protected ActionHistory actionHistory = ActionHistory.getInstance();
    /**
     * The loadLanguage to get the right property string
     */
    protected LoadLanguage loadLanguage = LoadLanguage.getInstance();

    /**
     * Sets layout, graph, pick support from the current syndrom presentation.
     */
    public GraphAction() {
        //standard constructor for javaDoc.
    }

    @Override
    public void redo() {
        throw new UnsupportedOperationException();
    }
}
