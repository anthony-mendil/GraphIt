package actions.template;

import actions.GraphAction;
import graph.graph.Template;

/**
 * Creates a template of a graph.
 */
public class RulesTemplateAction extends GraphAction {
    /**
     * TODO :^)
     */
    Template template;
    /**
     * Creates a new rules template action.
     *
     * @param pTemplate The template to use.
     */
    public RulesTemplateAction(Template pTemplate) {
        template=pTemplate;
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }
}
