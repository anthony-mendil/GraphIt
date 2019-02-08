package actions.template;

import actions.GraphAction;
import graph.graph.Syndrom;
import graph.graph.Template;

/**
 * Creates a template of a graph.
 */
public class RulesTemplateAction extends GraphAction {
    /**
     *
     */
    private Template template;
    /**
     * Creates a new rules template action to save the template so the Syndrom
     *
     * @param pTemplate The template to use.
     */
    public RulesTemplateAction(Template pTemplate) {
        template=pTemplate;
    }

    @Override
    public void action() {
        Syndrom.getInstance().setTemplate(template);
        notifyObserverGraph();
    }

    @Override
    public void undo() {
        //there is no undo operation for template
    }
    @Override
    public void redo() {
        //there is no redo operation for template
    }
}
