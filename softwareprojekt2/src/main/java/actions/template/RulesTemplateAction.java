package actions.template;

import actions.GraphAction;
import graph.graph.Syndrom;
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
     *
     */
    Template previousTemplate;
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
        previousTemplate=Syndrom.getInstance().getTemplate();
        Syndrom.getInstance().setTemplate(template);
    }

    @Override
    public void undo() {
        Syndrom.getInstance().setTemplate(previousTemplate);
    }
}
