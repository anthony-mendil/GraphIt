package actions.export_graph;

import actions.GraphAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    Aktionsleiste -> Datei.. -> Import GXL
 */
public class ImportGxlAction extends GraphAction {

    /**
     * Actionhandling for importing the graph as GXL
     */
    ImportGxlAction(){

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
