package actions.other;

import actions.GraphAction;
import graph.graph.SyndromGraph;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
 *  Aktionsleiste -> Datei.. -> Neuer/Neue Graph/Datei...
 *
 * Creates a new graph.
 *
 */
public class CreateGraphAction extends GraphAction {
    private String graphName;

    /**
     * Constructor in case the user creates a new graph.
     * @param pGraphName the name of the graph
     */
    public CreateGraphAction(String pGraphName){
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
