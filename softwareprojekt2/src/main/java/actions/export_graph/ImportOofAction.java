package actions.export_graph;

import actions.GraphAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ImportOofAction extends GraphAction {

    /**
     * Constructs Actionhandling for importing a graph as Oof
     */
    public ImportOofAction(){

    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        throw new NotImplementedException();
    }

    /**
     * Reverts the action. The internal state of the graph is the same as before the action was executed.
     */
    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
