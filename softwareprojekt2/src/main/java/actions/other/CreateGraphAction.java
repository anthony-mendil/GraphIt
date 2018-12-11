package actions.other;

import actions.GraphAction;
import graph.graph.SyndromGraph;

/*
    Aktionsleiste -> Datei.. -> Neuer/Neue Graph/Datei...
 */
public class CreateGraphAction extends GraphAction {
    private String graphName;

    @Override
    @SuppressWarnings("unchecked")
    public void action() {

    }

    @Override
    public void undo() {
        //
    }
}
