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
        SyndromGraph g = (SyndromGraph) getLayout().getGraph();
        // openInput
        String gotInput = "";
        g.setName(gotInput);
        getLayout().setGraph(g);
        setGraph(g);
    }

    @Override
    public void undo() {
        //
    }
}
